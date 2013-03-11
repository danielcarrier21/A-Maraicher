package com.studio21.mobile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.studio21.mobile.dummy.DummyContent;
import com.studio21.mobile.models.Employee;

/**
 * A fragment representing a single Employee detail screen. This fragment is
 * either contained in a {@link EmployeeListActivity} in two-pane mode (on
 * tablets) or a {@link EmployeeDetailActivity} on handsets.
 */
public class EmployeeDetailFragment extends Fragment
{
	/**
	 * The fragment argument representing the item ID that this fragment
	 * represents.
	 */
	public static final String ARG_ITEM_ID = "item_id";
	private final String TAG = "A-Log";

	/**
	 * The dummy content this fragment is presenting.
	 */
	//private DummyContent.DummyItem mItem;
	private Employee employee;
	/**
	 * Mandatory empty constructor for the fragment manager to instantiate the
	 * fragment (e.g. upon screen orientation changes).
	 */
	public EmployeeDetailFragment() {
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.d(TAG, "EmployeeDetailFragment: onCreate");
		super.onCreate(savedInstanceState);

		if (getArguments().containsKey("selectedEmployee"))
		{
			// Load the dummy content specified by the fragment
			// arguments. In a real-world scenario, use a Loader
			// to load content from a content provider.
			String selectedEmpl = getArguments().getString("selectedEmployee");
			
			Log.d(TAG, "EmployeeDetailFragment-onCreate: selectedEmployee = " + selectedEmpl);
			Log.d(TAG, "EmployeeDetailFragment-onCreate: Employees size = " + EmployeeListFragment.Employees.size());
			
			employee = EmployeeListFragment.Employees.get(Integer.parseInt(selectedEmpl));
			
			Log.d(TAG, "EmployeeDetailFragment-onCreate: employee name = " + employee.getPrenom());
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Log.d(TAG, "EmployeeDetailFragment: onCreateView");
		Log.d(TAG, "EmployeeDetailFragment-onCreateView: layout id = " + R.layout.employee_detail_item);
		View rootView = inflater.inflate(R.layout.employee_detail_item, container, false);

		// Show the dummy content as text in a TextView.
		if (employee != null)
		{
			Log.d(TAG, "EmployeeDetailFragment-onCreateView: setting view");
			
			TextView employeeName = (TextView) rootView.findViewById(R.id.employeeName);
			TextView employeeQte = (TextView) rootView.findViewById(R.id.qte);
			
			employeeName.setText(employee.getPrenom() + " " + employee.getNom());
			employeeQte.setText(Integer.toString(employee.getQte()));
		}
		
		Log.d(TAG, "EmployeeDetailFragment-onCreateView: returning view");
		return rootView;
	}
}