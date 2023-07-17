package vo;

import model.Speaker;

public class SpeakerVo {

	private String nombre;
	private String nombre_charla;
	private Speaker speaker;
		
	public SpeakerVo(String nombre, String nombre_charla, Speaker speaker) {
		this.nombre = nombre;
		this.nombre_charla = nombre_charla;
		this.speaker = speaker;
	}
	public Speaker getSpeaker() {
		return speaker;
	}
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNombre_charla() {
		return nombre_charla;
	}
	public void setNombre_charla(String nombre_charla) {
		this.nombre_charla = nombre_charla;
	}
	
	
}
