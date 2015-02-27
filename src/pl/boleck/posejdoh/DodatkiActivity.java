package pl.boleck.posejdoh;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class DodatkiActivity extends Activity {

	private TextView BtNextDA;
	private String[] Dodatek;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_dodatki);
		Dodatek =  DataHolder.dodatki;
		
		//Log.d("PB",stringArray.length + "");
		
		//stringArray = Arrays.copyOf(arr, arr.length, String[].class);
		
		listView = (ListView) findViewById(R.id.list2);
		
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,R.layout.list_fruit, R.id.text1, Dodatek);
	    listView.setAdapter(listAdapter);
	    
		BtNextDA = (TextView) findViewById(R.id.BtNextDA);
		BtNextDA.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Przejdz do nastepnej aktywnosci
		 		Intent intent = new Intent(
				 		DodatkiActivity.this,
				 		DoneActivity.class);
				 		startActivity(intent);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.dodatki, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
