package pl.boleck.posejdoh;

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
      		Firebase myFirebaseRef = new Firebase("https://myjnia.firebaseio.com/Rodzaj/");
      	    myFirebaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
      			
      			private Object stringArray;
				private Object[] arr;
				

				@Override
      			public void onDataChange(DataSnapshot snapshot) {
      			//pobieranie danych na temat pakietu

      				Map<String, Object> newPost = (Map<String, Object>) snapshot.getValue();
      				 
      				
      				//arr = newPost.keySet().toArray();
      				//stringArray = Arrays.copyOf(arr, arr.length, String[].class);
      				
      				DataHolder.setNewPost(newPost);
      				System.out.println(DataHolder.newPost);
      				pDialog.dismiss();
      				//ArrayList<String> list = (new Jsontoarray()).Convert(jsonObject);

      				System.out.println("fak de łot?");

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
    	// Zabespieczenie przed przypadkowym zamknieciem aplikacji
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