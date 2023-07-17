package controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.HashMap;

public class VersionsManager {

	private HashMap<Integer, LocalDateTime> presentationsMap = new HashMap<>();
	
	public boolean isNewVersion(Integer id, LocalDateTime newDate) {		
		LocalDateTime date = presentationsMap.get(id);
		if (date != null) {
			if (!newDate.equals(date)) {
				return true;
			}
			else return false;

		} else {
			return true;
		}
		
	}
	
	public void addVersion(Integer id, LocalDateTime newDate) {
		presentationsMap.put(id, newDate);
	}
	
	public void saveFile() {
		try {
			OutputStream file = new FileOutputStream("fileVersion.dat");
			OutputStream buffer = new BufferedOutputStream(file);
			ObjectOutput output = new ObjectOutputStream(buffer);
			output.writeObject(presentationsMap);
			output.close();
			buffer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getFile() {
		try {
			File file1 = new File("fileVersion.dat");
			if(!file1.exists()) {
				saveFile();
			}
			InputStream file = new FileInputStream("fileVersion.dat");
			InputStream buffer = new BufferedInputStream(file);
			ObjectInput input = new ObjectInputStream(buffer);
			presentationsMap = (HashMap<Integer, LocalDateTime>)input.readObject();
			input.close();
			buffer.close();
			System.out.println(presentationsMap);

		} catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
