package uni.fmi.bachelors;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddNodeForm {

	public JFrame frame;
	private JTextField tfNodeName;
	private JLabel lblX;
	private JLabel lblY;
	private JLabel lblWeight;
	private JTextField tfX;
	private JTextField tfY;
	private JTextField tfWeight;
	private JButton btnAdd;
	private JButton btnCancel;
	private JLabel lblNodeNameError;
	private JLabel lblXError;
	private JLabel lblYError;
	private JLabel lblWeightError;

	/**
	 * Create the application.
	 */
	public AddNodeForm() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 498, 220);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNodeName = new JLabel("Node name");
		lblNodeName.setBounds(10, 11, 70, 14);
		frame.getContentPane().add(lblNodeName);
		
		tfNodeName = new JTextField();
		tfNodeName.setBounds(90, 8, 109, 20);
		tfNodeName.setText("");
		frame.getContentPane().add(tfNodeName);
		tfNodeName.setColumns(10);
		
		lblX = new JLabel("X");
		lblX.setBounds(10, 42, 56, 14);
		frame.getContentPane().add(lblX);
		
		lblY = new JLabel("Y");
		lblY.setBounds(10, 73, 46, 14);
		frame.getContentPane().add(lblY);
		
		lblWeight = new JLabel("Weight");
		lblWeight.setBounds(10, 104, 46, 14);
		frame.getContentPane().add(lblWeight);
		
		tfX = new JTextField();
		tfX.setBounds(90, 39, 109, 20);
		tfX.setText("");
		frame.getContentPane().add(tfX);
		tfX.setColumns(10);
		
		tfY = new JTextField();
		tfY.setBounds(90, 70, 109, 20);
		tfY.setText("");
		frame.getContentPane().add(tfY);
		tfY.setColumns(10);
		
		tfWeight = new JTextField();
		tfWeight.setBounds(90, 101, 109, 20);
		tfWeight.setText("");
		frame.getContentPane().add(tfWeight);
		tfWeight.setColumns(10);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfNodeName.getText().isEmpty()) {
					lblNodeNameError.setText("This field is required");
					return;
				}
				lblNodeNameError.setText("");
				
				if(tfY.getText().isEmpty()) {
					lblYError.setText("This field is required!");
					return;
				}
				try {
					Integer.parseInt(tfY.getText());
				}catch(NumberFormatException ex){
					lblYError.setText("This field is for numbers only");
					return;
				}
				lblYError.setText("");
				
				if(tfX.getText().isEmpty()) {
					lblXError.setText("This field is required!");
					return;
				}
				try {
					Integer.parseInt(tfX.getText());
				}catch(NumberFormatException ex){
					lblXError.setText("This field is for numbers only");
					return;
				}
				lblXError.setText("");
				
				if(tfWeight.getText().isEmpty()) {
					lblWeightError.setText("This field is required!");
					return;
				}
				try {
					Double.parseDouble(tfWeight.getText());
				}catch(NumberFormatException ex){
					lblWeightError.setText("This field is for numbers only");
					return;
				}
				lblWeightError.setText("");
				
				if(!MainForm.graph.graph.containsKey(tfNodeName.getText())) {
					Node node = new Node(tfNodeName.getText(), Integer.parseInt(tfX.getText()), Integer.parseInt(tfY.getText()), Double.parseDouble(tfWeight.getText()));
					MainForm.graph.graph.put(node.getName(), node);
					MainForm.fillListNodes();
					frame.setVisible(false);
					frame.dispose();
				}
				else {
					lblNodeNameError.setText("This name is already used!");
				}
			}
		});
		btnAdd.setBounds(10, 146, 89, 23);
		frame.getContentPane().add(btnAdd);
		
		btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				frame.dispose();
			}
		});
		btnCancel.setBounds(110, 146, 89, 23);
		frame.getContentPane().add(btnCancel);
		
		lblNodeNameError = new JLabel("");
		lblNodeNameError.setBounds(209, 11, 263, 14);
		lblNodeNameError.setForeground(Color.RED);
		frame.getContentPane().add(lblNodeNameError);
		
		lblXError = new JLabel("");
		lblXError.setBounds(209, 42, 263, 14);
		lblXError.setForeground(Color.RED);
		frame.getContentPane().add(lblXError);
		
		lblYError = new JLabel("");
		lblYError.setBounds(209, 73, 263, 14);
		lblYError.setForeground(Color.RED);
		frame.getContentPane().add(lblYError);
		
		lblWeightError = new JLabel("");
		lblWeightError.setBounds(209, 104, 263, 14);
		lblWeightError.setForeground(Color.RED);
		frame.getContentPane().add(lblWeightError);
	}
}
