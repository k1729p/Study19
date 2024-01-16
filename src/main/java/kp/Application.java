package kp;

import kp.math.BigDecimalsFromStringRepresentation;
import kp.math.ClampNumbersAndCompareBigDecimals;
import kp.math.CubesAndRoots;
import kp.math.EvenAndOddNumbers;
import kp.math.FibonacciNumbers;
import kp.math.GoldenRatio;
import kp.math.LeastCommonMultiple;
import kp.math.Logic;
import kp.math.NumberInCompactForm;
import kp.math.VeryBigIntegerRaised;
import kp.math.VeryBigIntegerRandomlyGenerated;
import kp.math.means.Means;
import kp.math.means.SummaryStatistics;
import kp.math.means.bayes.RandomizedTrialsLauncher;
import kp.math.means.bayes.StatisticalMeasures;

/**
 * The main application for the mathematical research.
 *
 */
public class Application {
	private static final boolean MEANS = true;
	private static final boolean BAYES = true;

	/**
	 * The constructor.
	 */
	public Application() {
		super();
	}

	/**
	 * The main method.
	 * 
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		NumberInCompactForm.format();
		Logic.mergeMap();
		EvenAndOddNumbers.segregate();
		GoldenRatio.compute();
		FibonacciNumbers.compute();
		LeastCommonMultiple.compute();
		CubesAndRoots.compute();
		BigDecimalsFromStringRepresentation.multiply();
		VeryBigIntegerRaised.compute();
		VeryBigIntegerRandomlyGenerated.compute();
		ClampNumbersAndCompareBigDecimals.clampNumbers();
		ClampNumbersAndCompareBigDecimals.compareBigDecimals();
		if (MEANS) {
			Means.computeMeansWithTeeing();
			SummaryStatistics.show();
		}
		if (BAYES) {
			RandomizedTrialsLauncher.launchSeriesOfTrials(0);
			StatisticalMeasures.measureSamples();
		}
	}
}