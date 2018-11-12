package uk.co.dvla.services.filesinfoService;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.OrFileFilter;
import org.apache.commons.io.filefilter.SuffixFileFilter;

import uk.co.dvla.model.FileBean;

public class FilesInfoService {
	/**
	 * Service Layer for getting filemetatda from a configured directory
	 */
	// Filetypes to be filtered based on CSV, EXCEL
	private static final String CSV = "csv";
	private static final String EXCEL = "xlsx";
	
	FileBean filebean;
	
	
	String scanFolder;

	public String getScanFolder() {
		return scanFolder;
	}


	public void setScanFolder(String scanFolder) {
		this.scanFolder = scanFolder;
	}

	
	public File [] getSupportedFileListing() {
			File folder = new File(scanFolder);
		    IOFileFilter extFilter = new OrFileFilter(new SuffixFileFilter(EXCEL),
					   new SuffixFileFilter(CSV));
			List<File> files = (List<File>) FileUtils.listFiles(folder, extFilter,null );
			return FileUtils.convertFileCollectionToFileArray(files);
			 
		}
	
	public File [] getAllFileListing() {
		File folder = new File(scanFolder);
		return folder.listFiles();
		 
	}
	
	public void printFileInfo(FileBean bean) {
		System.out.println(bean.getFilename()+" -- "+bean.getFileextension()+" -- "+bean.getFilemimetype()+" -- "+bean.getFilesize());
		
	}
	public void getFileInfo() throws IOException {
		File [] filelist = getAllFileListing();
		filebean = new FileBean();
		if (filelist==null) throw new IOException("directory invalid or empty");
		for (File file : filelist) {
			filebean.setFilename(file.getName());
			filebean.setFilesize(file.length());
			filebean.setFilemimetype(java.nio.file.Files.probeContentType(file.toPath()));
			filebean.setFileextension(FilenameUtils.getExtension(file.toPath().toString()));
			
			printFileInfo(filebean);
		}
		

		
	}
	
	public static void main(String[] args) throws IOException {
		FilesInfoService fis= new FilesInfoService();
		fis.setScanFolder("C:\\Users\\masoo\\eclipse-workspace\\filescanautomation\\src\\main\\resources");
		fis.getFileInfo();
	}
}

