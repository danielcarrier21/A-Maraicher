package com.studio21.mobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.MenuItem;

public class EmployeeAddActivity extends FragmentActivity
{
	private final String TAG = "A-Log";
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.d(TAG, "EmployeeAddActivity: onCreate");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_add);

		// Show the Up button in the action bar.
		getActionBar().setDisplayHomeAsUpEnabled(true);

		// savedInstanceState is non-null when there is fragment state
		// saved from previous configurations of this activity
		// (e.g. when rotating the screen from portrait to landscape).
		// In this case, the fragment will automatically be re-added
		// to its container so we don't need to manually add it.
		// For more information, see the Fragments API guide at:
		//
		// http://developer.android.com/guide/components/fragments.html
		//
		if (savedInstanceState == null)
		{
			Log.d(TAG, "EmployeeAddActivity-onCreate: savedInstanceState is null");

			// Create the detail fragment and add it to the activity
			// using a fragment transaction.
			EmployeeAddFragment fragment = new EmployeeAddFragment();
			getSupportFragmentManager().beginTransaction()
					.add(R.id.employee_add_container, fragment).commit();
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch (item.getItemId())
		{
			case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpTo(this, new Intent(this, EmployeeListActivity.class));
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
