package robots;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.mars.robots.model.Point;
import com.mars.robots.service.MarsRobotApp;
import com.mars.robots.utils.MarsRobotUtils;

/**
 * Unit tests for Mars Grid Model.
 */
public class MarsModelTest {

	public MarsModelTest() {
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
	public void checkValidGridSizeCorrect_49_49() {
		String maxCo = "49 49";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		
		MarsRobotApp app = new MarsRobotApp();
		app.getGridModel().setupGrid(maxCo);
		
		Point expectedGridMaxSize = new Point(maxGridPoints[0], maxGridPoints[1]);      

		//Assert
		assertEquals(expectedGridMaxSize.getX(), app.getGridModel().getGridMaxSize().getX());
		assertEquals(expectedGridMaxSize.getY(), app.getGridModel().getGridMaxSize().getY());		
	}
	
	@Test
	public void checkValidGridSizeCorrect_0_49() {
		String maxCo = "0 49";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		
		MarsRobotApp app = new MarsRobotApp();
		app.getGridModel().setupGrid(maxCo);
		
		Point expectedGridMaxSize = new Point(maxGridPoints[0], maxGridPoints[1]);      

		//Assert
		assertEquals(expectedGridMaxSize.getX(), app.getGridModel().getGridMaxSize().getX());
		assertEquals(expectedGridMaxSize.getY(), app.getGridModel().getGridMaxSize().getY());			
	}
	
	@Test
	public void checkValidGridSizeCorrect_49_0() {
		String maxCo = "49 0";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		
		MarsRobotApp app = new MarsRobotApp();
		app.getGridModel().setupGrid(maxCo);
		
		Point expectedGridMaxSize = new Point(maxGridPoints[0], maxGridPoints[1]);      

		//Assert
		assertEquals(expectedGridMaxSize.getX(), app.getGridModel().getGridMaxSize().getX());
		assertEquals(expectedGridMaxSize.getY(), app.getGridModel().getGridMaxSize().getY());			
	}
	
	@Test
	public void checkValidGridSizeCorrect_0_0() {
		String maxCo = "0 0";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		
		MarsRobotApp app = new MarsRobotApp();
		app.getGridModel().setupGrid(maxCo);
		
		Point expectedGridMaxSize = new Point(maxGridPoints[0], maxGridPoints[1]);      

		//Assert
		assertEquals(expectedGridMaxSize.getX(), app.getGridModel().getGridMaxSize().getX());
		assertEquals(expectedGridMaxSize.getY(), app.getGridModel().getGridMaxSize().getY());			
	}	

	@Test
	public void checkValidGridSizeIncorrect_Only_50() {
		String maxCo = "50 ";		
		MarsRobotApp app = new MarsRobotApp();				
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("not validGridSize, x or y co ordinates missing");
		app.getGridModel().setupGrid(maxCo);		
	}
	
	@Test
	public void checkValidGridSizeIncorrect_50_49() {
		String maxCo = "50 49";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		
		MarsRobotApp app = new MarsRobotApp();				
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("Grid size outside max/min limit " + maxGridPoints[0] + "," + maxGridPoints[1]);
		app.getGridModel().setupGrid(maxCo);		
	}
	
	@Test
	public void checkValidGridSizeIncorrect_49_50() {
		String maxCo = "49 50";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		MarsRobotApp app = new MarsRobotApp();
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("Grid size outside max/min limit " + maxGridPoints[0] + "," + maxGridPoints[1]);
		app.getGridModel().setupGrid(maxCo);		
	}
	
	@Test
	public void checkValidGridSizeIncorrect_50_50() {
		String maxCo = "50 50";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		MarsRobotApp app = new MarsRobotApp();
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("Grid size outside max/min limit " + maxGridPoints[0] + "," + maxGridPoints[1]);
		app.getGridModel().setupGrid(maxCo);	
	}
	@Test
	public void checkValidGridSizeIncorrect_0_neg1() {
		String maxCo = "0 -1";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		MarsRobotApp app = new MarsRobotApp();
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("Grid size outside max/min limit " + maxGridPoints[0] + "," + maxGridPoints[1]);
		app.getGridModel().setupGrid(maxCo);		
	}
	@Test
	public void checkValidGridSizeIncorrect_neg1_0() {
		String maxCo = "-1 0";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		MarsRobotApp app = new MarsRobotApp();
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("Grid size outside max/min limit " + maxGridPoints[0] + "," + maxGridPoints[1]);
		app.getGridModel().setupGrid(maxCo);		
	}
	@Test
	public void checkValidGridSizeIncorrect_neg1_neg1() {
		String maxCo = "-1 -1";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		MarsRobotApp app = new MarsRobotApp();
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("Grid size outside max/min limit " + maxGridPoints[0] + "," + maxGridPoints[1]);
		app.getGridModel().setupGrid(maxCo);		
	}	
	
	@Test
	public void checkGridMaxSizeCorrect_test1() {
		String fileNameWithPath = "src/test/resources/test_input1.txt";
		List<String> instructionsLines = MarsRobotUtils.readInstructionsFromFile(fileNameWithPath);

		MarsRobotApp app = new MarsRobotApp();
		app.getGridModel().setupGrid(instructionsLines.get(0));		
		Point expectedGridMaxSize = new Point(5, 3);      

		//Assert
		assertEquals(expectedGridMaxSize.getX(), app.getGridModel().getGridMaxSize().getX());
		assertEquals(expectedGridMaxSize.getY(), app.getGridModel().getGridMaxSize().getY());        
	}

	@Test
	public void checkGridMaxSizeIncorrectY_test2() {
		String fileNameWithPath = "src/test/resources/test_input1.txt";
		List<String> instructionsLines = MarsRobotUtils.readInstructionsFromFile(fileNameWithPath);

		MarsRobotApp app = new MarsRobotApp();
		app.getGridModel().setupGrid(instructionsLines.get(0));		
		Point expectedGridMaxSize = new Point(5, 4);      

		//Assert
		assertEquals(expectedGridMaxSize.getX(), app.getGridModel().getGridMaxSize().getX());
		assertNotEquals(expectedGridMaxSize.getY(), app.getGridModel().getGridMaxSize().getY());        
	}

	@Test
	public void checkGridMaxSizeIncorrectX_test3() {
		String fileNameWithPath = "src/test/resources/test_input1.txt";
		List<String> instructionsLines = MarsRobotUtils.readInstructionsFromFile(fileNameWithPath);

		MarsRobotApp app = new MarsRobotApp();
		app.getGridModel().setupGrid(instructionsLines.get(0));		
		Point expectedGridMaxSize = new Point(6, 3);      

		//Assert
		assertNotEquals(expectedGridMaxSize.getX(), app.getGridModel().getGridMaxSize().getX());
		assertEquals(expectedGridMaxSize.getY(), app.getGridModel().getGridMaxSize().getY());        
	}

	@Test
	public void checkGridMaxSizeIncorrectXY_test4() {
		String fileNameWithPath = "src/test/resources/test_input1.txt";
		List<String> instructionsLines = MarsRobotUtils.readInstructionsFromFile(fileNameWithPath);

		MarsRobotApp app = new MarsRobotApp();
		app.getGridModel().setupGrid(instructionsLines.get(0));		
		Point expectedGridMaxSize = new Point(6, 5);      

		//Assert
		assertNotEquals(expectedGridMaxSize.getX(), app.getGridModel().getGridMaxSize().getX());
		assertNotEquals(expectedGridMaxSize.getY(), app.getGridModel().getGridMaxSize().getY());        
	}
	
	@Test
	public void checkValidGridSizeNaNIncorrect_5a_5() {
		String maxCo = "5a 5";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		
		MarsRobotApp app = new MarsRobotApp();
				
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("not validGridSize. x and/or y non numericals x = " + 
				maxGridPoints[0] + ", y = " + maxGridPoints[1]);
		app.getGridModel().setupGrid(maxCo);		
	}

	@Test
	public void checkValidGridSizeNaNIncorrect_5_5a() {
		String maxCo = "5 a5";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		
		MarsRobotApp app = new MarsRobotApp();
				
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("not validGridSize. x and/or y non numericals x = " + 
				maxGridPoints[0] + ", y = " + maxGridPoints[1]);
		app.getGridModel().setupGrid(maxCo);		
	}
	
	@Test
	public void checkValidGridSizeNaNIncorrect_5f_a5() {
		String maxCo = "5f a5";
		String delimiter = " ";
		String[] maxGridPoints = maxCo.split(delimiter);
		
		MarsRobotApp app = new MarsRobotApp();
				
		
		exceptionRule.expect(RuntimeException.class);
		exceptionRule.expectMessage("not validGridSize. x and/or y non numericals x = " + 
				maxGridPoints[0] + ", y = " + maxGridPoints[1]);
		app.getGridModel().setupGrid(maxCo);		
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


