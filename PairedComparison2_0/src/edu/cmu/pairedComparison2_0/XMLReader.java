package edu.cmu.pairedComparison2_0;

import java.util.HashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import edu.cmu.pairedComparison.UI.*;

public class XMLReader {

	static GlobalsVars globals;
	
	public XMLReader()
	{
		globals = GlobalsVars.getInstance();
	}
	
	public static void readFile(String fileName)
	{
		globals = GlobalsVars.getInstance();
		try {	
			SAXParserFactory factory = SAXParserFactory.newInstance();
			SAXParser saxParser = factory.newSAXParser();
			
			
			DefaultHandler handler = new DefaultHandler() {
				boolean name = false;
				boolean artifact = false;
				boolean artifactCount = false;
				boolean replicationFactor = false;
				boolean judgementMatrix = false;
				boolean row = false;
				boolean desc = false;
				boolean refVal = false;
				boolean estVal = false;
				boolean compNotes = false;
				
				int counter = 1;
				int judgementMatrixCounter = 0;
				String[] artifactNotes;
				int artifactNotesCounter = 0;
				HashMap<String,String> comparisonNotes = new HashMap<String,String>();
				
				public void startElement(String uri, String localName,String qName, 
			                Attributes attributes) throws SAXException {
	
					if (qName.equalsIgnoreCase("Name")) {
						name = true;
					}
			 
					if (qName.equalsIgnoreCase("Artifact")) {
						artifact = true;
						if(attributes.getValue(0).equalsIgnoreCase("true"))
							refVal = true;
					}
					
					if (qName.equalsIgnoreCase("ArtifactCount")){
						artifactCount = true;
					}
				
					if (qName.equalsIgnoreCase("replicationFactor")){
						replicationFactor = true;
					}
					
					if(qName.equalsIgnoreCase("JudgementMatrix"))
					{
						judgementMatrix = true;
					}
					
					if(qName.equalsIgnoreCase("row"))
					{
						row = true;
					}
					
					if(qName.equalsIgnoreCase("Description"))
					{
						desc = true;
					}
					
					if(qName.equalsIgnoreCase("EstValue"))
					{
						estVal = true;
					}
					
					if(qName.equalsIgnoreCase("comparisonNotes"))
					{
						compNotes = true;
					}
			 
				}
			 
				public void endElement(String uri, String localName,
					String qName) throws SAXException {
					if(qName.equalsIgnoreCase("Artifact")) {
						artifact = false;
						refVal = false;
					}
					if(qName.equalsIgnoreCase("JudgementMatrix")) {
						judgementMatrix = false;
						judgementMatrixCounter = 0;
					}
				}
			 
				public void characters(char ch[], int start, int length) throws SAXException {
			 
					if (artifactCount) 
					{
						int artifactCountx = Integer.parseInt(new String(ch,start,length));
						globals.setArtifactCount(artifactCountx);
						((PropertiesTab)globals.getPropertyPane()).setArtifactCountField(Integer.toString(artifactCountx));
						((PropertiesTab)globals.getPropertyPane()).ArtifactCountActionPerformed();
						artifactCount = false;
						artifactNotes = new String[artifactCountx];
					}
					
					if (replicationFactor)
					{
						((PropertiesTab)globals.getPropertyPane()).ReplicationFactorActionPerformed();
						int replicationFactorx = Integer.parseInt(new String(ch,start,length));
						globals.setReplicationFactor(replicationFactorx);
						((PropertiesTab)globals.getPropertyPane()).setReplicationFactorField(Integer.toString(replicationFactorx));
						replicationFactor = false;
					}
					
					if(estVal)
					{
						if(refVal)
						{
							((MatrixTableModel)globals.getMatrix().getModel()).setValueAt(new String(ch,start,length), counter - 1, 0);
							refVal = false;
						}
						estVal = false;
					}
					
					if(name)
					{
						if(artifact)
						{
								String artifactName = new String(ch,start,length);
								((MatrixTableModel)globals.getMatrix().getModel()).setValueAt(artifactName, counter, 1);
								counter++;
						}
						name = false;
					}
					
					if(desc)
					{
						if(artifact)
						{
							String description = new String(ch,start,length);
							((MatrixTableModel)globals.getMatrix().getModel()).setArtifactNotes(artifactNotesCounter++,description);
						}
						desc = false;
					}
					
					if(row)
					{
						String[] values = new String(ch,start,length).split(" ");
						int i = 2;
						for(String value:values)
						{
							((MatrixTableModel)globals.getMatrix().getModel()).setValueAt(value,judgementMatrixCounter+1,i++);
						}
						judgementMatrixCounter++;
						row = false;
					}
					
					if(compNotes)
					{
						if(!new String(ch,start,length).equals("{}"))
						{
							String[] Notes = new String(ch,start,length).split(",");
							for(String note:Notes)
							{
								note = note.replace("{", "");
								note = note.replace("}", "");
								note = note.trim();
								String[] pair = note.split("=");
								globals.setComparisonNotes(pair[0],pair[1]);
							}
							compNotes = false;
						}
					}
					
				}
		 
			};
		    saxParser.parse(fileName, handler);
		} 
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
