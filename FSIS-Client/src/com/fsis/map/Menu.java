package com.fsis.map;

import com.fsis.connector.ConnectionHandler;
import com.fsis.map.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Menu extends Activity{

	private ConnectionHandler conHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public boolean onCreateOptionsMenu(android.view.Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.layout.menu, menu);
	    
	    conHandler = new ConnectionHandler();
		return true;
	}

	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.menu_prices:
			TableLayout priceTable = new TableLayout(this);
			TableRow nwRow;
			TextView typeTV, priceTV;
			
			nwRow = new TableRow(this);
			typeTV = new TextView(this);
			typeTV.setText("Fuel Type");
			nwRow.addView(typeTV, new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1.0f));
			
			priceTV = new TextView(this);
			typeTV.setText("Current Price");
			nwRow.addView(priceTV, new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1.0f));
			priceTable.addView(nwRow, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			
			String prices = conHandler.getPrices();
			String[] temp1, temp2;
			temp1 = prices.split(";");
			
			for(int i=0; i<temp1.length-1; i++){
				temp2 = temp1[i].split(" ");
				nwRow = new TableRow(this);
				typeTV = new TextView(this);
				typeTV.setText(temp2[0]);
				nwRow.addView(typeTV, new TableRow.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT, 1.0f));
				
				priceTV = new TextView(this);
				typeTV.setText(temp2[1]);
				nwRow.addView(priceTV);
				priceTable.addView(nwRow, new TableLayout.LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
			}
			break;
		
		case R.id.menu_search:
			//TextView searchTxt = (TextView) findViewById(R.id.searchTxt);
			//String searchLocation = searchTxt.getText().toString();	
			//map.displayLocation(map.getLocationPoint(searchLocation));
			break;
		
		case R.id.menu_change:
			conHandler.setDiameter(0.5f);
			conHandler.changeDiameter();
			break;
		}
		return true;
		
	}
	
	
	
}
