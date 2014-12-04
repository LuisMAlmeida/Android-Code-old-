package com.luisalmeida.theblackbook;

import java.io.IOException;
import java.net.MalformedURLException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;
import com.facebook.android.Util;
import com.facebook.model.GraphUser;
import com.facebook.widget.ProfilePictureView;


public class MainActivity extends ActionBarActivity {
	ProfilePictureView profilePictureView;
	String fb_id;
	private Facebook facebook;
	private AsyncFacebookRunner mAsyncRunner;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		getAppKeyHash();
		profilePictureView = (ProfilePictureView) findViewById(R.id.imageFace);
		getUserNameAndFbAcess();
		openFbProf(fb_id,"luis.almeida.315");
	
		}

	
	public void addHookup(View view){
		Intent intent = new Intent(this, AddHookupActivity.class);
     	startActivity(intent);
	}
	
	public void checkHookups(View view){
		Intent intent = new Intent(this, CheckHookups.class);
		startActivity(intent);
	}
	
	public void panicButton(View view){
		Intent intent = new Intent(this,PanicButton.class);
		startActivity(intent);
	}
	
	private void getAppKeyHash() {
	    try {
	        PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
	        for (Signature signature : info.signatures) {
	            MessageDigest md;

	            md = MessageDigest.getInstance("SHA");
	            md.update(signature.toByteArray());
	            String something = new String(Base64.encode(md.digest(), 0));
	            Log.d("Hash key", something);
	        } 
	    }
	    catch (NameNotFoundException e1) {
	        // TODO Auto-generated catch block
	        Log.e("name not found", e1.toString());
	    }

	    catch (NoSuchAlgorithmException e) {
	        // TODO Auto-generated catch block
	        Log.e("no such an algorithm", e.toString());
	    }
	    catch (Exception e){
	        Log.e("exception", e.toString());
	    }

	}

	@Override
	  public void onActivityResult(int requestCode, int resultCode, Intent data) {
	      super.onActivityResult(requestCode, resultCode, data);
	      Session.getActiveSession().onActivityResult(this, requestCode, resultCode, data);
	  }
	  
	private void getUserNameAndFbAcess(){
	Session.openActiveSession(this, true, new Session.StatusCallback() {

	      @Override
	      public void call(Session session, SessionState state, Exception exception) {
	        if (session.isOpened()) {
	          // make request to the /me API
	          Request.newMeRequest(session, new Request.GraphUserCallback() {

	            // callback after Graph API response with user object
	            @Override
	            public void onCompleted(GraphUser user, Response response) {
	              if (user != null) {
	                TextView welcome = (TextView) findViewById(R.id.textViewMain1);
	                welcome.setText("Wellcome " + user.getName() + "!");
	            	profilePictureView.setProfileId(user.getId());
	            	try {
						JSONObject profile = Util.parseJson(facebook.request("me"));
						 fb_id = profile.getString("id");
					} catch (FacebookError e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (MalformedURLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	            	//fb_id = user.getId();
	            	
	            	Log.i("fb id", fb_id+"     "+user.getId());
	              }
	            }
	          }).executeAsync();
	        }
	      }
	    });	
	
	}

	public void openFbProf(String fbId, String UserName){
		try {
		    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://profile/"+fbId));
		    startActivity(intent);
		} catch(Exception e) {
		    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.facebook.com/"+UserName)));
		}
	}

}
