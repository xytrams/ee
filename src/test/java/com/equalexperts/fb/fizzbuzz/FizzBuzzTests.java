package com.equalexperts.fb.fizzbuzz;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.logging.Logger;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
public class FizzBuzzTests {

	static final Logger LOG = Logger.getLogger(FizzBuzzTests.class.getName());

	private FizzBuzz fizzbuzz = new FizzBuzz();

	@BeforeEach
	void beforeEachTest(TestInfo testInfo) {
		LOG.info(() -> String.format("About to execute [%s]", testInfo.getDisplayName()));
	}

	@AfterEach
	void afterEachTest(TestInfo testInfo) {
		LOG.info(() -> String.format("Finished executing [%s]", testInfo.getDisplayName()));
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 4, 5, 0, -1, -4, -10 })
	@DisplayName("Should return empty string if start and end are the same")
	public void handle_empty_range(int input) {
		int start = input, end = input - 1;
		String report = "fizz: 0 buzz: 0 fizzbuzz: 0 lucky: 0 integer: 0";

		String result = fizzbuzz.convertAndReport(start, end);
		assertThat(result, is("" + report));
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 4, -1, -4, -11, 91, 12001, -842 })
	@DisplayName("Should return just a number, no special case")
	public void handle_nonSpecial_number(int input) {
		int start = input, end = input;
		String report = " fizz: 0 buzz: 0 fizzbuzz: 0 lucky: 0 integer: 1";

		String result = fizzbuzz.convertAndReport(start, end);
		assertTrue(result.matches("-?[1-9]\\d*" + report));
	}

	@ParameterizedTest
	@ValueSource(ints = {6, 9, 12, -6, -9, -12, -99, 111, -999, 12012 })
	@DisplayName("Should return 'fizz' for multiples of '3'")
	public void handle_3_case(int input) {
		int start = input, end = input;
		String report = " fizz: 1 buzz: 0 fizzbuzz: 0 lucky: 0 integer: 0";

		String result = fizzbuzz.convertAndReport(start, end);
		assertThat(result, is("fizz" + report));
	}

	@ParameterizedTest
	@ValueSource(ints = { 5, 10, 20, 55, -5, -10, -55, -95, 5000 })
	@DisplayName("Should return 'buzz' for multiples of '5'")
	public void handle_5_case(int input) {
		int start = input, end = input;
		String report = " fizz: 0 buzz: 1 fizzbuzz: 0 lucky: 0 integer: 0";

		String result = fizzbuzz.convertAndReport(start, end);
		assertThat(result, is("buzz" + report));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 15, 45, 60, -15, -90, -12000, 525 })
	@DisplayName("Should return 'fizzbuzz' for multiples of '15'")
	public void handle_15_case(int input) {
		int start = input, end = input;
		String report = " fizz: 0 buzz: 0 fizzbuzz: 1 lucky: 0 integer: 0";

		String result = fizzbuzz.convertAndReport(start, end);
		assertThat(result, is("fizzbuzz" + report));
	}

	@Test
	@DisplayName("Should return 'fizzbuzz' for '0'")
	public void handle_0_case() {
		int start = 0, end = 0;
		String report = " fizz: 0 buzz: 0 fizzbuzz: 1 lucky: 0 integer: 0";

		String result = fizzbuzz.convertAndReport(start, end);
		assertThat(result, is("fizzbuzz" + report));
	}

	@Test
	@DisplayName("Should return '1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz' for 1 to 20 range")
	public void handle_positive_range() {
		int start = 1, end = 20;
		String report = " fizz: 4 buzz: 3 fizzbuzz: 1 lucky: 2 integer: 10";

		String result = fizzbuzz.convertAndReport(start, end);
		assertThat(result, is("1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz" + report));
	}

	@Test
	@DisplayName("Should return 'buzz -19 fizz -17 -16 fizzbuzz -14 lucky fizz -11 buzz fizz -8 -7 fizz buzz -4 lucky -2 -1' for -20 to -1 range")
	public void handle_negative_range() {
		int start = -20, end = -1;
		String report = " fizz: 4 buzz: 3 fizzbuzz: 1 lucky: 2 integer: 10";

		String result = fizzbuzz.convertAndReport(start, end);
		assertThat(result, is("buzz -19 fizz -17 -16 fizzbuzz -14 lucky fizz -11 buzz fizz -8 -7 fizz buzz -4 lucky -2 -1" + report));
	}

	@Test
	@DisplayName("Should return 'fizzbuzz -14 lucky fizz -11 buzz fizz -8 -7 fizz buzz -4 lucky -2 -1 fizzbuzz 1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz' for -15 to 15 range")
	public void handle_mixed_range() {
		int start = -15, end = 15;
		String report = " fizz: 6 buzz: 4 fizzbuzz: 3 lucky: 4 integer: 14";

		String result = fizzbuzz.convertAndReport(start, end);
		assertThat(result, is("fizzbuzz -14 lucky fizz -11 buzz fizz -8 -7 fizz buzz -4 lucky -2 -1 fizzbuzz 1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz" + report));
	}

}
