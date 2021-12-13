package com.mars.robots.utils;

import java.util.ArrayList;
import java.util.List;

/*
 * Utility class for general functionality associated to the Robot
 * */
public class RobotUtils {

	public RobotUtils() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * Construct a list of individual instructions, derived from the instructions given in the input file
	 * */
    public static List<Instruction> getIndividualInstructionList(final String instructionLine) {
    	List<Instruction> individualInstructionList = new ArrayList<Instruction>();
        for (char c : instructionLine.toCharArray()) {
            Instruction i = Instruction.valueOf(Character.toString(c));
            individualInstructionList.add(i);
        }
        return individualInstructionList;
    }
}
