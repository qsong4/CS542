package RoutingSimulator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ShortestPathWin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	JButton btnCalculate,btnEmpty;

	/**
	 * Create the frame.
	 */
	public ShortestPathWin() {
		setTitle("Shortest Path to Destination Router");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 204);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 438, 124);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Source Router :");
		lblNewLabel.setBounds(6, 6, 98, 24);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(110, 5, 89, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblDestinationRouter = new JLabel("Destination :");
		lblDestinationRouter.setBounds(213, 6, 89, 24);
		panel.add(lblDestinationRouter);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(312, 5, 89, 26);
		panel.add(textField_1);
		
		JLabel lblTotalCost = new JLabel("Total Cost :");
		lblTotalCost.setBounds(26, 49, 98, 24);
		panel.add(lblTotalCost);
		
		textField_2 = new JTextField();
		textField_2.setEditable(false);
		textField_2.setBounds(109, 48, 89, 26);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblPath = new JLabel("          Path :");
		lblPath.setBounds(26, 85, 98, 24);
		panel.add(lblPath);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setColumns(10);
		textField_3.setBounds(110, 85, 291, 26);
		panel.add(textField_3);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 142, 438, 34);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		btnCalculate = new JButton("Calculate");
		btnCalculate.setBounds(61, 6, 117, 29);
		panel_1.add(btnCalculate);
		btnCalculate.addActionListener(this);
		
		btnEmpty = new JButton("Empty");
		btnEmpty.setBounds(255, 6, 117, 29);
		panel_1.add(btnEmpty);
		btnEmpty.addActionListener(this);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		

		
		
		if(e.getSource()==btnCalculate){
			
			String a = textField.getText();
			String b = textField_1.getText();
			try{
			int start = Integer.parseInt(a);
			int destination = Integer.parseInt(b);
			int[][] cost = MainWin.getCost();
			Dijkstra djk = new Dijkstra();
			Map result = new HashMap();
			result = djk.shortPath(cost, start-1, destination-1);
			String shortPath = (String) result.get(1);
			String cost1 = (String) result.get(2);
			
			textField_2.setText(cost1);
			textField_3.setText(shortPath);
			}catch(NumberFormatException e1){
				JOptionPane.showMessageDialog(null,"The router number must be a number, please input again! ", "System Information", JOptionPane.ERROR_MESSAGE);
			    textField.setText("");
			    textField_1.setText("");
			    return;
			}catch(ArrayIndexOutOfBoundsException e2){
				JOptionPane.showMessageDialog(null,"The wrong router name, please input again! ", "System Information", JOptionPane.ERROR_MESSAGE);
			    textField.setText("");
			    textField_1.setText("");
			    textField_2.setText("");
			    textField_3.setText("");
			    return;
			}catch(NullPointerException e3){
				JOptionPane.showMessageDialog(null,"The router has been deleted, please change a router", "System Information", JOptionPane.ERROR_MESSAGE);
			    textField.setText("");
			    textField_1.setText("");
			    textField_2.setText("");
			    textField_3.setText("");
			}

			

		}
		
		if(e.getSource()==btnEmpty){
			
			textField.setText("");
			textField_1.setText("");
			textField_2.setText("");
			textField_3.setText("");
		}
	}

}
