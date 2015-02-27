package pl.boleck.posejdoh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView serwisBt;
	private TextView komisBt;
	private TextView noweBt;
	private TextView inneBt;
	private ProgressDialog pDialog;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //getActionBar().hide();
		pDialog = new ProgressDialog(MainActivity.this);
		pDialog.setMessage("Pobieranie danych od Szefa");
		pDialog.setCancelable(false); // nie da się zamknąć klikając w ekran
		pDialog.show();
      //Firebase
      		Firebase.setAndroidContext(this);
      		Firebase myFirebaseRef = new Firebase("https://myjnia.firebaseio.com/");
      	    myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
      			
      			private Object stringArray;
				private Object[] arr;
				private Object[] z;
				private Object[] e;
				

				@Override
      			public void onDataChange(DataSnapshot snapshot) {
      			//pobieranie danych na temat pakietu

      				Map<String, Object> rodzaje = (Map<String, Object>) snapshot.child("Rodzaj").getValue();
      				Map<String, Object> dodatki = (Map<String, Object>) snapshot.child("Dodatki").getValue();
      				 
      				Map<String, Object> value = (Map<String, Object>)snapshot.getValue();
      				z = dodatki.keySet().toArray();
    				String[] rodz = new String[z.length];
      				for (int i = 0; i < z.length; i++) {
      					Map<String, String> e =  (Map<String, String>) dodatki.get(z[i]);
      					
      					rodz[i] = e.get("Nazwa");
      					
					}
      				
      				DataHolder.setRodzaje(rodzaje);
      				DataHolder.setDodatki(rodz);
      				//Log.d("PB", DataHolder.rodzaje.toString());
      				//Log.d("PB", DataHolder.dodatki.toString());
      				//Zamykanie dialogu pobieranie jsona
      				pDialog.dismiss();

      			}
				
      			
      			@Override
      			public void onCancelled(FirebaseError arg0) {
      				//Errory
      				System.out.println("The read failed: " + arg0.getMessage());
      			}
      		});
        serwisBt = (TextView)findViewById(R.id.Serwis);
        komisBt = (TextView)findViewById(R.id.Komis);
        noweBt = (TextView)findViewById(R.id.Nowe);
        inneBt = (TextView)findViewById(R.id.Inne);
        serwisBt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		 		Intent intent = new Intent(
				 		MainActivity.this,
				 		MyPakiety.class);
				 		intent.putExtra("pakiet", "Serwis");
				 		startActivity(intent);
				
			}
		});
        komisBt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		 		Intent intent = new Intent(
				 		MainActivity.this,
				 		MyPakiety.class);
				 		intent.putExtra("pakiet", "Komis");
				 		startActivity(intent);
				
			}
		});
        noweBt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		 		Intent intent = new Intent(
				 		MainActivity.this,
				 		MyPakiety.class);
				 		intent.putExtra("pakiet", "Nowe");
				 		startActivity(intent);	
				 	
				
			}
		});
        inneBt.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
		 		Intent intent = new Intent(
				 		MainActivity.this,
				 		MyPakiety.class);
				 		intent.putExtra("pakiet", "Inne");
				 		startActivity(intent);
				
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
    	// Zabezpieczenie przed przypadkowym zamknieciem aplikacji
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Builder alert = new AlertDialog.Builder(this)
            .setIcon(android.R.drawable.ic_dialog_alert)
            .setTitle(R.string.quit)
            .setMessage(R.string.really_quit)
            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int which) {
                    MainActivity.this.finish();    
                }

            })
            .setNegativeButton(R.string.no, null);
            alert.show();

            return true;
        }
        else {
            return super.onKeyDown(keyCode, event);
        }
    }
    
}