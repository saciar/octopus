package model;

import java.util.ArrayList;

public class Date {
	
	private String name;
	private ArrayList<Block> blocks;
	
	public Date(String name, ArrayList<Block> blocks) {
		super();
		this.name = name;
		this.blocks = blocks;
	}

	public Date() {
		
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(ArrayList<Block> blocks) {
		this.blocks = blocks;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return name;
	}
	
	

}
