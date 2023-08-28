package model;

import java.io.Serializable;

public class Event implements Serializable{
	
	private String name;
	
	private String logo;
	
	private String code;

	public Event(String name, String logo, String code) {
		this.name = name;
		this.logo = logo;
		this.code = code;
	}

	public Event() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
