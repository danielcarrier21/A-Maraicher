package com.studio21.mobile.helper;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.studio21.mobile.R;

import android.content.Context;
import android.util.Log;

public class Storage
{
	private static final String TAG = "A-Log";
	
	public static void writeToInternal(Context ctx, String filename, String data)
	{
		Log.d(TAG, "Storage-writeToInternal: writing " + filename + "...");
		
		try
		{
			FileOutputStream fos = ctx.openFileOutput(filename, Context.MODE_PRIVATE);
			fos.write(data.getBytes());
			fos.close();
		} 
		catch (FileNotFoundException e)
		{
			Log.e(TAG, e.getMessage());
		} 
		catch (IOException e)
		{
			Log.e(TAG, e.getMessage());
		}
	}
	
	public static String readFromInternal(Context ctx, String filename)
	{
		Log.d(TAG, "Storage-readFromInternal: reading " + filename +  "...");
		
		StringBuffer fileContent = new StringBuffer("");
		try
		{
			FileInputStream fis = ctx.openFileInput(filename);
			byte[] buffer = new byte[1024];
			
			while(fis.read(buffer) != -1)
			{
				fileContent.append(new String(buffer));
			}
		}
		catch (IOException e)
		{
			Log.e(TAG, e.getMessage());
		}
		
		return fileContent.toString();
	}
	
	public static boolean fileExistsInternal(Context ctx, String filename)
	{
		Log.d(TAG, "Storage : fileExistsInternal");
		
		File file = ctx.getFileStreamPath(filename);
		
		Log.d(TAG, "Storage-fileExistsInternal: File exists = " + file.exists() + ", file size = " + file.length());
		return file.exists();
	}
}
