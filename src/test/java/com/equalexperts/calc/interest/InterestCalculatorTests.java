package com.equalexperts.calc.interest;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class InterestCalculatorTests {

	static final Logger LOG = Logger.getLogger(InterestCalculatorTests.class.getName());

	private InterestCalculator calc = new InterestCalculator();
	
	private InterestFunction band1 = new InterestFunction(BigDecimal.ZERO, new BigDecimal(1000), new BigDecimal("1.01"));
	private InterestFunction band2 = new InterestFunction(new BigDecimal(1000), new BigDecimal(5000), new BigDecimal("1.02"));
	private InterestFunction band3 = new InterestFunction(new BigDecimal(5000), new BigDecimal(1000000), new BigDecimal("1.03"));
	
	private List<InterestFunction> bands = Arrays.asList(band1, band2, band3);

	@BeforeEach
	void beforeEachTest(TestInfo testInfo) {
		LOG.info(() -> String.format("About to execute [%s]", testInfo.getDisplayName()));
	}

	@AfterEach
	void afterEachTest(TestInfo testInfo) {
		LOG.info(() -> String.format("Finished executing [%s]", testInfo.getDisplayName()));
	}


	@Test
	public void test_1_percent() {
		BigDecimal expected = new BigDecimal(101.00);
		BigDecimal actual = calc.getInterest(new BigDecimal(100.00), bands);
		assertTrue(expected.equals(actual));
	}
}
