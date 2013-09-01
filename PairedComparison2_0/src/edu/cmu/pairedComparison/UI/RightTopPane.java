package edu.cmu.pairedComparison.UI;

import java.awt.Color;
import javax.swing.JScrollPane;

public class RightTopPane extends JScrollPane
{
	private static final long serialVersionUID = 8469898229485009547L;
	MatrixTableHandler handler;
	public RightTopPane(int artifactCount, int replicationFactor)
	{
		handler = new MatrixTableHandler(artifactCount, replicationFactor);
		this.setBackground(Color.WHITE);
		this.setViewportView(handler);
	}
	
	public void resetDesign(int artifactCount, int replicationFactor)
	{
		handler.resetDesign(artifactCount, replicationFactor);
	}
}
