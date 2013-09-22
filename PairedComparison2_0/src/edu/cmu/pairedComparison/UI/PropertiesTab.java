package edu.cmu.pairedComparison.UI;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;

import com.explodingpixels.macwidgets.HudWidgetFactory;

import edu.cmu.pairedComparison2_0.Calculator;
import edu.cmu.pairedComparison2_0.MatrixTable;
import edu.cmu.pairedComparison2_0.MatrixTableModel;

public class PropertiesTab extends JPanel{
	private static final long serialVersionUID = 2535656614921532085L;
	JTextField RepicationFactortextField;
	JTextField ArtifactCountTextField;
	JTextField NamePaneTextField;
	JTextField ConfidenceLeveltextField;
	JButton calcButton;
	JTextArea ArtifactNotes1TextField;
	JTextArea ArtifactNotes2TextField;
	GlobalsVars globals;
	JPanel ArtifactNotesPane;
	JPanel ComparisonNotesPane;
	JTextArea ComparisonNotesArea;
	JTextArea NotesArea;
	Integer rowx;
	Integer colx;
	public void setNotesVisibility(Boolean flag, int row, int col)
	{
		ComparisonNotesArea.setEditable(flag);
		ArtifactNotes1TextField.setEditable(flag);
		ArtifactNotes2TextField.setEditable(flag);
		if(flag)
		{
			MatrixTableModel model = (MatrixTableModel) globals.getMatrix().getModel();
			if(model.getArtifactNotes(row-1) != null)
			{
				ArtifactNotes1TextField.setText(model.getArtifactNotes(row-1));
			}
			else
				ArtifactNotes1TextField.setText(" ");
			if(model.getArtifactNotes(col - 2) != null)				
			{
				ArtifactNotes2TextField.setText(model.getArtifactNotes(col-2));
			}
			else
				ArtifactNotes2TextField.setText(" ");
			rowx = row;
			colx = col;
			if(model.getComparisonNotes(rowx.toString()+"_"+colx) != null)
			{
				ComparisonNotesArea.setText((String)model.getComparisonNotes(rowx.toString()+"_"+colx));
			}
			else
				ComparisonNotesArea.setText("");
		}
	}
	
