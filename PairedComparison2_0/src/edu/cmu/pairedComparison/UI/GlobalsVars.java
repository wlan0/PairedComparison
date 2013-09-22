package edu.cmu.pairedComparison.UI;

import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTree;

import edu.cmu.pairedComparison2_0.MatrixTable;

public class GlobalsVars {
	private MatrixTable table[];
	private Integer artifactCount[];
	private Integer replicationFactor[];
	private JTree tree;
	private Double confidenceLevel[];
	private JTable Resultstable[];
	private JTabbedPane tabbedPane[];
	private JPanel propertyPane[];
	private HashMap<String, String> comparisonNotes;
	private Boolean calculated[];
	private JFrame MainFrame;
	public RightPanel rightPanel[];
	int curr;
	int MAX;
	int maxIndex;
	private GlobalsVars()
	{
		MAX = 100;
		curr = 0;
		maxIndex = 0;
		comparisonNotes = new HashMap<String, String>();
		table = new MatrixTable[MAX];
		artifactCount = new Integer[MAX];
		replicationFactor = new Integer[MAX];
		confidenceLevel = new Double[MAX];
		Resultstable = new JTable[MAX];
		tabbedPane = new JTabbedPane[MAX];
		propertyPane = new JPanel[MAX];
		calculated = new Boolean[MAX];
		rightPanel = new RightPanel[MAX];
		
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
		return table[curr];
	}
	
	public void setMatrix(MatrixTable table)
	{
		this.table[curr] = table;
	}
	
	public void setArtifactCount(int artifactCount)
	{
		this.artifactCount[curr] = artifactCount;
	}
	
	public Integer getArtifactCount()
	{
		return artifactCount[curr];
	}
	public void setReplicationFactor(int replicationFactor)
	{
		this.replicationFactor[curr] = replicationFactor;
	}
	
	public Integer getReplicationFactor()
	{
		return replicationFactor[curr];
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
		this.confidenceLevel[curr] = confidenceLevel;
	}
	
	public Double getConfidenceLevel()
	{
		return confidenceLevel[curr];
	}
	
	public void setTable(JTable table)
	{
		this.Resultstable[curr] = table;
	}
	
	public JTable getTable()
	{
		return Resultstable[curr];
	}
	
	public void setTabbedPane(JTabbedPane tabbedPane)
	{
		this.tabbedPane[curr] = tabbedPane;
	}
	
	public JTabbedPane getTabbedPane()
	{
		return tabbedPane[curr];
	}
	
	public JPanel getPropertyPane()
	{
		return propertyPane[curr];
	}
	public void setPropertyPane(JPanel propertyPane)
	{
		this.propertyPane[curr] = propertyPane;
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
		calculated[curr] = flag;
	}
	public Boolean isCalculated()
	{
		return calculated[curr];
	}
	public void setMainFrame(JFrame frame)
	{
		MainFrame = frame;
	}
	public JFrame getMainFrame()
	{
		return MainFrame;
	}
	public Integer getCurrIndex()
	{
		return curr;
	}
	public void setCurrIndex(Integer index)
	{
		curr = index;
		if(curr > maxIndex)
			maxIndex = curr;
	}
	public void setRightPanel(int artifcatCount, int replicationFactor, int width)
	{
		rightPanel[++curr] = new RightPanel(artifcatCount,replicationFactor,width);
	}
	public void notifyMainWindow()
	{
		((MainWindow)MainFrame).changeRightPanel(rightPanel[curr]);
	}
}
