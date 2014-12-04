package com.luisalmeida.geradorchaveseuromilhoes;

import java.util.Collections;
import java.util.Enumeration;
import java.util.Random;
import java.util.Vector;

public class Gerador {
	
		public static int randInt(int min, int max) {

		    // Usually this can be a field rather than a method variable
		    Random rand = new Random();

		    // nextInt is normally exclusive of the top value,
		    // so add 1 to make it inclusive
		    int randomNum = rand.nextInt((max - min) + 1) + min;

		    return randomNum;
		}
		
		public Vector geraNum(){
			Vector _numeros = new Vector();
			for(int j=1;j<51;j++)
				_numeros.addElement(j);
			Vector num =new Vector();
			for (int i=0;i<5;i++){
				int numero = randInt(0,_numeros.size()-1);
				num.addElement(_numeros.get(numero));	
				_numeros.remove(numero);
			}	
			Collections.sort(num);
			return num;
		}
			
		public Vector geraStar(){
			Vector estrelas =new Vector();
			for(int j=1;j<12;j++)
				estrelas.addElement(j);
			Vector star = new Vector();
			for (int i=0;i<2;i++){
				int numero = randInt(0,estrelas.size()-1);
				star.addElement(estrelas.get(numero));	
				estrelas.remove(numero);
			}			
			Collections.sort(star);
			return star;
		}
		
		public String toString(){
			StringBuilder storage =new StringBuilder();
			Enumeration vEnum = geraNum().elements();
			Enumeration vEstar = geraStar().elements();
			storage.append("\n");
		    storage.append("Números: ");
		    while(vEnum.hasMoreElements())
		       storage.append(vEnum.nextElement() + " ");
		    storage.append("\n");
		    storage.append("Estrelas: ");
		    while(vEstar.hasMoreElements())
		    	storage.append(vEstar.nextElement() + " ");
		    storage.append("\n");
		    return storage.toString();
		}
}

