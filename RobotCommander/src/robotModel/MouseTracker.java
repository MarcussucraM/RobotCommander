package robotModel;
import java.awt.MouseInfo;
import java.util.List;

import robotView.IRobotView;

import java.util.ArrayList;

public class MouseTracker implements IMouseTracker, Runnable {
	private String mousePos;
	private boolean running;
	private Thread mouseTracker;
	private List<IRobotView> observers;
	
	public MouseTracker(){
		observers = new ArrayList<IRobotView>();
		running = false;
		mouseTracker = new Thread(this);
		mouseTracker.start();
	}
	
	//starts the thread loop
	public void startMouseTracker() {
		running = true;
		notifyObservers();
	}

	//stops the thread loop
	public void stopMouseTracker() {
		running = false;
		notifyObservers();
	}
	
	//gets x,y coordinates of the mouse pointer via MouseInfo
	//makes a string out of this for the view to handle
	private String makeMouseLocationString() {
		int xCoor = (int)MouseInfo.getPointerInfo().getLocation().getX();
		int yCoor = (int)MouseInfo.getPointerInfo().getLocation().getY();
		String mouseLocation = "MousePosition: (" + xCoor + ", " + yCoor + ")";
		return mouseLocation;
	}


	@Override
	public void addObserver(IRobotView view) {
		observers.add(view);
	}

	@Override
	public void removeObserver(IRobotView view) {
		observers.remove(view);
	}

	//let our observers know that state has changed.
	public void notifyObservers() {
		for(IRobotView observer : observers){
			observer.update();
		}
	}

	//returns whether we are watching the mouse pos or not
	public boolean isStarted() {
		return running;
	}
	
	//the stuff we want.
	public String getMousePos(){
		return mousePos;
	}
	
	
	//Every second(provided that running is true) 
	//the mousePosition string is updated with the current mouselocation
	public void run() {
		while(true){
			while(running){
				mousePos = makeMouseLocationString();
				notifyObservers();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
