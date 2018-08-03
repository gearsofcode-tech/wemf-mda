package com.gearsofcode.wemf.ui;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class WEMFCodeGenApplication extends JFrame{

	private static final long serialVersionUID = -4329843960803908233L;
	
	
	private JPanel mainPanel;
	private CodegenPanel codegenPanel;
	
	public WEMFCodeGenApplication() {
		super("WEMFCodeGen");
		setSize(new Dimension(980,380));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel();
		mainPanel.setLayout(new GridBagLayout());
		setContentPane(mainPanel);
		
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx=0;
		gbc.gridy=0;
		gbc.fill = GridBagConstraints.BOTH;
		codegenPanel = new CodegenPanel();
		mainPanel.add(codegenPanel, gbc);
	}	
	
	
	public static void main(String args[]) {
		WEMFCodeGenApplication app = new WEMFCodeGenApplication();
		app.setVisible(true);
	}
}
