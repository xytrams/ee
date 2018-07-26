package com.equalexperts.calc.interest;

import java.math.BigDecimal;
import java.util.function.Function;

public class InterestFunction implements Function<BigDecimal, BigDecimal> {

	private BigDecimal min;
	private BigDecimal max;
	private BigDecimal interest;
	
	public InterestFunction(BigDecimal min, BigDecimal max, BigDecimal interest) {
		this.min = min;
		this.max = max;
		this.interest = interest;
	}


	@Override
	public BigDecimal apply(BigDecimal amount) {
		BigDecimal result = (amount.compareTo(min) > 0 && amount.compareTo(max) <= 0) ? amount.multiply(interest) : amount;
		return result;
	}

	public InterestFunction compose(InterestFunction before) {
		return ;
	}
}
