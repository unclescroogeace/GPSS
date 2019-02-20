package uni.fmi.bachelors;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddLinkForm {

	public JFrame frame;
	private JTextField tfLength;
	private JComboBox<String> comboBoxNodeTo;
	private JComboBox<String> comboBoxNodeFrom;
	private JLabel lblError;


	/**
	 * Create the application.
	 */
	public AddLinkForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 509, 151);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		comboBoxNodeFrom = new JComboBox<String>();
		comboBoxNodeFrom.setBounds(20, 39, 100, 20);
		frame.getContentPane().add(comboBoxNodeFrom);
		
		JLabel lblNodeFrom = new JLabel("Node from");
		lblNodeFrom.setBounds(42, 11, 67, 14);
		frame.getContentPane().add(lblNodeFrom);
		
		JLabel lblLength = new JLabel("Length");
		lblLength.setBounds(149, 11, 46, 14);
		frame.getContentPane().add(lblLength);
		
		tfLength = new JTextField();
		tfLength.setBounds(143, 39, 52, 20);
		tfLength.setText("");
		frame.getContentPane().add(tfLength);
		tfLength.setColumns(10);
		
		comboBoxNodeTo = new JComboBox<String>();
		comboBoxNodeTo.setBounds(217, 39, 100, 20);
		frame.getContentPane().add(comboBoxNodeTo);
		
		JLabel lblNodeTo = new JLabel("Node to");
		lblNodeTo.setBounds(242, 11, 67, 14);
		frame.getContentPane().add(lblNodeTo);
		
		JLabel lblPath = new JLabel("Choose Path");
		lblPath.setBounds(365, 11, 100, 14);
		frame.getContentPane().add(lblPath);
		
		JRadioButton rdbtnOneWay = new JRadioButton("One way");
		rdbtnOneWay.setBounds(323, 38, 83, 23);
		frame.getContentPane().add(rdbtnOneWay);
		
		JRadioButton rdbtnTwoWay = new JRadioButton("Two way");
		rdbtnTwoWay.setBounds(403, 38, 83, 23);
		frame.getContentPane().add(rdbtnTwoWay);
		
		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnOneWay);
		buttonGroup.add(rdbtnTwoWay);
		
		rdbtnOneWay.setSelected(false);
		rdbtnTwoWay.setSelected(true);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnCancel.setBounds(394, 78, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfLength.getText().isEmpty()) {
					lblError.setText("Length field is required!");
					return;
				}
				try {
					Double.parseDouble(tfLength.getText());
				}catch(NumberFormatException ex){
					lblError.setText("This field is for numbers only");
					return;
				}
				lblError.setText("");
				
				Node from = MainForm.graph.getNodeByKey((String)comboBoxNodeFrom.getSelectedItem());
				Node to = MainForm.graph.getNodeByKey((String)comboBoxNodeTo.getSelectedItem());
				
				if(from.equals(to)) {
					lblError.setText("Cannot make a path between same node!");
					return;
				}
				
				if(rdbtnOneWay.isSelected()) {
					if(!from.isPathExist(to)) {
						MainForm.graph.createPath(from, Double.parseDouble(tfLength.getText()), to);
					}
				}else {
					if(from.isPathExist(to)) {
						if(!to.isPathExist(from)) {
							MainForm.graph.createPath(to, Double.parseDouble(tfLength.getText()), from);
						}
					}else {
						if(to.isPathExist(from)) {
							MainForm.graph.createPath(from, Double.parseDouble(tfLength.getText()), to);
						}else {
							MainForm.graph.createTwoWayPath(from, Double.parseDouble(tfLength.getText()), to);
						}
					}
				}
				MainForm.fillListLinks();
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnAdd.setBounds(283, 78, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		lblError = new JLabel("");
		lblError.setBounds(98, 80, 160, 14);
		lblError.setForeground(Color.RED);
		frame.getContentPane().add(lblError);
		
		fillComboBoxesWithNodes();
	}
	
	private void fillComboBoxesWithNodes() {
		for(String s : MainForm.graph.graph.keySet()) {
			comboBoxNodeFrom.addItem(s);
			comboBoxNodeTo.addItem(s);
		}
	}
}
