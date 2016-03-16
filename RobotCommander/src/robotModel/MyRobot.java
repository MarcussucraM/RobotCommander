package robotModel;
import java.awt.AWTException;
import java.awt.Robot;
import static java.awt.event.InputEvent.*;
import static java.awt.event.KeyEvent.*;
import robotCommanderConstants.CommandConstants;

public class MyRobot implements IMyRobot, Runnable {
	private Robot myRobot;
	private Thread robotRunner;
	private boolean running;
	private String currentCommand;
	private String[] commands;
	
	public MyRobot() throws AWTException {
		myRobot = new Robot();		
		currentCommand = "";	
		robotRunner = new Thread(this);
		running = false;
		robotRunner.start();
	}
	
	//simulates a left click
	public void click(){
		myRobot.mousePress(BUTTON1_DOWN_MASK);
		myRobot.mouseRelease(BUTTON1_DOWN_MASK);
	}
	
	//simulates a right click
	public void rightClick(){
		myRobot.mousePress(BUTTON3_DOWN_MASK);
		myRobot.mouseRelease(BUTTON3_DOWN_MASK);
	}
	
	//simulates a double click
	public void doubleClick(){
		click();
		myRobot.delay(500);
		click();
	}
	
	//recieve the string in the form type(s)
	//we need to extract s then create a character sequence which
	//will be mapped to cooresponding key events.
	public void typeString(String command) {
		int start = command.indexOf(CommandConstants.fieldTag) + CommandConstants.fieldTag.length();
		int end = command.indexOf(CommandConstants.endFieldTag);
		String contents = command.substring(start,end);
		type(contents);
	}
	
	//turns string into sequence of characters - types one at a time
	private void type(CharSequence characters) {
        int length = characters.length();
        for (int i = 0; i < length; i++) {
            char character = characters.charAt(i);
            type(character);
        }
    }

	//maps characters to cooresponding key event
	private void type(char character) {
        switch (character) {
        case 'a': doType(VK_A); break;
        case 'b': doType(VK_B); break;
        case 'c': doType(VK_C); break;
        case 'd': doType(VK_D); break;
        case 'e': doType(VK_E); break;
        case 'f': doType(VK_F); break;
        case 'g': doType(VK_G); break;
        case 'h': doType(VK_H); break;
        case 'i': doType(VK_I); break;
        case 'j': doType(VK_J); break;
        case 'k': doType(VK_K); break;
        case 'l': doType(VK_L); break;
        case 'm': doType(VK_M); break;
        case 'n': doType(VK_N); break;
        case 'o': doType(VK_O); break;
        case 'p': doType(VK_P); break;
        case 'q': doType(VK_Q); break;
        case 'r': doType(VK_R); break;
        case 's': doType(VK_S); break;
        case 't': doType(VK_T); break;
        case 'u': doType(VK_U); break;
        case 'v': doType(VK_V); break;
        case 'w': doType(VK_W); break;
        case 'x': doType(VK_X); break;
        case 'y': doType(VK_Y); break;
        case 'z': doType(VK_Z); break;
        case 'A': doType(VK_SHIFT, VK_A); break;
        case 'B': doType(VK_SHIFT, VK_B); break;
        case 'C': doType(VK_SHIFT, VK_C); break;
        case 'D': doType(VK_SHIFT, VK_D); break;
        case 'E': doType(VK_SHIFT, VK_E); break;
        case 'F': doType(VK_SHIFT, VK_F); break;
        case 'G': doType(VK_SHIFT, VK_G); break;
        case 'H': doType(VK_SHIFT, VK_H); break;
        case 'I': doType(VK_SHIFT, VK_I); break;
        case 'J': doType(VK_SHIFT, VK_J); break;
        case 'K': doType(VK_SHIFT, VK_K); break;
        case 'L': doType(VK_SHIFT, VK_L); break;
        case 'M': doType(VK_SHIFT, VK_M); break;
        case 'N': doType(VK_SHIFT, VK_N); break;
        case 'O': doType(VK_SHIFT, VK_O); break;
        case 'P': doType(VK_SHIFT, VK_P); break;
        case 'Q': doType(VK_SHIFT, VK_Q); break;
        case 'R': doType(VK_SHIFT, VK_R); break;
        case 'S': doType(VK_SHIFT, VK_S); break;
        case 'T': doType(VK_SHIFT, VK_T); break;
        case 'U': doType(VK_SHIFT, VK_U); break;
        case 'V': doType(VK_SHIFT, VK_V); break;
        case 'W': doType(VK_SHIFT, VK_W); break;
        case 'X': doType(VK_SHIFT, VK_X); break;
        case 'Y': doType(VK_SHIFT, VK_Y); break;
        case 'Z': doType(VK_SHIFT, VK_Z); break;
        case '`': doType(VK_BACK_QUOTE); break;
        case '0': doType(VK_0); break;
        case '1': doType(VK_1); break;
        case '2': doType(VK_2); break;
        case '3': doType(VK_3); break;
        case '4': doType(VK_4); break;
        case '5': doType(VK_5); break;
        case '6': doType(VK_6); break;
        case '7': doType(VK_7); break;
        case '8': doType(VK_8); break;
        case '9': doType(VK_9); break;
        case '-': doType(VK_MINUS); break;
        case '=': doType(VK_EQUALS); break;
        case '~': doType(VK_SHIFT, VK_BACK_QUOTE); break;
        case '!': doType(VK_SHIFT, VK_1); break;
        case '@': doType(VK_SHIFT, VK_2); break;
        case '#': doType(VK_SHIFT, VK_3); break;
        case '$': doType(VK_SHIFT, VK_4); break;
        case '%': doType(VK_SHIFT, VK_5); break;
        case '^': doType(VK_SHIFT, VK_6); break;
        case '&': doType(VK_SHIFT, VK_7); break;
        case '*': doType(VK_SHIFT, VK_8); break;
        case '(': doType(VK_SHIFT, VK_9); break;
        case ')': doType(VK_SHIFT, VK_0); break;
        case '_': doType(VK_SHIFT, VK_MINUS); break;
        case '+': doType(VK_SHIFT, VK_EQUALS); break;
        case '\t': doType(VK_TAB); break;
        case '\n': doType(VK_ENTER); break;
        case '[': doType(VK_OPEN_BRACKET); break;
        case ']': doType(VK_CLOSE_BRACKET); break;
        case '\\': doType(VK_BACK_SLASH); break;
        case '{': doType(VK_SHIFT, VK_OPEN_BRACKET); break;
        case '}': doType(VK_SHIFT, VK_CLOSE_BRACKET); break;
        case '|': doType(VK_SHIFT, VK_BACK_SLASH); break;
        case ';': doType(VK_SEMICOLON); break;
        case ':': doType(VK_SHIFT, VK_SEMICOLON); break;
        case '\'': doType(VK_QUOTE); break;
        case '"': doType(VK_SHIFT, VK_QUOTE); break;
        case ',': doType(VK_COMMA); break;
        case '<': doType(VK_SHIFT, VK_COMMA); break;
        case '.': doType(VK_PERIOD); break;
        case '>': doType(VK_SHIFT, VK_PERIOD); break;
        case '/': doType(VK_SLASH); break;
        case '?': doType(VK_SHIFT, VK_SLASH); break;
        case ' ': doType(VK_SPACE); break;
        default:
            throw new IllegalArgumentException("Cannot type character " + character);
        }
    }
	