	public PropertiesTab()
	{
		globals = GlobalsVars.getInstance();
		globals.setPropertyPane(this);
		JPanel propertiesTabContainer = new JPanel();
		propertiesTabContainer.setLayout(new BoxLayout(propertiesTabContainer, BoxLayout.LINE_AXIS));
		JPanel propertiesTab = new JPanel();
		propertiesTab.setLayout(new BoxLayout(propertiesTab, BoxLayout.PAGE_AXIS));
		propertiesTab.setBackground(Color.DARK_GRAY);
		JLabel ReplicationFactorlabel = HudWidgetFactory.createHudLabel("Replication Factor : ");
		ReplicationFactorlabel.setHorizontalAlignment(SwingConstants.RIGHT);
		ReplicationFactorlabel.setFont(new Font("Lucida Grande",1,13));
		RepicationFactortextField = HudWidgetFactory.createHudTextField("");
		RepicationFactortextField.setFont(new Font("Lucida Grande",1,13));
		RepicationFactortextField.setText("2");
		JLabel ConfidenceLevelLabel = HudWidgetFactory.createHudLabel(" Confidence Level : ");
		ConfidenceLevelLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		ConfidenceLevelLabel.setFont(new Font("Lucida Grande",1,13));
		ConfidenceLeveltextField = HudWidgetFactory.createHudTextField("");
		ConfidenceLeveltextField.setFont(new Font("Lucida Grande",1,13));
		JLabel NamePaneLabel = HudWidgetFactory.createHudLabel("                    Name : ");
		NamePaneLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		NamePaneLabel.setFont(new Font("Lucida Grande",1,13));
		NamePaneTextField = HudWidgetFactory.createHudTextField("");
		NamePaneTextField.setFont(new Font("Lucida Grande",1,13));
		NamePaneTextField.setText("Group 1");
		JPanel NamePane = new JPanel();
		NamePane.setLayout(new BoxLayout(NamePane, BoxLayout.LINE_AXIS));
		NamePane.setBackground(Color.DARK_GRAY);
		NamePane.add(NamePaneLabel);
		NamePane.add(NamePaneTextField);
		propertiesTab.add(Box.createRigidArea(new Dimension(25,50)));
		propertiesTab.add(NamePane);
		JPanel confidenceIntervalPane = new JPanel();
		confidenceIntervalPane.setLayout(new BoxLayout(confidenceIntervalPane, BoxLayout.LINE_AXIS));
		confidenceIntervalPane.setBackground(Color.DARK_GRAY);
		confidenceIntervalPane.add(ConfidenceLevelLabel);
		confidenceIntervalPane.add(ConfidenceLeveltextField);
		propertiesTab.add(Box.createRigidArea(new Dimension(25,10)));
		propertiesTab.add(confidenceIntervalPane);
		propertiesTab.add(Box.createRigidArea(new Dimension(25,10)));
		JPanel replicationFactorPane = new JPanel();
		replicationFactorPane.setLayout(new BoxLayout(replicationFactorPane, BoxLayout.LINE_AXIS));
		replicationFactorPane.setBackground(Color.DARK_GRAY);
		replicationFactorPane.add(ReplicationFactorlabel);
		replicationFactorPane.add(RepicationFactortextField);
		propertiesTab.add(replicationFactorPane);
		propertiesTabContainer.setBackground(Color.DARK_GRAY);
		JLabel ArtifactCountLabel = HudWidgetFactory.createHudLabel("       Artifact Count : ");
		ArtifactCountLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		ArtifactCountLabel.setFont(new Font("Lucida Grande",1,13));
		ArtifactCountTextField = HudWidgetFactory.createHudTextField("");
		ArtifactCountTextField.setText("12");
		ArtifactCountTextField.setFont(new Font("Lucida Grande",1,13));
		JPanel ArtifactCountPane = new JPanel();
		ArtifactCountPane.setLayout(new BoxLayout(ArtifactCountPane, BoxLayout.LINE_AXIS));
		ArtifactCountPane.setBackground(Color.DARK_GRAY);
		ArtifactCountPane.add(ArtifactCountLabel);
		ArtifactCountPane.add(ArtifactCountTextField);
		propertiesTab.add(Box.createRigidArea(new Dimension(25,10)));
		propertiesTab.add(ArtifactCountPane);
		propertiesTabContainer.add(propertiesTab);
		propertiesTabContainer.add(Box.createRigidArea(new Dimension(25,10)));
		JPanel propertiesRightTab = new JPanel();
		JLabel NotesLabel = HudWidgetFactory.createHudLabel("Group Notes : ");
		NotesLabel.setFont(new Font("Lucida Grande",1,13));
		propertiesRightTab.setLayout(new BoxLayout(propertiesRightTab,BoxLayout.PAGE_AXIS));
		NotesArea = new JTextArea();
		NotesArea.setBackground(Color.DARK_GRAY);
		NotesArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		NotesArea.setPreferredSize(new Dimension(200, 100));
		NotesArea.setLineWrap(true);
		NotesArea.setForeground(Color.WHITE);
		JPanel NotesLabelPane = new JPanel();
		NotesLabelPane.add(NotesLabel);
		NotesLabelPane.setBackground(Color.DARK_GRAY);
		propertiesRightTab.add(Box.createRigidArea(new Dimension(25,30)));
		propertiesRightTab.add(NotesLabelPane);
		JScrollPane NotesLabelPanex = new JScrollPane(NotesArea);
		propertiesRightTab.add(NotesLabelPanex);
		propertiesRightTab.add(Box.createRigidArea(new Dimension(25,30)));
		calcButton = HudWidgetFactory.createHudButton("        Calculate          ");
		propertiesTab.add(Box.createRigidArea(new Dimension(25,25)));
		propertiesTab.add(calcButton);
		propertiesRightTab.setBackground(Color.DARK_GRAY);
		propertiesTabContainer.add(propertiesRightTab);
		ArtifactNotesPane = new JPanel();
		ArtifactNotesPane.setLayout(new BoxLayout(ArtifactNotesPane, BoxLayout.PAGE_AXIS));
		JLabel ArtifactNotes1Label = HudWidgetFactory.createHudLabel("    Notes for Artifact 1 :     ");
		JLabel ArtifactNotes2Label = HudWidgetFactory.createHudLabel("    Notes for Artifact 2 :     ");
		ArtifactNotes1Label.setFont(new Font("Lucida Grande",1,13));
		ArtifactNotes2Label.setFont(new Font("Lucida Grande",1,13));
		ArtifactNotes1TextField = new JTextArea();
		ArtifactNotes2TextField = new JTextArea();
		ArtifactNotes1TextField.setBackground(Color.DARK_GRAY);
		ArtifactNotes2TextField.setBackground(Color.DARK_GRAY);
		ArtifactNotes1TextField.setForeground(Color.WHITE);
		ArtifactNotes2TextField.setForeground(Color.WHITE);
		ArtifactNotes1TextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		ArtifactNotes2TextField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		ArtifactNotesPane.add(Box.createRigidArea(new Dimension(25,10)));
		ArtifactNotesPane.setBackground(Color.DARK_GRAY);
		ArtifactNotesPane.add(ArtifactNotes1Label);
		JScrollPane ArtifactNotes1TextFieldPane = new JScrollPane(ArtifactNotes1TextField);
		JScrollPane ArtifactNotes2TextFieldPane = new JScrollPane(ArtifactNotes2TextField);
		ArtifactNotesPane.add(ArtifactNotes1TextFieldPane);
		ArtifactNotesPane.add(ArtifactNotes2Label);
		ArtifactNotesPane.add(ArtifactNotes2TextFieldPane);
		propertiesTabContainer.add(Box.createRigidArea(new Dimension(25,10)));
		ArtifactNotes1TextField.setEditable(false);
		ArtifactNotes2TextField.setEditable(false);
		propertiesTabContainer.add(ArtifactNotesPane);
		ComparisonNotesPane = new JPanel();
		ComparisonNotesPane.setBackground(Color.DARK_GRAY);
		ComparisonNotesPane.setLayout(new BoxLayout(ComparisonNotesPane, BoxLayout.PAGE_AXIS));
		JLabel ComparisonNotesLabel = HudWidgetFactory.createHudLabel("     Comparison notes :    ");
		ComparisonNotesLabel.setFont(new Font("Lucida Grande",1,13));
		ComparisonNotesPane.add(Box.createRigidArea(new Dimension(25,20)));
		ComparisonNotesPane.add(ComparisonNotesLabel);
		ComparisonNotesPane.add(Box.createRigidArea(new Dimension(25,10)));
		ComparisonNotesArea = new JTextArea();
		ComparisonNotesArea.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		ComparisonNotesArea.setBackground(Color.DARK_GRAY);
		ComparisonNotesArea.setForeground(Color.WHITE);
		JScrollPane ComparisonNotesAreaPane = new JScrollPane(ComparisonNotesArea);
		ComparisonNotesPane.add(ComparisonNotesAreaPane);
		ComparisonNotesArea.setEditable(false);
		propertiesTabContainer.add(Box.createRigidArea(new Dimension(25,10)));
		propertiesTabContainer.add(ComparisonNotesPane);
		this.add(propertiesTabContainer);
		this.setBackground(Color.DARK_GRAY);
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		//Events
		RepicationFactortextField.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				ReplicationFactorActionPerformed();
			}
			
		});
		
		ArtifactCountTextField.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				ArtifactCountActionPerformed();
			}
			
		});
		
		NamePaneTextField.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				String text = NamePaneTextField.getText();
				JTree tree = globals.getTree();
				DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
				DefaultMutableTreeNode child = (DefaultMutableTreeNode) root.getChildAt(0);
				child.setUserObject(text);
				tree.updateUI();
			}
		});
		
		ConfidenceLeveltextField.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				Double confidenceLevel = Double.parseDouble(ConfidenceLeveltextField.getText());
				try 
				{
					if(confidenceLevel < 0 || confidenceLevel > 100)
						throw new Exception("Confidence Level should be between 0 and 100(inclusive)");
					confidenceLevel = confidenceLevel/100;
					DecimalFormat df = new DecimalFormat("#.##");
					ConfidenceLeveltextField.setText(df.format(confidenceLevel));
					globals.setConfidenceLevel(confidenceLevel);
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());					
				}
			}
		});
		
		calcButton.addActionListener(new ActionListener()
		{
			double[] absoluteSizeStdDev;
			double[] absoluteSize;
			double inconsistencyIndex;
			double sumAbsoluteSizeStdDev;
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{
					globals.setCalculated(true);
					MatrixTable table = globals.getMatrix();
					MatrixTableModel model = (MatrixTableModel) table.getModel();
					if(model.getComparisonsRemainingFromDesign() != 0)
						throw new Exception("Please fill in all the fields that are white");
					if(globals.getConfidenceLevel() == null)
						throw new Exception("Please fill in the confidence Level");
					// get input values
					String[] artifacts = model.getArtifacts();
					double[][] judgmentMatrix = model
							.getJudgmentMatrix();
					boolean[][] designMatrix = model
							.getDesignMatrix();
					int referenceId = model.getReferenceIndex();
					double referenceSize = model.getReferenceSize();

					// validate input
					for (int i = 0; i < artifacts.length; i++) {
						if (artifacts[i] == null || artifacts[i].equals("")) {
							JOptionPane.showMessageDialog(null,"\nPlease enter all artifact names.\n");
							return;
						}
					}
					for (int i = 0; i < judgmentMatrix.length; i++) {
						for (int j = 0; j < judgmentMatrix[i].length; j++) {
							if (designMatrix[i][j] && judgmentMatrix[i][j] == 0.0) {
								JOptionPane.showMessageDialog(null,"\nPlease enter all relative indices.\n");
								return;
							}
						}
					}
					if (referenceId == -1) {
						JOptionPane.showMessageDialog(null,"\nPlease enter reference value.\n");
						return;
					}
					Calculator calculator = Calculator.getInstance();
					// paired comparison calculations
					judgmentMatrix = calculator.calcReciprocals(designMatrix,
							judgmentMatrix, globals.getArtifactCount());
					judgmentMatrix = calculator.fillJudgmentMatrix(
							judgmentMatrix, globals.getArtifactCount());
					inconsistencyIndex = calculator
							.calcInconsistencyIndex(judgmentMatrix, globals.getArtifactCount(),
									globals.getReplicationFactor());
					double[] relativeSize = calculator.calcRatio(judgmentMatrix,
							judgmentMatrix.length);
					absoluteSize = calculator.calcAbsoluteSize(
							referenceSize, referenceId, relativeSize, globals.getArtifactCount());
					double[] lowerLimit = calculator.calcLowerLimit(absoluteSize,globals.getArtifactCount(),judgmentMatrix,globals.getReplicationFactor(),globals.getConfidenceLevel());
					double[] upperLimit = calculator.calcUpperLimit(absoluteSize,globals.getArtifactCount(),judgmentMatrix,globals.getReplicationFactor(),globals.getConfidenceLevel());
					
					absoluteSizeStdDev = calculator
							.calcAbsoluteSizeStdDev(absoluteSize, inconsistencyIndex,
									globals.getArtifactCount());
					sumAbsoluteSizeStdDev = calculator
							.calcSumAbsoluteSizeStdDev(absoluteSizeStdDev,
									globals.getArtifactCount());
					String[] columnNames = new String[]{"Artifact Name", "Abs. Size", "Abs. Std. Deviation","Lower Limit","Upper Limit","Inconsistency Index","Sum absolute std. deviation"};
					String[][] data = new String[globals.getArtifactCount()][7];
					for (int i = 0; i < globals.getArtifactCount(); i++) 
					{
						data[i][0] = model.getArtifacts()[i];
						data[i][1] = new Double(absoluteSize[i]).toString();
						data[i][2] = new Double(absoluteSizeStdDev[i]).toString();
						data[i][3] = new Double(lowerLimit[i]).toString();
						data[i][4] = new Double(upperLimit[i]).toString();
						data[i][5] = new Double(inconsistencyIndex).toString();
						data[i][6] = new Double(sumAbsoluteSizeStdDev).toString();		
					}
					DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
					JTable Resultstable = globals.getTable();
					Resultstable.setModel(tableModel);
					GlobalsVars.getInstance().getTabbedPane().setSelectedIndex(1);
					JTree tree = GlobalsVars.getInstance().getTree();
					DefaultMutableTreeNode root = (DefaultMutableTreeNode) tree.getModel().getRoot();
					DefaultMutableTreeNode node = (DefaultMutableTreeNode) root.getChildAt(0);
					for(int i=0;i<globals.getArtifactCount();i++)
					{
						node.add(new DefaultMutableTreeNode(model.getArtifacts()[i]));
					}
				}
				catch(Exception e)
				{
					JOptionPane.showMessageDialog(null, e.getMessage());										
				}			
			}
			
		});
		
		ComparisonNotesArea.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent arg0) {
				{
					MatrixTableModel model = (MatrixTableModel) globals.getMatrix().getModel();
					model.setComparisonNotes(rowx.toString()+"_"+colx.toString(), ComparisonNotesArea.getText());	
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				{
					MatrixTableModel model = (MatrixTableModel) globals.getMatrix().getModel();
					model.setComparisonNotes(rowx.toString()+"_"+colx.toString(), ComparisonNotesArea.getText());	
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) 
			{
	
			}
		});
		
		ArtifactNotes1TextField.addKeyListener(new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent arg0) {
				//if(arg0.getKeyChar() == KeyEvent.VK_ENTER)
				{	
					MatrixTableModel model = (MatrixTableModel) globals.getMatrix().getModel();
					model.setArtifactNotes(rowx-1,ArtifactNotes1TextField.getText());	
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				{	
					MatrixTableModel model = (MatrixTableModel) globals.getMatrix().getModel();
					model.setArtifactNotes(rowx-1,ArtifactNotes1TextField.getText());	
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		ArtifactNotes2TextField.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent arg0) {
				//if(arg0.getKeyChar() == KeyEvent.VK_ENTER)
				{
					MatrixTableModel model = (MatrixTableModel) globals.getMatrix().getModel();
					model.setArtifactNotes(colx-2,ArtifactNotes2TextField.getText());	
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				{	
					MatrixTableModel model = (MatrixTableModel) globals.getMatrix().getModel();
					model.setArtifactNotes(colx-2,ArtifactNotes2TextField.getText());	
				}
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
		});
		NotesArea.addKeyListener(new KeyListener()
		{

			@Override
			public void keyPressed(KeyEvent arg0) {
				if(arg0.getKeyChar() == KeyEvent.VK_ENTER)
				{
					MatrixTableModel model = (MatrixTableModel) globals.getMatrix().getModel();
					model.setModelNotes(NotesArea.getText());	
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
			}
			
		});
	}
	
	public void setArtifactCountField(String text)
	{
		ArtifactCountTextField.setText(text);
	}
	
	public void setReplicationFactorField(String text)
	{
		RepicationFactortextField.setText(text);
	}
	
	public void ArtifactCountActionPerformed()
	{
		String text = ArtifactCountTextField.getText();
		try
		{
			Integer newArtifactCount = Integer.parseInt(text);
			if(newArtifactCount < 2)
				throw new Exception("Enter a value >= 2");
			MatrixTable table = globals.getMatrix();
			MatrixTableModel model = (MatrixTableModel) table.getModel();				
			if (globals.getReplicationFactor() > newArtifactCount) {
				globals.setReplicationFactor(newArtifactCount);
				RepicationFactortextField.setText(newArtifactCount.toString());
			}

			// copy old data to new. note that if the new size is
			// smaller, you will lose data.
			model = model.getCopy(newArtifactCount, globals.getReplicationFactor());
			table.setModel(model);
			table.refresh();
			table.updateUI();
			globals.setArtifactCount(newArtifactCount);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
	}
	
	public void ReplicationFactorActionPerformed()
	{
		String text = RepicationFactortextField.getText();
		try 
		{
			Integer newReplicationFactor = Integer.parseInt(text);
			if(newReplicationFactor < 2)
				throw new Exception("Enter a number >= 2");
			int artifactCount = globals.getArtifactCount();
			if(newReplicationFactor > artifactCount)
				throw new Exception("Enter a number <= artifactCount");
			MatrixTable table = globals.getMatrix();
			MatrixTableModel model = (MatrixTableModel) table.getModel();
			model.setReplicationFactor(newReplicationFactor);
			model.resetDesign();
			model.fireTableDataChanged();
			table.updateUI();
			globals.setReplicationFactor(newReplicationFactor);
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
			RepicationFactortextField.setText(globals.getReplicationFactor().toString());
		}
	}
	
	public void changeName(String newName)
	{
		NamePaneTextField.setText(newName);
	}
}
