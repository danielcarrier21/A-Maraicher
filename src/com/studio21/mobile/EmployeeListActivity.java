package com.studio21.mobile;

import java.util.concurrent.ExecutionException;

import org.json.JSONArray;

import com.studio21.mobile.helper.JsonDataDownloadTask;
import com.studio21.mobile.models.Employee;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * An activity representing a list of Employees. This activity has different
 * presentations for handset and tablet-size devices. On handsets, the activity
 * presents a list of items, which when touched, lead to a
 * {@link EmployeeDetailActivity} representing item details. On tablets, the
 * activity presents the list of items and item details side-by-side using two
 * vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link EmployeeListFragment} and the item details (if present) is a
 * {@link EmployeeDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link EmployeeListFragment.Callbacks} interface to listen for item
 * selections.
 */
public class EmployeeListActivity extends FragmentActivity implements IToActivityCommunicator
{

	/**
	 * Whether or not the activity is in two-pane mode, i.e. running on a tablet
	 * device.
	 */
	private boolean mTwoPane;
	private final String TAG = "A-Log";
	public IToFragmentCommunicator ToFragmentCommunicator;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		Log.d(TAG, "EmployeeListActivity: onCreate");
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_employee_list);

		if (findViewById(R.id.employee_detail_container) != null)
		{
			// The detail container view will be present only in the
			// large-screen layouts (res/values-large and
			// res/values-sw600dp). If this view is present, then the
			// activity should be in two-pane mode.
			mTwoPane = true;

			// In two-pane mode, list items should be given the
			// 'activated' state when touched.
			((EmployeeListFragment) getSupportFragmentManager()
					.findFragmentById(R.id.employee_list)).setActivateOnItemClick(true);
		}
	}

	/**
	 * Callback method from {@link EmployeeListFragment.Callbacks} indicating
	 * that the item with the given ID was selected.
	 */
	@Override
	public void onItemSelected(int selectedIndex)
	{
		Log.d(TAG, "EmployeeListActivity: onItemSelected");
		if (mTwoPane)
		{
			// In two-pane mode, show the detail view in this activity by
			// adding or replacing the detail fragment using a
			// fragment transaction.
			Bundle arguments = new Bundle();
			Log.d(TAG, "EmployeeListActivity-onItemSelected: selectedIndex = " + selectedIndex);
			arguments.putString("selectedEmployee", Integer.toString(selectedIndex));
			EmployeeDetailFragment fragment = new EmployeeDetailFragment();
			fragment.setArguments(arguments);
			getSupportFragmentManager().beginTransaction()
					.replace(R.id.employee_detail_container, fragment).commit();

		} 
		else
		{
			// In single-pane mode, simply start the detail activity
			// for the selected item ID.
			Log.d(TAG, "EmployeeListActivity-onItemSelected: selectedIndex = " + selectedIndex);
			Intent detailIntent = new Intent(this, EmployeeDetailActivity.class);
			detailIntent.putExtra("selectedEmployee", Integer.toString(selectedIndex));
			startActivity(detailIntent);
		}
	}

	/**
	@Override
	public void passDataToActivity(Object data)
	{
		Employee empl = (Employee)data;
		
	}
	*/
}
