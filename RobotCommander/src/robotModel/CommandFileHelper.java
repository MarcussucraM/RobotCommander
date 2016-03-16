package robotModel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import robotCommanderConstants.CommandConstants;

public class CommandFileHelper implements ICommandFileHelper
{
	//if the file is empty this breaks
	public Map<String, String> loadFile() throws IOException {
		Map<String,String> commands = new HashMap<String,String>();
		File commandFile = new File("commands.txt");
		if(!commandFile.exists()) commandFile.createNewFile();
		if(commandFile.length() <= 4) return commands;
		else{
			//get the contents from the file.
			FileReader fr = new FileReader(commandFile);
			BufferedReader br = new BufferedReader(fr);
			
			String line = "";
			while((line = br.readLine()) != null){
				String[] keyAndValue = line.split(CommandConstants.keyValueSeperator);
				commands.put(keyAndValue[0], keyAndValue[1]);
			}
			
			br.close();
		}
		
		return commands;
	}

	@Override
	public void saveFile(Map<String,String> commandMap) throws IOException {
		File commandFile = new File("commands.txt");
		FileWriter fw = new FileWriter(commandFile);
		PrintWriter pw = new PrintWriter(fw);
		
		String[] keys = commandMap.keySet().toArray(new String[commandMap.keySet().size()]);
		String[] values = commandMap.values().toArray(new String[commandMap.values().size()]);
		
		for(int i = 0; i < keys.length; i++){
			String line = keys[i] + CommandConstants.keyValueSeperator + values[i];
			//we dont want to save any command that doesn't have a command string attached to it.
			//this causes load file errors 
			if(values[i].length() != 0)
				pw.println(line);
		}
		
		pw.close();
	}


	
	
	
}
