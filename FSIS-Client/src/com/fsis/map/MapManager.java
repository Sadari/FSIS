package com.fsis.map;

import java.util.ArrayList;
import java.util.List;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;

import com.fsis.map.R;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.fsis.connector.ConnectionHandler;

public class MapManager extends MapActivity implements LocationListener{
	private MapView mapView;
	private static MapManager mapManager;
	private Map map;
	
	private LocationManager locationManager;
	private String locationProvider = LocationManager.GPS_PROVIDER;
	private String towers;
	private int lat, longi;
	public static GeoPoint currentLocation;
	private final float minDistance = 100.0f;   //wait till user moves 100m
	private final long minTime = 10000;			//rest for 10s 
	List<Overlay> overlayList;
	private Drawable FSIcon;
	private float diameter = 1;
	private ArrayList<FillingStation> fsList;		
	
	//FillingStation fs1,fs2, fs3, fs4, fs5;
	
	public MapView getMapView() {
		return mapView;
	}
		
	public static MapManager getMapManagerInstance(){
		if(mapManager == null)
			mapManager = new MapManager();
		return mapManager;
	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mapView = (MapView)findViewById(R.id.mapView);
        
        overlayList = mapView.getOverlays();        
        map = new Map(mapView, this.getBaseContext());
        
        initiliseMap();
        findCurrentLocation();
         
        boolean status = false;
        if(currentLocation != null){
        	//do{
        		System.out.println("start");
        		status = searchFS(currentLocation);
        		System.out.println("end");
        	//}while(!status);
        }
        
        if(status){
        	for(int i=0; i<fsList.size(); i++){
        		markFS(fsList.get(i));
        	}
        }
        
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		locationManager.removeUpdates(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		locationManager.requestLocationUpdates(locationProvider, minTime, minDistance, this);
	}
	
	

	//load the map
	private void initiliseMap(){
		mapView.setBuiltInZoomControls(true);
		mapView.setSoundEffectsEnabled(true);
		mapView.setEnabled(true);
        mapView.setClickable(true);
        //mapView.setStreetView(true);
        //map.displayLocation(6997343, 79954269);
	}
	
	//find current location using GPS service
	private void findCurrentLocation(){
		locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        towers = locationManager.getBestProvider(criteria, false);
        Location location = locationManager.getLastKnownLocation(locationProvider);
        
        if(location != null){
        	lat = (int) (location.getLatitude()*1E6);             //find current location coordinates
        	longi = (int) (location.getLongitude()*1E6);
        	map.displayLocation(lat, longi);                        //centre the map to current location
        	
        	currentLocation = new GeoPoint(lat, longi);
        	MyLocationPinPoint myLocationPinPoint = new MyLocationPinPoint(this.getBaseContext(), currentLocation);
        	overlayList.add(myLocationPinPoint);                     //mark current location
        }
        else{
        	Toast.makeText(MapManager.this, "Couldn't get the provider" , Toast.LENGTH_SHORT).show();
        }
	}
	
	//find nearby filling stations
	private boolean searchFS(GeoPoint location){
		/*ConnectionHandler conHandler = new ConnectionHandler();
		conHandler.getData(x, y, diameter);*/
		float x = (float) (location.getLatitudeE6()/1E6), y = (float) (location.getLongitudeE6()/1E6);
		ConnectionHandler conHandler = new ConnectionHandler();
		conHandler.setLat(x);
		conHandler.setLang(y);
		fsList = conHandler.getFS();
		boolean status = false;
		try{
			status = (fsList.size()>0);
		} catch(NullPointerException e){
			status = false;
		}
		return status;
	}
	
	
	//draw the filling station icon on the map
	private void markFS(FillingStation fs){
		String msg = "";
		FSIcon = this.getResources().getDrawable(R.drawable.fs_icon);
        FSPinPoint fs_pinpoint = new FSPinPoint(FSIcon, this);
         
        int x = (int) (fs.getLatitude()*1E6);
        int y = (int) (fs.getLongtitude()*1E6);
        GeoPoint fslocation = new GeoPoint(x, y);
        msg = fs.getLocation() + ";" + fs.getStationType() + ";" + map.getDistanceInMeteres(currentLocation, fslocation) + "; ";
        for(int i=0; i<fs.getFuelType().size(); i++){
        	msg += fs.getFuelType().get(i) + ":\tRs." + " ";
        }
        msg += ";" + fs.getAddress() + ";";
        for(int i=0; i<fs.getTelNO().size(); i++){
        	msg += fs.getTelNO().get(i) + " ";
        }
        OverlayItem overlayitem = new OverlayItem(fslocation, "Filling Station", msg);
        fs_pinpoint.addPinPoint(overlayitem);
        overlayList.add(fs_pinpoint);
	}
	
	
	//Location Listner methods
	@Override
	public void onLocationChanged(Location nwLocation) {
		// TODO Auto-generated method stub
		lat = (int) nwLocation.getLatitude();
    	longi = (int) nwLocation.getLongitude();
    	map.displayLocation(lat, longi);
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	
	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
}