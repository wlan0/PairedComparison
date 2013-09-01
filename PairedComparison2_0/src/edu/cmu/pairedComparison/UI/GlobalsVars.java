package edu.cmu.pairedComparison.UI;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;

import edu.cmu.pairedComparison2_0.MatrixTable;

public class GlobalsVars {
	private MatrixTable table;
	private Integer artifactCount;
	private Integer replicationFactor;
	private JTree tree;
	private Double confidenceLevel;
	private JTable Resultstable;
	private JTabbedPane tabbedPane;
	private JPanel propertyPane;
	private HashMap<String, String> comparisonNotes;
	private Boolean calculated;
	private JFrame MainFrame;
	private GlobalsVars()
	{
		comparisonNotes = new HashMap<String, String>();
	}

	private static GlobalsVars instance;
	
	public static GlobalsVars getInstance()
	{
		if(instance == null)
			instance = new GlobalsVars();
		return instance;
	}
	
	public MatrixTable getMatrix()
	{
		return table;
	}
	
	public void setMatrix(MatrixTable table)
	{
		this.table = table;
	}
	
	public void setArtifactCount(int artifactCount)
	{
		this.artifactCount = artifactCount;
	}
	
	public Integer getArtifactCount()
	{
		return artifactCount;
	}
	public void setReplicationFactor(int replicationFactor)
	{
		this.replicationFactor = replicationFactor;
	}
	
	public Integer getReplicationFactor()
	{
		return replicationFactor;
	}
	
	public void setTree(JTree tree)
	{
		this.tree = tree;
	}
	
	public JTree getTree()
	{
		return tree;
	}

	public void setConfidenceLevel(Double confidenceLevel)
	{
		this.confidenceLevel = confidenceLevel;
	}
	
	public Double getConfidenceLevel()
	{
		return confidenceLevel;
	}
	
	public void setTable(JTable table)
	{
		this.Resultstable = table;
	}
	
	public JTable getTable()
	{
		return Resultstable;
	}
	
	public void setTabbedPane(JTabbedPane tabbedPane)
	{
		this.tabbedPane = tabbedPane;
	}
	
	public JTabbedPane getTabbedPane()
	{
		return tabbedPane;
	}
	
	public JPanel getPropertyPane()
	{
		return propertyPane;
	}
	public void setPropertyPane(JPanel propertyPane)
	{
		this.propertyPane = propertyPane;
	}
	
	public void setComparisonNotes(String Key, String Value)
	{
		comparisonNotes.put(Key, Value);
	}
	public String getComparisonNotes(String Key)
	{
		return comparisonNotes.get(Key);
	}
	public void setCalculated(Boolean flag)
	{
		calculated = flag;
	}
	public Boolean isCalculated()
	{
		return calculated;
	}
	public void setMainFrame(JFrame frame)
	{
		MainFrame = frame;
	}
	public JFrame getMainFrame()
	{
		return MainFrame;
	}
}
