package uni.fmi.bachelors;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class GreedyForm {

	public JFrame frame;
	private JComboBox<String> comboBoxFrom;
	private JComboBox<String> comboBoxTo;
	private Graph graph;
	private JTextArea taResult;

	/**
	 * Create the application.
	 */
	public GreedyForm(Graph graph) {
		this.graph = graph;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 391, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(43, 11, 46, 14);
		frame.getContentPane().add(lblFrom);
		
		comboBoxFrom = new JComboBox<String>();
		comboBoxFrom.setBounds(10, 36, 92, 20);
		frame.getContentPane().add(comboBoxFrom);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(310, 11, 46, 14);
		frame.getContentPane().add(lblTo);
		
		comboBoxTo = new JComboBox<String>();
		comboBoxTo.setBounds(273, 36, 92, 20);
		frame.getContentPane().add(comboBoxTo);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String from = graph.getNodeByKey((String)comboBoxFrom.getSelectedItem()).getName();
				String to = graph.getNodeByKey((String)comboBoxTo.getSelectedItem()).getName();
				
				if(from == to) {
					taResult.setText("Same nodes error!");
				}
				
				GreedySearch gs = new GreedySearch(graph);
				
				ArrayList<String> path = gs.search(from, to);
				
				
				if(!path.isEmpty()) {
					taResult.setText("Path between " + from + " and " + to + " has been found! \n");
					for(String s: path) {
						taResult.append(s + " ");
					}
					
				}else{
					taResult.setText("Path between " + from + " and " + to + " has not been found!");
				}
			}
		});
		btnStart.setBounds(144, 11, 89, 31);
		frame.getContentPane().add(btnStart);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnCancel.setBounds(144, 53, 89, 31);
		frame.getContentPane().add(btnCancel);
		
		taResult = new JTextArea();
		taResult.setBounds(10, 95, 355, 155);
		frame.getContentPane().add(taResult);
		
		fillNodesFrom();
		fillNodesTo();
	}
	
	private void fillNodesFrom() {
		for(String n : graph.graph.keySet()) {
			comboBoxFrom.addItem(n);
		}
	}
	
	private void fillNodesTo() {
		for(String n : graph.graph.keySet()) {
			comboBoxTo.addItem(n);
		}
	}
}
