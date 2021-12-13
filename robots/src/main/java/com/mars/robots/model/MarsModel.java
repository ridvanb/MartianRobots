package com.mars.robots.model;

import java.util.HashSet;
import java.util.Set;

import com.mars.robots.utils.MarsModelUtils;
import com.mars.robots.utils.MarsRobotUtils;
import com.mars.robots.utils.Orientation;

/*
 * This is the Grid on which all activities will happen
 * */
public class MarsModel {	
	private Point gridMaxSize;
	private Robot robot;
	private Set<Point> robotDangerMarks = new HashSet<>();

	public MarsModel() {		
	}

	/*
	 * The grid layout is set with valid parmaters
	 * */
	public void setupGrid(final String maxGridPoint) {
		String delimiter = " ";
		String[] maxGridPoints = maxGridPoint.split(delimiter);
		
		if (MarsModelUtils.validGridSize(maxGridPoints)) {
			gridMaxSize = new Point(maxGridPoints[0],maxGridPoints[1]);
			//System.out.println("setupGrid done");
		}
	}	
	
	/*
	 * A valid fully prepared robot is created to execute the instructions
	 * */	
	public void createRobot(final String initialState, final String instructionLine) {
		String delimiter = " ";
		String[] initialStatePoint = initialState.split(delimiter);

		// Basic checks for validity of start point and instructions
		MarsRobotUtils.validRobotStartPoint(initialStatePoint,gridMaxSize);
		MarsRobotUtils.validInstructions(instructionLine);
		
		int startX = Integer.parseInt(initialStatePoint[0]);
		int startY = Integer.parseInt(initialStatePoint[1]);

		Point startCoordinates = new Point(startX, startY);
		Orientation orientation = Orientation.valueOf(initialStatePoint[2]);

		Robot robot = new Robot(startCoordinates, orientation);			
		robot.addInstructions(instructionLine);		
		this.robot = robot;
	}

	public Robot getRobot() {
		return robot;
	}

	/*
	 * Execution of instructions ensuring all danger points are avoided
	 * in addition the robot leaves a scent of a danger mark in case of issues.
	 * */
	public void processInstructions() {

		while (robot.hasInstructions()) {
			if (isOnDangerMarkLocation(robot.getCurrentLocation()) && MarsRobotUtils.isOutsideGrid(robot.getNextInstructionExecutionPoint(),gridMaxSize)) {
				//System.out.println("skipping instruction = " + robot.getInstructions().get(0));
				robot.skipInstructionExecution();
			} else {
				robot.executeInstruction();
				if (MarsRobotUtils.isOutsideGrid(robot.getCurrentLocation(),gridMaxSize)) {
					robot.setLost(true);
					addRobotDangerMark(robot.getPreviousLocation());
					break;				
				}
			}
		}
	}
	
	/*
	 * Check if robot is currently on any recorded danger marks
	 * */
	private boolean isOnDangerMarkLocation(final Point coordinates) {			
		Point dangerPoint = robotDangerMarks.stream()
				.filter(point -> (coordinates.getX() == point.getX() && coordinates.getY() == point.getY()))
				.findAny()
				.orElse(null);
		boolean onDangerMarkFlag = (dangerPoint == null) ? false : true;
		return onDangerMarkFlag;
	}

	/*
	 * Add a specific grid point as a danger mark
	 * */
	public void addRobotDangerMark(final Point robotDangerMark) {
		robotDangerMarks.add(robotDangerMark);
	}

	public Set<Point> getRobotDangerMarks() {
		return robotDangerMarks;
	}

	public void setRobotDangerMarks(Set<Point> robotDangerMarks) {
		this.robotDangerMarks = robotDangerMarks;
	}

	public Point getGridMaxSize() {
		return gridMaxSize;
	}

	public void setGridMaxSize(Point gridMaxSize) {
		this.gridMaxSize = gridMaxSize;
	}	
}