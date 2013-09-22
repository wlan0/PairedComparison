package edu.cmu.pairedComparison.UI;


import javax.swing.JTabbedPane;

public class RightBottomPane extends JTabbedPane
{
	private static final long serialVersionUID = 1865721232438614295L;

	ArtifactProperties artifact;
	
	public RightBottomPane(int width)
	{
		this.setTabPlacement(JTabbedPane.TOP);
		//results tab
		ResultsTab rightBottom = new ResultsTab(width);
		//properties tab
		PropertiesTab propertiesTab = new PropertiesTab();
		artifact = new ArtifactProperties();
		this.addTab("Properties",propertiesTab);
		this.addTab("Results",rightBottom);
		GlobalsVars.getInstance().setTabbedPane(this);
	}
	
	public void addTabb()
	{
		this.addTab("Artifact",artifact);
	}
	
	public void removeTabb()
	{
		this.removeTabAt(2);
	}
}
