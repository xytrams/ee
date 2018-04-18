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
	@ValueSource(ints = { 1, 2, 3, 4, 5, 0, -1, -4, -10 })
	@DisplayName("Should return empty string if start and end are the same")
	public void handle_empty_range(int input) {
		int start = input, end = input - 1;

		String result = fizzbuzz.convert(start, end);
		assertThat(result, is(""));
	}

	@ParameterizedTest
	@ValueSource(ints = { 1, 2, 4, -1, -4, -11, 91, 13001, -832 })
	@DisplayName("Should return just a number, no special case")
	public void handle_nonSpecial_number(int input) {
		int start = input, end = input;

		String result = fizzbuzz.convert(start, end);
		assertTrue(result.matches("-?[1-9]\\d*"));
	}

	@ParameterizedTest
	@ValueSource(ints = { 3, 6, 9, 12, -3, -6, -9, -12, -99, 111, -999, 12012 })
	@DisplayName("Should return 'fizz' for multiples of '3'")
	public void handle_3_case(int input) {
		int start = input, end = input;

		String result = fizzbuzz.convert(start, end);
		assertThat(result, is("fizz"));
	}

	@ParameterizedTest
	@ValueSource(ints = { 5, 10, 20, 55, -5, -10, -35, -95, 5000 })
	@DisplayName("Should return 'buzz' for multiples of '5'")
	public void handle_5_case(int input) {
		int start = input, end = input;

		String result = fizzbuzz.convert(start, end);
		assertThat(result, is("buzz"));
	}

	@ParameterizedTest
	@ValueSource(ints = { 0, 15, 30, 60, -15, -90, -12000, 525 })
	@DisplayName("Should return 'fizzbuzz' for multiples of '15'")
	public void handle_15_case(int input) {
		int start = input, end = input;

		String result = fizzbuzz.convert(start, end);
		assertThat(result, is("fizzbuzz"));
	}

	@Test
	@DisplayName("Should return '1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz' for 1 to 20 range")
	public void handle_positive_range() {
		int start = 1, end = 20;

		String result = fizzbuzz.convert(start, end);
		assertThat(result, is("1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz"));
	}

	@Test
	@DisplayName("Should return 'buzz -19 fizz -17 -16 fizzbuzz -14 -13 fizz -11 buzz fizz -8 -7 fizz buzz -4 fizz -2 -1' for -20 to -1 range")
	public void handle_negative_range() {
		int start = -20, end = -1;

		String result = fizzbuzz.convert(start, end);
		assertThat(result, is("buzz -19 fizz -17 -16 fizzbuzz -14 -13 fizz -11 buzz fizz -8 -7 fizz buzz -4 fizz -2 -1"));
	}

	@Test
	@DisplayName("Should return 'fizzbuzz -14 -13 fizz -11 buzz fizz -8 -7 fizz buzz -4 fizz -2 -1 fizzbuzz 1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz' for -15 to 15 range")
	public void handle_mixed_range() {
		int start = -15, end = 15;

		String result = fizzbuzz.convert(start, end);
		assertThat(result, is("fizzbuzz -14 -13 fizz -11 buzz fizz -8 -7 fizz buzz -4 fizz -2 -1 fizzbuzz 1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz"));
	}
}
