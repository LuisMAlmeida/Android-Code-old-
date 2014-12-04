package com.luisalmeida.theblackbook;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class DataManager {

	
	public void writeData(String data, String file,Context ctx){
		FileOutputStream outputStream;

		try {
		  outputStream = ctx.openFileOutput(file, Context.MODE_PRIVATE);
		  outputStream.write(data.getBytes());
		  outputStream.close();
		} catch (Exception e) {
		  e.printStackTrace();
		}
	}
	
	
	public String readSavedData(String file,String target,Context ctx){
		StringBuffer storage = new StringBuffer("");
		try{
			FileInputStream fIn = ctx.openFileInput(file);
			InputStreamReader isr = new InputStreamReader(fIn);
			BufferedReader buffreader = new BufferedReader(isr);
			String readString = buffreader.readLine();
			while(readString != null) {
				String[] split = readString.split("\\|");
				if(split[0].equals(target)){
						storage.append(split[1]);						
				}
				readString = buffreader.readLine();
			}
			isr.close();
		} catch (IOException ioe) {
            ioe.printStackTrace();
        }
		return storage.toString();
	}
	
	
	public void saveImage(Bitmap bitmapImage,String name,Context ctx){
        ContextWrapper cw = new ContextWrapper(ctx.getApplicationContext());
        
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
       
        File mypath=new File(directory,name);

        FileOutputStream fos = null;
        try {           

            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
	
	
	public Bitmap loadImage(String name,Context ctx){
		Bitmap b=null;
		ContextWrapper cw = new ContextWrapper(ctx.getApplicationContext());
		//File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
		try{
		String filePath = "/data/data/com.luisalmeida.theblackbook/app_imageDir/"+ name;
		Log.i("info",filePath.toString());
		FileInputStream fi = new FileInputStream(filePath);
		//File f=new File(directory, name);
		//Log.i("info",f.toString());
	    b = BitmapFactory.decodeStream(fi);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return b;
	}
	
	
}

