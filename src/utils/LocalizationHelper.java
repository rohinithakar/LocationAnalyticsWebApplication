package utils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class LocalizationHelper {
	
	private static HashMap<String,LocalizationHelper> map = new HashMap<String,LocalizationHelper>();
	private HashMap<String,String> pairs = new HashMap<String,String>();
	
	private LocalizationHelper(String lang,  ServletContext context) throws FileNotFoundException, IOException, ParseException {
		JSONParser parser = new JSONParser();
		
		String relativeWebPath = "lang/" + lang + ".json";
		String absoluteDiskPath = context.getRealPath(relativeWebPath);
		
		Object obj = parser.parse(new FileReader(absoluteDiskPath));
		 
		JSONObject jsonObject = (JSONObject) obj;
		for(Object k:jsonObject.keySet()){
			String key = (String)k;
			String value = (String) jsonObject.get(key);
			pairs.put(key, value);
		}
	}

	public static LocalizationHelper getInstance(String lang,  ServletContext context) throws FileNotFoundException, IOException, ParseException{
		if(!map.containsKey(lang)){
			LocalizationHelper helper = new LocalizationHelper(lang, context);
			map.put(lang, helper);
		}
			return map.get(lang);
	}
	
	public String getText(String key){
		if(pairs.containsKey(key)){
			return pairs.get(key);
		}
		return null;
	}

}
