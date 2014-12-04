package com.luisalmeida.geradorchaveseuromilhoes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends FragmentActivity {

String file = "chaves.dat";
public final static String EXTRA_MESSAGE = "Sup";

	public void writeData(String data){
		
		try {
			FileOutputStream fOut = openFileOutput(file , MODE_APPEND);
		    OutputStreamWriter osw = new OutputStreamWriter(fOut);
		    osw.write(data);
		    osw.flush();
		    osw.close();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	
	public String readSavedData(){
		StringBuffer storage = new StringBuffer("");
		try{
		 FileInputStream fIn = openFileInput(file);
		 InputStreamReader isr = new InputStreamReader(fIn);
		 BufferedReader buffreader = new BufferedReader(isr);
		 String readString = buffreader.readLine();
		 while ( readString != null ) {
			 String[] split = readString.split("\\ ");
			if(split[0].equals("Números:")){
	        		storage.append("Números: " + split[1]+" "+split[2]+" "+split[3]+" "+split[4]+" "+split[5]+"\n");
	        	}
	        if(split[0].equals("Estrelas:")){
	        	storage.append("Estrelas: "+ split[1]+" "+split[2]+" "+"\n\n");
	        	}
			 readString = buffreader.readLine();
		 }
		 isr.close();
		        } catch (IOException ioe) {
		            ioe.printStackTrace();
		        }
	return storage.toString();
}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
	}

	public void gerar(View view){
    	Intent intent = new Intent(this, DisplayChavesActivity.class);
    	Gerador gera = new Gerador(); 
    	String message = gera.toString();
    	writeData(message);
    	intent.putExtra(EXTRA_MESSAGE, message);
    	startActivity(intent);
    }
	
	 public void apagarHistorico(View view){
	    	Intent intent = new Intent(this, DisplayChavesActivity.class);
	    	String filePath=getFilesDir().toString();
	    	File file = new File(filePath+"/"+"chaves.dat");
	    	file.delete();
	    	intent.putExtra(EXTRA_MESSAGE, "Histórico de chaves apagado");
	    	startActivity(intent);
	    }
	 
	 public void chavesAnteriores(View view){
		Intent intent = new Intent(this, DisplayChavesActivity.class);
		intent.putExtra(EXTRA_MESSAGE, readSavedData());
     	startActivity(intent);
	 }

}
