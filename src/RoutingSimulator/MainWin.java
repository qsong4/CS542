package RoutingSimulator;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.JTextPane;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.File.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.AbstractAction;
import javax.swing.Action;
import java.awt.Component;
import javax.swing.Box;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileSystemView;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;
import java.awt.TextArea;

public class MainWin extends JFrame implements ActionListener {

	private JPanel contentPane;
	private static JTextField textField;
    public JButton SP,BCT,MaT,btnExit,btnFile,btnCreate;
    private JTextField textField_1;
    public static TextArea textArea;
    static boolean flg=false ;
    static int nodeNumber;
    
    static int[][] cost;
    //make sure the information in cost would not change because of the algorithm
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWin frame = new MainWin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public static int[][] getCost(){
		
		if(flg){
			String path = textField.getText();
			if(path != null && !path.equals("") ){
				cost = readTxt(path);
			}
			deleteNode dn = new deleteNode();
			cost = dn.delete(cost, nodeNumber);
			return cost;
		}
		else{
		String path = textField.getText();
		if(path != null && !path.equals("") ){
			cost = readTxt(path);
		}
		return cost;
		}

	}

	/**
	 * Create the frame.
	 */
	public MainWin() {
		setTitle("CS542 Link State Routing Simulator");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 739, 474);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 5, 739, 34);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Create a Network Topology");
		lblNewLabel.setBounds(6, 6, 177, 22);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(195, 4, 326, 26);
		panel.add(textField);
		textField.setColumns(10);
		
		btnFile = new JButton("file");
		btnFile.setBounds(521, 4, 106, 29);
		btnFile.addActionListener(this);
		panel.add(btnFile);
		
		btnCreate = new JButton("create");
		btnCreate.setBounds(627, 4, 106, 29);
		btnCreate.addActionListener(this);
		panel.add(btnCreate);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBounds(10, 51, 517, 384);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
	    textArea = new TextArea();
		textArea.setText("Please make sure the Matrix in file is like the example below(use ','):\n"
				+ "1\t-1\t5\t1\t-1\n"
				+ "-1\t0\t-1\t7\t9\n"
				+ "5\t-1\t0\t-1\t4\n"
				+ "1\t7\t-1\t0\t2\n"
				+ "-1\t9\t4\t2\t0\n");
		textArea.setEditable(false);
		textArea.setBounds(10, 10, 497, 364);
		panel_1.add(textArea);
		

