package robotView;

import javax.swing.JMenuBar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import robotCommander.IRobotController;
import robotModel.ICommandModel;
import robotModel.IMouseTracker;
import robotCommanderConstants.GUIConstants;

public class RobotView_MenuBar extends JMenuBar implements ActionListener, IRobotView {
	private JMenu menu;
	private JMenuItem menuItem_new;
	private JMenuItem menuItem_open;
	private JMenuItem menuItem_save;
	private JMenuItem menuItem_startRobot;
	private JMenuItem menuItem_stopRobot;
	private JMenuItem menuItem_startTrackMouse;
	private JMenuItem menuItem_stopTrackMouse;

	// our model and commander
	ICommandModel model;
	IRobotController controller;
	IMouseTracker mouseTracker;

	public RobotView_MenuBar(IRobotController c, ICommandModel m, IMouseTracker mt) {
		// init controller and model, add self as observer of the model
		controller = c;
		model = m;
		mouseTracker = mt;
		mouseTracker.addObserver(this);

		// init menu and menuItems
		menu = new JMenu("File");
		menuItem_new = new JMenuItem(GUIConstants.menu_new_string);
		menuItem_open = new JMenuItem(GUIConstants.menu_open_string);
		menuItem_save = new JMenuItem(GUIConstants.menu_save_string);
		menuItem_startRobot = new JMenuItem(GUIConstants.menu_robot_string1);
		menuItem_stopRobot = new JMenuItem(GUIConstants.menu_robot_string2);
		menuItem_startTrackMouse = new JMenuItem(GUIConstants.menu_trackmouse_string1);
		menuItem_stopTrackMouse = new JMenuItem(GUIConstants.menu_trackmouse_string2);

		// add the menu to the menubar and the menu items to the menu
		add(menu);
		menu.add(menuItem_new);
		menu.add(menuItem_open);
		menu.add(menuItem_save);
		menu.add(menuItem_startRobot);
		menu.add(menuItem_stopRobot);
		menu.add(menuItem_startTrackMouse);
		menu.add(menuItem_stopTrackMouse);

		// add actionListeners to the components
		menuItem_new.addActionListener(this);
		menuItem_open.addActionListener(this);
		menuItem_save.addActionListener(this);
		menuItem_startRobot.addActionListener(this);
		menuItem_stopRobot.addActionListener(this);
		menuItem_startTrackMouse.addActionListener(this);
		menuItem_stopTrackMouse.addActionListener(this);

		// disable trackmouseStop and robotStop for now
		menuItem_stopTrackMouse.setEnabled(false);
		menuItem_stopRobot.setEnabled(false);
	}

	// Handle events here (user presses a menu item button)
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == menuItem_new) {
			System.out.println("New Pressed");
			String newCommandName = JOptionPane.showInputDialog(this, "New Command", "Enter a new command name",
					JOptionPane.QUESTION_MESSAGE);
			if (!controller.createNewCommand(newCommandName)) {
				JOptionPane.showMessageDialog(this, "New Command Not Created", "Error Creating Command",
						JOptionPane.ERROR_MESSAGE);
			}
		}
		if (e.getSource() == menuItem_open) {
			System.out.println("Open Pressed");
			String[] commands = controller.getCommands().toArray(new String[controller.getCommands().size()]);
			JComboBox<String> jcb = new JComboBox<String>(commands);
			JOptionPane.showMessageDialog(this, jcb, "Select the command you wish to open",
					JOptionPane.INFORMATION_MESSAGE);

			String selectedCommand = (String) jcb.getSelectedItem();
			controller.openCommand(selectedCommand);
		}
		if (e.getSource() == menuItem_save) {
			System.out.println("Save Pressed");
			controller.saveCommands();
		}
		if (e.getSource() == menuItem_startRobot) {
			System.out.println("Robot Started");
			if(!controller.startRobot()){
				JOptionPane.showMessageDialog(this, "Please select a command first",
						"Could Not Start Robot", JOptionPane.ERROR_MESSAGE);
			}
			else{
				menuItem_startRobot.setEnabled(false);
				menuItem_stopRobot.setEnabled(true);
			}
		}
		if(e.getSource() == menuItem_stopRobot){
			System.out.println("Robot Stopped");
			controller.stopRobot();
			menuItem_startRobot.setEnabled(true);
			menuItem_stopRobot.setEnabled(false);
		}
		if (e.getSource() == menuItem_startTrackMouse) {
			System.out.println("Trackmouse Started");
			controller.startMouseTracker();
		}
		if (e.getSource() == menuItem_stopTrackMouse) {
			System.out.println("Trackmouse Stopped");
			controller.stopMouseTracker();
		}
	}

	@Override
	public void update() {
		menuItem_startTrackMouse.setEnabled(!mouseTracker.isStarted());
		menuItem_stopTrackMouse.setEnabled(mouseTracker.isStarted());
	}
}
