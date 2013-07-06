package com.fsis.map;
/**
 * Sadari
 */
import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class FSPinPoint extends ItemizedOverlay<OverlayItem> {
	
	private ArrayList<OverlayItem> pinPoints = new ArrayList<OverlayItem>();          //overlays list
	private Context context;
	private MapManager mapManager;
	OverlayItem item;
	public static GeoPoint fsLocation;
	
	public FSPinPoint(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public FSPinPoint(Drawable defaultMarker, Context context) {
		this(defaultMarker);
		this.context = context;
		this.mapManager = MapManager.getMapManagerInstance();
	}

	//add an overlay to the list
	public void addPinPoint(OverlayItem overlay) {
		System.out.println("pin point");
	    pinPoints.add(overlay);
	    this.populate();
	    System.out.println("pin pointed");
	}
	
	@Override
	protected boolean onTap(int index) {
	  item = pinPoints.get(index);
	  fsLocation = item.getPoint();
	  String[] temp1 = item.getSnippet().split(";");
	  
	  Bundle bundle = new Bundle();
	  bundle.putString("location", temp1[0]);
	  bundle.putString("stationType", temp1[1]);
	  bundle.putFloat("distance", new Float(temp1[2]));
	  
	  String[] temp2 = temp1[3].split(" ");
	  bundle.putInt("typeCount", temp2.length);
	  for(int i=1; i<temp2.length-1; i++){
		  bundle.putString(("type"+i), temp2[i]);
	  }
	  bundle.putString("address", temp1[4]);
	  
	  temp2 = temp1[4].split(" ");
	  bundle.putInt("noCount", temp2.length);
	  for(int i=0; i<temp2.length-1; i++){
		  bundle.putString(("telNo"+i), temp2[i]);
	  }
	  
	  Intent fsManager = new Intent(context, FSManager.class);
	  fsManager.putExtras(bundle);
	  context.startActivity(fsManager);
	  return true;
	}
	
	@Override
	protected OverlayItem createItem(int i) {
		return pinPoints.get(i);
	}


	@Override
	public int size() {
		return pinPoints.size();
	} 
}
