package model;

import java.io.Serializable;
import java.util.ArrayList;

public class Room implements Serializable{	
 
	private int id;
	private ArrayList<Date> dates;
	private String name;
	
	public Room() {

	}

	public Room(int id, ArrayList<Date> dates, String name) {
		super();
		this.id = id;
		this.dates = dates;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ArrayList<Date> getDates() {
		return dates;
	}

	public void setDates(ArrayList<Date> dates) {
		this.dates = dates;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name ;
	}

	
}
