package edu.pitt.cs;

public class CatImpl implements Cat {

	private String catName;
	private int catId;
	private boolean rented;

	public CatImpl(int id, String name) {
		catId = id;
		catName = name;
		rented = false;
	}

	public void rentCat() {
		rented = true;
	}

	public void returnCat() {
		rented = false;
	}

	public void renameCat(String name) {
		catName = name;
	}

	public String getName() {

		return catName;
	}

	public int getId() {

		return catId;
	}

	public boolean getRented() {
		return rented;
	}

	public String toString() {

		return "ID " + catId + ". " + catName;
	}

}