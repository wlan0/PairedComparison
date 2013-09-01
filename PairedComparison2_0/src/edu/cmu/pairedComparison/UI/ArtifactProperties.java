package edu.cmu.pairedComparison.UI;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;

public class ArtifactProperties extends JPanel{

	private static final long serialVersionUID = 8733744878326633209L;

	JTextField name = new JTextField(); 
	
	public ArtifactProperties()
	{
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		name.setBackground(Color.DARK_GRAY);
		name.setForeground(Color.WHITE);
		this.add(name);
	}
}
