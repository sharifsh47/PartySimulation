package gui;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI extends JPanel {

	private JButton enter, stop, resume;
	private JTextField agentnum, obstaclesnum, field_high, field_width, FieldNumHorizontal, FieldNumVertical,speedSimulation,barAgent,danceAgent;
	private ButtonClickListener listener;
	private JCheckBox Logs;

	public GUI() {
		super();
		setLayout(new BorderLayout());
		
		JPanel panel = new JPanel();
		GridBagLayout gbl = new GridBagLayout();
	    GridBagConstraints gbc = new GridBagConstraints();
	    gbl.setConstraints(panel, gbc);
	    panel.setLayout(gbl);
		
		//Agent Number
		gbc.gridx = 0;
		gbc.gridy = 0;
		panel.add(new JLabel("Number of Agents"), gbc);
		
		agentnum = new JTextField("10", 5);
		gbc.gridx = 1;
		gbc.gridy = 0;
		panel.add(agentnum, gbc);
		
		//Obstacles Number
		gbc.gridx = 0;
		gbc.gridy = 1;
		panel.add(new JLabel("Number of Obstacles"), gbc);
		
		obstaclesnum = new JTextField("10", 5);
		gbc.gridx = 1;
		gbc.gridy = 1;
		panel.add(obstaclesnum, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		panel.add(Box.createVerticalStrut(20), gbc);

		//Field Size
		gbc.gridx = 0;
		gbc.gridy = 3;
		panel.add(new JLabel("Field Size"), gbc);
		
		//width
		gbc.gridx = 0;
		gbc.gridy = 4;
		panel.add(new JLabel("width"), gbc);
		
		field_high = new JTextField("200", 4);
		gbc.gridx = 1;
		gbc.gridy = 4;
		panel.add(field_high, gbc);
		
		//height
		gbc.gridx = 0;
		gbc.gridy = 5;
		panel.add(new JLabel("hieght"), gbc);
		
		field_width = new JTextField("200", 4);
		gbc.gridx = 1;
		gbc.gridy = 5;
		panel.add(field_width, gbc);

		gbc.gridx = 0;
		gbc.gridy = 6;
		panel.add(Box.createVerticalStrut(20), gbc);

		// Agent Type
		gbc.gridx = 0;
		gbc.gridy = 7;
		panel.add(new JLabel("Agent Type"), gbc);
		
		//width
		gbc.gridx = 0;
		gbc.gridy = 8;
		panel.add(new JLabel("Dance Type"), gbc);
		
		danceAgent = new JTextField("50", 4);
		gbc.gridx = 1;
		gbc.gridy = 8;
		panel.add(danceAgent, gbc);
		gbc.gridx = 2;
		gbc.gridy = 8;
		panel.add(new JLabel("%"), gbc);
		
		//height
		gbc.gridx = 0;
		gbc.gridy = 9;
		panel.add(new JLabel("Bar Type"), gbc);
		
		barAgent = new JTextField("50", 4);
		gbc.gridx = 1;
		gbc.gridy = 9;
		panel.add(barAgent, gbc);
		gbc.gridx = 2;
		gbc.gridy = 9;
		panel.add(new JLabel("%"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 10;
		panel.add(Box.createVerticalStrut(20), gbc);

		//Rows
		gbc.gridx = 0;
		gbc.gridy = 11;
		panel.add(new JLabel("Rows"), gbc);

		FieldNumHorizontal = new JTextField("20", 4);
		gbc.gridx = 1;
		gbc.gridy = 11;
		panel.add(FieldNumHorizontal, gbc);
		
		//Columns
		gbc.gridx = 0;
		gbc.gridy = 12;
		panel.add(new JLabel("Columns"), gbc);
		
		FieldNumVertical = new JTextField("20", 4);
		gbc.gridx = 1;
		gbc.gridy = 12;
		panel.add(FieldNumVertical, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 13;
		panel.add(Box.createVerticalStrut(20), gbc);
		//speed
		
		gbc.gridx = 0;
		gbc.gridy = 14;
		panel.add(new JLabel("Speed Simulation"), gbc);
		
		speedSimulation = new JTextField("200", 4);
		gbc.gridx = 1;
		gbc.gridy = 14;
		panel.add(speedSimulation, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 15;
		panel.add(Box.createVerticalStrut(20), gbc);
		
		
		//Logs
		gbc.gridx = 0;
		gbc.gridy = 16;
		panel.add(new JLabel("Aktiv Logs:"), gbc);
		
		Logs = new JCheckBox();
		gbc.gridx = 1;
		gbc.gridy = 16;
		panel.add(Logs, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 17;
		panel.add(Box.createVerticalStrut(20), gbc);
		
		//Startbutton
		enter = new JButton("Start");
		enter.setText("Start");
		enter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					listener.onButtonClick();
				}
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 18;
		panel.add(Box.createVerticalStrut(50), gbc);
		panel.add(enter, gbc);

		// Stop button
		stop = new JButton("Stop");
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					listener.onStopClick();
				}
			}
		});
		gbc.gridx = 0;
		gbc.gridy = 19;
		panel.add(Box.createVerticalStrut(50), gbc);
		panel.add(stop, gbc);

		// Resume button
		resume = new JButton("Resume");
		resume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (listener != null) {
					listener.onResumeClick();
				}
			}
		});
		gbc.gridx = 1;
		gbc.gridy = 18;
		panel.add(Box.createVerticalStrut(50), gbc);
		panel.add(resume, gbc);
		
		add(panel, BorderLayout.WEST);

	}

	public void setButtonClickListener(ButtonClickListener listener) {
		this.listener = listener;
	}

	public int getNumOfAgent() {
		return Integer.valueOf(agentnum.getText());
	}
	
	public int getNumOfObstacles() {
		return Integer.valueOf(obstaclesnum.getText());
	}

	public int getFieldHeight() {
		return Integer.valueOf(field_high.getText());
	}

	public int getFieldWidth() {
		return Integer.valueOf(field_width.getText());
	}

	public int getRows() {
		return Integer.valueOf(FieldNumHorizontal.getText());
	}

	public int getColumns() {
		return Integer.valueOf(FieldNumVertical.getText());
	}
    public int getSpeed() {
		return Integer.valueOf(speedSimulation.getText());
	}
    public int getDanceType() {
    	return Integer.valueOf(danceAgent.getText());
    }
    public int getBarType() {
    	return Integer.valueOf(barAgent.getText());
    }
	
	public boolean getLogs() {
		return Logs.isSelected();
	}

}
