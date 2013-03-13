package com.studio21.mobile.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class Employee
{
	private String Prenom;
	private String Nom;
	private String Champ;
	private String Fruit;
	private int Qte;
	
	public Employee()
	{
	}
	
	public Employee(String prenom, String nom, int qte)
	{
		setPrenom(prenom);
		setNom(nom);
		setQte(qte);
		setChamp("champ-1");
		setFruit("bleuet");
	}

	public String getPrenom() {
		return Prenom;
	}

	public void setPrenom(String prenom) {
		Prenom = prenom;
	}

	public String getNom() {
		return Nom;
	}

	public void setNom(String nom) {
		Nom = nom;
	}

	public String getChamp() {
		return Champ;
	}

	public void setChamp(String champ) {
		Champ = champ;
	}

	public String getFruit() {
		return Fruit;
	}

	public void setFruit(String fruit) {
		Fruit = fruit;
	}

	public int getQte() {
		return Qte;
	}

	public void setQte(int qte) {
		Qte = qte;
	}
	
	public JSONObject toJSONObject()
	{
		JSONObject jsonObj = new JSONObject();
		try
		{
			jsonObj.put("prenom", Prenom);
			jsonObj.put("nom", Nom);
			jsonObj.put("qte", Qte);
			jsonObj.put("fruit", Fruit);
			jsonObj.put("champ", Champ);
		} 
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		return jsonObj;
	}
	
	public static ArrayList<Employee> JsonToArrayList(JSONArray jsonArr)
	{
		ArrayList<Employee> Employees = new ArrayList<Employee>();
		
		try 
		{
			for(int i=0; i < jsonArr.length(); i++)
			{
				JSONObject obj = jsonArr.getJSONObject(i);
				Employee emp = new Employee();
				emp.setPrenom(obj.getString("prenom"));
				emp.setNom(obj.getString("nom"));
				emp.setQte(obj.getInt("qte"));
				Employees.add(emp);
			}
		} 
		catch (JSONException e) 
		{
			Log.e("A-Log", e.getMessage());
		}
		
		Log.d("A-Log", "Employees count: " + Employees.size());
		
		return Employees;
	}

}
