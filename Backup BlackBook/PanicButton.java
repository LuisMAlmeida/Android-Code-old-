package com.luisalmeida.theblackbook;

import java.io.File;
import java.util.Vector;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class PanicButton extends ActionBarActivity {

	int sure = 1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_panic_button);	
	}

	
	public void panicYes(View view){
		FileManager file = new FileManager();
		File path = new File("/data/data/com.luisalmeida.theblackbook");
		TextView text = (TextView)findViewById(R.id.textViewPanic);
		Vector files = file.getFileList(getApplicationContext());
		Button button = (Button)findViewById(R.id.buttonPanicYes);
		switch(sure){
		case 1: text.setText("Are you really sure dude? COMON!");
				button.setText("Yes i am!");
				sure++;
				break;
		case 2: text.setText("Dont make me kill the APP so you cant Delete!!");
				button.setText("You dont have the balls!");
				sure++;
				break;
		case 3: text.setText("Your girlfriend... who am i kidding your RIGHT HAND found this out right? HAHA");
				button.setText("FUCK YOU SHE IS REAL!");
				sure++;
				break;
		case 4: file.deleteDirectory(path);
				text.setText("DELETED!");
				finish();
			
		}
		
	}
	
	public void panicNo(View view){
		finish();
	}

}
