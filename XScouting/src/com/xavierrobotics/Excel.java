package com.xavierrobotics;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import static com.xavierrobotics.ScoutingMain.teams;

public class Excel {
	private ScoutingMain instance;
	
	public Excel(ScoutingMain instance) {
		this.instance = instance;
	}
	
	private void addTeamColumn(Row r, Team team) {
		Cell c = null;
		c = r.createCell(0);
		c.setCellValue(team.getTeamName());
		c = r.createCell(1);
		c.setCellValue(team.getTeamNum());
		c = r.createCell(2);
		c.setCellValue(team.getLocation());
		c = r.createCell(3);
		c.setCellValue(team.getDate());
		c = r.createCell(4);
		c.setCellValue(team.getTeamColor());

	}
	
	public void writeToXls(String path) {
		FileOutputStream out = null;
		HSSFWorkbook workbook = null;
		try {
			workbook = new HSSFWorkbook();
			out = new FileOutputStream(new File(path + "/results.xls"));
			Sheet s = workbook.createSheet("Results");
			Row r = s.createRow(0);
			Cell c;
			CellStyle cs = workbook.createCellStyle();
			cs.setBorderBottom(CellStyle.BORDER_THIN);
			Font f = workbook.createFont();
			f.setBoldweight(Font.BOLDWEIGHT_BOLD);
			for (int i = 0; i < instance.labels.length; i++){
				c = r.createCell(i);
				c.setCellValue(instance.labels[i]);
				c.setCellStyle(cs);
			}
			for (int i = 0; i < teams.size(); i++){
				r = s.createRow(i + 1);
				addTeamColumn(r, teams.get(i));
			}
			for (int i = 0; i < 10; i++) {
//				if (i != 5) s.autoSizeColumn(i);
			}
			workbook.write(out);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			try {
				workbook.close();
				out.close();
				System.exit(0);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}		
	}
}
