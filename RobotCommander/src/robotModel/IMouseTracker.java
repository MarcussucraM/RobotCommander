package robotModel;

import robotView.IRobotView;

public interface IMouseTracker {
	//keep track of whose observing the tracker
	public void addObserver(IRobotView view);
	public void removeObserver(IRobotView view);
	public void notifyObservers();
	
	//for controller to start and stop the tracker thread
	public void startMouseTracker();
	public void stopMouseTracker();
	
	//get state for view on update
	public String getMousePos();
	public boolean isStarted();
}
