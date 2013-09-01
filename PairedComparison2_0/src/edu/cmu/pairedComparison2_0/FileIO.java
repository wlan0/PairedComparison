package edu.cmu.pairedComparison2_0;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import edu.cmu.pairedComparison.UI.GlobalsVars;


/**
 * Handle input/output for the files containing matrix data
 * 
 * @author Shigeru Sasao
 */
public class FileIO {

	/**
	 * characters that are invalid in file names
	 */
	public static char[] INVALID_CHARS = { ';', '=', '+', '<', '>', '|', '"',
			'[', ']', '\\', '/', '\'', ':', '*', '?' };

	GlobalsVars globals;
	
	/**
	 * constructor
	 */
	public FileIO() {
		globals = GlobalsVars.getInstance();
	}

	/**
	 * read matrix data from file
	 * 
	 * 
	 * @param inFile
	 *            name of input file
	 * @return table model with loaded data
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws NumberFormatException
	 */
	public void readFromFile(File inFile)
			throws FileNotFoundException, IOException {

		// check name of file
		if (inFile == null || inFile.equals("")) {
			throw new IllegalStateException("Name of file cannot be blank.");
		}
		
		// use jyaml to read from file
		XMLReader.readFile(inFile.getAbsolutePath());
	}

	/**
	 * write matrix data to file
	 * 
	 * @param outFile
	 *            name of output file
	 * @param numData
	 *            number data
	 * @throws IOException
	 */
	public void writeToFile(File outFile)
			throws IOException {

		// check file name
		if (outFile == null || outFile.equals("")) {
			throw new IllegalStateException("Name of file cannot be blank.");
		}
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date(System.currentTimeMillis());
		
		FileWriter fileWriter = new FileWriter(outFile);
		fileWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n<Project Version=\"2.0\" TimeStamp=\"" + sdf.format(date) + "\" xmlns:ex=\"edu.cmu.pairedComparison\" ");
		fileWriter.write("xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n");
		fileWriter.write("xsi:schemaLocation=\"edu.cmu.pairedComparison EstimateInstance\">");
		fileWriter.write("\n<Name>" + outFile.getName() + "</Name>");
		fileWriter.write("\n<Description>" + "..." + "</Description>");	
		//for each package
		fileWriter.write("\n<Package>");	
		fileWriter.write("\n<Name>Package</Name>");	
		fileWriter.write("\n<Desc>Description</Desc>");
		fileWriter.write("\n<ArtifactCount>" + globals.getArtifactCount() + "</ArtifactCount>");
		fileWriter.write("\n<StdDev>" + globals.getTable().getModel().getValueAt(1, 6) + "</StdDev>");	
		fileWriter.write("\n<ReplicationFactor>" + globals.getReplicationFactor() + "</ReplicationFactor>");		
		fileWriter.write("\n<InconsistencyIndex>" + globals.getTable().getModel().getValueAt(1, 5) + "</InconsistencyIndex>");		
		fileWriter.write("\n<JudgementMatrix>");
		fileWriter.write(printMatrix(((MatrixTableModel)globals.getMatrix().getModel()).getJudgmentMatrix()));
		fileWriter.write("\n</JudgementMatrix>");	
		//for each artifact
		for(int i=0;i<((MatrixTableModel)globals.getMatrix().getModel()).getJudgmentMatrix().length;i++)
		{
			fileWriter.write("\n<Artifact RefValue=\"" + Boolean.toString(((MatrixTableModel)globals.getMatrix().getModel()).getReferenceIndex() == i) + "\">");
			fileWriter.write("\n<Name>" + ((MatrixTableModel)globals.getMatrix().getModel()).getArtifacts()[i]  + "</Name>");
			fileWriter.write("\n<Description>"+ ((MatrixTableModel)globals.getMatrix().getModel()).getArtifactNotes(i) +"</Description>");
			fileWriter.write("\n<StdDev>" + globals.getTable().getModel().getValueAt(i, 2) + "</StdDev>");
			fileWriter.write("\n<Value>");
			fileWriter.write("\n<EstValue>" + globals.getTable().getModel().getValueAt(i, 1) + "</EstValue>");
			fileWriter.write("\n</Value>");
			fileWriter.write("\n</Artifact>");
		}
		fileWriter.write("\n<ComparisonNotes>\n"  + ((MatrixTableModel)globals.getMatrix().getModel()).getComparisonNotesAll().toString() + "\n</ComparisonNotes>");
		fileWriter.write("\n</Package>");	
		fileWriter.write("\n</Project>");			
		fileWriter.close();
		return;
	}
	
	private String printMatrix(double[] matrix)
	{
		String result = "";
		for(int i=0;i<matrix.length;i++)
		{
			result += (Double.toString(matrix[i]) + " ");
		}
		return result;
	}
	
	private String printMatrix(double[][] matrix)
	{
		String result = "";
		for(int i=0;i<matrix.length;i++)
		{
			result += "\n<Row RowNum=\"" + i + "\">";
			result += printMatrix(matrix[i]);
			result += "</Row>";
		}
		return result;
	}
	
	/**
	 * check whether file name contains invalid characters
	 * 
	 * @param fullPath
	 *            the file path to check
	 * @return true if valid, false if invalid
	 */
	public boolean isValidFileName(String fullPath) {

		// get file name from full path
		File f = new File(fullPath);
		String fileName = f.getName();

		boolean isValid = true;
		for (char c : INVALID_CHARS) {
			if (fileName.indexOf(c) != -1) {
				isValid = false;
			}
		}

		return isValid;
	}

	/**
	 * check whether file exists.
	 * 
	 * @param fullPath
	 *            the file path to check
	 * @return true if exists, false if not exists
	 */
	public boolean checkFileExists(String fullPath) {
		File f = new File(fullPath);
		return f.exists();
	}
}
