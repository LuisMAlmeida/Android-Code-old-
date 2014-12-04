package com.luisalmeida.theblackbook;

import android.media.Image;

public class Hookup {
	
	String _name;
	String _place;
	String _obs;
	String _from;
	String _age;
	String _image;
	String _filename;
	boolean _checkBJ=false;
	boolean _checkHJ=false;
	boolean _checkAll=false;	
	
	
	/**
	 * Set's the Hookup's Name.
	 * <p>
	 * @param  name  the Hookup's name.
	 */
	public void setName(String name){
		_name=name;
	}
	
	/**
	 * Set's the Hookup's FileName.
	 * <p>
	 * @param  filename  the Hookup's Filename.
	 */
	public void setFileName(String filename){
		_filename=filename;
	}
	
	/**
	 * Set's the Hookup's CheckBox on BJ.
	 * <p>
	 * @param  bj  the Hookup's CheckBox on BJ.
	 */
	public void setBJ(boolean bj){
		_checkBJ=bj;
	}
	
	/**
	 * Set's the Hookup's CheckBox on HJ.
	 * <p>
	 * @param  hj  the Hookup's CheckBox on HJ.
	 */
	public void setHJ(boolean hj){
		_checkHJ=hj;
	}
	
	/**
	 * Set's the Hookup's CheckBox on All the Way.
	 * <p>
	 * @param  all  the Hookup's CheckBox on All the Way.
	 */
	public void setAll(boolean all){
		_checkAll=all;
	}
	
	/**
	 * Set's where the Hookup's from.
	 * <p>
	 * @param  from  Hookup's from.
	 */
	public void setFrom(String from){
		_from=from;
	}
	
	/**
	 * Set's the Place where you met the Hookup.
	 * <p>
	 * @param  place  where you met the Hookup.
	 */
	public void setPlace(String place){
		_place=place;
	}
	
	/**
	 * Set's an observation on whatever you want.
	 * <p>
	 * @param  obser Observation about the Hookup.
	 */
	public void setObser(String obser){
		_obs=obser;
	}
	
	/**
	 * Set's the Hookup's Age.
	 * <p>
	 * @param  age Hookup's age.
	 */
	public void setAge(String age){
		_age=age;
	}
	
	/**
	 * Set's the Hookup's Image.
	 * <p>
	 * @param  selectedImagePath  the Hookup's image.
	 * @see Image
	 */
	public void setImage(String image){
		_image=image;
	}

	/**
	 * Get's the Hookup's Name.
	 * <p>
	 * @return  name  the Hookup's name.
	 */
	public String getName(){
		return _name;
	}
	
	/**
	 * Get's the Hookup's FileName.
	 * <p>
	 * @return  filename  the Hookup's Filename.
	 */
	public String getFileName(){
		return _filename;
	}
	
	/**
	 * Get's where the Hookup's from.
	 * <p>
	 * @return  from  Hookup's from.
	 */
	public String getFrom(){
		return _from;
	}
	
	/**
	 * Get's the Place where you met the Hookup.
	 * <p>
	 * @return  _place  where you met the Hookup.
	 */
	public String getPlace(){
		return _place;
	}
	
	/**
	 * Get's the Hookup's Age.
	 * <p>
	 * @return  _age Hookup's age.
	 */
	public String getAge(){
		return _age;
	}
	
	/**
	 * Get's an observation on whatever you want.
	 * <p>
	 * @return _ober Observation about the Hookup.
	 */
	public String getObser(){
		return _obs;
	}
	
	/**
	 * Get's the Hookup's Image.
	 * <p>
	 * @param  image  the Hookup's image.
	 * @see Image
	 */
	public String getImage(){
		return _image;
	}
	
	/**
	 * Get's the Hookup's CheckBox on All the Way.
	 * <p>
	 * @param  _checkall  the Hookup's CheckBox on All the Way.
	 */
	public boolean getAll(){
		return _checkAll;
	}
	
	/**
	 * Get's the Hookup's CheckBox on HJ.
	 * <p>
	 * @param  _checkHJ  the Hookup's CheckBox on HJ.
	 */
	public boolean getHJ(){
		return _checkHJ;
	}
	
	/**
	 * Get's the Hookup's CheckBox on BJ.
	 * <p>
	 * @param  _checkbj  the Hookup's CheckBox on BJ.
	 */
	public boolean getBJ(){
		return _checkBJ;
	}
	
	public String toString(){
		return "NAME | "+getName()+"\n"+
				"AGE | "+getAge()+"\n"+
				"PLACE | "+getPlace()+"\n"+
				"FROM | "+getFrom()+"\n"+
				"BJ | "+getBJ() +"\n" +
				"HJ | "+getHJ() +"\n" +
				"ALL | "+getAll() +"\n" +
				"OBS | "+getObser()+"\n"+
				"IMAGE | "+getImage()+"\n" +
				"FILE | "+getFileName()+"\n";
		}
}
