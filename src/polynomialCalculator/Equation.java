package polynomialCalculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.Arrays;
import org.ddogleg.solver.Polynomial;
import org.ddogleg.solver.PolynomialOps;
import org.ddogleg.solver.PolynomialRoots;
import org.ddogleg.solver.RootFinderType;
import org.ejml.data.Complex64F;

public class Equation {
	
	  private final int maxDegree;
	  private StringBuilder polynomialEquation = new StringBuilder();  
	  private StringBuilder factoredPolynomialEquation = new StringBuilder(); 
	  
	  public Equation (int degree){
	    this.maxDegree = degree;
	  }
	  
	  public String createPolynomialEquation(List <BigDecimal> listOfCoefficients, int d){ 
		polynomialEquation.setLength(0);
	    BigDecimal[] coefficientsOfPolynomial = (BigDecimal[]) listOfCoefficients.toArray();
	    
	    for(int x = d;x >= 0; x--){
		    if (x == d){
		    	polynomialEquation.append("(").append(coefficientsOfPolynomial[x]).append(")");
		        polynomialEquation.append("*").append("X^").append(x);
		        polynomialEquation.append(" + ");
		    }
		    else if (x == 0){
		    	polynomialEquation.append("(").append(coefficientsOfPolynomial[x]).append(")");
		        polynomialEquation.append("*").append("X^").append(x);
		    }
		    else{
		    	polynomialEquation.append("(").append(coefficientsOfPolynomial[x]).append(")");
		    	polynomialEquation.append("*").append("X^").append(x);
		    	polynomialEquation.append(" + ");
		    }
	    }
	    return polynomialEquation.toString();
	  }
	  
	  public BigDecimal evaluateEquation(List<BigDecimal> C, long x){
		  Evaluation e = new Evaluation(C, x);
		  BigDecimal total = e.Evaluate();
		  return total;  
	  }
	  
	  public String roots (List<BigDecimal> listOfCoefficients){
	        BigDecimal[] coefficients = (BigDecimal[]) listOfCoefficients.toArray();
	        double[] coeffiecientsToDouble = new double [coefficients.length];
	        
	        for (int x = 0; x <= coefficients.length - 1; x++){
	            BigDecimal d;
	            d = coefficients[x];
	            double value = d.doubleValue();
	            coeffiecientsToDouble[x] = value;
	        }
	       
	        PolynomialRoots finder = PolynomialOps.createRootFinder(6, RootFinderType.EVD);
	        Polynomial poly = Polynomial.wrap(coeffiecientsToDouble);
	         
	        		
			if( !finder.process(poly)) throw new RuntimeException("Failed to find solution!");

			List<Complex64F> roots = finder.getRoots();

			for( Complex64F c : roots ) {
				if( !c.isReal() ) {
					factoredPolynomialEquation.append("(").append("N/a").append(")");
					continue;
				}
				double a = (c.real) * -1; 
				if (a > 0){
					factoredPolynomialEquation.append("( ").append("X + ").append(a).append(" )");
				}
				else{
					a *= -1;
					factoredPolynomialEquation.append("( ").append("X - ").append(a).append(" )");
				}

			}

			return factoredPolynomialEquation.toString();
	  }
	  
	  public String deriveEquation (List <BigDecimal> listOfCoefficients, int d){
	     
	      BigDecimal[] tempCoefficeintHolder = (BigDecimal[]) listOfCoefficients.toArray();
	      BigDecimal[] newCoefficients = new BigDecimal[tempCoefficeintHolder.length];
	      
	      for (int x = 0; x <= maxDegree-1; x++){
	          newCoefficients[x] = tempCoefficeintHolder[x+1].multiply(BigDecimal.valueOf(x+1));
	      
	      }
	      List <BigDecimal> coeffList = Arrays.asList(newCoefficients);
	      String derivedEquation = createPolynomialEquation(coeffList, d - 1);
	      return derivedEquation;
	  }

}
