package polynomialCalculator;

import javax.swing.JFrame;

public class Polyfactor {
	
	private void polyFactor(){
		UserInterface D = new UserInterface();
		D.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		D.setSize(800, 300);
		D.setVisible(true);		
	}
	
	public static void main(String[] args){		
		Polyfactor polyfactor = new Polyfactor();
	    polyfactor.polyFactor();
	}
}
