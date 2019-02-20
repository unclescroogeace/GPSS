package uni.fmi.bachelors;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;

public class MiddlePoint {

	public JFrame frame;
	private Graph graph;
	private JComboBox<String> comboBoxFrom;
	private JComboBox<String> comboBoxMidOne;
	private JComboBox<String> comboBoxMidTwo;
	private JComboBox<String> comboBoxTo;
	/**
	 * Create the application.
	 */
	public MiddlePoint(Graph graph) {
		this.graph = graph;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFrom = new JLabel("From");
		lblFrom.setBounds(10, 14, 104, 14);
		frame.getContentPane().add(lblFrom);
		
		JLabel lblMidpointOne = new JLabel("Midpoint 1");
		lblMidpointOne.setBounds(10, 39, 104, 14);
		frame.getContentPane().add(lblMidpointOne);
		
		JLabel lblMidpointTwo = new JLabel("Midpoint 2");
		lblMidpointTwo.setBounds(10, 64, 104, 14);
		frame.getContentPane().add(lblMidpointTwo);
		
		JLabel lblTo = new JLabel("To");
		lblTo.setBounds(10, 89, 104, 14);
		frame.getContentPane().add(lblTo);
		
		comboBoxFrom = new JComboBox<String>();
		comboBoxFrom.setBounds(124, 11, 111, 20);
		frame.getContentPane().add(comboBoxFrom);
		
		comboBoxMidOne = new JComboBox<String>();
		comboBoxMidOne.setBounds(124, 36, 111, 20);
		frame.getContentPane().add(comboBoxMidOne);
		
		comboBoxMidTwo = new JComboBox<String>();
		comboBoxMidTwo.setBounds(124, 61, 111, 20);
		frame.getContentPane().add(comboBoxMidTwo);
		
		comboBoxTo = new JComboBox<String>();
		comboBoxTo.setBounds(124, 86, 111, 20);
		frame.getContentPane().add(comboBoxTo);
		
		JTextArea taResult = new JTextArea();
		taResult.setBounds(10, 114, 414, 136);
		frame.getContentPane().add(taResult);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double totalPathDistance = 0;
				taResult.setText("");
				ArrayList<String> fullPath = new ArrayList<>();
				String from = graph.getNodeByKey((String)comboBoxFrom.getSelectedItem()).getName();
				String midOne = graph.getNodeByKey((String)comboBoxMidOne.getSelectedItem()).getName();
				String midTwo = graph.getNodeByKey((String)comboBoxMidTwo.getSelectedItem()).getName();
				String to = graph.getNodeByKey((String)comboBoxTo.getSelectedItem()).getName();
				
				if((from != midOne)
					&& (midOne != midTwo)
					&& (midTwo != to)) {
					
					Dijkstra dijkstra = new Dijkstra(graph);
					
					
					ArrayList<String> pathOne = dijkstra.getShortestPath(from, midOne);
					totalPathDistance += Dijkstra.totalPathDistance;
					if(pathOne.isEmpty()) {
						taResult.setText("Path between " + from + " and " + midOne + " does not exist!");
					}
					else {
						Collections.reverse(pathOne);
						pathOne.add(0, from);
						fullPath.addAll(pathOne);
						ArrayList<String> pathTwo = dijkstra.getShortestPath(midOne, midTwo);
						totalPathDistance += Dijkstra.totalPathDistance;
						if(pathTwo.isEmpty()) {
							taResult.setText("Path between " + midOne + " and " + midTwo + " does not exist!");
						}
						else {
							Collections.reverse(pathTwo);
							fullPath.addAll(pathTwo);
							ArrayList<String> pathThree = dijkstra.getShortestPath(midTwo, to);
							totalPathDistance += Dijkstra.totalPathDistance;
							if(pathThree.isEmpty()) {
								taResult.setText("Path between " + midTwo + " and " + to + " does not exist!");
							}
							else {
								Collections.reverse(pathThree);
								fullPath.addAll(pathThree);
								
								for(String s: fullPath) {
									taResult.append("  " + s);
								}
								
								taResult.append("\n Total path distance : " + totalPathDistance);
							}
						}
					}
				}
				else {
					taResult.setText("Wrong nodes!");
				}
				
			}
		});
		btnStart.setBounds(295, 10, 89, 43);
		frame.getContentPane().add(btnStart);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnCancel.setBounds(295, 60, 89, 43);
		frame.getContentPane().add(btnCancel);
		
		fillNodes();
	}
	
	private void fillNodes() {
		for(String n : graph.graph.keySet()) {
			comboBoxTo.addItem(n);
			comboBoxFrom.addItem(n);
			comboBoxMidTwo.addItem(n);
			comboBoxMidOne.addItem(n);
		}
	}
}
