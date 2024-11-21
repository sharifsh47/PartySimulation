package simulation;

import visualisation.Visualisation;
import gui.ButtonClickListener;
import gui.GUI;

import javax.swing.*;
import java.awt.*;

@SuppressWarnings("serial")
public class Simulation extends JFrame implements ButtonClickListener {
    private Visualisation visualisation;
    private GUI gui;
    private int numAgents;
    private boolean allowNewAgents = true; // Control addition of new agents
    private boolean running = true; // Keep agents moving

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
        add(gui, BorderLayout.SOUTH);
    }

    private void createVisualization() {
        visualisation = new Visualisation(600, 400, numAgents, 40, 40);
        add(visualisation, BorderLayout.CENTER);
    }

    public void run() {
        int agentsAdded = 0; // Count of agents added

        while (true) {
            if (running) {
                // Allow movement of existing agents
                visualisation.getRoom().navigate(); 
                visualisation.repaint(); // Refresh visualization
            }

            if (allowNewAgents && agentsAdded < numAgents) {
                // Add new agents only when allowed
                boolean added = visualisation.getRoom().addAgent();
                if (added) {
                    agentsAdded++;
                }
            }

            try {
                Thread.sleep(700); // Control simulation speed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onButtonClick() {
        numAgents = gui.getNumOfAgent();
        remove(visualisation);

        visualisation = new Visualisation(gui.getFieldHigh(), gui.getFieldWidth(), numAgents, gui.getRows(), gui.getColumns());
        add(visualisation, BorderLayout.CENTER);

        allowNewAgents = true; // Allow new agents to start entering
        running = true; // Start moving agents
        revalidate(); // Refresh layout
        repaint();
    }

    @Override
    public void onStopClick() {
        allowNewAgents = false; // Stop new agents from entering
        JOptionPane.showMessageDialog(this, "Ay Ay where your ID at?", "Notification", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void onResumeClick() {
        allowNewAgents = true; // Allow new agents to start entering again
        JOptionPane.showMessageDialog(this, " Aight you can enter", "Notification", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new Simulation("Let's PartyYyYy!!").run();
    }
}
