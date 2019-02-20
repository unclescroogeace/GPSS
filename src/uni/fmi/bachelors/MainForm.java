package uni.fmi.bachelors;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class MainForm {

	public JFrame frame;
	static JList<String> listNodes;
	static JList<String> listLinks;
	static Graph graph = new Graph();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainForm window = new MainForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 550, 343);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		listNodes = new JList<String>();
		listNodes.setBounds(10, 34, 150, 216);
		frame.getContentPane().add(listNodes);
		
		listLinks = new JList<String>();
		listLinks.setBounds(183, 34, 150, 216);
		frame.getContentPane().add(listLinks);
		
		JLabel lblListOfNodes = new JLabel("List of Nodes");
		lblListOfNodes.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfNodes.setBounds(10, 9, 150, 14);
		frame.getContentPane().add(lblListOfNodes);
		
		JLabel lblListOfLinks = new JLabel("List of Links");
		lblListOfLinks.setHorizontalAlignment(SwingConstants.CENTER);
		lblListOfLinks.setBounds(183, 9, 150, 14);
		frame.getContentPane().add(lblListOfLinks);
		
		JButton btnAddNode = new JButton("Add Node");
		btnAddNode.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				AddNodeForm anf = new AddNodeForm();
				anf.frame.setVisible(true);
			}
		});
		btnAddNode.setBounds(39, 261, 89, 23);
		frame.getContentPane().add(btnAddNode);
		
		JButton btnAddLink = new JButton("Add Link");
		btnAddLink.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddLinkForm alf = new AddLinkForm();
				alf.frame.setVisible(true);
			}
		});
		btnAddLink.setBounds(215, 261, 89, 23);
		frame.getContentPane().add(btnAddLink);
		
		JButton btnShortestPath = new JButton("Shortest path");
		btnShortestPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SearchOne so = new SearchOne(graph);
				so.frame.setVisible(true);
			}
		});
		btnShortestPath.setBounds(371, 31, 132, 42);
		frame.getContentPane().add(btnShortestPath);
		
		JButton btnFillForTest = new JButton("Fill for Test");
		btnFillForTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Node a = new Node("A", 3, 10, 60);
				Node b = new Node("B", 2, 2, 12);
				Node c = new Node("C", 0, 0, 50);
				Node d = new Node("D", 4, 6, 45);
				Node e = new Node("E", 2, 0, 10);
				Node f = new Node("F", 4, 0, 30);
				Node j = new Node("J", 8, 12, 35);
				
				graph.graph.put(a.getName(), a);
				graph.graph.put(b.getName(), b);
				graph.graph.put(c.getName(), c);
				graph.graph.put(d.getName(), d);
				graph.graph.put(e.getName(), e);
				graph.graph.put(f.getName(), f);
				graph.graph.put(j.getName(), j);
				
				graph.createTwoWayPath(a, 6.0, b);
				graph.createTwoWayPath(a, 5.0, d);
				graph.createTwoWayPath(b, 2.0, c);
				graph.createTwoWayPath(b, 3.0, e);
				graph.createTwoWayPath(d, 10.0, j);
				graph.createTwoWayPath(d, 3.0, f);
				
				
				/*
				Node a = new Node("A", 7, 1, 1);
				Node b = new Node("B", 6, 1, 12);
				Node c = new Node("C", 6, 2, 4);
				Node d = new Node("D", 5, 1, 22);
				Node e = new Node("E", 5, 2, 18);
				Node f = new Node("F", 5, 3, 3);
				Node g = new Node("G", 4, 1, 28);
				Node h = new Node("H", 4, 4, 5);
				Node i = new Node("I", 4, 5, 1);
				Node j = new Node("J", 5, 4, 30);
				
				graph.graph.put(a.getName(), a);
				graph.graph.put(b.getName(), b);
				graph.graph.put(c.getName(), c);
				graph.graph.put(d.getName(), d);
				graph.graph.put(e.getName(), e);
				graph.graph.put(f.getName(), f);
				graph.graph.put(g.getName(), g);
				graph.graph.put(h.getName(), h);
				graph.graph.put(j.getName(), j);
				graph.graph.put(i.getName(), i);
				

				graph.createTwoWayPath(a, 3.0, b);
				graph.createTwoWayPath(a, 12.0, c);
				graph.createTwoWayPath(b, 2.0, d);
				graph.createTwoWayPath(b, 5.0, e);
				graph.createTwoWayPath(c, 25.0, f);
				graph.createTwoWayPath(d, 3.0, g);
				graph.createTwoWayPath(e, 10.0, j);
				graph.createTwoWayPath(f, 29.0, h);
				graph.createTwoWayPath(f, 38.0, i);
				graph.createTwoWayPath(g, 7.0, j);
				graph.createTwoWayPath(h, 19.0, j);
				graph.createTwoWayPath(i, 9.0, j);
				*/
				fillListNodes();
				fillListLinks();
			}
		});
		btnFillForTest.setBounds(395, 261, 89, 23);
		frame.getContentPane().add(btnFillForTest);
		
		JButton btnMiddlePoint = new JButton("Middle Point");
		btnMiddlePoint.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MiddlePoint mp = new MiddlePoint(graph);
				mp.frame.setVisible(true);
			}
		});
		btnMiddlePoint.setBounds(371, 84, 132, 42);
		frame.getContentPane().add(btnMiddlePoint);
		
		JButton btnGreedy = new JButton("Greedy");
		btnGreedy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GreedyForm gf = new GreedyForm(graph);
				gf.frame.setVisible(true);
			}
		});
		btnGreedy.setBounds(371, 137, 132, 42);
		frame.getContentPane().add(btnGreedy);
	}
	
	
	
	public static void fillListNodes() {
		DefaultListModel<String> DLM = new DefaultListModel<String>();
		
		for(String s : graph.graph.keySet()) {
			DLM.addElement(s);
		}
		
		listNodes.setModel(DLM);
	}
	
	public static void fillListLinks() {
		ArrayList<String> links = new ArrayList<String>();
		String nodeOne;
		String nodeTwo;
		
		for(String key : graph.graph.keySet()) {
			for(Link l : graph.graph.get(key).paths) {
				
				nodeOne = graph.graph.get(key).getName();
				nodeTwo = graph.graph.get(l.getEndPoint().getName()).getName();
				
				if(graph.graph.get(l.getEndPoint().getName()).isPathExist(graph.graph.get(key))) {
					
					links.add(nodeOne + " <-> " + nodeTwo);
				}
				else{
					links.add(nodeOne + " --> " + nodeTwo);
				}
			}
		}
		
		DefaultListModel<String> DLM = new DefaultListModel<String>();
		
		for(String s : links) {
			DLM.addElement(s);
		}
		
		listLinks.setModel(DLM);
	}
}
