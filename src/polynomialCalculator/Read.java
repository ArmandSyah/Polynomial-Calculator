package polynomialCalculator;

import java.util.*;
import java.io.*;
import java.lang.*;

public class Read {

	private Scanner x;
	
	public void	openFile(){
		try{
			x = new Scanner(new File("HoldEquation.txt"));
		}
		catch(Exception e){
			System.out.println("Error");
		}
	}
	
	public void readFile(){
		while(x.hasNext()){
			String a = x.next();
			
			System.out.printf("%s",a);
		}
	}
	
	public void closeFile(){
		x.close();
	}
}
