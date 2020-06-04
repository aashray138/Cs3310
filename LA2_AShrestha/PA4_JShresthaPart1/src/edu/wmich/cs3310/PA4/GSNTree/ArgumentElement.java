package edu.wmich.cs3310.PA4.GSNTree;

import java.util.ArrayList;

public abstract class ArgumentElement {
	private String id;
	private String description;
	private ArrayList<Value> content;
	private ArgumentElement source;
	private ArgumentElement target;

	
	public ArgumentElement(String id, String description) {
		setId(id);
		setDescription(description);
	}
	public ArgumentElement getSrc() {
		return source;
		
	}
	public ArgumentElement getTrg() {
		return target;
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	

}
