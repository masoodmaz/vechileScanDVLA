package uk.co.dvla.model;

public class FileBean {
	
	/**
	 * This is a POJO for holding File Objects Metadata namely
	 * Name, MimeType, FileSize, Extension
	 */
	private String filename;
	private String filemimetype;
	private  long filesize;
	private String fileextension;

	 public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilemimetype() {
		return filemimetype;
	}
	public void setFilemimetype(String filemimetype) {
		this.filemimetype = filemimetype;
	}
	public long getFilesize() {
		return filesize;
	}
	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}
	public String getFileextension() {
		return fileextension;
	}
	public void setFileextension(String fileextension) {
		this.fileextension = fileextension;
	}
	
	
}
