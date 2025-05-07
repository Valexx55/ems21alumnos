package edu.kyndryl.academy.msalumnos.model;

public class FraseChuckNorris {
	
	private String value;
	private String id;
	
	
	public FraseChuckNorris() {
		super();
	}
	public FraseChuckNorris(String value, String id) {
		super();
		this.value = value;
		this.id = id;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return "FraseChuckNorris [value=" + value + ", id=" + id + "]";
	}
	
	
		

}
