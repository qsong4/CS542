package RoutingSimulator;

import javax.swing.JFileChooser;



public class txtFileFilter extends javax.swing.filechooser.FileFilter{
	
	
  public boolean accept(java.io.File f) {
    if (f.isDirectory())return true;
    return f.getName().endsWith(".txt"); 
  } 
  public String getDescription(){
    return ".txt";
  }
}