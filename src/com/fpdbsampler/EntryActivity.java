package com.fpdbsampler;

import android.app.Activity;
import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.widget.TextView;

public class EntryActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_entry);
		
		TextView tvwifi = (TextView)findViewById(R.id.textViewMacAdr);
		
		WifiManager wifimgr = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiinfo = wifimgr.getConnectionInfo();
		tvwifi.setText(wifiinfo.getMacAddress());
	}

}
