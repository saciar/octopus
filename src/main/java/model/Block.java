package model;

import java.time.LocalTime;
import java.util.ArrayList;

public class Block {

	private int id;
	private String name;
	private ArrayList<Speaker> speakers = new ArrayList<Speaker>();
	private LocalTime initBlock;
	private LocalTime endBlock;

	public Block(int id, String name, ArrayList<Speaker> speakers, LocalTime initBlock, LocalTime endBlock) {
		super();
		this.id = id;
		this.name = name;
		this.speakers = speakers;
		this.initBlock = initBlock;
		this.endBlock = endBlock;
	}
	
	public Block() {
	
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public ArrayList<Speaker> getSpeakers() {
		return speakers;
	}
	public void setSpeakers(ArrayList<Speaker> speakers) {
		this.speakers = speakers;
	}
	public LocalTime getInitBlock() {
		return initBlock;
	}
	public void setInitBlock(LocalTime initBlock) {
		this.initBlock = initBlock;
	}
	public LocalTime getEndBlock() {
		return endBlock;
	}
	public void setEndBlock(LocalTime endBlock) {
		this.endBlock = endBlock;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	
	public int compareTo(Block o) {
        return this.initBlock.compareTo(o.initBlock);
    }
	
}
