package com.mars.robots.model;

/*
 * Captures the x and y co ordinate on the grid
 * */
public class Point{
	private int x;
	private int y;
	
	public Point () {}
	
	public Point (int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Point (final String x, final String y) {
		this.x = Integer.valueOf(x);
		this.y = Integer.valueOf(y);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}	
}