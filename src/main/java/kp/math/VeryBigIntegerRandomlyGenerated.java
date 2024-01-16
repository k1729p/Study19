package kp.math;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.time.Instant;
import java.util.concurrent.atomic.LongAdder;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.IntStream;

import kp.utils.Printer;
import kp.utils.Utils;

/**
 * Randomly generate the big integer.
 *
 */
public class VeryBigIntegerRandomlyGenerated {

	private static final int WORKERS = 5;

	/**
	 * The constructor.
	 */
	private VeryBigIntegerRandomlyGenerated() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Generate the big integer.
	 * 
	 */
	public static void compute() {

		Printer.print("Randomly generated BigInteger:");
		final LongAdder counter = new LongAdder();
		final Instant startTotal = Instant.now();
		final IntFunction<Runnable> mapper = arg -> () -> {
			final Instant start = Instant.now();
			final SecureRandom secureRandom = new SecureRandom();
			final BigInteger veryBigInteger = new BigInteger(Integer.MAX_VALUE, secureRandom);
			final Instant stop = Instant.now();
			Printer.printf("(%1d) bitLength[%2$,d], bitCount[%3$,d], %4$s", arg, veryBigInteger.bitLength(),
					veryBigInteger.bitCount(), Utils.formatElapsed(start, stop));
			counter.increment();
		};
		final Consumer<Runnable> starter = runnable -> new Thread(runnable).start();
		IntStream.rangeClosed(1, WORKERS).mapToObj(mapper).forEach(starter);
		do {
			Utils.sleepMillis(250);
		} while (counter.sum() < WORKERS);
		Printer.printf("Total %s", Utils.formatElapsed(startTotal, Instant.now()));
		Printer.printHor();
	}
}