//
//		topologyMatrix = new JLabel("<html><body>"
//				+ "Please make sure the Matrix in file is like the example below:<br />"
//				+ "<table border=‘1’>"
//				+ "<tr><th>1</th><th>-1</th><th>5</th><th>1</th><th>-1</th></tr>"
//				+ "<tr><th>－1</th><th>0</th><th>－1</th><th>7</th><th>9</th></tr>"
//				+ "<tr><th>5</th><th>-1</th><th>0</th><th>－1</th><th>4</th></tr>"
//				+ "<tr><th>1</th><th>7</th><th>-1</th><th>0</th><th>2</th></tr>"
//				+ "<tr><th>-1</th><th>9</th><th>4</th><th>2</th><th>0</th></tr>"
//				+ "  </table></body></html>"  );

		
//		topologyMatrix.setBounds(6, 6, 505, 372);
//		panel_1.add(topologyMatrix);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, Color.YELLOW));
		panel_2.setBounds(539, 51, 194, 384);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		BCT = new JButton("Build Connection Table");
		BCT.setFont(new Font("Lucida Grande", Font.PLAIN, 11));;
		BCT.setBounds(30, 192, 138, 40);
		BCT.addActionListener(this);
		BCT.setEnabled(false);
		panel_2.add(BCT);

		
		SP = new JButton("Shortest Path");
		SP.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		SP.setBounds(30, 244, 138, 40);
		SP.addActionListener(this);
		SP.setEnabled(false);
		panel_2.add(SP);
		
		MaT = new JButton("Delete");
		MaT.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		MaT.setBounds(75, 118, 93, 34);
		MaT.addActionListener(this);
		MaT.setEnabled(false);
		panel_2.add(MaT);
		
		btnExit = new JButton("Exit");
		btnExit.setFont(new Font("Lucida Grande", Font.PLAIN, 11));
		btnExit.setBounds(30, 296, 138, 40);
		btnExit.addActionListener(this);
		panel_2.add(btnExit);
			
		JLabel lblNewLabel_1 = new JLabel("--Design by SQY,YSY");
		lblNewLabel_1.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		lblNewLabel_1.setBounds(69, 344, 119, 34);
		panel_2.add(lblNewLabel_1);
		
		JLabel lblNewLabel_3 = new JLabel("－－－－－－－－－－－－－－");
		lblNewLabel_3.setBounds(6, 164, 182, 16);
		panel_2.add(lblNewLabel_3);
		
		JLabel lblRouter = new JLabel("Router ：");
		lblRouter.setBounds(6, 59, 61, 23);
		panel_2.add(lblRouter);
		
		textField_1 = new JTextField();
		textField_1.setBounds(69, 54, 105, 33);
		panel_2.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("         Modify a topology");
		lblNewLabel_4.setBounds(6, 6, 182, 40);
		panel_2.add(lblNewLabel_4);
	}

	
	/**
	 * read the data in the txt
	 */
	    public static int[][] readTxt(String path){
	    	
	    	List list = new ArrayList();
	    	int[][] cost = null;
	    	String [][] Scost = null;//temporary store the matrix in txt 
	    	String [] str = null;//temporary store each line of matrix
	    	try {


	            File file=new File(path);
	            if(file.isFile() && file.exists()){ //if the file is exist.
	                InputStreamReader read = new InputStreamReader( new FileInputStream(file));
	                BufferedReader bufferedReader = new BufferedReader(read);
	                String lineTxt = null;
	                while((lineTxt = bufferedReader.readLine()) != null&& !lineTxt.equals("")){
	                    list.add(lineTxt);
	                
	                }

	                read.close();
	    }else{
	    	JOptionPane.showMessageDialog(null,"can not find the file", "Error Information", JOptionPane.ERROR_MESSAGE);
	    }
			} catch (IOException e) {
				e.printStackTrace();
			}
	//put the matrix into Scost, the type of Scost is String    	
	    	cost = new int [list.size()][list.size()];
	    	
	        for(int i=0;i<list.size();i++){
	            str = ((String) list.get(i)).split(",");

	            if(list.size() != str.length){//if the matrix is not n*n, throw the error
	            	JOptionPane.showMessageDialog(null,"Wrong topology matrix, you shoud submit the n*n matrix", "Error Information", JOptionPane.ERROR_MESSAGE);
	            	
	            	textField.setText("");
	            	return null;
	            }
	            try{
	       	for(int j = 0;j<list.size();j++){
       
	       		cost[i][j] = Integer.parseInt(str[j]);
	       	}    
	            }catch(NumberFormatException e){
	            	JOptionPane.showMessageDialog(null,"Wrong topology matrix, the topology matrix should only contain number split with comma", "Error Information", JOptionPane.ERROR_MESSAGE);
	            	textField.setText("");
	            	textArea.setText("");
	            	return null;
	            }
	       }


			return cost;
	    	
	    	
	    	
	    }
	
	    @Override
	public void actionPerformed(ActionEvent e) {
//press the shortest path button	
		if (e.getSource() == SP){
			ShortestPathWin frame = new ShortestPathWin();
			frame.setVisible(true);
		}
//press the delete buttom	
		else if (e.getSource() == MaT){
			flg=true;
			String number = textField_1.getText();
			nodeNumber = Integer.parseInt(number);
			
			deleteNode dn = new deleteNode();
			cost = dn.delete(cost, nodeNumber);
			String matrix1 = "";
			int j1 ;
        if(cost != null){//judge whether the array is null
        	System.out.println("11111");
				for(int i=0;i<cost.length;i++){
					if(i==0)
					matrix1 = "Review original topology: \n";
					for(j1=0;j1<cost.length;j1++){
						matrix1 += String.valueOf(cost[i][j1])+"\t";
					if(j1==cost.length-1){
						matrix1 = matrix1 +"\n";
					}
					}
				}
        }
        
        textArea.setText(matrix1);
			
		}
		
//press the build button
		else if (e.getSource() == BCT){
			ConnectionTableWin frame1 = new ConnectionTableWin();
			frame1.setVisible(true);

			
		}
		
		else if (e.getSource() == btnExit){
			System.exit(0);
		}
//press the file button		
		else if (e.getSource() == btnFile){
			
		final JFileChooser fc = new JFileChooser();//create a instance of JFileChooser
		fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);	
		//create a file filter which make sure we only choose the '.txt' file.
		txtFileFilter fileFilter=new txtFileFilter (); 
		fc.setFileFilter(fileFilter);       
		int returnVal = fc.showOpenDialog(this);
		File file_choosed = fc.getSelectedFile();
		
		try{// avoid the null point exception
		String filePath = file_choosed.getAbsolutePath();
		textField.setText(filePath);

		}catch(Exception e1){
			
		}
		

		}
//press the create button		
		else if(e.getSource() == btnCreate){
			
			String path = textField.getText();
			String matrix = "";
			int j ;
			if(path != null && !path.equals("") ){//judge there is a path of file
			cost = readTxt(path);
			if(cost != null){//judge whether the array is null
				
				for(int i=0;i<cost.length;i++){
					if(i==0)
					matrix = "Review original topology: \n";
					for(j=0;j<cost.length;j++){
						matrix += String.valueOf(cost[i][j])+"\t";
					if(j==cost.length-1){
						matrix = matrix +"\n";
					}
					}
				}
				
//				topologyMatrix.setText("Reciew original topology matrix: <br>"+matrix);
				
				textArea.setText(matrix);
				
				SP.setEnabled(true);
				MaT.setEnabled(true);
				BCT.setEnabled(true);
			}
			}
			else{
				JOptionPane.showMessageDialog(null,"Please input the path of file! ", "System Information", JOptionPane.ERROR_MESSAGE);
				textArea.setText("");
			}
			
		}
		
		
	}
}
