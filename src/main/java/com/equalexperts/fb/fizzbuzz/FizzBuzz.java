package com.equalexperts.fb.fizzbuzz;

import static java.util.stream.Collectors.joining;

import java.util.function.IntFunction;
import java.util.stream.IntStream;

public class FizzBuzz {

	private IntFunction<String> fizzbuzz = ifModulo(15, "fizzbuzz", ifModulo(5, "buzz", ifModulo(3, "fizz", Integer::toString)));

	public static void main(String... args) {
		FizzBuzz fizzbuzz = new FizzBuzz();
		System.out.println(fizzbuzz.convert(1, 21));
	}

	public String convert(int start, int end) {
		return IntStream.rangeClosed(start, end).boxed().map(i -> fizzbuzz.apply(i)).collect(joining(" ")).trim();
	}

	private IntFunction<String> ifModulo(int mod, String replacer, IntFunction<String> function) {
		return (int i) -> (i % mod == 0) ? replacer : function.apply(i);
	}

}
