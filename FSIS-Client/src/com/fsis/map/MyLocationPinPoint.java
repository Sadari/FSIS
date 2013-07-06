package com.fsis.map;

import com.fsis.map.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Point;
import android.webkit.WebSettings.PluginState;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

public class MyLocationPinPoint extends Overlay {
	
	private Paint iconPaint;
	private Paint textPaint;
	private Point myLocation;
	private Context context;
	private GeoPoint myPoint;
	
	public MyLocationPinPoint(Context context, GeoPoint geoPoint) {
		this.context = context;
		this.myPoint = geoPoint;
	}



	@Override
	public boolean draw(Canvas canvas, MapView mapView, boolean shadow,	long when) {
		super.draw(canvas, mapView, shadow);
		iconPaint = new Paint();
		iconPaint.setStrokeWidth(1);                             
		iconPaint.setARGB(255, 255, 255, 255);
		iconPaint.setStyle(Paint.Style.STROKE);
		
		textPaint = new Paint();
		textPaint.setColor(Color.BLACK);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(20);
		
		myLocation = new Point();
		mapView.getProjection().toPixels(myPoint, myLocation);
		
		Bitmap bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.marker);
		canvas.drawBitmap(bmp, myLocation.x, myLocation.y, iconPaint);
		canvas.drawText("", myLocation.x, myLocation.y, textPaint);
		return true;
	}
	
	
}
