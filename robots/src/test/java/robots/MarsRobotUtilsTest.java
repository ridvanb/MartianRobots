package robots;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.mars.robots.exceptions.InvalidInstructionException;
import com.mars.robots.model.MarsModel;
import com.mars.robots.model.Point;
import com.mars.robots.model.Robot;
import com.mars.robots.utils.Instruction;
import com.mars.robots.utils.MarsRobotUtils;
import com.mars.robots.utils.Orientation;
import com.mars.robots.utils.RobotUtils;

/**
 * Unit test for MarsRobotUtils
 */
public class MarsRobotUtilsTest {

	public MarsRobotUtilsTest() {
		// TODO Auto-generated constructor stub
	}

	@Rule
	public final ExpectedException exceptionRule = ExpectedException.none();

	@BeforeClass
	public static void setupOnce() {
		//System.out.println("Inside setupOnce");
	}

	@Before
	public void setupForEachCase() {
	}    

	@Test
	  public void checkFileIO_test1() {
		final String fileNameWithPath = "c:\\abc\\mars.txt";
		
		exceptionRule.expect(IllegalArgumentException.class);
		exceptionRule.expectMessage("File " + fileNameWithPath + " not found");	    
	    MarsRobotUtils.readInstructionsFromFile(fileNameWithPath);
	  }
	
	@Test
	  public void checkFileIO_test2() {
		final String fileNameWithPath = "src/test/resources/test_input1.txt";
			    
		List<String> list = MarsRobotUtils.readInstructionsFromFile(fileNameWithPath);
		assertEquals(list.size(), 7);
	  }	
	
	@Test
	public void checkValidInstructionsAtEnd_test1() {
		String instructionLine = "RLFRLFRLFA";

		exceptionRule.expect(InvalidInstructionException.class);
		exceptionRule.expectMessage("Invalid instruction character: A");
		MarsRobotUtils.validInstructions(instructionLine);
	}

	@Test
	public void checkValidInstructionsAtBegin_test2() {
		String instructionLine = "BRLFRLFRLF";

		exceptionRule.expect(InvalidInstructionException.class);
		exceptionRule.expectMessage("Invalid instruction character: B");
		MarsRobotUtils.validInstructions(instructionLine);
	}

	@Test
	public void checkValidInstructionsInBetween_test2() {
		String instructionLine = "RLFGRLFRLF";

		exceptionRule.expect(InvalidInstructionException.class);
		exceptionRule.expectMessage("Invalid instruction character: G");
		MarsRobotUtils.validInstructions(instructionLine);
	}	

	@Test
	public void checkValidInstructionsLength100_test2() {
		String instructionLine = "RLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFR";

		exceptionRule.expect(InvalidInstructionException.class);
		exceptionRule.expectMessage("Instructions size not valid: " + instructionLine.length());
		MarsRobotUtils.validInstructions(instructionLine);
	}

	@Test
	public void checkValidInstructionsLength99_test3() {
		String instructionLine = "RLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLF";
		boolean isValid = MarsRobotUtils.validInstructions(instructionLine);

		assertTrue(isValid);
	}	

	@Test
	public void checkValidInstructionsLength1_test3() {
		String instructionLine = "R";
		boolean isValid = MarsRobotUtils.validInstructions(instructionLine);

		assertTrue(isValid);
	}

	@Test
	public void checkValidInstructionsMixCase_test3() {
		String instructionLine = "RFRfrfrlL";
		boolean isValid = MarsRobotUtils.validInstructions(instructionLine);

		assertTrue(isValid);
	}
	
	@Test
	public void checkValidInstructionsLength0_test3() {
		String instructionLine = "";
		boolean isValid = MarsRobotUtils.validInstructions(instructionLine);

		assertTrue(isValid);
	}	

	@Test
	public void checkValidInstructionsLength98_test3() {
		String instructionLine = "RLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRLFRL";
		boolean isValid = MarsRobotUtils.validInstructions(instructionLine);

		assertTrue(isValid);
	}

	@Test
	public void checkValidInstructionSetMalformed_test1() {
		String fileNameWithPath = "src/test/resources/test_input3.txt";
		List<String> instructionsLines = MarsRobotUtils.readInstructionsFromFile(fileNameWithPath);

		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("not validInstructionSet. Input Instruction Set is malformed");
		MarsRobotUtils.validInstructionSet(instructionsLines);
	}
	
