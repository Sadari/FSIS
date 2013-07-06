package com.fsis.connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.R.integer;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class HttpConnector extends Activity{
	
	HttpClient client;
	
	final static String URL = "";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		client = new DefaultHttpClient();
	}
	
	public JSONObject getData(String myLocation){
		StringBuilder url = new StringBuilder(URL);
		
		HttpGet httpGet = new HttpGet(url.toString());
		HttpResponse response = null;
		JSONObject obj = null;
		try {
			response = client.execute(httpGet);
			int status = response.getStatusLine().getStatusCode();
		
			if(status==200){
				HttpEntity entity = response.getEntity();
				String data = EntityUtils.toString(entity);
				JSONArray timeline;
				try {
					timeline = new JSONArray(data);
					obj = timeline.getJSONObject(0);
				} catch (JSONException e1) {
					e1.printStackTrace();
				}
			}
		} catch (ParseException e) {}
		catch (IOException e){}
		
		return obj;
	}
	
	public void sendData(JSONObject obj){
		HttpParams myParams = new BasicHttpParams();
	    HttpConnectionParams.setConnectionTimeout(myParams, 10000);
	    HttpConnectionParams.setSoTimeout(myParams, 10000);

	    String json=obj.toString();

	    try {

	        HttpPost httppost = new HttpPost(URL);
	        httppost.setHeader("Content-type", "application/json");

	        StringEntity se = new StringEntity(obj.toString()); 
	        se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
	        httppost.setEntity(se); 

	        HttpResponse response = client.execute(httppost);
	        
	    } catch (ClientProtocolException e) {} 
	    catch (IOException e) {}		
	}
}
