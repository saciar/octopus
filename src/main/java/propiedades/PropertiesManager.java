package propiedades;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

public class PropertiesManager {
	
	private Properties properties;
	private static PropertiesManager propertiesManager;
	
	public static PropertiesManager getInstance() {

		if (propertiesManager == null) {
			propertiesManager = new PropertiesManager();
			
		}

		return propertiesManager;
	}

	public PropertiesManager() {
		initProperties();
		try {
			properties = new Properties();
			properties.load(new FileReader("config.properties"));
			//properties.load(getClass().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void setProperties(HashMap<String, String> p) {
		try {
			OutputStream out = new FileOutputStream("config.properties");
			for (Entry<String, String> property : p.entrySet()) {
				properties.setProperty(property.getKey(), property.getValue());
			}
			
			properties.store(out, "Ultimo acceso");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getProperty(String prop) {
		try {
			properties.load(new FileReader("config.properties"));
			//properties.load(PropertiesManager.class.getResourceAsStream("config.properties"));
			return properties.getProperty(prop);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}

	private void initProperties() {
		
		try {
			String ruta = "config.properties";
			File file = new File(ruta);

			String contenidoNombreEvento = "eventName=";
            String sala = "sala=";
            String contenidoSalaId = "salaId=";
            String contenidoLogo= "logo="; 
            String contenidoServer = "server=";
            String contenidoEventCode = "eventPrefix=";   
            String contenidoPwerpoint = "powerpointRoute=";
            String contenidoVideo = "mediaRoute=";                    
            String contenidoPdf = "pdfRoute=";                  
            String contenidoIdioma = "idioma=Espanol";
            String contenidoAdminPass = "adminPass=octo";
            String contenidoDataPath= "data=";
            String contenidoServerStorage = "server_storage=https://storageoctopus.online";
			String res_x = "1280";
			String res_y = "720";
			if (!file.exists()) {	
				
				file.createNewFile();			
	            FileWriter fw = new FileWriter(file);
	            BufferedWriter bw = new BufferedWriter(fw);
	            bw.write(sala);
                bw.newLine();
                bw.write(contenidoPwerpoint);
                bw.newLine();
                bw.write(contenidoVideo);
                bw.newLine();
                bw.write(contenidoServer);
                bw.newLine();
                bw.write(contenidoEventCode);
                bw.newLine();
                bw.write(contenidoSalaId);
                bw.newLine();
                bw.write(contenidoPdf);
                bw.newLine();
                bw.write(contenidoLogo);
                bw.newLine();
                bw.write(contenidoNombreEvento);
                bw.newLine();
                bw.write(contenidoIdioma);
                bw.newLine();
                bw.write(contenidoAdminPass);
                bw.newLine();
                bw.write(contenidoDataPath);
                bw.newLine();
                bw.write(contenidoServerStorage);
                bw.newLine();
                bw.write(res_x);
                bw.newLine();
                bw.write(res_y);
                bw.close();
                fw.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