	@Test
	public void checkValidInstructionSetMalformed_test2() {
		String fileNameWithPath = "src/test/resources/test_input4.txt";
		List<String> instructionsLines = MarsRobotUtils.readInstructionsFromFile(fileNameWithPath);

		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("not validInstructionSet. Input Instruction Set is malformed");
		MarsRobotUtils.validInstructionSet(instructionsLines);
	}
	
	@Test
	public void checkRobots_test1() {
		String initialRobotState = "1 1 E";
		String instructionLine = "RFRFRFRF";
		
		Point gridMaxSize = new Point(5, 3);
		Point startCoordinates = new Point(1, 1);
		MarsModel model = new MarsModel();
		model.setGridMaxSize(gridMaxSize);
		model.createRobot(initialRobotState,instructionLine);
		Robot r1 = model.getRobot();
		List<Instruction> instructions = RobotUtils.getIndividualInstructionList(instructionLine);
		
		//Assert
		assertEquals(r1.getCurrentLocation().getX(), startCoordinates.getX());
		assertEquals(r1.getCurrentLocation().getY(), startCoordinates.getY());
		assertEquals(r1.getOrientation(), Orientation.E);
		assertEquals(r1.getInstructions(), instructions);		
	}	
	
	@Test
	public void checkRobots_test2() {
		String initialRobotState = "1 1 E";
		String instructionLine = "RFRFRFRF";
		
		Point gridMaxSize = new Point(5, 3);
		Point startCoordinates = new Point(1, 1);
		MarsModel model = new MarsModel();
		model.setGridMaxSize(gridMaxSize);
		model.createRobot(initialRobotState,instructionLine);
		Robot r1 = model.getRobot();
		List<Instruction> instructions = RobotUtils.getIndividualInstructionList(instructionLine);		
		
		//Assert
		assertEquals(r1.getCurrentLocation().getX(), startCoordinates.getX());
		assertEquals(r1.getCurrentLocation().getY(), startCoordinates.getY());
		assertEquals(r1.getOrientation(), Orientation.E);
		assertEquals(r1.getInstructions(), instructions);
		assertFalse(r1.isLost());
		assertEquals(model.getRobotDangerMarks().size(),0);
		
		model.processInstructions();
		
		Point prevCoordinates = new Point(0, 1);
		assertEquals(r1.getCurrentLocation().getX(), startCoordinates.getX());
		assertEquals(r1.getCurrentLocation().getY(), startCoordinates.getY());
		assertEquals(r1.getPreviousLocation().getX(), prevCoordinates.getX());
		assertEquals(r1.getPreviousLocation().getY(), prevCoordinates.getY());		
		assertEquals(r1.getOrientation(), Orientation.E);		
		assertEquals(r1.getInstructions(), new ArrayList<Instruction>());
		assertFalse(r1.isLost());		
	}		

	@Test
	public void checkRobots_test3() {
		String initialRobotState = "3 2 N";
		String instructionLine = "FRRFLLFFRRFLL";
		
		Point gridMaxSize = new Point(5, 3);
		Point startCoordinates = new Point(3, 2);
		MarsModel model = new MarsModel();
		model.setGridMaxSize(gridMaxSize);
		model.createRobot(initialRobotState,instructionLine);
		Robot r2 = model.getRobot();
		List<Instruction> instructions = RobotUtils.getIndividualInstructionList(instructionLine);		
		
		//Assert
		assertEquals(r2.getCurrentLocation().getX(), startCoordinates.getX());
		assertEquals(r2.getCurrentLocation().getY(), startCoordinates.getY());
		assertEquals(r2.getOrientation(), Orientation.N);
		assertEquals(r2.getInstructions(), instructions);
		assertFalse(r2.isLost());
		assertEquals(model.getRobotDangerMarks().size(),0);
		
		model.processInstructions();
		Point currentCoordinates = new Point(3, 4);
		Point prevCoordinates = new Point(3, 3);
		Instruction[] array = {Instruction.R,Instruction.R,Instruction.F,Instruction.L,Instruction.L};
		Point dangerPoint = model.getRobotDangerMarks().iterator().next();
		
		assertEquals(r2.getCurrentLocation().getX(), currentCoordinates.getX());
		assertEquals(r2.getCurrentLocation().getY(), currentCoordinates.getY());
		assertEquals(r2.getPreviousLocation().getX(), prevCoordinates.getX());
		assertEquals(r2.getPreviousLocation().getY(), prevCoordinates.getY());		
		assertEquals(r2.getOrientation(), Orientation.N);		
		assertEquals(r2.getInstructions(), Arrays.asList(array));
		assertTrue(r2.isLost());
		assertEquals(r2.getPreviousLocation().getX(), dangerPoint.getX());
		assertEquals(r2.getPreviousLocation().getY(), dangerPoint.getY());		
		
	}
	
