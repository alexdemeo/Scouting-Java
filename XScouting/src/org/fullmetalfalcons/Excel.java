package org.fullmetalfalcons;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.fullmetalfalcons.data.DataPoint;
import org.fullmetalfalcons.teams.Team;


/**
 * Handles the setup and writing of the Excel workbook containing the results
 * 
 * @author Dan
 * 
 */
public class Excel {
	
	private static XSSFWorkbook workbook;
	private static Sheet sheet;
	
	public static void init(){
		setupWorkbook();
	}
	
	
	/**
	 * Sets up workbook based on the sections.txt and headers.txt files
	 * Iterates along the headers and when it reaches one that starts with an !, it retroactively adds the next section in the 
	 * cells above it
	 * 
	 * 
	 * Here be dragons
	 */
	public static void setupWorkbook(){
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Results");
		
		Row topRow = sheet.createRow(0);
		Row bottomRow = sheet.createRow(1);
		
		//Create Style with a bottom border, right border, and center alligned
		CellStyle topLabel = workbook.createCellStyle();
		topLabel.setBorderBottom(CellStyle.BORDER_THIN);
		topLabel.setBorderRight(CellStyle.BORDER_THIN);
		topLabel.setAlignment(CellStyle.ALIGN_CENTER);
		topLabel.setWrapText(true);
		
		//Create a style with a bottom border
		CellStyle bottomLabel = workbook.createCellStyle();
		bottomLabel.setBorderBottom(CellStyle.BORDER_THIN);
		bottomLabel.setWrapText(true);
		
		Cell topCell;
		Cell bottomCell;
		
		try (
				InputStream sectionIn = Excel.class.getResourceAsStream("/org/fullmetalfalcons/files/sections.txt");
				BufferedReader sectionReader = new BufferedReader(new InputStreamReader(sectionIn));
				){
			//Current cell
			int counter = 0;
			//Column number where this section began
			int lastSection = 0;

			
			bottomCell = bottomRow.createCell(counter++);
			bottomCell.setCellValue("Team Number");
			bottomCell.setCellStyle(bottomLabel);
			
			bottomCell = bottomRow.createCell(counter++);
			bottomCell.setCellValue("Team Name");
			bottomCell.setCellStyle(bottomLabel);
			
			bottomCell = bottomRow.createCell(counter++);
			bottomCell.setCellValue("Team Location");
			bottomCell.setCellStyle(bottomLabel);
			
			bottomCell = bottomRow.createCell(counter++);
			bottomCell.setCellValue("Match Number");
			bottomCell.setCellStyle(bottomLabel);
			
			bottomCell = bottomRow.createCell(counter++);
			bottomCell.setCellValue("Alliance Color");
			bottomCell.setCellStyle(topLabel);
			
			for (int i = lastSection; i<=counter; i++){
				topCell = topRow.createCell(i);
				topCell.setCellStyle(topLabel);
			}
			
			topCell = topRow.getCell(lastSection);
			topCell.setCellValue("General");
			sheet.addMergedRegion(new CellRangeAddress(0,0,lastSection,counter));
			
			lastSection = counter+1;
			
			//For every header:
			for (DataPoint p: ScoutingMain.dataPoints){
				bottomCell = bottomRow.createCell(counter);
				//If the line in the file starts with !
				if (p.getTitle().charAt(0)=='!'){
					bottomCell.setCellValue(p.getTitle().substring(1));
					//add a side border
					bottomCell.setCellStyle(topLabel);
					
					//Make all of the cells in the top row of this section topStyle
					for(int i = lastSection; i<=counter;i++){
						topCell = topRow.createCell(i);
						topCell.setCellStyle(topLabel);
					}
					
					topCell = topRow.getCell(lastSection);
					//set the section values to the correct value
					topCell.setCellValue(sectionReader.readLine());
					//Merge the section labels
					sheet.addMergedRegion(new CellRangeAddress(0,0,lastSection,counter));
					
					lastSection = counter+1;
					
				} else {
					bottomCell.setCellValue(p.getTitle());
					bottomCell.setCellStyle(bottomLabel);
				}
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	    write();
		
	}
	
	public static void export(){
		int rowNum = sheet.getLastRowNum()+1;
		Row r;
		Cell c;
		CellStyle sectionEnd = workbook.createCellStyle();
		sectionEnd.setBorderRight(CellStyle.BORDER_THIN);
		int cellnum;
		for (Team t: TeamUtils.TEAMS){
			cellnum = 0;
			r = sheet.createRow(rowNum);
			
			//These 5 values will always be needed in competition, therefore they are manually added
			
			c = r.createCell(cellnum++);
			c.setCellValue(t.getID().getNumber());
			
			c = r.createCell(cellnum++);
			c.setCellValue(t.getID().getName());

			c = r.createCell(cellnum++);
			c.setCellValue(t.getID().getLocation());
			
			c = r.createCell(cellnum++);
			c.setCellValue(Integer.parseInt(t.getDataAt("match_number")));
			
			c = r.createCell(cellnum++);
			c.setCellValue(t.getDataAt("team_color"));
			c.setCellStyle(sectionEnd);
			
			for (DataPoint p: ScoutingMain.dataPoints){
				c = r.createCell(cellnum++);
				String value = p.parseData(t.getDataAt(p.getKey()),t);
				try{
					c.setCellValue(Double.parseDouble(value));
				} catch(NumberFormatException e){
					c.setCellValue(value);
				}
				if (p.getTitle().charAt(0)=='!'){
					c.setCellStyle(sectionEnd);
				}
			}
				
			rowNum++;
		}
		
		write();
		
	}
	
	
	/**
	 * Writes the excel workbook out to "results.xlsx" in the Directory specified
	 */
	private static void write(){
		
		try {
		    FileOutputStream out = new FileOutputStream(ScoutingMain.directoryPath + "/results.xlsx");
		    workbook.write(out);
		    out.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

}
