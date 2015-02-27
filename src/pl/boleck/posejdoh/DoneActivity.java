package pl.boleck.posejdoh;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.firebase.client.Firebase;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DoneActivity extends Activity {

	private TextView BtDone;
	private EditText Reje;
	private EditText Prac;
	private EditText Kome;
	private TextView Pods;
	private Firebase myFirebaseRef;
	private Firebase newref;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_done);
        SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd");
        String time = SDF.format(new Date());
		Firebase.setAndroidContext(this);
		
  		myFirebaseRef = new Firebase("https://myjnia.firebaseio.com/Zlecenia/"+time.toString());
  		newref = myFirebaseRef.push();
		BtDone = (TextView)findViewById(R.id.BtDone);
		Reje = (EditText)findViewById(R.id.Rejestracja);
		Prac = (EditText)findViewById(R.id.Pracownik);
		Kome = (EditText)findViewById(R.id.Komentarz);
		Pods = (TextView)findViewById(R.id.Podsumowanie);
		Pods.setText("Podsumowanie: \nRodzaj: "+ DataHolder.getRODZAJ() + "\nPakiet: " + DataHolder.getPAKIET());
		BtDone.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Map<String, String> post1 = new HashMap<String, String>();
				post1.put("Rodzaj",DataHolder.getRODZAJ());
				post1.put("Pakiet",DataHolder.getPAKIET());
				post1.put("Rejestracja",Reje.getText().toString());
				post1.put("Pracownik",Prac.getText().toString());
				post1.put("Komentarz",Kome.getText().toString());
				newref.setValue(post1);
				// TODO Wysyłanie danych do SQLite
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.done, menu);
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