	@Test
	public void checkRobots_test4() {
		String initialRobotState = "0 3 W";
		String instructionLine = "LLFFFLFLFL";
		
		Point gridMaxSize = new Point(5, 3);
		Point startCoordinates = new Point(0, 3);
		MarsModel model = new MarsModel();
		model.setGridMaxSize(gridMaxSize);
		model.createRobot(initialRobotState,instructionLine);
		Robot r2 = model.getRobot();
		List<Instruction> instructions = RobotUtils.getIndividualInstructionList(instructionLine);
		Point dangerPoint = new Point(3, 3);
		model.addRobotDangerMark(dangerPoint);		
		
		//Assert
		assertEquals(r2.getCurrentLocation().getX(), startCoordinates.getX());
		assertEquals(r2.getCurrentLocation().getY(), startCoordinates.getY());
		assertEquals(r2.getOrientation(), Orientation.W);
		assertEquals(r2.getInstructions(), instructions);
		assertFalse(r2.isLost());
		assertEquals(model.getRobotDangerMarks().size(),1);
		
		model.processInstructions();
		Point currentCoordinates = new Point(2, 3);
		Point prevCoordinates = new Point(3, 3);
		//Point dangerPoint = model.getRobotDangerMarks().iterator().next();
		
		assertEquals(r2.getCurrentLocation().getX(), currentCoordinates.getX());
		assertEquals(r2.getCurrentLocation().getY(), currentCoordinates.getY());
		assertEquals(r2.getPreviousLocation().getX(), prevCoordinates.getX());
		assertEquals(r2.getPreviousLocation().getY(), prevCoordinates.getY());		
		assertEquals(r2.getOrientation(), Orientation.S);		
		assertEquals(r2.getInstructions(), new ArrayList<Instruction>());
		assertFalse(r2.isLost());
		assertEquals(r2.getPreviousLocation().getX(), dangerPoint.getX());
		assertEquals(r2.getPreviousLocation().getY(), dangerPoint.getY());		
		
	}
	
	
	@Test
	public void checkRobots_test5() {
		String initialRobotState = "0 0 N";
		String instructionLine = "RFFR";
		
		Point gridMaxSize = new Point(5, 3);
		Point startCoordinates = new Point(0, 0);
		MarsModel model = new MarsModel();
		model.setGridMaxSize(gridMaxSize);
		model.createRobot(initialRobotState,instructionLine);
		Robot r2 = model.getRobot();
		List<Instruction> instructions = RobotUtils.getIndividualInstructionList(instructionLine);
		Point dangerPoint = new Point(3, 3);
		model.addRobotDangerMark(dangerPoint);
		model.addRobotDangerMark(new Point(2, 0));		
		
		//Assert
		assertEquals(r2.getCurrentLocation().getX(), startCoordinates.getX());
		assertEquals(r2.getCurrentLocation().getY(), startCoordinates.getY());
		assertEquals(r2.getOrientation(), Orientation.N);
		assertEquals(r2.getInstructions(), instructions);
		assertFalse(r2.isLost());
		assertEquals(model.getRobotDangerMarks().size(),2);
		
		model.processInstructions();
		Point currentCoordinates = new Point(2, 0);
		Point prevCoordinates = new Point(1, 0);
		//Point dangerPoint = model.getRobotDangerMarks().iterator().next();
		
		assertEquals(r2.getCurrentLocation().getX(), currentCoordinates.getX());
		assertEquals(r2.getCurrentLocation().getY(), currentCoordinates.getY());
		assertEquals(r2.getPreviousLocation().getX(), prevCoordinates.getX());
		assertEquals(r2.getPreviousLocation().getY(), prevCoordinates.getY());		
		assertEquals(r2.getOrientation(), Orientation.S);		
		assertEquals(r2.getInstructions(), new ArrayList<Instruction>());
		assertFalse(r2.isLost());
	}
	
	@After
	public void destroyForEachCase() {	
		//System.out.println("Inside Destroy");
	}

	@AfterClass
	public static void destroyOnce() {
		//System.out.println("Inside destroyOnce");
	}
}

