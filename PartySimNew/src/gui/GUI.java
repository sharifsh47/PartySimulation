package gui;

    import java.awt.FlowLayout;
import java.awt.GridLayout;
    import java.awt.Panel;
    import java.awt.event.ActionEvent;
    import java.awt.event.ActionListener;

    import javax.swing.JButton;
    import javax.swing.JFrame;
    import javax.swing.JLabel;
    import javax.swing.JPanel;
    import javax.swing.JTextField;
import javax.swing.SpringLayout;

import simulation.Simulation;

    public class GUI extends JPanel{

    	private JButton enter, stop, resume;
    	private JTextField agentnum, field_high, field_width, FieldNumHorizontal, FieldNumVertical;
    	private ButtonClickListener listener;
    	
    	public GUI() {
    		super();
    		//setLayout(new GridLayout(0, 2, 20, 20));
    		setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
    		
    		
    		add(new JLabel("Number of Agents"));
    		agentnum = new JTextField("10",4);
    		//agentnum.setText("Number of Agents");
    		add(agentnum);
    		

    		add(new JLabel("Field Size"));
    		//add(new JLabel(""));
    		add(new JLabel("Hight"));
    		field_high = new JTextField("400",4);
    		add(field_high);
    		add(new JLabel("Width"));
    		field_width = new JTextField("400",4);
    		add(field_width);
    		
    		add(new JLabel("Rows"));
    		FieldNumHorizontal = new JTextField("20",4);
    		add(FieldNumHorizontal);
    		
    		add(new JLabel("Columns"));
    		FieldNumVertical = new JTextField("20",4);
    		add(FieldNumVertical);
    		
    		enter = new JButton("Start");
    		enter.setText("Start");
    		enter.addActionListener(new ActionListener() {
    			@Override
    			public void actionPerformed(ActionEvent e) {
    				if(listener != null) {
    					listener.onButtonClick();
    				}
    			}
    		});
    		add(enter);
    		
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
            add(stop);

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
            add(resume);
    		
    	}
    	
    	public void setButtonClickListener(ButtonClickListener listener) {
    		this.listener = listener;
    	}

    	public int getNumOfAgent() {
    		return Integer.valueOf(agentnum.getText());
    	}

    	public int getFieldHigh() {
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

    }

