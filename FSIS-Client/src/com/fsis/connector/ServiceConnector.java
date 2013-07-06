package com.fsis.connector;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import com.fsis.map.FillingStation;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;

public class ServiceConnector {
	
	private String METHOD_NAME = "";
	private String NAMESPACE = "http://webService";
	private String SOAP_ACTION;//= NAMESPACE + METHOD_NAME;
	
	private static final String URL = "http://10.0.2.2:8080/FSISWebService/services/ClientPort?wsdl";//http://localhost:8080/FSISWebService/services/ClientPort?wsdl
	
	private float lat=7, lang=81, diameter=1.0f;
	private Gson gson = new Gson();
	
	public static ArrayList<FillingStation> fsList = null;
	public static String prices = null;
	
	public void setDiameter(float diameter) {
        new Task1().execute(new Float(diameter)); // wsTask1 is assigned for the functionality
        
        try {
             Thread.sleep(5000); 
        } catch (Exception e) {
        	 e.printStackTrace();
        }
    }
	
	public void getFS(float lat, float lang) {
        Task2 task2 = new Task2();
        task2.execute(new Float(lat), new Float(lang)); // wsTask1 is assigned for the functionality
        
        
        /*try {
			//fsList = task2.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
        return;
	}
	
	public void getPrices() {
        new Task3().execute(); // wsTask1 is assigned for the functionality
    }
	
	private class Task1 extends AsyncTask<Float, Void, Void>{	
		@Override
		protected Void doInBackground(Float... params) {
			// TODO Auto-generated method stub
			METHOD_NAME = "changeDiameter";
			SOAP_ACTION = NAMESPACE + METHOD_NAME;
			try {
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			    request.addProperty("diameter", params[0].toString());
			    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			    envelope.dotNet = true;
			    envelope.setOutputSoapObject(request);
			    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			    androidHttpTransport.call(SOAP_ACTION, envelope);
			} catch (Exception e) {
			    e.printStackTrace();
			}
			return null;
		}
	}
	
	
	
	private class Task2 extends AsyncTask<Float, Void, Void>{	
		
		@Override
		protected  Void doInBackground(Float... params) {
			// TODO Auto-generated method stub
			//ArrayList<FillingStation> fsList = new ArrayList<FillingStation>();
			Object resultset = null;
			
			METHOD_NAME = "findFS";
			SOAP_ACTION = NAMESPACE + METHOD_NAME;//  public String findFS(String cordinations)
			try {
				System.out.println("web service");
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
				String s = params[0].toString() + " " + params[1].toString();
			    request.addProperty("cordinations", s);
			    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			    envelope.dotNet = true;
			    envelope.setOutputSoapObject(request);
			    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			    androidHttpTransport.call(SOAP_ACTION, envelope);
			    resultset = envelope.getResponse();
			    System.out.println("result: " + resultset.toString());
			    
			    Type listType = new TypeToken<ArrayList<FillingStation>>() {}.getType();
			    fsList = gson.fromJson(resultset.toString(), listType); 
			} catch (Exception e) {
			    e.printStackTrace();
			}
			return null;
		}	
	}
	
	
	private class Task3 extends AsyncTask<Void, Void, String>{	
		@Override
		protected String doInBackground(Void... params) {
			// TODO Auto-generated method stub
			Object resultset = null;
			
			METHOD_NAME = "getPrices";
			SOAP_ACTION = NAMESPACE + METHOD_NAME;
			try {
				SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
			    envelope.dotNet = true;
			    envelope.setOutputSoapObject(request);
			    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
			    androidHttpTransport.call(SOAP_ACTION, envelope);
			    resultset = envelope.getResponse();
			    prices = resultset.toString();
			} catch (Exception e) {
			    e.printStackTrace();
			}
			return null;
		}
	}
	
}
