package downloaders;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import propiedades.PropertiesManager;

public class FileDownloader {

	private static final String TO_FOLDER_PROFILES = PropertiesManager.getInstance().getProperty("data")+"/Octopus/data/profiles/";
	
	private String fileName;
	private String fileUrl;
	private String speakerName;
	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}	
	
	public String getSpeakerName() {
		return speakerName;
	}

	public void setSpeakerName(String speakerName) {
		this.speakerName = speakerName;
	}

	public File getFileFrom() throws MalformedURLException, IOException {

		/*
		 * File dir = new File(TO_FOLDER_PROFILES); if (!dir.exists()) { if
		 * (!dir.mkdirs()) return null; }
		 * 
		 * File destFile = new File(TO_FOLDER_PROFILES +
		 * speakerName.substring(speakerName.lastIndexOf(" ")+1,speakerName.length())+
		 * fileName.substring(fileName.indexOf("."), fileName.length()));
		 * if(!destFile.exists()) {
		 * System.out.println("Descargando foto de: "+speakerName);
		 * FileUtils.copyURLToFile(new URL(fileUrl+"/"+fileName), destFile); } return
		 * destFile;
		 */

		File dir = new File(TO_FOLDER_PROFILES+"/"+id);
		if (!dir.exists()) {
			if (!dir.mkdirs())
				return null;
		}

		//File destFile = new File(TO_FOLDER + speakerName.substring(speakerName.lastIndexOf(" ")+1,speakerName.length())+fileName.substring(fileName.indexOf("."), fileName.length()));
		File destFile = new File(TO_FOLDER_PROFILES + id+"/"+id+fileName.substring(fileName.indexOf("."), fileName.length()));
		if(!destFile.exists()) {
			System.out.println("Descargando QR de: "+speakerName);
			FileUtils.copyURLToFile(new URL(fileUrl+"/"+fileName), destFile);				
		}
		return destFile;
		
	}
	
}
