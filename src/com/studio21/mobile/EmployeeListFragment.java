package com.studio21.mobile;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.studio21.mobile.dummy.DummyContent;
import com.studio21.mobile.helper.CustomAdapter;
import com.studio21.mobile.helper.JsonDataDownloadTask;
import com.studio21.mobile.models.Employee;

/**
 * A list fragment representing a list of Employees. This fragment also supports
 * tablet devices by allowing list items to be given an 'activated' state upon
 * selection. This helps indicate which item is currently being viewed in a
 * {@link EmployeeDetailFragment}.
 * <p>
 * Activities containing this fragment MUST implement the {@link Callbacks}
 * interface.
 */
public class EmployeeListFragment extends ListFragment implements IToFragmentCommunicator
{
	/**
	*/
	private final String TAG = "A-Log";
	public static ArrayList<Employee> Employees;
	private IToActivityCommunicator ToActivityCommunicator;
	public Context ctx;
	/**
	 * The serialization (saved instance state) Bundle key representing the
	 * activated item position. Only used on tablets.
	 */
	private static final String STATE_ACTIVATED_POSITION = "activated_position";

	/**
	 * The fragment's current callback object, which is notified of list item
	 * clicks.
	 */
	//private Callbacks mCallbacks = sDummyCallbacks;

	/**
	 * The current activated item position. Only used on tablets.
	 */
	private int mActivatedPosition = ListView.INVALID_POSITION;

	/**
	 * A callback interface that all activities containing this fragment must
	 * implement. This mechanism allows activities to be notified of item
	 * selections.
	 */
	public interface Callbacks
	{
		/**
		 * Callback for when an item has been selected.
		 */
		//public void onItemSelected(String id);
	}

	/**
	 * A dummy implementation of the {@link Callbacks} interface that does
	 * nothing. Used only when this fragment is not attached to an activity.
	 */
	/**
	private static Callbacks sDummyCallbacks = new Callbacks()
	{
		@Override
		public void onItemSelected(String id)
		{
		}
	};
	*/
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public EmployeeListFragment() 
	{
	}

	@Override
	public void onAttach(Activity activity)
	{
		Log.d(TAG, "EmployeeListFragment: onAttach");
		
		super.onAttach(activity);
		
		ctx = activity;
		ToActivityCommunicator = (IToActivityCommunicator)activity;
		((EmployeeListActivity)activity).ToFragmentCommunicator = this;
		
		// Activities containing this fragment must implement its callbacks.
		if (!(activity instanceof IToActivityCommunicator))
		{
			throw new IllegalStateException("Activity must implement fragment's callbacks.");
		}

		//mCallbacks = (Callbacks) activity;
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.d(TAG, "EmployeeListFragment: onCreate");
		
		super.onCreate(savedInstanceState);
		
		try
		{
			if(Employees == null && isConnected())
			{
				Log.d(TAG, "EmployeeListFragment: getting data");
				AsyncTask response = new JsonDataDownloadTask().execute("http://www.html5.angularsamurai.com/employees.json");
				JSONArray jsonArr = new JSONArray(response.get().toString());
				Employees = Employee.JsonToArrayList(jsonArr);
			}
			else
			{
				sendToast("Device is not connected");
			}
			
		} 
		catch (JSONException e)
		{
			Log.e(TAG, e.getMessage());
		} 
		catch (InterruptedException e)
		{
			Log.e(TAG, e.getMessage());
		} 
		catch (ExecutionException e)
		{
			Log.e(TAG, e.getMessage());
		}
		
		if(Employees != null)
			Log.d(TAG, "EmployeeListFragment-onCreate: ArrayList is not null, counting " + Employees.size() + " items");
		else
			Log.d(TAG, "EmployeeListFragment-onCreate: ArrayList is null");
		
		// TODO: replace with a real list adapter.
		setListAdapter(new CustomAdapter(Employees, R.layout.employee_list_item, getActivity()));
		
		Log.d(TAG, "EmployeeListFragment-onCreate: ListAdapter is set");
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState)
	{
		Log.d(TAG, "EmployeeListFragment: onViewCreated");
		
		super.onViewCreated(view, savedInstanceState);

		// Restore the previously serialized activated item position.
		if (savedInstanceState != null && savedInstanceState.containsKey(STATE_ACTIVATED_POSITION))
		{
			Log.d(TAG, "EmployeeListFragment-onViewCreated: settingActivatedPosition");
			setActivatedPosition(savedInstanceState.getInt(STATE_ACTIVATED_POSITION));
		}
	}

	@Override
	public void onDetach()
	{
		Log.d(TAG, "EmployeeListFragment: onDetach");
		super.onDetach();

		// Reset the active callbacks interface to the dummy implementation.
		//mCallbacks = sDummyCallbacks;
	}

	@Override
	public void onListItemClick(ListView listView, View view, int position, long id)
	{
		Log.d(TAG, "EmployeeListFragment: onListItemClick");
		super.onListItemClick(listView, view, position, id);

		// Notify the active callbacks interface (the activity, if the
		// fragment is attached to one) that an item has been selected.
		//mCallbacks.onItemSelected(DummyContent.ITEMS.get(position).id);
		
		ToActivityCommunicator.onItemSelected(position);
	}

	@Override
	public void onSaveInstanceState(Bundle outState)
	{
		Log.d(TAG, "EmployeeListFragment: onSaveInstanceState");
		super.onSaveInstanceState(outState);
		if (mActivatedPosition != ListView.INVALID_POSITION)
		{
			// Serialize and persist the activated item position.
			outState.putInt(STATE_ACTIVATED_POSITION, mActivatedPosition);
		}
	}

	/**
	 * Turns on activate-on-click mode. When this mode is on, list items will be
	 * given the 'activated' state when touched.
	 */
	public void setActivateOnItemClick(boolean activateOnItemClick)
	{
		Log.d(TAG, "EmployeeListFragment: setActivateOnItemClick");
		// When setting CHOICE_MODE_SINGLE, ListView will automatically
		// give items the 'activated' state when touched.
		getListView().setChoiceMode(
				activateOnItemClick ? ListView.CHOICE_MODE_SINGLE : ListView.CHOICE_MODE_NONE);
	}

	private void setActivatedPosition(int position)
	{
		Log.d(TAG, "EmployeeListFragment: setActivatedPosition");
		if (position == ListView.INVALID_POSITION)
		{
			getListView().setItemChecked(mActivatedPosition, false);
		} else
		{
			getListView().setItemChecked(position, true);
		}

		mActivatedPosition = position;
	}

	private boolean isConnected() 
	{	
    	Log.d(TAG, "EmployeeListFragment: Checking if connected");
	    ConnectivityManager connMgr = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
	    if (networkInfo != null && networkInfo.isConnected()) 
	    {
	        return true;
	    } 
	    else 
	    {
	        return false;
	    }
	}
	
	private void sendToast(String message)
    {
    	int duration = Toast.LENGTH_LONG;
    	Toast toast = Toast.makeText(ctx, message, duration);
    	toast.show();
    }
	
	@Override
	public void passDataToFragment(Object data)
	{
		Log.d(TAG, "EmployeeListFragment-passDataToFragment: Receiving data from activity");
		ArrayList<Employee> empls = (ArrayList<Employee>)data;
		Employees = empls;
		
	}
}
