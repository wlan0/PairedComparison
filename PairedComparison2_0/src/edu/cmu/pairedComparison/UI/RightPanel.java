package edu.cmu.pairedComparison.UI;

import javax.swing.BoxLayout;
import javax.swing.JPanel;


public class RightPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	RightTopPane rightTop;
	RightBottomPane rightBottom;
	public RightPanel(int artifactCount, int replicationFactor, int width)	
	{
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		rightTop = new RightTopPane(artifactCount, replicationFactor);
		rightBottom = new RightBottomPane(width);
		this.add(rightTop);
		this.add(rightBottom);
	}	
	
	public void updateMatrixTable(int artifactCount, int replicationFactor)
	{
		rightTop.resetDesign(artifactCount, replicationFactor);
	}
}
