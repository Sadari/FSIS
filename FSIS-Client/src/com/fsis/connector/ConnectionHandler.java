package com.fsis.connector;

import java.util.ArrayList;

import org.json.JSONObject;

import com.fsis.map.FillingStation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class ConnectionHandler{
	
	private ServiceConnector serviceConnector;
	private float lat, lang, diameter;
	
	public ConnectionHandler() {
		super();
		// TODO Auto-generated constructor stub
		serviceConnector = new ServiceConnector();
	}

		
	public void setLat(float lat) {
		this.lat = lat;
	}


	public void setLang(float lang) {
		this.lang = lang;
	}


	public void setDiameter(float diameter) {
		this.diameter = diameter;
	}


	public void changeDiameter(){
		serviceConnector.setDiameter(diameter);
	}

	public ArrayList<FillingStation> getFS(){
		 serviceConnector.getFS(lat, lang);
		 
		 try {
             Thread.sleep(5000);
		 } catch (Exception e) {
        	System.out.println();
		 }
		 
		 ArrayList<FillingStation> fsList=ServiceConnector.fsList;
		return fsList;
	}
	
	public String getPrices(){
		serviceConnector.getPrices();
			try {
				Thread.sleep(5000); 
			} catch (Exception e) {
				System.out.println();
			}
		String prices = ServiceConnector.prices;
		return prices;
	}
}
