package edu.cmu.pairedComparison.UI;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.*;

import com.explodingpixels.macwidgets.BottomBar;
import com.explodingpixels.macwidgets.BottomBarSize;
import com.explodingpixels.macwidgets.MacWidgetFactory;

import edu.cmu.pairedComparison2_0.FileIO;

public class MainWindow extends JFrame{
	/**
	 * Default serial version ID 
	 */
	private static final long serialVersionUID = 1L;
	public JFileChooser fileDialog;
	public FileIO fileIO;
	RightPanel rightPane;
	private void setSize(Boolean... isMax)
	{
		assert isMax.length <= 1;
	    Boolean isMaximum = isMax.length > 0 ? isMax[0].booleanValue() : true;
		if(isMaximum)
		{
			int width =  java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width;
			int height =  java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().height;
			this.setBounds(0, 0, width, height);
			this.setResizable(false);
		}
	}
	
	//This aggregates the left, top, middle and bottom parts of the window
	public MainWindow()
	{	
		fileIO = new FileIO();
		fileDialog = new JFileChooser();
		GlobalsVars.getInstance().setMainFrame(this);
		GlobalsVars.getInstance().setCalculated(false);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ToolBarHandler toolBar = new ToolBarHandler(this);
		//set Size
		this.setSize();
		//create and set layout
		BorderLayout obj = new BorderLayout();
		this.setLayout(obj);
		// Add components to the frame.
		//menu bar(top)
		this.add(toolBar.getComponent(), BorderLayout.NORTH);
		//left pane
		LeftPanel leftPane = new LeftPanel(); 
		this.add(leftPane, BorderLayout.WEST);
		//Add the Right Panel
		rightPane = new RightPanel(12,2,java.awt.GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds().width);
		GlobalsVars.getInstance().rightPanel[0] = rightPane;
		this.add(rightPane, BorderLayout.CENTER);
		//Add the Bottom Bar
		BottomBar bottomBar = new BottomBar(BottomBarSize.LARGE);
		bottomBar.addComponentToCenter(MacWidgetFactory.createEmphasizedLabel("Paired Comparison Tool (c) Dr.Eduardo Miranda"));
		this.add(bottomBar.getComponent(),BorderLayout.SOUTH);
	}
	public void changeRightPanel(RightPanel panel)
	{
		this.remove(rightPane);
		Container cnt = this.getContentPane();
		rightPane = panel;
		cnt.add(rightPane, BorderLayout.CENTER);
		cnt.validate();
		cnt.repaint();
	}
}
