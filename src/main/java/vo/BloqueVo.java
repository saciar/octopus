package vo;

import java.util.ArrayList;

import model.Speaker;

public class BloqueVo {
	
	private String inicio;
	private String cierre;
	private String nombre_bloque;
	private int id_bloque;
	
	private ArrayList<Speaker> speakers;
	
	public BloqueVo() {
	}

	public BloqueVo(String inicio, String cierre, String nombre_bloque, int id_bloque, ArrayList<Speaker> speakers) {
		this.inicio = inicio;
		this.cierre = cierre;
		this.nombre_bloque = nombre_bloque;
		this.id_bloque = id_bloque;
		this.speakers = speakers;
	}

	public String getInicio() {
		return inicio;
	}

	public void setInicio(String inicio) {
		this.inicio = inicio;
	}

	public String getCierre() {
		return cierre;
	}

	public void setCierre(String cierre) {
		this.cierre = cierre;
	}

	public String getNombre_bloque() {
		return nombre_bloque;
	}

	public void setNombre_bloque(String nombre_bloque) {
		this.nombre_bloque = nombre_bloque;
	}

	public ArrayList<Speaker> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(ArrayList<Speaker> speakers) {
		this.speakers = speakers;
	}

	public int getId_bloque() {
		return id_bloque;
	}

	public void setId_bloque(int id_bloque) {
		this.id_bloque = id_bloque;
	}	


}
