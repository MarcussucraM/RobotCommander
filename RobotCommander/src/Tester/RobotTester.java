package Tester;
import java.awt.AWTException;
import java.io.IOException;

import robotCommander.*;
import robotModel.*;
import java.util.Map;
import java.util.HashMap;

/*
* Tests various components of the system
* Also the entry point to the application
*/
public class RobotTester {
	public static void main(String[] args){
		//testRobotMouseTracker();
		testGUI();
		//testFileHelper();
		//testRobot();
	}
	
	//waits 5 seconds for you to open up notepad and then types hello world
	public static void testRobot(){
		MyRobot robot;
		try {
			robot = new MyRobot();
			String commandString = "Wait(5000)-Type(Hello World)-";
			robot.setCommandString(commandString);
			robot.start();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Tests to make sure the mouse tracker works as intended
	public static void testRobotMouseTracker(){
		MouseTracker mouseTracker = new MouseTracker();
		mouseTracker.startMouseTracker();
	}
	
	//makes sure that saving command map works correctly
	public static void testFileHelper(){
		CommandFileHelper c = new CommandFileHelper();
		Map<String,String> commandMap = new HashMap<String,String>();
		commandMap.put("Command1", "Click-Click-Click-");
		commandMap.put("Command2", "DoubleClick-Type(HelloWorld)-");

		try {
			c.saveFile(commandMap);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Starts the Actual program
	public static void testGUI(){
		try {
			ICommandModel model = new CommandModel();
			IMouseTracker mouseTracker = new MouseTracker();
			IMyRobot robot = new MyRobot();
			IRobotController controller = new RobotController(model, mouseTracker, robot);
		} catch (AWTException e) {
			e.printStackTrace();
		}	
	}
}
