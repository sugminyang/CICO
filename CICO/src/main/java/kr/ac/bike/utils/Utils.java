package kr.ac.bike.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import kr.ac.bike.model.Result;


public class Utils {
	private static String SEPERATE_TERM = "__";

	public static List<Result> sparqlQuery(String request_url, boolean isTumorQueryfalse) {
		List<Result> lists = null;
		URL url = null;
		BufferedReader br = null;
		
		try {
			url = new URL(request_url);
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
	        con.setRequestMethod("GET");
	        
	        int responseCode = con.getResponseCode();
	        
	        if(responseCode==200) { // 정상 호출
	            br = new BufferedReader(new InputStreamReader(con.getInputStream()));
	        } else {  // 에러 발생
	            br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
	        }
	        
	        String inputLine;
	        StringBuffer response = new StringBuffer();
	        while ((inputLine = br.readLine()) != null) {
	            response.append(inputLine);
	        }
	        br.close();
	        
	        System.out.println(response.toString());
	        if(isTumorQueryfalse)	{
	        	lists = Utils.jsonParseForTumor(response.toString());
	        }
	        else	{
	        	lists = Utils.jsonParse(response.toString());	
	        }
	        
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return lists;
	}
	
	private static List<Result> jsonParseForTumor(String json) {
		List<Result> lists = new ArrayList<Result>();
		List<String> header = getJsonHeader(json);
		System.out.println("####: header: " + header.toString());
		if(header.size() == 0)	{
			System.out.println("#Not searched... result 0 rows..");
			return null;
		}

		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
			JSONObject resultObject= (JSONObject) jsonObj.get("results");
			JSONArray bindingArray = (JSONArray) resultObject.get("bindings");

			//            System.out.println("=====Members=====");
			for(int i=0 ; i<bindingArray.size() ; i++){
				JSONObject tempObj = (JSONObject) bindingArray.get(i);
				JSONObject doObject = (JSONObject)tempObj.get("doid");
				JSONObject tissueObject = (JSONObject)tempObj.get("tissueName");
				JSONObject expObject = (JSONObject)tempObj.get("experiment");
				JSONObject chemObject = (JSONObject)tempObj.get("chemName");
				JSONObject dosageObject = (JSONObject)tempObj.get("dosage");
				JSONObject speciesObject = (JSONObject)tempObj.get("species");
				JSONObject refObject = (JSONObject)tempObj.get("reference");

				String doID = (String)doObject.get("value");
				String experiment = (String)expObject.get("value");
				String chemName = (String)chemObject.get("value");
				String dosage = (String)dosageObject.get("value");
				String species = (String)speciesObject.get("value");
				String tissue = (String)tissueObject.get("value");
				String reference = (String)refObject.get("value");
				
				Result r = new Result(experiment,chemName,dosage,species,doID,tissue,reference,false);
				lists.add(r);
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return lists;
	}

	public static List<Result> jsonParse(String json)	{
		List<Result> lists = new ArrayList<Result>();
		Map<String,List<String>> mapDOIDrelatedGenes = new HashMap<String,List<String>>();
		List<String> header = getJsonHeader(json);
		System.out.println("####: header: " + header.toString());
		if(header.size() == 0)	{
			System.out.println("#Not searched... result 0 rows..");
			return null;
		}

		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
			JSONObject resultObject= (JSONObject) jsonObj.get("results");
			JSONArray bindingArray = (JSONArray) resultObject.get("bindings");

			//            System.out.println("=====Members=====");
			for(int i=0 ; i<bindingArray.size() ; i++){
				JSONObject tempObj = (JSONObject) bindingArray.get(i);
				JSONObject doObject = (JSONObject)tempObj.get("diseaseName");
				if(doObject == null)	{
					doObject = (JSONObject)tempObj.get("doid");
				}
				JSONObject expObject = (JSONObject)tempObj.get("experiment");
				JSONObject chemObject = (JSONObject)tempObj.get("chemName");
				JSONObject dosageObject = (JSONObject)tempObj.get("dosage");
				JSONObject speciesObject = (JSONObject)tempObj.get("species");
				JSONObject geneSymbolObject = (JSONObject)tempObj.get("geneSymbol");
				JSONObject refObject = (JSONObject)tempObj.get("reference");
				
				String doID = (String)doObject.get("value");
				String gene = (String)geneSymbolObject.get("value");
				String experiment = (String)expObject.get("value");
				String chemName = (String)chemObject.get("value");
				String dosage = (String)dosageObject.get("value");
				String species = (String)speciesObject.get("value");
				String reference = (String)refObject.get("value");
				
				String	mergedKey = experiment + SEPERATE_TERM +chemName + SEPERATE_TERM
						+dosage + SEPERATE_TERM +species + SEPERATE_TERM
						 +doID + SEPERATE_TERM +reference;
						
				if(mapDOIDrelatedGenes.containsKey(mergedKey))	{
					List<String> genes = mapDOIDrelatedGenes.get(mergedKey);
					if(!genes.contains(gene))	{
						genes.add(gene);
					}
					mapDOIDrelatedGenes.put(mergedKey, genes);
				}
				else	{
					List<String> temp = new ArrayList<String>();
					temp.add(gene);
					mapDOIDrelatedGenes.put(mergedKey, temp);
				}
			}

			for(String key : mapDOIDrelatedGenes.keySet())	{
				String[] items = key.split(SEPERATE_TERM);

				String experiment = items[0];
				String chemName = items[1];
				String dosage = items[2];
				String species = items[3];
				String doID = items[4];
				String gene = mapDOIDrelatedGenes.get(key).toString();
				String reference = items[5];

				Result r = new Result(experiment,chemName,dosage,species,doID,gene,reference);
				lists.add(r);
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return lists;
	}

	private static List<String> getJsonHeader(String json) {
		List<String> header = new ArrayList<String>();

		try {
			JSONParser jsonParser = new JSONParser();
			JSONObject jsonObj = (JSONObject) jsonParser.parse(json);
			JSONObject headObject= (JSONObject) jsonObj.get("head");
			JSONArray varsArray = (JSONArray) headObject.get("vars");
			for(int i=0 ; i<varsArray.size() ; i++){
				String item = (String) varsArray.get(i);
				header.add(item);
			}
		}catch (ParseException e) {
			e.printStackTrace();
		}

		return header;
	}



}
