package com.betacom.ec.utils;

import java.sql.Date;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.betacom.ec.exception.EcommerceVinoException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Utilities {
	private final static String PATTERN_DATE = "dd/MM/yyyy";
	private final static String PATTERN_DATETIME = "dd/MM/yyyy HH:mm:ss";
	
	/*
	 * TRANSFORM DATE TO FORMAT STRING
	 */
	public static String dateToString(LocalDate myDate) {
		return dateToString(PATTERN_DATE,myDate);
	}
	
	public static String dateToString(String pattern, LocalDateTime myDate) {
		//utilizzo una funzione di formattazione
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ITALIAN); //trasforma la data nel formato interessato
		
		return myDate.format(formatter);
	}
	
	public static String dateToString(String pattern, LocalDate myDate) {
		//utilizzo una funzione di formattazione
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ITALIAN); //trasforma la data nel formato interessato
		
		return myDate.format(formatter);
	}

	public static LocalDate stringToDate(String myDate) {
		LocalDate localDate = null;
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_DATE, Locale.ITALIAN);
			localDate = LocalDate.parse(myDate, formatter);
		} catch (DateTimeException e) {
			throw new EcommerceVinoException("Formato della data invalido: " + myDate + " .Formato previsto: " + PATTERN_DATE);
		}
		
		
		return localDate; 
	}
	
	public static LocalDate dateToLocalDate(Object value) {
		if (value == null)
				return null;
		
		return ((Date) value).toLocalDate();
	}
	
	public static String buildClassName(String par) {
		return par.substring(0, 1).toUpperCase() + par.substring(1).toLowerCase() + "Manager";
	}
	
		public static LocalDateTime stringToDateTime(String myDate) {
			return stringToDateTime(PATTERN_DATETIME, myDate);
		}
		
		public static LocalDateTime stringToDateTime(String pattern, String myDate) {
			LocalDateTime localDateTime = null;
			
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern, Locale.ITALIAN);
				localDateTime = LocalDateTime.parse(myDate, formatter);
			} catch (DateTimeException e) {
				throw new EcommerceVinoException("Formato della data e ora invalido: " + myDate + " .Formato previsto: " + pattern);
			}
			
			return localDateTime; 
		}
		
		public static String dateToString(LocalDateTime myDate) {
			return dateToString(PATTERN_DATETIME, myDate);
		}
		
	
}
