package edu.csula.cs460.graph.search;

public class Huristic {
	int colDiff=0;
	int rowDiff=0;
	int pathDiff=0;
	double value=0;
	public Huristic(int colDiff, int rowDiff) {
		super();
		this.colDiff = colDiff;
		this.rowDiff = rowDiff;
	}
	public int getColDiff() {
		return colDiff;
	}
	public void setColDiff(int colDiff) {
		this.colDiff = colDiff;
	}
	public int getRowDiff() {
		return rowDiff;
	}
	public void setRowDiff(int rowDiff) {
		this.rowDiff = rowDiff;
	}
	public int getPathDiff() {
		return pathDiff;
	}
	public void setPathDiff(int pathDiff) {
		this.pathDiff = pathDiff;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}

}
