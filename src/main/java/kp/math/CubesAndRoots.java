package kp.math;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.LongStream;

import kp.utils.Printer;

/**
 * Computes the cubes and the roots.
 *
 */
public class CubesAndRoots {

	/**
	 * The constructor.
	 */
	private CubesAndRoots() {
		throw new IllegalStateException("Utility class");
	}

	/**
	 * Computes the cubes and the roots.
	 * 
	 */
	public static void compute() {

		final List<Long> numberList = LongStream.rangeClosed(0, 8).boxed().toList();
		Printer.printf("Numbers %s", numberList);
		final double exponent = 3;
		final List<Long> cubeList = computeCubes(numberList, exponent);
		computeRoots(cubeList, exponent);
		Printer.printHor();
	}

	/**
	 * Computes the cubes.
	 * 
	 * @param numberList the number list
	 * @param exponent   the exponent
	 * @return cubeList the cube list
	 */
	private static List<Long> computeCubes(List<Long> numberList, double exponent) {

		final List<Long> cubeList1 = numberList.stream()/*-*/
				.map(arg -> arg * arg * arg)/*-*/
				.toList();
		Printer.printf("Cubes   %s", cubeList1);
		final UnaryOperator<Double> powerOperator = base -> Math.pow(base, exponent);
		final List<Long> cubeList2 = cubeList1.stream()/*-*/
				.map(Double::valueOf)/*-*/
				.map(powerOperator)/*-*/
				.map(Double::longValue)/*-*/
				.toList();
		Printer.printf("Cubes   %s", cubeList2);
		return cubeList2;
	}

	/**
	 * Computes the roots.
	 * 
	 * @param cubeList the cube list
	 * @param exponent the exponent
	 */
	private static void computeRoots(List<Long> cubeList, double exponent) {

		final Function<Double, Long> rounding = arg -> BigDecimal.valueOf(arg).setScale(0, RoundingMode.HALF_UP)
				.longValue();
		final List<Long> cubeRootList1 = cubeList.stream()/*-*/
				.map(Double::valueOf)/*-*/
				.map(Math::cbrt)/*-*/
				.map(rounding)/*-*/
				.toList();
		Printer.printf("Roots   %s", cubeRootList1);
		/*-
		 *  Computed with 'Math.log' because computing with 'Math.pow' loses precision!
		 *  The n-th root of a number x is equal with the number x in the power of 1/n.
		 */
		final UnaryOperator<Double> rootOperator = base -> Math.pow(Math.E, Math.log(base) / exponent);
		final List<Long> cubeRootList2 = cubeRootList1.stream()/*-*/
				.map(Double::valueOf)/*-*/
				.map(rootOperator)/*-*/
				.map(rounding)/*-*/
				.toList();
		Printer.printf("Roots   %s", cubeRootList2);
	}
}