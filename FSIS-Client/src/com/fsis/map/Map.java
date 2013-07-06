package com.fsis.map;

import java.io.IOException;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.util.Log;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapController;
import com.google.android.maps.MapView;
import com.google.android.maps.MyLocationOverlay;
import com.google.android.maps.Overlay;

public class Map implements Serializable{
	
	private MapView mapView;
	private MapController mapController;
	private GeoPoint geoPoint;
	private Context context;
	
	private int ZOOMSIZE = 13;
	
	
	public Map(MapView mapView, Context context){
		this.mapView = mapView;
		mapController = mapView.getController();
		this.context = context;
	}
	
	//centre the map to the given point
	public void displayLocation(int lat, int lag) {
		geoPoint = new GeoPoint(lat, lag);
        mapController.animateTo(geoPoint);
        mapController.setZoom(ZOOMSIZE); 
        mapView.invalidate();
	}
	
	//centre the map to the given point
	public void displayLocation(GeoPoint gPoint) {
		mapController.animateTo(gPoint);
        mapController.setZoom(ZOOMSIZE); 
        mapView.invalidate();
	}
	
	//return name of the location
	public String getLocationAddress(GeoPoint gPoint) {
		Geocoder geoCoder = new Geocoder(context, Locale.getDefault());
		String locationAddress = "";
        
		try {
			List<Address> addresses = geoCoder.getFromLocation(gPoint.getLatitudeE6()  / 1E6, gPoint.getLongitudeE6() / 1E6, 1);
            
            if (addresses.size() > 0) {
            	for (int i=0; i<addresses.get(0).getMaxAddressLineIndex(); i++)
                locationAddress += addresses.get(0).getAddressLine(i) + "\n";
            }
        }
        catch (IOException e) {                
        	e.printStackTrace();
        }
        return locationAddress;
	}
	
	//return coordinates of the given location
	public GeoPoint getLocationPoint(String address) {
		 Geocoder geoCoder = new Geocoder(context, Locale.getDefault());    
		 GeoPoint gPoint = null;
	     try {
	    	 List<Address> addresses = geoCoder.getFromLocationName(address, 1);
	         if (addresses.size() > 0) {
	        	 gPoint = new GeoPoint((int) (addresses.get(0).getLatitude() * 1E6), (int) (addresses.get(0).getLongitude() * 1E6));
	         }    
	     } catch (IOException e) {
	         e.printStackTrace();
	     }	
	     return gPoint;
	}
	
		
	//get path distance between two locations
	public long getDistanceInMeteres(GeoPoint p1, GeoPoint p2) {
	    double lat1 = ((double)p1.getLatitudeE6()) / 1e6;
	    double lng1 = ((double)p1.getLongitudeE6()) / 1e6;
	    double lat2 = ((double)p2.getLatitudeE6()) / 1e6;
	    double lng2 = ((double)p2.getLongitudeE6()) / 1e6;
	    float [] dist = new float[1];
	    Location.distanceBetween(lat1, lng1, lat2, lng2, dist);
	    return Math.round(dist[0]);
	}

	
	
}
