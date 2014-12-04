package com.luisalmeida.theblackbook;

import java.util.Vector;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CheckHookups extends Activity {

	ListView listView ;
	FileManager file = new FileManager();
	
	public Vector getFileNameList(Vector list){
		DataManager data = new DataManager();
		String name;
		Vector namelist = new Vector();
		for(int i = 0; i < list.size();i++){
			name = data.readSavedData((String) list.get(i), "NAME ", this.getApplicationContext());
			namelist.addElement(name);			
			}
		return namelist;
	}

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_check_hookups);
        
        // Get ListView object from xml
        listView = (ListView) findViewById(R.id.list);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
        
        // Defined Array values to show in ListView
        Vector lista = getFileNameList(file.getFileList(this.getApplicationContext()));
        Log.i("info",lista.toString());

        // Define a new Adapter
        // First parameter - Context
        // Second parameter - Layout for the row
        // Third parameter - ID of the TextView to which the data is written
        // Forth - the Array of data

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
          android.R.layout.simple_list_item_1, android.R.id.text1, lista);


        // Assign adapter to ListView
        listView.setAdapter(adapter); 
        
        // ListView Item Click Listener
        listView.setOnItemClickListener(new OnItemClickListener() {

              @Override
              public void onItemClick(AdapterView<?> parent, View view,
                 int position, long id) {
               Intent intent= new Intent(getApplicationContext(),CheckHookupsfinal.class);
              intent.putExtra("", file.getFileList(getApplicationContext()).get(position).toString());
               startActivity(intent);
              
               // ListView Clicked item value
               String  itemValue    = (String) listView.getItemAtPosition(position);
                  
   
              }

         }); 
    }

}
