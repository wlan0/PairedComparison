package edu.cmu.pairedComparison.UI;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import com.explodingpixels.macwidgets.MacUtils;

public class ToolBar
{
	private GlobalsVars globals;
	private JMenuBar toolBar;
	
	public ToolBar(JFrame frame)
	{
		MacUtils.makeWindowLeopardStyle(frame.getRootPane());
		toolBar =  new JMenuBar();
		toolBar.setBackground(Color.GRAY);
		globals = GlobalsVars.getInstance();
	}
	
	public void addMenuItem(String Name, String... mnemonic)
	{
		JMenu item = new JMenu(Name);
		item.setBackground(Color.GRAY);
		int i = 0;
		while(i < mnemonic.length)
		{
			JMenuItem itemx = new JMenuItem(mnemonic[i]);
			if(mnemonic[i].equals("Save"))
			{
				itemx.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent arg0) {
						if(globals.isCalculated() == false)
						{
							JOptionPane.showMessageDialog(null, "Error : Cannot Save unless project estimate is calculated!");
							return;
						}
						((MainWindow)globals.getMainFrame()).fileDialog.showSaveDialog(globals.getMainFrame());
						File exportFile = ((MainWindow)globals.getMainFrame()).fileDialog.getSelectedFile();
						File realExportFile = new File(exportFile.getAbsolutePath() + ".pc");
						try {
							((MainWindow)globals.getMainFrame()).fileIO.writeToFile(realExportFile);
							globals.setCalculated(false);
						} catch (IOException ioe) {
							JOptionPane.showMessageDialog(null, "Problem while writing to the file!");
						}
					}
					
				});
			}
			else if(mnemonic[i].equals("About Us"))
			{
				itemx.addActionListener(new ActionListener()
				{

					@Override
					public void actionPerformed(ActionEvent e) {
						JOptionPane.showMessageDialog(null, "PairedComparison (c) \nSidhartha Mani\nShigeru Sasao\n2013 All Rights Reserved\n\nIf you have comments please contact  \nSidhartha Mani at sidhartm@andrew.cmu.edu (or)\nShigeru Sasao at ssasao@alumni.cmu.edu\n\n","About us",JOptionPane.PLAIN_MESSAGE);
					}
					
				});
			}
			else if(mnemonic[i].equals("Open"))
			{
				itemx.addActionListener(new ActionListener()
				{
					@Override
					public void actionPerformed(ActionEvent e) {
						((MainWindow)globals.getMainFrame()).fileDialog.showOpenDialog(((MainWindow)globals.getMainFrame()));
						File importFile = ((MainWindow)globals.getMainFrame()).fileDialog.getSelectedFile();
						try {
							((MainWindow)globals.getMainFrame()).fileIO.readFromFile(importFile);
							globals.getMatrix().refresh();
							globals.getMatrix().updateUI();

						} catch (FileNotFoundException ioe) {
							JOptionPane.showMessageDialog(null, "File not found...\n");
						} catch (IOException ioe) {
							JOptionPane.showMessageDialog(null, "Problems while reading from file...\n");
						} catch (NumberFormatException ioe) {
							JOptionPane.showMessageDialog(null, "Problems while reading from file...\n");
						}

					}
					
				});
			}
			item.add(itemx);
			i++;
		}
		toolBar.add(item);
	}
	
	public JComponent getComponent()
	{
		return toolBar;
	}
}
