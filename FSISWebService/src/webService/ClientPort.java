package webService;

import java.util.ArrayList;
import com.google.gson.Gson;

import database.*;

public class ClientPort {
	
	private DBManager dbManager;
	private Gson gson;
	
	public ClientPort() {
		super();
	}
	
	private void initialize(){
		if(dbManager == null)
			dbManager = new DBManager();
		if(gson == null)
			gson = new Gson();
	}

	public String findFS(String cordinations){
		System.out.println("connected");
		initialize();
		System.out.println("AAA"+cordinations);
		String[] temp = cordinations.split(" ");
		ArrayList<FillingStation> fsList = dbManager.findFS(new Float(temp[0]), new Float(temp[1]));
		
		return gson.toJson(fsList);
	}
	
	public void changeDiameter(String diameter){
		initialize();
		System.out.println("Diam: "+diameter);
		dbManager.changeDiameter(new Float(diameter));
	}
	
	public String getPrices(){
		initialize();
		String result = dbManager.getPrices();
		
		return result;
	}
}
