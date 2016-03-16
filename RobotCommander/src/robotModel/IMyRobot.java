package robotModel;

import robotView.IRobotView;

public interface IMyRobot {
	//the robot commands
	public void click();
	public void doubleClick();
	public void rightClick();
	public void typeString(String command);
	public void wait(String command);
	public void moveMouseTo(String command);
	
	//state manipulator methods
	public void setCommandString(String commandString);
	public String getCurrentCommand();
	public void start();
	public void stop();
}
