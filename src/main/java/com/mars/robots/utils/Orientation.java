package com.mars.robots.utils;

/*
 * Enum to store the direction/orientation of the robot and associated functionality.
 * 
 * */
public enum Orientation {
    N,
    E,
    S,
    W;

	/*
	 * Provide resultant direction on the R moves
	 * */
    public static Orientation rotateRight(final Orientation o) {
        switch(o) {
            case N: return E;
            case E: return S;
            case S: return W;
            case W: return N;
        }
        return null;
    }

	/*
	 * Provide resultant direction on the L moves
	 * */
    public static Orientation rotateLeft(final Orientation o) {
        switch(o) {
            case N: return W;
            case W: return S;
            case S: return E;
            case E: return N;
        }
        return null;
    }
}