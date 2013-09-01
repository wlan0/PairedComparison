package edu.cmu.pairedComparison.UI;

import javax.swing.JComponent;
import javax.swing.JFrame;

public class ToolBarHandler {

	private ToolBar toolBar;
	
	public ToolBarHandler(JFrame frame)
	{
		toolBar = new ToolBar(frame);
		this.createDefaultToolBar();
	}
	
	private void createDefaultToolBar()
	{
		toolBar.addMenuItem("File", "Open","Save");
		//toolBar.addMenuItem("Edit", null, null);
		toolBar.addMenuItem("Help", "About Us");
	}
	
	public JComponent getComponent()
	{
		return toolBar.getComponent();
	}
	
}
