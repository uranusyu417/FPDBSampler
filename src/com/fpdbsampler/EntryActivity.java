package com.fpdbsampler;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EntryActivity extends Activity {
	
	private EditText editTextServer;
	private TextView tvwifi;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry);
		
		//display wifi mac address of mobile
		tvwifi = (TextView)findViewById(R.id.textViewMacAdr);
		
		WifiManager wifimgr = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiinfo = wifimgr.getConnectionInfo();
		tvwifi.setText(wifiinfo.getMacAddress());
		
		editTextServer = (EditText)findViewById(R.id.editTextServerAdr);
		
		Button btn = (Button)findViewById(R.id.buttonStart);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v)
			{
				// create session to server
				String mac = tvwifi.getText().toString();
				String ip = editTextServer.getText().toString();
				if(FPSamplingSession.initialize(ip,mac) == null)
				{
					Toast.makeText(v.getContext(), R.string.start_session_failure, Toast.LENGTH_LONG).show();
				}
				else
				{
				// start map window ui
				v.getContext().startActivity(new Intent(v.getContext(), MapWindowActivity.class));
				}
			}});
	}

}
