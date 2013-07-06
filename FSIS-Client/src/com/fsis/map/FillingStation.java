package com.fsis.map;

import java.util.ArrayList;
import java.util.List;

public class FillingStation {
	
	private String location;
	private String stationType;
	private float latitude;
	private float longtitude;
	private String address;
	private ArrayList<String> fuelType;
	private ArrayList<String> telNO;
	
	public FillingStation(){
		fuelType = new ArrayList<String>();
	}

	public String getLocation() {
		return location;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongtitude() {
		return longtitude;
	}

	public void setLongtitude(float longtitude) {
		this.longtitude = longtitude;
	}

	public ArrayList<String> getFuelType() {
		return fuelType;
	}
	
	public void setFuelType(String type){
		this.fuelType.add(type);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public ArrayList<String> getTelNO() {
		return telNO;
	}

	public void setTelNO(String telNO) {
		this.telNO.add(telNO);
	}
	
	
}
