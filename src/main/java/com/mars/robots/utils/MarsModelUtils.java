package com.mars.robots.utils;

/*
 * Utility class for general functionality associated to the Mars Grid Model
 * */
public final class MarsModelUtils {
	private static int MAX_GRID_CoOrds = 49;
	
	public MarsModelUtils() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Check for correctness of the created Grid
	 * Check that atleast 2 co ordinates (x,y) are provided
	 * Check that the 2 co ordinates (x,y) are integers
	 * Check that Grid maximum size is permitted for the model
	 * */
	public static boolean validGridSize(final String[] maxGridPoints) {
		if (maxGridPoints.length < 2) {
			throw new RuntimeException("not validGridSize, x or y co ordinates missing");
		}
		int gridMaxXSize, gridMaxYSize = -1;
		try {
			gridMaxXSize = Integer.valueOf(maxGridPoints[0]);
			gridMaxYSize = Integer.valueOf(maxGridPoints[1]);
		} catch (NumberFormatException nfe) {
			throw new RuntimeException("not validGridSize. x and/or y non numericals x = " + 
					maxGridPoints[0] + ", y = " + maxGridPoints[1]);
		}
		if (gridMaxXSize > MAX_GRID_CoOrds || gridMaxYSize > MAX_GRID_CoOrds || gridMaxXSize < 0 || gridMaxYSize < 0) {
			throw new RuntimeException("Grid size outside max/min limit " + gridMaxXSize + "," + gridMaxYSize);
		}
		return true;
	}	
}