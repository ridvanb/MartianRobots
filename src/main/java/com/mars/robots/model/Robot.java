package com.mars.robots.model;

import java.util.ArrayList;
import java.util.List;

import com.mars.robots.utils.Instruction;
import com.mars.robots.utils.Orientation;

/**
 * Robot Object which will turn and move on the Grid model
 * **/
public class Robot {

	private Point currentLocation;
	private Point previousLocation;
	List<Instruction> instructions;
	private Orientation orientation;
	private boolean isLost;

	public Robot(Point location, Orientation orientation) {
		this.currentLocation = location;
		this.previousLocation = null;
		this.orientation = orientation;
		this.instructions = new ArrayList<Instruction>();
		this.isLost = false;        		
	}

	/*
	 * Get the point on the next move 
	 * */
    public Point getNextInstructionExecutionPoint() {
        return move(instructions.get(0));
    }
    
	/*
	 * Need to skip an instruction if danger point is encountered 
	 * */    
    public void skipInstructionExecution() {
    	instructions.remove(0);
    }

    /*
     * Convert String instructions into List of individual instructions
     * */
	public void addInstructions(final String instructionLine) {		
    	List<Instruction> individualInstructionList = new ArrayList<Instruction>();
        for (char c : instructionLine.toCharArray()) {
            Instruction i = Instruction.valueOf(Character.toString(c));
            individualInstructionList.add(i);
        }
		this.instructions.addAll(individualInstructionList);
	}

	/*
	 * Check for emptiness of the list of instructions
	 * */
	public boolean hasInstructions() {
		//System.out.println("instructions.size() = " + instructions.size());
		return (instructions.size() > 0);
	}  
	
	/*
	 * Execute a rotation (L or R) or a forward move. Once done that instruction is no longer needed
	 * in the list and can be removed.
	 * */
	public void executeInstruction() {
		Instruction i = instructions.get(0); // Always the head
		switch (i) {
		case F:			
			previousLocation = currentLocation;
			currentLocation = move(i);
			break;
		default:
			orientation = rotate(i);
		}
		instructions.remove(0);
	}

	/*
	 * Get the new Orientation after rotation execution
	 * */
	private Orientation rotate(final Instruction i) {
		if(i == Instruction.R) {
			return Orientation.rotateRight(orientation);
		} else {
			return Orientation.rotateLeft(orientation);
		}
	}

	/*
	 * Get the new Point after move execution
	 * */
	private Point move(final Instruction i) {
		int x = currentLocation.getX();
		int y = currentLocation.getY();

		if(i == Instruction.F) {
			switch (getOrientation()) {
			case N: return new Point(x, y + 1);
			case S: return new Point(x, y - 1);
			case E: return new Point(x + 1, y);
			case W: return new Point(x - 1, y);
			}
		}
		return currentLocation;
	}


	public Point getCurrentLocation() {
		return currentLocation;
	}

	public void setCurrentLocation(Point currentLocation) {
		this.currentLocation = currentLocation;
	}

	public Point getPreviousLocation() {
		return previousLocation;
	}

	public void setPreviousLocation(Point previousLocation) {
		this.previousLocation = previousLocation;
	}

	public Orientation getOrientation() {
		return orientation;
	}

	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}

	public boolean isLost() {
		return isLost;
	}

	public void setLost(boolean isLost) {
		this.isLost = isLost;
	}	

	public List<Instruction> getInstructions() {
		return instructions;
	}

	public void setInstructions(List<Instruction> instructions) {
		this.instructions = instructions;
	}

	public String getResult() {
		String result;
		if(isLost) {
			result = previousLocation.getX() +  " " + previousLocation.getY() +  " " + orientation.toString() +  " LOST";
		} else {
			result = currentLocation.getX() +  " " + currentLocation.getY() +  " " + orientation.toString();
		}
		return result;
	}
	
	@Override
	public String toString() {
		if(isLost) {
			return String.format("%d %d %s LOST", previousLocation.getX(), previousLocation.getY(), orientation.toString());
		} else {
			return String.format("%d %d %s", currentLocation.getX(), currentLocation.getY(), orientation.toString());
		}
	}
}