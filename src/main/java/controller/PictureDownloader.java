package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class PictureDownloader {
	
	private static final String TO_FOLDER = "data/profiles/";
	
	private String fileName;
	private String fileUrl;
	private String speakerName;

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

	public File downloadImage() {

		try {
			File dir = new File(TO_FOLDER);
			if (!dir.exists()) {
				if (!dir.mkdir())
					return null;
			}

			File destFile = new File(TO_FOLDER + speakerName.substring(speakerName.lastIndexOf(" ")+1,speakerName.length())+fileName.substring(fileName.indexOf("."), fileName.length()));

			URLConnection conn = new URL(fileUrl+"/"+fileName).openConnection();
			conn.connect();
			System.out.println("\nempezando descarga de foto de profile de "+speakerName+" : \n");
			System.out.println(">> URL: " + fileUrl);
			System.out.println(">> Nombre: " + fileName);
			System.out.println(">> tamaño: " + conn.getContentLength() + " bytes");

			InputStream in = conn.getInputStream();
			OutputStream out = new FileOutputStream(destFile);

			int b = 0;
			while (b != -1) {
				b = in.read();
				if (b != -1)
					out.write(b);
			}

			out.close();
			in.close();
			return destFile;
		} catch (MalformedURLException e) {
			System.out.println("la url: " + fileUrl + " no es valida!");
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	


}
