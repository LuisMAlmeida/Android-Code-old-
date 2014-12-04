package com.luisalmeida.theblackbook;

import java.io.File;
import java.util.Vector;

import android.content.Context;
import android.util.Log;

public class FileManager {
	
	public Vector getFileList(Context ctx){
		File directory = ctx.getFilesDir();
		Vector filelist = new Vector();
		String name;
		for (File f : directory.listFiles()) {
		    if (f.isFile()){
		        name = f.getName();
		        filelist.addElement(name);
		    }
		}
		Log.i("info",filelist.toString());
		return filelist;
	}
	
	public static boolean deleteDirectory(File path) {
        // TODO Auto-generated method stub
        if( path.exists() ) {
            File[] files = path.listFiles();
            for(int i=0; i<files.length; i++) {
                if(files[i].isDirectory()) {
                    deleteDirectory(files[i]);
                }
                else {
                    files[i].delete();
                }
            }
        }
        return(path.delete());
    }
}
	
	
