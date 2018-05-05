package hw5.model;

public class CheckoutEntry {

	private int id;
	private int cin;
	private String name;
	private String borrowed;
	private String due;
	private String returned;
	
	public CheckoutEntry() {}
	
	public CheckoutEntry(int id, int cin, String name, String borrowed, String due, String returned) {
		this.id = id;
		this.cin = cin;
		this.name = name;
		this.borrowed = borrowed;
		this.due = due;
		this.returned = returned;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCin() {
		return cin;
	}

	public void setCin(int cin) {
		this.cin = cin;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBorrowed() {
		return borrowed;
	}

	public void setBorrowed(String borrowed) {
		this.borrowed = borrowed;
	}

	public String getDue() {
		return due;
	}

	public void setDue(String due) {
		this.due = due;
	}

	public String getReturned() {
		return returned;
	}

	public void setReturned(String returned) {
		this.returned = returned;
	}
}
