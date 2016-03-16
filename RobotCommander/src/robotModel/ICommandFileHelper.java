package robotModel;

import java.util.Map;
import java.io.IOException;

public interface ICommandFileHelper{
	//creates the command map from a text file
	public Map<String,String> loadFile() throws IOException ;
	
	//saves the command map to a text file
	public void saveFile(Map<String,String> commandMap) throws IOException;
	
	
}
