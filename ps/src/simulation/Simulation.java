package simulation;

import gui.ButtonClickListener;
import gui.GUI;
import visualisation.Visualisation;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Simulation extends JFrame implements ButtonClickListener {
	private Visualisation visualisation;
	private GUI gui;
	private int numAgents, numObstacles, speed;
	private int agentsAdded = 0;
	private boolean running = true; // Flag to control the run state

	public Simulation(String title) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLayout(new BorderLayout());

		Dimension screenSize = getToolkit().getScreenSize();
		setBounds(0, 0, screenSize.width, screenSize.height);

		createVisualization();
		createGUI();
		setVisible(true);
	}

	private void createGUI() {
		gui = new GUI();
		gui.setButtonClickListener(this); // Set the current class as the listener
		add(gui, BorderLayout.WEST);
	}

	private void createVisualization() {
		visualisation = new Visualisation(600, 400, numAgents, numObstacles, 40, 40);
		add(visualisation, BorderLayout.CENTER);
	}

	public void run() { 
		while (true) {
			if (running) {
				visualisation.getRoom().navigateAgents();
				visualisation.repaint();
			}

			if (agentsAdded < numAgents) {
				boolean added = visualisation.getRoom().addAgent();
				if (added) {
					agentsAdded++;
				}
			}

			try {
				Thread.sleep(speed);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onButtonClick() {
		agentsAdded = 0; // reset to zero when agents are added/removed
		numAgents = gui.getNumOfAgent();
		numObstacles = gui.getNumOfObstacles(); // Get number of obstacles
		remove(visualisation);

		visualisation = new Visualisation(gui.getFieldWidth(), gui.getFieldHeight(), numAgents, numObstacles, gui.getRows(), gui.getColumns());
		add(visualisation, BorderLayout.CENTER);
		
		speed = gui.getSpeed();

		running = true; // Start moving agents
		revalidate(); // Refresh layout
		repaint();
	}

	@Override
	public void onStopClick() {
		running = false; // Stop updating
	}

	@Override
	public void onResumeClick() {
		running = true; // Resume updating
	}

	public static void main(String[] args) {
		new Simulation("Let's Party!").run();
	}
}