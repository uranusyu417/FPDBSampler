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
import android.widget.TextView;

public class EntryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry);
		
		//display wifi mac address of mobile
		TextView tvwifi = (TextView)findViewById(R.id.textViewMacAdr);
		
		WifiManager wifimgr = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiinfo = wifimgr.getConnectionInfo();
		tvwifi.setText(wifiinfo.getMacAddress());
		
		Button btn = (Button)findViewById(R.id.buttonStart);
		btn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v)
			{
				// TODO format mac string, send to web server
				// start map window ui
				v.getContext().startActivity(new Intent(v.getContext(), MapWindowActivity.class));
			}});
	}

}
