package com.equalexperts.calc.interest;

import java.math.BigDecimal;
import java.util.function.Function;

public class InterestCalculatorOld {


	private BigDecimal ZERO = BigDecimal.ZERO;
	private BigDecimal THOUSAND = new BigDecimal("1000");
	private BigDecimal FIVE_THOUSANDS = new BigDecimal("5000");
	private BigDecimal MILLION = new BigDecimal("1000000");
	
	private BigDecimal PERCENT_1 = new BigDecimal("1.01");
	private BigDecimal PERCENT_2 = new BigDecimal("1.02");
	private BigDecimal PERCENT_3 = new BigDecimal("1.03");
	
	private Function<BigDecimal, BigDecimal> bands = interest(ZERO, THOUSAND, PERCENT_1)
														.compose(interest(THOUSAND, FIVE_THOUSANDS, PERCENT_2)
																	.compose(interest(FIVE_THOUSANDS, MILLION, PERCENT_3))); 
	
	public static void main (String...bands) {
		InterestCalculatorOld calc = new InterestCalculatorOld();
		System.out.println((calc.getInterest(BigDecimal.TEN)).toString());
	}

	public BigDecimal getInterest(BigDecimal amount) {
		return bands.apply(amount);
	}
	
	private Function<BigDecimal, BigDecimal> interest(BigDecimal min, BigDecimal max, BigDecimal interest) {
		return amount -> (amount.compareTo(min) > 0 && amount.compareTo(max) <= 0) ? amount.multiply(interest) : amount;
	}
}
