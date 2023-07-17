package idiomas;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class Idioma extends Properties {
	
	public Idioma(String idioma) {

		if(idioma.equals("Ingles")) {
			getProperties("/idioma/ingles.properties");
		}
		else {
			getProperties("/idioma/espanol.properties");
		}


    }

    private void getProperties(String idioma) {
        try {
            this.load(getClass().getResourceAsStream(idioma));
        	//this.load(new FileReader(idioma));
        } catch (IOException ex) {

        }
    }

}
