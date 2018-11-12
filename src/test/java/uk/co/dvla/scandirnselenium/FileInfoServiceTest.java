package uk.co.dvla.scandirnselenium;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.io.FilenameUtils;
import org.junit.BeforeClass;

import junit.framework.TestCase;
import uk.co.dvla.services.filesinfoService.FilesInfoService;

/**
 * Unit test for establishing whether the Scan Folder filter and stores only the supported mime types
 */
public class FileInfoServiceTest 
    extends TestCase
{
    
	private static final String SCAN_DIR = "C:\\Users\\masoo\\eclipse-workspace\\filescanautomation\\src\\main\\resources";
	List supportedExt = null;
	/**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public FileInfoServiceTest( String testName )
    {
        super( testName );
    }
    
    /**
     * Creates the Arraylist of the supported filetypes
     */
    @BeforeClass
	public void setup() {	
    	List supportedExt = new ArrayList();

		supportedExt.add("xlsx");
		supportedExt.add("csv");

	}
    
    /**
     * Test Establishes that only the Supported types are passed through in the Scanned list of the dir
     * @throws Exception
     */
	@org.junit.Test
	
	public void testGetSupportedFileListing()  throws Exception {
		FilesInfoService fis = new FilesInfoService();
		fis.setScanFolder(SCAN_DIR);

		File [] files = fis.getSupportedFileListing();
		List obtainedExt = new ArrayList();
		for(File file:files) {
			obtainedExt.add(FilenameUtils.getExtension(file.getName()));
		}
		assertEquals(true, obtainedExt.containsAll(supportedExt));

	}
}
