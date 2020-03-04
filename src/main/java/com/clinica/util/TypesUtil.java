package com.clinica.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public final class TypesUtil {
	
	private TypesUtil() {
	}
	
	public static String getStringValue(Cell cell) {
		if (cell == null) return "";
		if(cell.getCellType() == CellType.NUMERIC){
			return String.valueOf(getNumericValue(cell));
		}
		else if(cell.getCellType() == CellType.STRING){
			return cell.getStringCellValue();
		}
		return "";
	}
	
	
	public static Number getNumericValue(Cell cell) {
		if (cell != null) {
			if (cell.getCellType() == CellType.NUMERIC) {
				return cell.getNumericCellValue();
			} else {
				String valor = cell.getStringCellValue();
				if (!valor.equals("")) {
					DecimalFormat df = new DecimalFormat("#.#", new DecimalFormatSymbols(Locale.US));
					try {
						return df.parse(valor);
					} catch (ParseException e) {
						
					}
				}
			}
		}
		return 0.0;
	}
	
	public static Date getDateValue(Cell cell, String pattern) {
		if (cell != null) {
			if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
				return cell.getDateCellValue();
			} else {
				String valor = getStringValue(cell);
				if (!valor.equals("")) {
					if (pattern == null) {
						
					}
					try {
						return new SimpleDateFormat(pattern, Locale.US).parse(valor);
					} catch (ParseException e) {
						
					}
				}
			}
		}
		return null;
	}

	public static void validarBDDouble(PreparedStatement stmt, Integer indice, Double numero) throws SQLException{
		if(numero == null)
			stmt.setNull(indice, Types.NUMERIC);
		else
			stmt.setDouble(indice, numero);
	}

	public static void validarBDFecha(PreparedStatement stmt, Integer indice, Date fecha) throws SQLException{
		if(fecha == null)
			stmt.setNull(indice, Types.DATE);
		else
			stmt.setDate(indice, new java.sql.Date(fecha.getTime()));
	}
	
	public static void validarBDTime(PreparedStatement stmt, Integer indice, Time time) throws SQLException{
		if(time == null)
			stmt.setNull(indice, Types.TIME);
		else
			stmt.setTime(indice, time);
	}

	public static void validarBDString(PreparedStatement stmt, Integer indice, String cadena) throws SQLException{
		if (cadena == null)
			stmt.setNull(indice, Types.VARCHAR);
		else
			stmt.setString(indice, cadena);
	}
	
	public static void validarBDInteger(PreparedStatement stmt, Integer indice, Integer cadena) throws SQLException{
		if (cadena == null)
			stmt.setNull(indice, Types.VARCHAR);
		else
			stmt.setInt(indice, cadena);
	}
}