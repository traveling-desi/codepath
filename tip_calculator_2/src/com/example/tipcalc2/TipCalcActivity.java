package com.example.tipcalc2;



import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class TipCalcActivity extends Activity {

	private EditText amnt_txt = null;
	private Button btn = null;
	private enum action {amnt, tip, split};
	private action now = null; 
	private int split = 0;
	private double tip_perc = 0;
	private double amnt = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tip_calc);
		
		amnt_txt = (EditText) findViewById(R.id.amnt_txt);
		btn = (Button) findViewById(R.id.btn);
		btn.setText("Submit");
		amnt_txt.setHint("Enter the Amount of Check");
		amnt_txt.setText("");
		now = action.amnt;

		
	}

	private double get_amount() {
		// TODO Auto-generated method stub
		double amnt = 0;
		String amnt_str = amnt_txt.getText().toString();
		try {
				amnt = Integer.parseInt(amnt_str);
				
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		//Log.d("AMNT1", Double.toString(amnt));
		return amnt;
	}

	
	
	public void btn_click(View v) {
		// TODO Auto-generated method stub
		
		double tip = 0;

		switch(now) {
			case amnt:
				amnt = get_amount();
			    now = action.tip;
				amnt_txt.setHint("Enter the tip %");
				amnt_txt.setText("");
			    break;
			case tip:
				tip_perc = get_amount()/100;
				now = action.split;
				amnt_txt.setHint("Enter the # of splits");
				amnt_txt.setText("");
				break;
			case split:
				split=(int) get_amount();
				now = action.amnt;
				amnt_txt.setHint("Enter the Amount of Check");
				amnt_txt.setText("");
				tip =  (amnt * tip_perc)/split;
				Toast.makeText(getApplicationContext(), "Pay this tip amount " + Double.toString(tip), Toast.LENGTH_LONG).show();
		}
		//Log.d("AMNT2", Double.toString(amnt));
	}
}
