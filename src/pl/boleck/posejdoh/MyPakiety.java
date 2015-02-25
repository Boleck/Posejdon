package pl.boleck.posejdoh;

import java.util.Arrays;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MyPakiety extends Activity {

	private ListView listView;
	private String extras;

	private Object[] arr;
	private String[] stringArray;
	private Map<String, Map<String, Object>> Pakiet;



	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_pakiety);
		//Getting Extras
		extras = getIntent().getExtras().getString("pakiet");
		DataHolder.setRodzaj(extras);
		Pakiet = (Map<String, Map<String, Object>>) DataHolder.rodzaje.get(extras.toString());
		
		arr = Pakiet.get("Pakiety").keySet().toArray();
		stringArray = Arrays.copyOf(arr, arr.length, String[].class);
		
	    //Ukrywanie Actionbara
		//getActionBar().hide();
		//ListView
        listView = (ListView) findViewById(R.id.list);
       
	    ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,R.layout.list_fruit, R.id.text1, stringArray);
	    listView.setAdapter(listAdapter);
       
	    listView.setOnItemClickListener(new OnItemClickListener() {

	    	@Override
	    	public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
	    		String  itemValue    = (String) listView.getItemAtPosition(position);
	    		DataHolder.setPakiet(itemValue);
	    		view.setSelected(true);
	    		//Log.d("PM", DataHolder.PAKIET);
	    		
				Intent intent = new Intent(
						MyPakiety.this,
						DodatkiActivity.class);
			 		startActivity(intent);
		}
	        
	});
	  
	    

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my_pakiety, menu);
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