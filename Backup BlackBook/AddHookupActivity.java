package com.luisalmeida.theblackbook;

import java.util.Vector;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddHookupActivity extends ActionBarActivity {
	
	private static final int SELECT_PICTURE = 1;
	 
    private static ImageView img;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_add_hookup);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
		
	}

	public void loadImage(View view){
		
		img = (ImageView)findViewById(R.id.imageViewHookup);
		Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"), SELECT_PICTURE);
	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        if (resultCode == RESULT_OK) {
	            if (requestCode == SELECT_PICTURE) {
	                Uri selectedImageUri = data.getData();
	                img.setImageURI(selectedImageUri);
	                scaleImage();
	            }
	        }
	    }
	 
	public String getPath(Uri uri) {
	        String[] projection = { MediaStore.Images.Media.DATA };
	        Cursor cursor = managedQuery(uri, projection, null, null, null);
	        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
	        cursor.moveToFirst();
	        return cursor.getString(column_index);
	    }
	 
	public void scaleImage()
	  {
	      // Get the ImageView and its bitmap
	      ImageView view = (ImageView) findViewById(R.id.imageViewHookup);
	      Drawable drawing = view.getDrawable();
	      if (drawing == null) {
	          return; // Checking for null & return, as suggested in comments
	      }
	      Bitmap bitmap = ((BitmapDrawable)drawing).getBitmap();

	      // Get current dimensions AND the desired bounding box
	      int width = bitmap.getWidth();
	      int height = bitmap.getHeight();
	      int bounding = dpToPx(250);
	      Log.i("Test", "original width = " + Integer.toString(width));
	      Log.i("Test", "original height = " + Integer.toString(height));
	      Log.i("Test", "bounding = " + Integer.toString(bounding));

	      // Determine how much to scale: the dimension requiring less scaling is
	      // closer to the its side. This way the image always stays inside your
	      // bounding box AND either x/y axis touches it.  
	      float xScale = ((float) bounding) / width;
	      float yScale = ((float) bounding) / height;
	      float scale = (xScale <= yScale) ? xScale : yScale;
	      Log.i("Test", "xScale = " + Float.toString(xScale));
	      Log.i("Test", "yScale = " + Float.toString(yScale));
	      Log.i("Test", "scale = " + Float.toString(scale));

	      // Create a matrix for the scaling and add the scaling data
	      Matrix matrix = new Matrix();
	      matrix.postScale(scale, scale);

	      // Create a new bitmap and convert it to a format understood by the ImageView 
	      Bitmap scaledBitmap = Bitmap.createBitmap(bitmap, 0, 0, width, height, matrix, true);
	      width = scaledBitmap.getWidth(); // re-use
	      height = scaledBitmap.getHeight(); // re-use
	      BitmapDrawable result = new BitmapDrawable(scaledBitmap);
	      Log.i("Test", "scaled width = " + Integer.toString(width));
	      Log.i("Test", "scaled height = " + Integer.toString(height));

	      // Apply the scaled bitmap
	      view.setImageDrawable(result);

	      // Now change ImageView's dimensions to match the scaled image
	      LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) view.getLayoutParams(); 
	      params.width = width;
	      params.height = height;
	      view.setLayoutParams(params);

	      Log.i("Test", "done");
	  }
	 
	private int dpToPx(int dp)
	  {
	      float density = getApplicationContext().getResources().getDisplayMetrics().density;
	      return Math.round((float)dp * density);
	  }
	
	public void saveHookup(View view){
		Hookup hookup = new Hookup();
		DataManager data = new DataManager();
		FileManager files = new FileManager();
		Vector filelist = files.getFileList(this.getApplicationContext());
		EditText hookupname = (EditText)findViewById(R.id.editTextName);
		EditText hookupage = (EditText)findViewById(R.id.editTextAge);
		EditText hookupplace = (EditText)findViewById(R.id.editTextPlace);
		EditText hookupfrom = (EditText)findViewById(R.id.editTextFrom);
		EditText hookupobs = (EditText)findViewById(R.id.editTextObs);
		CheckBox hookupBJ = (CheckBox)findViewById(R.id.checkBox1);
		CheckBox hookupHJ = (CheckBox)findViewById(R.id.checkBox2);
		CheckBox hookupAll = (CheckBox)findViewById(R.id.checkBox3);
		TextView saved = (TextView)findViewById(R.id.textViewSaved);
		ImageView hookupimage = (ImageView)findViewById(R.id.imageViewHookup);
		hookupimage.buildDrawingCache();
	    Bitmap bm=hookupimage.getDrawingCache();
		hookup.setName(hookupname.getText().toString());
		hookup.setAge(hookupage.getText().toString());
		hookup.setPlace(hookupplace.getText().toString());
		hookup.setFrom(hookupfrom.getText().toString());
		hookup.setObser(hookupobs.getText().toString());
		if(hookupBJ.isChecked())
			hookup.setBJ(true);
		if(hookupHJ.isChecked())
			hookup.setHJ(true);
		if(hookupAll.isChecked())
			hookup.setAll(true);
		hookup.setFileName(hookup.getName().replaceAll("\\s", ""));
		hookup.setImage(hookup.getFileName()+"img");
	
		if(hookup.getName().equals("")){
			saved.setText("Insert NAME!");
			saved.setTextColor( -65536);
		}
		else {
			int i=0;
			String hookupfileini=hookup.getFileName(); 
			while(filelist.contains(hookup.getFileName()+".txt")){
				i++;
				hookup.setFileName(hookupfileini+i);
				hookup.setImage(hookupfileini+"img"+i);
			}
		data.writeData(hookup.toString(), hookup.getFileName()+".txt", this.getApplicationContext());
		data.saveImage(bm,hookup.getImage(),this.getApplicationContext());
		saved.setText("YEAAAAH!");
		saved.setTextColor(-16711936);
		finish();
		}
	}

}
