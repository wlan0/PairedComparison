package edu.cmu.pairedComparison.UI;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import edu.cmu.pairedComparison2_0.MatrixTable;
import edu.cmu.pairedComparison2_0.MatrixTableModel;

public class MatrixTableHandler extends JPanel
{
	private static final long serialVersionUID = 4189855860908980571L;
	private MatrixTable table;
	private MatrixTableModel model;
	public MatrixTableHandler(int artifactCount, int replicationFactor)
	{
		model = new MatrixTableModel(artifactCount,replicationFactor);
		table = new MatrixTable(model);
		table.setBackground(Color.DARK_GRAY);
		table.setBorder(BorderFactory.createEtchedBorder());
		this.add(table);
		GlobalsVars.getInstance().setMatrix(table);
		GlobalsVars.getInstance().setArtifactCount(artifactCount);	
		GlobalsVars.getInstance().setReplicationFactor(replicationFactor);	
	}
	
	public void resetDesign(int artifactCount, int replicationFactor)
	{
		model.setArtifactCount(artifactCount);
		model.setReplicationFactor(replicationFactor);
		model.resetDesign();
		model.fireTableDataChanged();
		table.updateUI();
		GlobalsVars.getInstance().setArtifactCount(artifactCount);	
	}
}
