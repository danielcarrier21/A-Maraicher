package com.studio21.mobile.helper;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.studio21.mobile.R;
import com.studio21.mobile.models.Employee;

public class CustomAdapter extends BaseAdapter
{
	private ArrayList<Employee> employees;
    Context ctx;
    int layoutId; 
    
    public CustomAdapter (ArrayList<Employee> data, int layoutRessourceId, Context c)
    {
        employees = data;
        ctx = c;
        layoutId = layoutRessourceId;
    }
   
    public int getCount() 
    {
        return employees.size();
    }
    
    public Object getItem(int position) 
    {
        return employees.get(position);
    }
 
    public long getItemId(int position) 
    {
        return position;
    }
   
    @Override
    public View getView(int position, View convertView, ViewGroup parent) 
    {
         View view = convertView;
         if (view == null)
         {
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(layoutId, null);
         }
         
         //ImageView image = (ImageView) v.findViewById(R.id.employeePict);
         TextView firstNameView = (TextView)view.findViewById(R.id.employeeFirstName);
         TextView lastNameView = (TextView)view.findViewById(R.id.employeeLastName);
         //TextView fruitView = (TextView)v.findViewById(R.id.fruitName);
         //TextView qteView = (TextView)v.findViewById(R.id.qte);
 		
         Employee empl = employees.get(position);
           
         //image.setImageResource(msg.icon);
         firstNameView.setText(empl.getPrenom());
         lastNameView.setText(empl.getNom());
         //fruitView.setText(empl.getFruit());
         //qteView.setText(empl.getQte());                             
                      
         return view;
    }
}
