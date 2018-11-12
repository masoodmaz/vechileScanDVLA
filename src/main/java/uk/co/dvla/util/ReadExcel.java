package uk.co.dvla.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import uk.co.dvla.model.VehicleBean;
/**
 * 
 * @author 
 * This file is used to read Vehicle information from a spreadsheet
 */
public class ReadExcel {
	
	/**
	 * 
	 * @param filename: Excel file to be read
	 * @return List of Vehicle Beans
	 */
	public List <VehicleBean> getAllVehiclesFromFile(String filename){
		
		List <VehicleBean> vehicleList = new ArrayList<VehicleBean>();
		

		
	 try {
         FileInputStream excelFile = new FileInputStream(new File(filename));
         Workbook workbook = new XSSFWorkbook(excelFile);
         Sheet datatypeSheet = workbook.getSheetAt(0);
         Iterator<Row> iterator = datatypeSheet.iterator();

         while (iterator.hasNext()) {

             Row currentRow = iterator.next();
             Iterator<Cell> cellIterator = currentRow.iterator();
             int columnNum = currentRow.getLastCellNum();
             int iterator_index = 0;
             VehicleBean vehicle = new VehicleBean();

             while(cellIterator.hasNext()) {
            	 
                 Cell currentCell = cellIterator.next();
                 if(iterator_index==0) {
                	 vehicle.setReg(currentCell.getStringCellValue());
                 }
                 else if(iterator_index==columnNum-1) {
            	 vehicle.setColour(currentCell.getStringCellValue());

                 }
                 else {
                     vehicle.setMake(currentCell.getStringCellValue());


                 }
                 iterator_index++;
                 

             }
             vehicleList.add(vehicle);

             }

         
     } catch (FileNotFoundException e) {
         e.printStackTrace();
     } catch (IOException e) {
         e.printStackTrace();
     }
	 return vehicleList;
 }
	
}



