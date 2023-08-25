package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.io.FileUtils;

import propiedades.PropertiesManager;

public class QrDownloader {

	
private static final String TO_FOLDER = PropertiesManager.getInstance().getProperty("data")+"/Octopus/data/qrs/";
	
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
	
	/*
	 * public File downloadImage() {
	 * 
	 * try {
	 * 
	 * File dir_complex = new File(TO_FOLDER+"/"+id); if (!dir_complex.exists()) {
	 * if (!dir_complex.mkdirs()) return null; }
	 * 
	 * 
	 * 
	 * File destFile = new File(TO_FOLDER + id
	 * +"/"+id+fileName.substring(fileName.indexOf("."), fileName.length()));
	 * 
	 * URLConnection conn = new URL(fileUrl+"/"+fileName).openConnection();
	 * conn.connect();
	 * 
	 * InputStream in = conn.getInputStream(); OutputStream out = new
	 * FileOutputStream(destFile);
	 * 
	 * int b = 0; while (b != -1) { b = in.read(); if (b != -1) out.write(b); }
	 * 
	 * out.close(); in.close(); return destFile; } catch (MalformedURLException e) {
	 * System.out.println("la url: " + fileUrl + " no es valida!"); } catch
	 * (IOException e) { e.printStackTrace(); return null; }
	 * 
	 * return null; }
	 */
	
	public File downloadImage() throws MalformedURLException, IOException {
		//try {
			
			File dir = new File(TO_FOLDER+"/"+id);
			if (!dir.exists()) {
				if (!dir.mkdirs())
					return null;
			}

			//File destFile = new File(TO_FOLDER + speakerName.substring(speakerName.lastIndexOf(" ")+1,speakerName.length())+fileName.substring(fileName.indexOf("."), fileName.length()));
			File destFile = new File(TO_FOLDER + id+"/"+id+fileName.substring(fileName.indexOf("."), fileName.length()));
			if(!destFile.exists()) {
				System.out.println("Descargando QR de: "+speakerName);
				FileUtils.copyURLToFile(new URL(fileUrl+"/"+fileName), destFile);				
			}
			return destFile;
		/*}
		catch (Exception e) {
			e.printStackTrace();
			return null;
		}*/
		
	}
}
