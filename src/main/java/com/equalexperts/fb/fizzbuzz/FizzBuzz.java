package com.equalexperts.fb.fizzbuzz;

import static java.util.stream.Collectors.joining;

import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class FizzBuzz {

	private IntFunction<String> fizzbuzzlucky = ifLuckyNumber(3, "lucky", ifModulo(15, "fizzbuzz", ifModulo(5, "buzz", ifModulo(3, "fizz", Integer::toString))));

	public static void main(String... args) {
		FizzBuzz fizzbuzz = new FizzBuzz();
		System.out.println(fizzbuzz.convert(-20, 20));
	}

	public String convert(int start, int end) {
		return IntStream.rangeClosed(start, end).boxed().map(i -> fizzbuzzlucky.apply(i)).collect(joining(" ")).trim();
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
}
