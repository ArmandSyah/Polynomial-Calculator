package polynomialCalculator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Evaluation {
	 	List<BigDecimal> listOfCoefficients = new ArrayList<BigDecimal>();
		private final long evaluatingPoint;
		
		public Evaluation(List<BigDecimal> listOfCoefficients, long evaluatingPoint) {
			this.listOfCoefficients = listOfCoefficients;
			this.evaluatingPoint = evaluatingPoint;
		}

		public BigDecimal Evaluate(){
			BigDecimal output = BigDecimal.ZERO;
			for (int i = 0; i < listOfCoefficients.size();i++)
			{
				 output = output.add(listOfCoefficients.get(i).multiply(BigDecimal.valueOf(evaluatingPoint).pow(i)));
			}
			return output;
			
		}
}
