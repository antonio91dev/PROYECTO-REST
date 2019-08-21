package com.control.personal.empresa.servicios;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Utilitarios {


	
	
	public static long obtenerMinutos(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {

		LocalDateTime fechaReglaInicio = parseFecha("2019-08-19T22:00:00"); // A LAS 10 PM
		LocalDateTime fechaReglaFin = parseFecha("2019-08-20T06:00:00"); // A LAS 6 AM

		if ((fechaReglaInicio.compareTo(fechaHoraInicio) <= 0) && (fechaReglaFin.compareTo(fechaHoraInicio) >= 0)
				|| (fechaReglaInicio.compareTo(fechaHoraFin) <= 0) && (fechaReglaFin.compareTo(fechaHoraFin) >= 0)) {

			long minutosRango = obtenerMinutosRango(fechaHoraInicio, fechaHoraFin);
			
			return calcularMinutosRegla(minutosRango, 60, 72);
			
		} else {

			long minutoRangoIngresoyEntrada = obtenerMinutosRango(fechaHoraInicio, fechaHoraFin);
			
			return minutoRangoIngresoyEntrada;
			
		}

	}
	
	
	/*
	public static long calcularReglaPorDia(LocalDateTime horaSalida, long minutosDia) {

		long totalMinutos;
		DayOfWeek dayOfWeek = horaSalida.getDayOfWeek();
		String dia = dayOfWeek.getDisplayName(TextStyle.NARROW_STANDALONE, Locale.getDefault());
		switch (dia) {
		case "SATURDAY":
			totalMinutos = calcularMinutosRegla(minutosDia, 60, 90);
			break;
		case "SUNDAY":
			totalMinutos = calcularMinutosRegla(minutosDia, 60, 120);
			break;
		default:
			totalMinutos = calcularMinutosRegla(minutosDia, 60, 60);
			break;
		}

		return totalMinutos;
	}*/
	
	public static long obtenerMinutosRango(LocalDateTime fechaHoraInicio, LocalDateTime fechaHoraFin) {
		long minutosDia = ChronoUnit.MINUTES.between(fechaHoraInicio,fechaHoraFin);
		return minutosDia;
	}
	
	public static long calcularMinutosRegla(long minutosTrabajados, long minutosDefault, long minutosRegla) {

		return (minutosTrabajados / minutosDefault) * minutosRegla;

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
	
}
