package com.equalexperts.fb.fizzbuzz;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class FizzBuzz {

	private final String FIZZ = "fizz";
	private final String BUZZ = "buzz";
	private final String FIZZBUZZ = "fizzbuzz";
	private final String LUCKY = "lucky";
	private final String INTEGER = "integer";

	private IntFunction<String> fizzbuzzlucky = ifLuckyNumber(3, LUCKY, ifModulo(15, FIZZBUZZ, ifModulo(5, BUZZ, ifModulo(3, FIZZ, Integer::toString))));

	public static void main(String... args) {
		FizzBuzz fizzbuzz = new FizzBuzz();
		System.out.println(fizzbuzz.convertAndReport(-20, 20));
	}

	public String convertAndReport(int start, int end) {
		String out = IntStream.rangeClosed(start, end)
							  .boxed()
							  .map(i -> fizzbuzzlucky.apply(i))
							  .collect(joining(" "))
							  .trim();
		
		return (out + " " +report(out)).trim();
	}

	private IntFunction<String> ifModulo(int mod, String replacer, IntFunction<String> function) {
		return (int i) -> (i % mod == 0) ? replacer : function.apply(i);
	}

	private IntFunction<String> ifLuckyNumber(int lucky, String replacer, IntFunction<String> function) {
        return (int i) -> isLucky(Math.abs(i), lucky) ? replacer : function.apply(i);
    }

	private boolean isLucky(int number, int lucky) {
		while (number != 0) {
			if (number % 10 == lucky) {
				return true;
			} else {
				number = number / 10;
			}
		}
		return false;
	}

	private Map<String, Integer> blankReport() {
		Map<String, Integer> report = new LinkedHashMap<>();
		report.put(FIZZ, 0);
		report.put(BUZZ, 0);
		report.put(FIZZBUZZ, 0);
		report.put(LUCKY, 0);
		report.put(INTEGER, 0);

		return report;
	}

	private String report(String s) {

		String[] all = !s.isEmpty() ? s.split(" ") : new String[0];
		
		List<String> words = Arrays.asList(all).stream()
								   			  .filter(i -> !isNumber(i))
								   			  .collect(toList());

		Map<String, Integer> currentReport = words.stream()
												 .collect(groupingBy(e -> e, summingInt(x -> 1)));
		
		currentReport.put(INTEGER, all.length - words.size());

		Map<String, Integer> fullReport = new LinkedHashMap<>(blankReport());
		currentReport.forEach((key, value) -> fullReport.merge(key, value, (v1, v2) -> v1 + v2));

		String result = fullReport.entrySet().stream()
											.map(entry -> entry.getKey() + ": " + entry.getValue())
											.collect(joining(" "))
											.trim();

		return result;
	}

	private boolean isNumber(String s) {
		return s.matches("^-?\\d+$");
	}
}
