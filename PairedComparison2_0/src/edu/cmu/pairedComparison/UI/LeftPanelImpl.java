package edu.cmu.pairedComparison.UI;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.event.TreeModelEvent;
import javax.swing.event.TreeModelListener;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeCellRenderer;
import javax.swing.tree.TreePath;

import com.explodingpixels.macwidgets.HudWidgetFactory;

public class LeftPanelImpl extends JPanel
{
	private static final long serialVersionUID = 1L;
	JTree tree;
	DefaultMutableTreeNode root;
	HashMap<String, DefaultMutableTreeNode> groups;
	int i;
	DefaultTreeModel treeModel;
	public LeftPanelImpl()
	{
		i = 2;
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
		tree.setEditable(true);
		root = new DefaultMutableTreeNode("Project");
		treeModel = new DefaultTreeModel(root);
		tree.setModel(treeModel);
		this.setLayout(new BorderLayout());
		tree.setBackground(Color.WHITE);
		this.add(tree,BorderLayout.CENTER);
		this.setBackground(Color.DARK_GRAY);
		tree.setBackground(Color.DARK_GRAY);
		tree.setForeground(Color.WHITE);
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.LINE_AXIS));
		buttonPanel.setBackground(Color.DARK_GRAY);
		JButton xbutton = HudWidgetFactory.createHudButton("   + Add    ");
		JButton ybutton = HudWidgetFactory.createHudButton("- Remove ");
		buttonPanel.add(xbutton);
		buttonPanel.add(Box.createRigidArea(new Dimension(5,10)));
		buttonPanel.add(ybutton);
		this.add(buttonPanel,BorderLayout.SOUTH);
		this.setBorder(BorderFactory.createLineBorder(Color.black));
		this.setBorder(BorderFactory.createEtchedBorder());
		
		//Events
		xbutton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				addNewGroup("Group" + Integer.toString(i));
				i = i +1;
			}
			
		});
		
		ybutton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				TreePath path = tree.getSelectionPath();
				treeModel.removeNodeFromParent((DefaultMutableTreeNode)path.getLastPathComponent());
			}
			
		});
		
		treeModel.addTreeModelListener(new TreeModelListener()
		{

			@Override
			public void treeNodesChanged(TreeModelEvent arg0) {
				if(arg0.getChildIndices() != null && arg0.getChildIndices()[0] != -1)
				{
					DefaultMutableTreeNode node = (DefaultMutableTreeNode)arg0.getTreePath().getLastPathComponent();
					node = (DefaultMutableTreeNode)node.getChildAt(arg0.getChildIndices()[0]);
					String newName = (String)node.getUserObject();
					PropertiesTab panel = (PropertiesTab)GlobalsVars.getInstance().getPropertyPane();
					panel.changeName(newName);
				}
			}

			@Override
			public void treeNodesInserted(TreeModelEvent arg0) {
				repaint();
			}

			@Override
			public void treeNodesRemoved(TreeModelEvent arg0) {
				repaint();				
			}

			@Override
			public void treeStructureChanged(TreeModelEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		
	}
	
	public void addNewGroup(String groupName)
	{
		DefaultMutableTreeNode newGroup = new DefaultMutableTreeNode(groupName);
		DefaultMutableTreeNode parent = null;
		TreePath parentPath = tree.getSelectionPath();
		if(parentPath == null)
		{
			parent = root;
		}
		else
		{
			JOptionPane.showMessageDialog(GlobalsVars.getInstance().getMainFrame(), "Children can only be added to Project, not to groups");
			return;
		}
		treeModel.insertNodeInto(newGroup, parent, parent.getChildCount());
		tree.expandPath(new TreePath(newGroup.getPath()));

	}
}