	private void doType(int... keyCodes) {
        doType(keyCodes, 0, keyCodes.length);
    }

    private void doType(int[] keyCodes, int offset, int length) {
        if (length == 0) {
            return;
        }

        myRobot.keyPress(keyCodes[offset]);
        doType(keyCodes, offset + 1, length - 1);
        myRobot.keyRelease(keyCodes[offset]);
    }

	//recieve the string in the form wait(t)
    //wait a time of t milliseconds
	public void wait(String command) {
		//first find out what the value of t is
		int start = command.indexOf(CommandConstants.fieldTag) + CommandConstants.fieldTag.length();
		int end = command.indexOf(CommandConstants.endFieldTag);
		int t = Integer.valueOf(command.substring(start, end));
		myRobot.delay(t);
	}

	//recieve the string in the form moveMouse(x,y)
	//this method extracts the integers from the string
	//then calls the robot mouseMove method.
	public void moveMouseTo(String command) {
		int start = command.indexOf(CommandConstants.fieldTag) + CommandConstants.fieldTag.length();
		int end = command.indexOf(CommandConstants.endFieldTag);
		
		String[] contents = command.substring(start,end).split(",");
		
		int x = Integer.valueOf(contents[0]);
		int y = Integer.valueOf(contents[1]);
		
		myRobot.mouseMove(x, y);
	}
	
	public void start(){
		running = true;
	}
	
	public void stop(){
		running = false;
	}
	
	//were going to set this and immediately convert it into an array of commands
	public void setCommandString(String commandString){
		commands = commandString.split(CommandConstants.delimiter);
	}
	
	public String getCurrentCommand(){
		return currentCommand;
	}
	
	@Override
	//basically what this is going to do is check
	//the start of each command then map to a specific function accordingly
	public void run() {
		while(true){
			while(running){
				for(int i = 0; i < commands.length; i++){
					currentCommand = commands[i];				
					if(currentCommand.startsWith("Click")) click();
					if(currentCommand.startsWith("Double")) doubleClick();
					if(currentCommand.startsWith("Type")) typeString(currentCommand);
					if(currentCommand.startsWith("MoveMouse")) moveMouseTo(currentCommand);
					if(currentCommand.startsWith("Wait")) wait(currentCommand);
					if(currentCommand.startsWith("Quit")) running = false;
					
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}	
	}
}
