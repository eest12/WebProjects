package hw5.model;

public class LibraryItem {
	
	private int id;
	private String type;
	private String name;
	private String info;
	private boolean available;
	
	public LibraryItem() {}
	
	public LibraryItem(int id, String type, String name, String info, boolean available) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.info = info;
		this.available = available;
	}

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

}
