package com.fsis.map;

import java.util.ArrayList;

import com.fsis.map.R;
import com.fsis.route.RouteActivity;
import com.google.android.maps.GeoPoint;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class FSManager extends Activity{
	private RouteActivity route;
	
	private TextView locationTxt, distanceTxt, addressTxt, typeTxt;
	private Button navigateBtn, callBtn, moreBtn;
	private Button backBtn;
	
	private String location, stationType, address;
	private float distance;
	private TableLayout fuelTypesTable, telNoTable;
	private int typeCount, noCount;
	private ArrayList<String> fuelTypes, telNos;
	private GeoPoint sPoint = new GeoPoint(6500000, 80000000), ePoint = new GeoPoint(6550000, 81000000);
	Bundle bundle;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		route = new RouteActivity();
		fuelTypes = new ArrayList<String>();
		telNos = new ArrayList<String>();
		
		bundle = getIntent().getExtras();
		location = bundle.getString("location");
		stationType = bundle.getString("stationType");
		distance = bundle.getFloat("distance");
		address = bundle.getString("address");                  
		typeCount = bundle.getInt("typeCount");
		noCount = bundle.getInt("noCount");
		
		setContentView(R.layout.fs_info);
		typeTxt = (TextView) findViewById(R.id.typeTxt);
		locationTxt = (TextView) findViewById(R.id.locationTxt);
		distanceTxt = (TextView) findViewById(R.id.distanceTxt);
		addressTxt = (TextView) findViewById(R.id.addressTxt);
		navigateBtn = (Button) findViewById(R.id.navigateBtn);
		moreBtn = (Button) findViewById(R.id.mornBtn);
		callBtn = (Button) findViewById(R.id.callBtn);
				
		navigateBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivity(new Intent("com.fsis.route.RouteActivity"));
			}
		});
		
		callBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
		
		moreBtn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			}
		});
		displayDetails();
	}
	
	private void displayDetails(){
		
		locationTxt.setText(location);
		distanceTxt.setText(new Float(distance).toString());
		addressTxt.setText(address);
	}

}
