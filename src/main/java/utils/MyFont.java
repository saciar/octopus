package utils;

import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MyFont {
	
	public static final int LIGHT = 0;
	public static final int BOLD = 1;
	
	private Font font = null;

	public MyFont(int type) {
		
		String fontName = "/fonts/NunitoSans-Light.ttf";

		if(type == BOLD) {
			fontName ="/fonts/NunitoSans-Bold.ttf";
		}
		try {
			// Se carga la fuente
			InputStream myStream = new BufferedInputStream(getClass().getResourceAsStream(fontName));

			font = Font.createFont(Font.TRUETYPE_FONT, myStream);
		} catch (Exception ex) {
			// Si existe un error se carga fuente por defecto ARIAL
			ex.printStackTrace();
			font = new Font("Arial", Font.PLAIN, 14);
		}
	}
	

	/*
	 *  tamanio = float
	 */
	public Font getFont(float tamanio) {
		Font tfont = font.deriveFont(tamanio);
		return tfont;
	}

}
