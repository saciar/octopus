package controller;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FileUtils;

import propiedades.PropertiesManager;

public class EventPictureDownloader {
	
private static final String TO_FOLDER = PropertiesManager.getInstance().getProperty("data")+"/Octopus/data/";
	
	private String fileName;
	private String fileUrl; //= PropertiesManager.getInstance().getProperty("server") + "/img/logos/";
	private String server ;


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
	
	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public File downloadImage() throws MalformedURLException, IOException {
		//try {
			
			File dir = new File(fileUrl);
			if (!dir.exists()) {
				if (!dir.mkdirs())
					return null;
			}

			//File destFile = new File(TO_FOLDER + speakerName.substring(speakerName.lastIndexOf(" ")+1,speakerName.length())+fileName.substring(fileName.indexOf("."), fileName.length()));
			File destFile = new File(fileUrl + "/" +fileName);
			if(!destFile.exists()) {
				System.out.println("Descargando imagen evento ");
				FileUtils.copyURLToFile(new URL(server+"/"+fileName), destFile);				
			}
			return destFile;
		/*}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/
		
	}
}
