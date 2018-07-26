package com.equalexperts.calc.interest;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class InterestCalculator {

	@SafeVarargs
	public static void main(InterestFunction... bands) {
		InterestCalculator calc = new InterestCalculator();
		List<InterestFunction> list = Arrays.stream(bands).collect(Collectors.toList());
		System.out.println((calc.getInterest(BigDecimal.TEN, list)).toString());
	}

	public BigDecimal getInterest(BigDecimal amount, List<InterestFunction> bands) {
		Optional<InterestFunction> allBands = bands.stream().reduce((f1, f2) -> (InterestFunction)f1.compose(f2));

		return allBands.isPresent() ? allBands.get().apply(amount) : amount;
	}

}
