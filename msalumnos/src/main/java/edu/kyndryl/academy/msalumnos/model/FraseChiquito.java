package edu.kyndryl.academy.msalumnos.model;

public class FraseChiquito {
	
	private int id;
	private String quote;
	
	
	
	
	public FraseChiquito() {
		// TODO Auto-generated constructor stub
	}
	
	public FraseChiquito(int id, String quote) {
		super();
		this.id = id;
		this.quote = quote;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getQuote() {
		return quote;
	}
	public void setQuote(String quote) {
		this.quote = quote;
	}

	@Override
	public String toString() {
		return "FraseChiquito [id=" + id + ", quote=" + quote + "]";
	}
	
	
	

}
