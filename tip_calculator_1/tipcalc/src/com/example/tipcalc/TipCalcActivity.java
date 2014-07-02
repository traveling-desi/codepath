package com.example.tipcalc;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TipCalcActivity extends Activity {

	private EditText amnt_txt = null;
	private Button btn_15 = null;
	private Button btn_20 = null;
	private Button btn_25 = null;
	private int amnt = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calc);
		
		amnt_txt = (EditText) findViewById(R.id.amnt_txt);
		
		
		
		btn_15 = (Button) findViewById(R.id.btn_15);
		btn_20 = (Button) findViewById(R.id.btn_20);
		btn_25 = (Button) findViewById(R.id.btn_25);
		
	}

	private int get_amount() {
		// TODO Auto-generated method stub
		int amnt = 10;
		String amnt_str = amnt_txt.getText().toString();
		try {
				amnt = Integer.parseInt(amnt_str);
				
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		Log.d("AMNT1", Integer.toString(amnt));
		return amnt;
	}

	public void btn_click(View v) {
		// TODO Auto-generated method stub
		
		int viewid = v.getId();
		amnt = get_amount();
		double tip = 0;
		Log.d("AMNT2", Integer.toString(amnt));
		switch (viewid) {
				case(R.id.btn_15):
					tip =  (amnt * 0.15);
					break;
				case(R.id.btn_20):
					tip =  (amnt * 0.20);
					break;
				case(R.id.btn_25):
					tip =  (amnt * 0.25);
		}
		Toast.makeText(getApplicationContext(), "Pay this tip amount " + Double.toString(tip), Toast.LENGTH_LONG).show();
	}
}
