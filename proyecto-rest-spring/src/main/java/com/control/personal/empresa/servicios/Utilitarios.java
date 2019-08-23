package com.control.personal.empresa.servicios;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utilitarios {

	private static Logger log = LoggerFactory.getLogger(Utilitarios.class);

	public static long obtenerRangoNoche(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {

		long minutosNoche = Utilitarios.obtenerMinutosRango(fechaHoraInicio, fechaHoraFin);
		BigDecimal totalMinutos = Utilitarios.calcularMinutosRegla(minutosNoche, 72);
		log.info("TOTAL DE MINUTOS TRABAJADOS CON LA REGLA DE NOCHE:::::: " + redondeoDecimales(totalMinutos, 0)
				+ "TRABAJADOS");

		return redondeoDecimales(totalMinutos, 0);
	}

	public static long obtenerRangoDia(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {

		long minutosDia = obtenerMinutosRango(fechaHoraInicio, fechaHoraFin);
		BigDecimal totalMinutos = BigDecimal.ZERO;

		DayOfWeek dayOfWeek = fechaHoraInicio.getDayOfWeek();
		String diaEntrada = dayOfWeek.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.getDefault());

		switch (diaEntrada) {
		case "6":
			totalMinutos = Utilitarios.calcularMinutosRegla(minutosDia, 90);
			break;
		case "7":
			totalMinutos = Utilitarios.calcularMinutosRegla(minutosDia, 120);
			break;
		default:
			totalMinutos = Utilitarios.calcularMinutosRegla(minutosDia, 60);
			break;
		}
		log.info("TOTAL DE MINUTOS TRABAJADOS CON LA REGLA DE DIA:::::: " + redondeoDecimales(totalMinutos, 0)
				+ "TRABAJADOS");
		return redondeoDecimales(totalMinutos, 0);
	}

	public static long obtenerRangoDiaNoche(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {

		LocalDateTime rango1 = LocalDateTime.now().withHour(22).withMinute(0).withSecond(0);
		LocalDateTime rango2 = LocalDateTime.now().withHour(22).withMinute(0).withSecond(0).plusHours(8);
		LocalDateTime fechaCorte = LocalDateTime.now().withHour(23).withMinute(59).withSecond(0);
		BigDecimal totalMinutos = BigDecimal.ZERO;

		DayOfWeek dayOfWeek = fechaHoraFin.getDayOfWeek();
		String diaSalida = dayOfWeek.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.getDefault());
		DayOfWeek dayOfWeek1 = fechaHoraInicio.getDayOfWeek();
		String diaEntrada = dayOfWeek1.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.getDefault());

		if (diaEntrada.equals(diaSalida)) {
			long minutosNoche = obtenerMinutosRango(fechaHoraInicio, rango2);
			long minutosDia = obtenerMinutosRango(rango2, fechaHoraFin);
			return obtenerReglaDia(diaEntrada, minutosDia, minutosNoche);

		} else {
			long minutosParteDia = Utilitarios.obtenerMinutosRango(fechaHoraInicio, rango1);
			long minutosParteNoche = Utilitarios.obtenerMinutosRango(rango1, fechaCorte);
			long minutosParteFin = Utilitarios.obtenerMinutosRango(fechaCorte, rango2);
			totalMinutos = calcularMinutosRegla(minutosParteDia + minutosParteNoche + minutosParteFin, 72);
			return Utilitarios.redondeoDecimales(totalMinutos, 0);
		}
	}

	public static long obtenerRangoNocheDia(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {

		LocalDateTime rango1 = LocalDateTime.now().withHour(22).withMinute(0).withSecond(0);
		LocalDateTime rango2 = LocalDateTime.now().withHour(22).withMinute(0).withSecond(0).plusHours(8);
		LocalDateTime fechaCorte = LocalDateTime.now().withHour(23).withMinute(59).withSecond(0);
		BigDecimal totalMinutos = BigDecimal.ZERO;

		DayOfWeek dayOfWeek = fechaHoraFin.getDayOfWeek();
		String diaSalida = dayOfWeek.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.getDefault());
		DayOfWeek dayOfWeek1 = fechaHoraInicio.getDayOfWeek();
		String diaEntrada = dayOfWeek1.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.getDefault());

		if (diaEntrada.equals(diaSalida)) {
			long minutosNoche = obtenerMinutosRango(fechaHoraInicio, rango2);
			long minutosDia = obtenerMinutosRango(rango2, fechaHoraFin);
			return obtenerReglaDia(diaEntrada, minutosDia, minutosNoche);

		} else {
			long minutosParteNoche = obtenerMinutosRango(fechaHoraInicio, fechaCorte);
			long minutosParteDia = Utilitarios.obtenerMinutosRango(fechaCorte, rango2);
			BigDecimal totalTurnoNoche = Utilitarios.calcularMinutosRegla(minutosParteDia + minutosParteNoche, 72);

			long minutosParteFin = Utilitarios.obtenerMinutosRango(rango2, fechaHoraFin);

			switch (diaSalida) {
			case "6":
				totalMinutos = totalTurnoNoche.add(Utilitarios.calcularMinutosRegla(minutosParteFin, 90));
				break;
			case "7":
				totalMinutos = totalTurnoNoche.add(Utilitarios.calcularMinutosRegla(minutosParteFin, 120));
				break;
			default:
				totalMinutos = totalTurnoNoche.add(Utilitarios.calcularMinutosRegla(minutosParteFin, 60));
				break;
			}
			return Utilitarios.redondeoDecimales(totalMinutos, 0);
		}

	}

	public static long obtenerReglaDia(String dia, long minutosDia, long minutosNoche) {
		BigDecimal totalMinutos = BigDecimal.ZERO;
		switch (dia) {
		case "6":
			totalMinutos = Utilitarios.calcularMinutosRegla(minutosDia, 90)
					.add(Utilitarios.calcularMinutosRegla(minutosNoche, 72));
			break;
		case "7":
			totalMinutos = Utilitarios.calcularMinutosRegla(minutosDia, 120)
					.add(Utilitarios.calcularMinutosRegla(minutosNoche, 72));
			break;
		default:
			totalMinutos = Utilitarios.calcularMinutosRegla(minutosDia, 60)
					.add(Utilitarios.calcularMinutosRegla(minutosNoche, 72));
			break;
		}
		return redondeoDecimales(totalMinutos, 0);
	}

	public static long obtenerminutoRangoIngresoyEntrada(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {

		LocalDateTime rango1 = LocalDateTime.now().withHour(22).withMinute(0).withSecond(0);
		LocalDateTime rango2 = LocalDateTime.now().withHour(22).withMinute(0).withSecond(0).plusHours(8);

		if ((fechaEntrada.isAfter(rango1) && fechaEntrada.isBefore(rango2))
				&& (fechaSalida.isAfter(rango1) && fechaSalida.isBefore(rango2))) {

			log.info(
					"TRABAJO EN RANGO DE NOCHE :::::" + Utilitarios.obtenerRangoNoche(fechaEntrada, fechaSalida));
			return Utilitarios.obtenerRangoNoche(fechaEntrada, fechaSalida);

		} else if (!(fechaEntrada.isAfter(rango1) && fechaEntrada.isBefore(rango2))
				&& (fechaSalida.isAfter(rango1) && fechaSalida.isBefore(rango2))) {

			log.info("ENTRO DE TURNO DIA Y SALIO TURNO NOCHE");
			log.info("TRABAJO EN RANGO  :::::" + Utilitarios.obtenerRangoDiaNoche(fechaEntrada, fechaSalida));
			return Utilitarios.obtenerRangoDiaNoche(fechaEntrada, fechaSalida);

		} else if ((fechaEntrada.isAfter(rango1) && fechaEntrada.isBefore(rango2))
				&& !(fechaSalida.isAfter(rango1) && fechaSalida.isBefore(rango2))) {
			log.info("*******ENTRO DE NOCHE Y SALIO DE DIA");
			log.info("TRABAJO EN RANGO  :::::" + Utilitarios.obtenerRangoNocheDia(fechaEntrada, fechaSalida));
			return Utilitarios.obtenerRangoNocheDia(fechaEntrada, fechaSalida);

		} else if (!(fechaEntrada.isAfter(rango1) && fechaEntrada.isBefore(rango2))
				&& !(fechaSalida.isAfter(rango1) && fechaSalida.isBefore(rango2))) {
			log.info("TRABAJO EN RANGO DE DIA :::::" + Utilitarios.obtenerRangoDia(fechaEntrada, fechaSalida));
			return Utilitarios.obtenerRangoDia(fechaEntrada, fechaSalida);
		} else {
			return 0l;
		}

	}

	public static boolean validarEntreDosFechas(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) {
		if (fechaSalida.isAfter(fechaEntrada)) {
			return true;
		} else {
			return false;
		}
	}


	public static long obtenerMinutosRango(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
		long minutosDia = ChronoUnit.MINUTES.between(fechaHoraInicio, fechaHoraFin);
		return minutosDia;
	}

	public static BigDecimal calcularMinutosRegla(long minutosTrabajados, long minutosRegla) {

		BigDecimal a = new BigDecimal(minutosTrabajados);

		BigDecimal result = a.divide(new BigDecimal(60), 2, RoundingMode.HALF_UP)
				.multiply(new BigDecimal(minutosRegla));

		return result;

	}

	public static long redondeoDecimales(BigDecimal numero, int numeroDecimales) {
		BigDecimal redondeado = numero.setScale(numeroDecimales, RoundingMode.HALF_EVEN);
		return redondeado.longValue();
	}

	public static String formatFecha(LocalDateTime fechaParseada, String dateFormato) {

		try {

			DateTimeFormatter mascaraFormato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

			String formatDateTime = fechaParseada.format(mascaraFormato);

			return formatDateTime;

		} catch (DateTimeParseException ex) {
			ex.printStackTrace();
			return null;
		}
	}

	public static LocalDateTime parseFecha(String Fecha) {

		try {
			LocalDateTime fechaParseada = LocalDateTime.parse(Fecha, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
			return fechaParseada;
		} catch (DateTimeParseException ex) {
			ex.printStackTrace();
			return null;
		}
	}
	
	public static String minuteToTime(int minute) {
	    int hour = minute / 60;
	    minute %= 60;
	    String p = "AM";
	    if (hour >= 12) {
	        hour %= 12;
	        p = "PM";
	    }
	    if (hour == 0) {
	        hour = 12;
	    }
	    return (hour < 10 ? "0" + hour : hour) + ":" + (minute < 10 ? "0" + minute : minute) + " " + p;
	}

}
