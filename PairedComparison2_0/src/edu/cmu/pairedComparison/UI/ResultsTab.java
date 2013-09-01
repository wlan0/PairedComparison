package edu.cmu.pairedComparison.UI;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.explodingpixels.macwidgets.IAppWidgetFactory;
import com.explodingpixels.macwidgets.MacWidgetFactory;

public class ResultsTab extends JScrollPane
{
	private static final long serialVersionUID = 7781877952276486319L;
	public ResultsTab(int width)
	{
		String[][] data = new String[][]{
		};

		String[] columnNames = new String[]{"Artifact Name", "Abs. Size", "Abs. Std. Deviation","Lower Limit","Upper Limit","Inconsistency Index","Sum absolute std. deviation"};
		DefaultTableModel model = new DefaultTableModel(data, columnNames);
		JTable table = MacWidgetFactory.createITunesTable(model);
		this.setViewportView(table);
		IAppWidgetFactory.makeIAppScrollPane(this);
		GlobalsVars.getInstance().setTable(table);
	}
}
