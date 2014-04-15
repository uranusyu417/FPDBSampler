package com.fpdbsampler;
import com.FPMap.FPMap;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;


public class MapWindowActivity extends Activity {
	//display and choose floor
	private Spinner spinnerFloor;
	
	//display and manipulate Fingerprint points
	private FPMap fpmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map_window);
		
		spinnerFloor = (Spinner)findViewById(R.id.spinnerFloor);
		fpmap = (FPMap)findViewById(R.id.fpmap);
	}

	//used to download floor list and update UI.
	private class FloorListDownloadTask extends AsyncTask<String, Integer, ArrayAdapter<?>>
	{

		@Override
		protected ArrayAdapter<?> doInBackground(String... arg0)
		{
			// TODO perform http download
			return null;
		}

		@Override
		protected void onPostExecute(ArrayAdapter<?> result)
		{
			// TODO update spinner of floor
			super.onPostExecute(result);
		}
		
	}
	
	//used to download floor map and update UI
	private class MapDownloadTask extends AsyncTask<String, Integer, Bitmap>
	{

		@Override
		protected Bitmap doInBackground(String... params)
		{
			// TODO download map from http server
			return null;
		}
		
	}

}
