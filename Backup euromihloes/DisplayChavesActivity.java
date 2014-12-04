package com.luisalmeida.geradorchaveseuromilhoes;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

public class DisplayChavesActivity extends FragmentActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_display_chaves);
	    Intent intent = getIntent();
	    String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
	    TextView textView = (TextView) findViewById(R.id.textView1) ;
	    textView.setTextSize(20);
	    textView.setText(message); 
	}

}
