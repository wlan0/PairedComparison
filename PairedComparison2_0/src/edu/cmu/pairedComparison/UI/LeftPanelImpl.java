package edu.cmu.pairedComparison.UI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;

import com.explodingpixels.macwidgets.HudWidgetFactory;

public class LeftPanelImpl extends JPanel
{
	private static final long serialVersionUID = 1L;
	JTree tree;
	DefaultMutableTreeNode root;
	HashMap<String, DefaultMutableTreeNode> groups;
	public LeftPanelImpl()
	{
		groups = new HashMap<String, DefaultMutableTreeNode>();
		tree = new JTree();
		GlobalsVars.getInstance().setTree(tree);
		TreeCellRenderer r = tree.getCellRenderer();
		((DefaultTreeCellRenderer) r).setBackgroundNonSelectionColor(Color.DARK_GRAY);
		((DefaultTreeCellRenderer) r).setBackgroundSelectionColor(Color.white);
		((DefaultTreeCellRenderer) r).setTextSelectionColor(Color.DARK_GRAY); 
		((DefaultTreeCellRenderer) r).setTextNonSelectionColor(Color.white);
		((DefaultTreeCellRenderer) r).setIcon(null);
		((DefaultTreeCellRenderer) r).setLabelFor(null);	
		root = new DefaultMutableTreeNode("Project");
		tree.setModel(new DefaultTreeModel(root));
		this.setLayout(new BorderLayout());
		tree.setBackground(Color.WHITE);
		this.add(tree,BorderLayout.CENTER);
		this.setBackground(Color.DARK_GRAY);
		tree.setBackground(Color.DARK_GRAY);
		tree.setForeground(Color.WHITE);
		JButton xbutton = HudWidgetFactory.createHudButton("        + Add Group          ");
		
		this.add(xbutton,BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBorder(BorderFactory.createEtchedBorder());
		
		//Events
		xbutton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				JOptionPane.showMessageDialog(null,"Only one group is supported in this version.");
			}
			
		});
	}
	
	public void addNewGroup(String groupName)
	{
		DefaultMutableTreeNode newGroup = new DefaultMutableTreeNode(groupName);
		root.add(newGroup);
		groups.put(groupName, newGroup);

	}
	
	public void addNewArtifact(String ArtifactName, String Parent)
	{
		if(!groups.containsKey(Parent))
			throw new IllegalArgumentException();
		DefaultMutableTreeNode group = groups.get(Parent);
		DefaultMutableTreeNode artifact = new DefaultMutableTreeNode(ArtifactName);
		group.add(artifact);
	}
	
}
