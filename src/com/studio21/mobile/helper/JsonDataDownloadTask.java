package com.studio21.mobile.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.os.AsyncTask;
import android.util.Log;

public class JsonDataDownloadTask extends AsyncTask
{
private final String TAG = "A-Log";
	
	private Object getJSONData(String url) 
	{	
		try 
		{   
			Log.d(TAG, "Getting Json data");
	        URL target = new URL(url);
	              
	        HttpURLConnection conn = (HttpURLConnection) target.openConnection();
	        conn.setReadTimeout(10000 /* milliseconds */);
	        conn.setConnectTimeout(15000 /* milliseconds */);
	        conn.setRequestMethod("GET");
	        
	        conn.connect();
	        int statusCode = conn.getResponseCode();
	        Log.d(TAG, "Status code: " + statusCode);
	        
	        if (statusCode == 200) 
	        {
	        	String response = readInput( conn.getInputStream());
	        	String contenttype = conn.getContentType();
	        	Log.d(TAG, "ContentType: " + contenttype);
	        	Log.d(TAG, "Response: " + response);
	        	return response;      
	        } 
	        else 
	        {
	        	Log.d(TAG, "Failed to download file at " + url);
	        }
	        
	    } 
		catch(IOException ioe)
		{
			Log.e(TAG, ioe.getMessage());
		}
		catch (Exception e) 
		{
	        Log.e(TAG, e.getMessage());
	    }        
		
        return "";
    }
	
	private String readInput(InputStream input) throws IOException
	{
		Log.d(TAG, "Reading inputstream ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(input));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try 
		{
		  while ((line = reader.readLine()) != null) 
		  {
			  Log.d(TAG, "Reading line");
		      sb.append(line + "\n");
		  }
		} 
		catch (IOException e) 
		{
		  Log.e(TAG, e.getMessage());
		} 
		finally 
		{
		  try 
		  {
		      input.close();
		  } 
		  catch (IOException e) 
		  {
		      Log.e(TAG, e.getMessage());
		  }
		}
		return sb.toString();
	}
		
	@Override
	protected Object doInBackground(Object... arg0) 
	{
		Log.d(TAG,"Begining background job...");
		String url = arg0[0].toString();
		return getJSONData(url);
	}
}
