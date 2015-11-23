package RoutingSimulator;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class ConnectionTableWin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField textField;
	JButton btnBuild,btnEmpty;
	JTextPane textPane;
	
	public String getText(){
		String text = textField.getText();
	    return text;
	}

	/**
	 * Create the frame.
	 */
	public ConnectionTableWin() {
		setTitle("Build a Connection Table");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 451, 448);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(6, 6, 439, 39);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Source Router:");
		lblNewLabel.setBounds(6, 6, 96, 27);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(100, 6, 139, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		btnBuild = new JButton("Build");
		btnBuild.setBounds(248, 6, 96, 29);
		panel.add(btnBuild);
		btnBuild.addActionListener(this);;
		
		btnEmpty = new JButton("Empty");
		btnEmpty.setBounds(337, 6, 96, 29);
		panel.add(btnEmpty);
		btnEmpty.addActionListener(this);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(6, 57, 439, 363);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		textPane = new JTextPane();
		textPane.setBounds(6, 6, 427, 351);
		panel_1.add(textPane);
		
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

    public void actionPerformed(ActionEvent e){
    	
    	//The action after press button empty
    	if(e.getSource()==btnEmpty){
    		textField.setText("");
    		textPane.setText("");
    	}
    	//The action after press button build
    	if(e.getSource()==btnBuild){
    		
			String text = textField.getText();
			String[] interface1 = null;
			int[][] cost = MainWin.getCost();

			if(text!=null&&!text.equals("")){
				Dijkstra djk = new Dijkstra();
				try{
				interface1 = djk.getInterface(cost,Integer.parseInt(text)-1);
				}catch(ArrayIndexOutOfBoundsException e1){
					JOptionPane.showMessageDialog(null,"The wrong router name, please input again! ", "System Information", JOptionPane.ERROR_MESSAGE);
				    textField.setText("");
				    return;
				}catch(NumberFormatException e2){
					JOptionPane.showMessageDialog(null,"The wrong router name, please input again! ", "System Information", JOptionPane.ERROR_MESSAGE);
				    textField.setText("");
				    return;
				}
			}
			else{
				JOptionPane.showMessageDialog(null,"Please input the source! ", "System Information", JOptionPane.ERROR_MESSAGE);
				return;
			}
			
			int[] router = new int[cost.length];
			for(int i=0;i<router.length;i++){
				router[i]=i+1;
			}
			
			String connectionTable="Router "+text+" Connection Table \n"
					+ "Destionation\t"+"Interface\t\n";
			for(int i=0;i<interface1.length;i++){
				connectionTable += router[i]+"\t"+"                  "+interface1[i] + "\t\n";
			
			}
			textPane.setText(connectionTable);
    	}
    	
    }
}
