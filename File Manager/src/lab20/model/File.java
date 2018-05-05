package lab20.model;

public class File {

	private int id;
	private String name;
	private boolean isFolder;
	private int parentID;
	private int ownerID;
	
	public File() {}
	
	public File(int id, String name, boolean isFolder, int parentID, int ownerID) {
		this.id = id;
		this.name = name;
		this.isFolder = isFolder;
		this.parentID = parentID;
		this.ownerID = ownerID;
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

	public boolean isFolder() {
		return isFolder;
	}

	public void setFolder(boolean isFolder) {
		this.isFolder = isFolder;
	}

	public int getParentID() {
		return parentID;
	}

	public void setParentID(int parentID) {
		this.parentID = parentID;
	}

	public int getOwnerID() {
		return ownerID;
	}

	public void setOwnerID(int ownerID) {
		this.ownerID = ownerID;
	}
}
