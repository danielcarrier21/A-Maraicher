package com.studio21.mobile;

import com.studio21.mobile.models.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class EmployeeAddFragment extends Fragment
{
	private final String TAG = "A-Log";
	
	public EmployeeAddFragment() {
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		Log.d(TAG, "EmployeeAddFragment: onCreate");
		setHasOptionsMenu(true);
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		Log.d(TAG, "EmployeeAddFragment: onCreateView");
		View rootview = inflater.inflate(R.layout.employee_add_item, container, false);	
		return rootview;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		Log.d(TAG, "EmployeeAddFragment-onCreateView: onOptionsItemSelected");
		switch(item.getItemId())
		{
		case R.id.item_save:
			addEmployee();
			getFragmentManager().popBackStackImmediate();
			return true;
		case R.id.item_cancel:
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
		
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
	{
		menu.add(Menu.NONE, R.id.item_save, 10, "Save").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		menu.add(Menu.NONE, R.id.item_cancel, 10, "Cancel").setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
		super.onCreateOptionsMenu(menu, inflater);
	}
	
	private void addEmployee()
	{
		TextView emplName = (TextView)this.getView().findViewById(R.id.newEmployeeName);
		TextView emplQte = (TextView)this.getView().findViewById(R.id.newQte);
		Employee newEmployee = new Employee();
		newEmployee.setPrenom(emplName.getText().toString());
		newEmployee.setQte(Integer.parseInt(emplQte.getText().toString()));
		EmployeeListFragment.Employees.add(newEmployee);
	}

}
