package kp.math;

import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import kp.utils.Printer;

/**
 * Segregates the even and the odd numbers.
 * 
 */
public class EvenAndOddNumbers {

	/**
	 * The constructor.
	 */
	private EvenAndOddNumbers() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Segregates the even and the odd numbers.
	 * 
	 */
	public static void segregate() {

		final Predicate<Integer> predicate = arg -> arg % 2 == 0;
		final Map<Boolean, List<Integer>> map = IntStream.rangeClosed(1, 5).boxed()
				.collect(Collectors.partitioningBy(predicate));
		Printer.printf("Evens%s, odds%s", map.get(Boolean.TRUE), map.get(Boolean.FALSE));
		Printer.printHor();
	}
}
