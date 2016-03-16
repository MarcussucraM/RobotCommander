package robotModel;

import java.util.Map;
import java.util.Set;

import robotView.IRobotView;
import java.util.List;
import java.io.IOException;
import java.util.ArrayList;

public class CommandModel implements ICommandModel{
	private ICommandEditor editor;
	private Map<String,String> commandMap;
	private ICommandFileHelper fileHelper;
	private String currentCommand;
	private List<robotView.IRobotView> observers;
	
	//init all models to be used.
	public CommandModel(){
		observers = new ArrayList<robotView.IRobotView>();
		editor = new CommandEditor();
		fileHelper = new CommandFileHelper();
		try {
			setCommandMap(fileHelper.loadFile());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addObserver(IRobotView o) {
		observers.add(o);		
	}
	
	public void removeObserver(IRobotView o) {
		observers.remove(o);
	}
	
	public void notifyObservers() {
		for(IRobotView r : observers){
			r.update();
		}
	}
	
	public void setCommandMap(Map<String,String> commandMap){
		this.commandMap = commandMap;
	}
	
	public void setCurrentCommand(String currentCommand){
		this.currentCommand = currentCommand;
		notifyObservers();
	}
	
	public String getCurrentCommand() {
		return currentCommand;
	}
	
	public String getCurrentCommandString() {
		if(commandMap != null && currentCommand != null){
			return commandMap.get(currentCommand);
		}
		return null;
	}
	
	public Set<String> getCommands(){
		return commandMap.keySet();
	}
	
	public boolean addNewCommand(String newCommand){
		return editor.addCommand(commandMap, newCommand);
	}
	
	public void addToCommandString(String newCommand){
		editor.addToCommandString(commandMap, currentCommand, newCommand);
		notifyObservers();
	}
	
	public void deleteFromCommandString(){
		editor.deleteFromCommandString(commandMap, currentCommand);
		notifyObservers();
	}
	
	public void save(){
		try {
			fileHelper.saveFile(commandMap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
}
