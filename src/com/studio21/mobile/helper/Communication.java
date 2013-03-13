package com.studio21.mobile.helper;

import android.content.Context;
import android.widget.Toast;

public class Communication
{
	public static void sendToast(Context ctx, String message)
    {
    	int duration = Toast.LENGTH_LONG;
    	Toast toast = Toast.makeText(ctx, message, duration);
    	toast.show();
    }
}
