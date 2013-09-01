package edu.cmu.pairedComparison.UI;

import javax.swing.JScrollPane;
public class LeftPanel extends JScrollPane
{
	private static final long serialVersionUID = 1L;
	LeftPanelImpl child;
	public LeftPanel()
	{
		child = new LeftPanelImpl();
		child.addNewGroup("Group 1");
		this.setViewportView(child);
	}
}
