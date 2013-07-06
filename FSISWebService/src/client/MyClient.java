package client;

import java.io.Serializable;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

public class MyClient implements Serializable{

	/**
	 * @param args
	 */
	
	private String METHOD_NAME = "";
	private String NAMESPACE = "http://webService";
	private String SOAP_ACTION = NAMESPACE + METHOD_NAME;
	private static final String URL = "http://localhost:8080/FSISWebService/services/ClientPort?wsdl";
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	float lat=7, lang=81, diameter=1.0f;

	public void oncreate(){
		System.out.print("hello");
		METHOD_NAME = "changeDiameter";
		try {
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
		    request.addProperty("diameter", new Float(diameter).toString());
		    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    envelope.dotNet = true;
		    envelope.setOutputSoapObject(request);
		    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		    androidHttpTransport.call(SOAP_ACTION, envelope);
		} catch (Exception e) {
		    e.printStackTrace();
		}	
		
		METHOD_NAME = "findFS";
		try {
			SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
			String s = new Float(lat).toString() + new Float(lang).toString();
		    request.addProperty("cordinations", s);
		    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
		    envelope.dotNet = true;
		    envelope.setOutputSoapObject(request);
		    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
		    androidHttpTransport.call(SOAP_ACTION, envelope);
		    Object resultset = envelope.getResponse();
		 
		} catch (Exception e) {
		    e.printStackTrace();
		}	
		
	}
	
}