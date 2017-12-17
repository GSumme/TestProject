package com.cappuccino.util;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;

public class ExcleUtil {

	public static String getStringVal(HSSFCell hssfCell) {
		switch (hssfCell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			return hssfCell.getBooleanCellValue() ? "TRUE" : "FALSE";
		case Cell.CELL_TYPE_FORMULA:
			return hssfCell.getCellFormula();
		case Cell.CELL_TYPE_NUMERIC:
			hssfCell.setCellType(Cell.CELL_TYPE_STRING);
			return hssfCell.getStringCellValue();
		case Cell.CELL_TYPE_STRING:
			return hssfCell.getStringCellValue();
		default:
			return hssfCell.getStringCellValue();
		}
	}
}
