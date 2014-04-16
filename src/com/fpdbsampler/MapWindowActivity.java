package com.fpdbsampler;
import java.util.ArrayList;

import com.FPMap.FPMap;
import com.FPMap.FPPoint;
import com.FPMap.FPPointClickListener;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class MapWindowActivity extends Activity implements FPPointClickListener {
	//display and choose floor
	private Spinner spinnerFloor;
	
	//display and manipulate Fingerprint points
	private FPMap fpmap;
	
	private FPSamplingSession session;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_window);
		
		spinnerFloor = (Spinner)findViewById(R.id.spinnerFloor);
		fpmap = (FPMap)findViewById(R.id.fpmap);
		fpmap.setFPPointClickListener(this);
		
		downloadFloorList();
		
		spinnerFloor.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id)
			{
				Floor f = (Floor)parent.getItemAtPosition(pos);
				//trigger map updating to selected floor
				downloadMap(f.map_id);
				//update fingerprint list also
				downloadFPPointList(f.map_id);
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent)
			{
				//select item 0 as default
				if(parent.getCount()>0)
					parent.setSelection(0);
				
			}});
	}


	@Override
	public void OnClick(FPPoint fppoint)
	{
		// TODO handle fp point click action
		
	}
	
	//create AsyncTask to download floor list
	private void downloadFloorList()
	{
		new FloorListDownloadTask().execute();
	}
	
	//AsyncTask to download floor map
	private void downloadMap(int map_id)
	{
		new MapDownloadTask().execute(map_id);
	}
	
	//AyncTask to download fp point list
	private void downloadFPPointList(int map_id)
	{
		new FPListDownloadTask().execute(map_id);
	}
	
	//only used for floor spinner
	private class Floor
	{
		String floor_text;
		int map_id;
		@Override
		public String toString()
		{
			return floor_text;
		}
	}

	//used to download floor list and update UI.
	private class FloorListDownloadTask extends AsyncTask<Void, Integer, ArrayAdapter<Floor>>
	{

		@Override
		protected void onPostExecute(ArrayAdapter<Floor> result)
		{
			if(result != null)
			{
				//update UI
				spinnerFloor.setAdapter(result);
			}
			else
			{
				Toast.makeText(getApplicationContext(), R.string.load_floor_failure, Toast.LENGTH_LONG).show();
			}
		}

		@Override
		protected ArrayAdapter<Floor> doInBackground(Void... params)
		{
			// TODO use session object to download
			return null;
		}

	}

	//used to download floor map and update UI
	private class MapDownloadTask extends AsyncTask<Integer, Integer, Bitmap>
	{

		@Override
		protected Bitmap doInBackground(Integer... map_ids)
		{
			// TODO use session object to download
			return null;
		}

		@Override
		protected void onPostExecute(Bitmap result)
		{
			if(result != null)
			{
				//update UI
				fpmap.setImageBitmap(result);
			}
			else
			{
				Toast.makeText(getApplicationContext(), R.string.load_map_failure, Toast.LENGTH_LONG).show();
			}
		}

	}
	
	//used to download fingerprint point list of one floor
	private class FPListDownloadTask extends AsyncTask<Integer, Integer, ArrayList<FPPoint>>
	{

		@Override
		protected ArrayList<FPPoint> doInBackground(Integer... params)
		{
			// TODO use session to download fp point list, also state list of fp point
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<FPPoint> result)
		{
			// update UI
			if(result != null)
			{
				fpmap.setFPlist(result);
			}
			else
			{
				Toast.makeText(getApplicationContext(), R.string.load_fplist_failure, Toast.LENGTH_LONG).show();
			}
		}

	}
}
