package edu.csula.cs460.graph.search;

public class Tile {
	int rowNumber;
	int columNumber;
	String data;
	String direction;
	Tile parent;
	Huristic huristicValue=null;
	public Tile(int rowNumber, int columNumber, String data) {
		super();
		this.rowNumber = rowNumber;
		this.columNumber = columNumber;
		this.data = data;
	}
	public int getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}
	public int getColumNumber() {
		return columNumber;
	}
	public void setColumNumber(int columNumber) {
		this.columNumber = columNumber;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public Tile getParent() {
		return parent;
	}
	public void setParent(Tile parent) {
		this.parent = parent;
	}
	public Huristic getHuristicValue() {
		return huristicValue;
	}
	public void setHuristicValue(Huristic huristicValue) {
		this.huristicValue = huristicValue;
	}

}
