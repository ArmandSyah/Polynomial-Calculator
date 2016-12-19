package polynomialCalculator;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Save {
	
	private Formatter x;
	
	public void openFile(){
		try{
			x = new Formatter("HoldEquation.txt");
		}
		catch(Exception e){
			System.out.println("Error");
		}
		
	
	}
	
	public void addRecords(String y){
		x.format("%s", y);
	}
	
	public void closeFile(){
		x.close();
	}
}
