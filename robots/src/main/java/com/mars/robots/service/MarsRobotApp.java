package com.mars.robots.service;

import java.util.List;

import com.mars.robots.model.MarsModel;
import com.mars.robots.utils.MarsRobotUtils;

/*
 * The main app
 * */
public class MarsRobotApp {
	private MarsModel gridModel;

	public MarsRobotApp() {
		this.gridModel = new MarsModel();
	}

	public static void main(String[] args) {

		MarsRobotApp app = new MarsRobotApp();
		app.usage(args);		
		final String fileNameWithPath = args[0];
		
		List<String> instructionsLines = MarsRobotUtils.readInstructionsFromFile(fileNameWithPath);
		MarsRobotUtils.validInstructionSet(instructionsLines);
		//System.out.println(instructionsLines);

		app.gridModel.setupGrid(instructionsLines.get(0));
		// for loop
		for (int i=1; i < instructionsLines.size() -1; ) {
			String initialRobotState = instructionsLines.get(i);
			String instructionLine = instructionsLines.get(i+1);

			app.gridModel.createRobot(initialRobotState,instructionLine);
			app.gridModel.processInstructions();
			System.out.println(app.gridModel.getRobot());
			//System.out.println(app.getGridModel().getRobot().getResult());			
			i += 2;
		}
	}

	public MarsModel getGridModel() {
		return gridModel;
	}

	public void setGridModel(MarsModel gridModel) {
		this.gridModel = gridModel;
	}
	
	/*
	 * Usage of invoking this application via command line
	 * */
	private void usage(String[] args) {
		if (args.length < 1 || args[0] == null) {
			System.out.println("Usage : java -jar <jarFile> <filePath> OR java com.mars.robots.service.MarsRobotApp <inputFilePath> " +
				"\ne.g java -jar robots-0.0.1-SNAPSHOT.jar c:/mars.txt OR java com.mars.robots.service.MarsRobotApp c:/mars.txt");
			System.exit(0);
		}		
	}	
}