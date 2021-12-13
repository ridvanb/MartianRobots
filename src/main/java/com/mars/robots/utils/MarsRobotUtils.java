package com.mars.robots.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mars.robots.exceptions.InvalidInstructionException;
import com.mars.robots.model.Point;

/*
 * Utility class for general functionality associated to the application
 * */

public final class MarsRobotUtils {
	private static int MAX_INSTRUCTION_SIZE = 99;

	/*
	 * Read the lines from the input file that are necessary for the model to work
	 * */
	public static List<String> readInstructionsFromFile(final String fileNameWithPath) {
		Path path = Paths.get(fileNameWithPath);
		List<String> instructionsList = new ArrayList<>();
		try (Stream<String> stream = Files.lines(path).filter(s -> !s.isEmpty())) {
			instructionsList = stream.collect(Collectors.toList());
		} catch (IOException e) {
			//e.printStackTrace();
			throw new IllegalArgumentException("File " + fileNameWithPath + " not found");
		}
		return instructionsList;

	}
	
	@SuppressWarnings("unlikely-arg-type")
	public static boolean validInstructions(final String instructionLine) {
		//System.out.println("instructionLine = " + instructionLine.length());
		if (instructionLine.length() > MAX_INSTRUCTION_SIZE)
			throw new InvalidInstructionException(instructionLine);

		for (Character c: instructionLine.toCharArray()) {
			String strChar = new Character(Character.toUpperCase(c)).toString();
			
			if (strChar.equals(Instruction.L.toString()) || 
					strChar.equals(Instruction.R.toString()) || 
					strChar.equals(Instruction.F.toString())) {
				continue; 
			} else
				throw new InvalidInstructionException(c);
		}
		return true;
	}
	
	public static boolean validInstructionSet(final List<String> instructionsLines) {
		if (!(instructionsLines.size() % 2 != 0 && instructionsLines.size() > 2)) {
			// exception
			//System.out.println("x and y tokens size = " + tokens.length);
			throw new RuntimeException("not validInstructionSet. Input Instruction Set is malformed");
		}		
		return true;
	}
	
	public static boolean validRobotStartPoint(final String[] instructionLineMembers, final Point gridMaxSize) {
		if (instructionLineMembers.length < 3) { // includes facing as well
			// exception
			//System.out.println("x and y tokens size = " + tokens.length);
			throw new RuntimeException("not validGridSize x and y tokens size " + instructionLineMembers.length);
		} 
		int startX = Integer.parseInt(instructionLineMembers[0]);
		int startY = Integer.parseInt(instructionLineMembers[1]);
		Point coordinates = new Point(startX, startY);

		if (isOutsideGrid(coordinates, gridMaxSize)) {
			// exception
			//System.out.println("Robot x and y is outside Grid = " + tokens.length);
			throw new RuntimeException("Robot x and y is outside Grid " + coordinates.getX() + "," + coordinates.getY());
		}
		return true;
	}
	
	public static boolean isOutsideGrid(final Point coordinates, final Point gridMaxSize) {
		return (coordinates.getX() > gridMaxSize.getX() || coordinates.getY() > gridMaxSize.getY() || coordinates.getX() < 0 || coordinates.getY() < 0);
	}	
}
