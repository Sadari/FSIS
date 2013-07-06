package database;

import java.util.ArrayList;

import org.apache.axis2.databinding.types.xsd.Integer;

public class DBManager {
	
	private Connector dbConnector;
	
	private ArrayList<String> tempArr1, tempArr2;

	public DBManager() {
		super();
		// TODO Auto-generated constructor stub
		dbConnector = new Connector();
		tempArr1 = new ArrayList<String>();
		tempArr2 = new ArrayList<String>();
	}
	
	public ArrayList<FillingStation> findFS(float lat, float lang){
		System.out.println("BBB");
		tempArr1 = dbConnector.selectFS(lat, lang);
		ArrayList<FillingStation> FSList = new ArrayList<FillingStation>();
		FillingStation newFS;
		String[] tempBuf = null;
		
		for(int i=0; i<tempArr1.size(); i++){
			newFS = new FillingStation();
			tempBuf = tempArr1.get(i).split(" ");
			newFS.setLocation(tempBuf[1]);
			newFS.setStationType(tempBuf[2]);
			newFS.setLatitude(new Float(tempBuf[3]));
			newFS.setLongtitude(new Float(tempBuf[4]));
			newFS.setAddress(tempBuf[5]);
			
			tempArr2 = dbConnector.selectFSInfo(new java.lang.Integer(tempBuf[0]));
			for(int j=0; j<tempArr2.size(); j++){
				newFS.setFuelType(tempArr2.get(j));
				System.out.print("1");
			}
			
			tempArr2 = dbConnector.selectTelNo(new java.lang.Integer(tempBuf[0]));
			for(int j=0; j<tempArr2.size(); j++){
				newFS.setTelNO(tempArr2.get(j));
				System.out.print("2");
			}
			FSList.add(newFS);
		}
		return FSList;
	}
	
	public void changeDiameter(float diameter){
		System.out.println("second");
		dbConnector.setDiameter(diameter);		
	}

	public String getPrices(){
		String result = dbConnector.selectPrices();	
		return result;
	}
}
