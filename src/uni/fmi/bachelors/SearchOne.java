package uni.fmi.bachelors;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;

public class SearchOne {

	public JFrame frame;
	private JComboBox<String> comboBoxNodeStart;
	private JComboBox<String> comboBoxNodeEnd;
	private Graph graph;
	private JTextArea taResult;
	

	/**
	 * Create the application.
	 */
	public SearchOne(Graph graph) {
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
		
		comboBoxNodeStart = new JComboBox<String>();
		comboBoxNodeStart.setBounds(10, 11, 126, 20);
		frame.getContentPane().add(comboBoxNodeStart);
		
		comboBoxNodeEnd = new JComboBox<String>();
		comboBoxNodeEnd.setBounds(280, 11, 126, 20);
		frame.getContentPane().add(comboBoxNodeEnd);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String start = graph.getNodeByKey((String)comboBoxNodeStart.getSelectedItem()).getName();
				String finish = graph.getNodeByKey((String)comboBoxNodeEnd.getSelectedItem()).getName();
				
				Dijkstra dijkstra = new Dijkstra(graph);
				
				ArrayList<String> path = dijkstra.getShortestPath(start, finish);
				if(!path.isEmpty()) {
					Collections.reverse(path);
					path.add(0, start);
					
					taResult.setText("Path between " + start + " and " + finish + " has been found!");
					
					for(String s: path) {
						taResult.append("  " + s);
					}
					taResult.append("\n Total distance : " + Dijkstra.totalPathDistance);
				}
				else {
					taResult.setText("Path between " + start + " and " + finish + " do not exist!");
				}
				
				
			}
		});
		btnStart.setBounds(163, 10, 89, 40);
		frame.getContentPane().add(btnStart);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.setVisible(false);
				frame.dispose();
				
			}
		});
		btnCancel.setBounds(163, 61, 89, 40);
		frame.getContentPane().add(btnCancel);
		
		taResult = new JTextArea();
		taResult.setEditable(false);
		taResult.setBounds(10, 116, 414, 134);
		frame.getContentPane().add(taResult);
		fillNodesStart();
		fillNodesEnd();
	}

	private void fillNodesStart() {
		for(String n : graph.graph.keySet()) {
			comboBoxNodeStart.addItem(n);
		}
	}
	
	private void fillNodesEnd() {
		for(String n : graph.graph.keySet()) {
			comboBoxNodeEnd.addItem(n);
		}
	}
}
