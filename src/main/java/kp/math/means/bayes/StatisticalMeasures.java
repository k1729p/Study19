package kp.math.means.bayes;

import java.math.BigDecimal;
import java.math.MathContext;

import kp.utils.Printer;

/**
 * The presentation of statistical measures.
 *
 */
public class StatisticalMeasures {

	/**
	 * The constructor.
	 */
	private StatisticalMeasures() {
		throw new IllegalStateException("Utility class");
	}

	private static final MathContext MC = MathContext.DECIMAL64;

	/**
	 * Measures the samples for Bayes Formula.
	 * 
	 */
	public static void measureSamples() {

		for (int index = 0; index < Samples.LEVELS.length; index++) {
			showRatesAndComputeBayesFormula(index, Samples.LEVELS[index]);
		}
		Printer.printHor();
	}

	/**
	 * Shows rates and computes Bayes Formula.
	 * 
	 * @param index     the index
	 * @param levelsArr the array of levels
	 */
	private static void showRatesAndComputeBayesFormula(int index, int[] levelsArr) {

		final int nGen = levelsArr[0];
		final int pGen = levelsArr[1];
		final int fn = levelsArr[2];
		final int fp = levelsArr[3];

		final int tn = nGen - fp;
		final int tp = pGen - fn;
		final int nRec = tn + fn;
		final int pRec = tp + fp;

		// Without that cast it caused bug in 'SonarQube' with message:
		// "Cast one of the operands of this addition operation to a 'long'."
		final long together = (long) nGen + pGen;
		final BigDecimal togetherBD = BigDecimal.valueOf(together);
		final BigDecimal tnBD = BigDecimal.valueOf(tn);
		final BigDecimal tpBD = BigDecimal.valueOf(tp);
		final BigDecimal nGenBD = BigDecimal.valueOf(nGen);
		final BigDecimal pGenBD = BigDecimal.valueOf(pGen);
		final BigDecimal nRecBD = BigDecimal.valueOf(nRec);
		final BigDecimal pRecBD = BigDecimal.valueOf(pRec);

		// Prior Probability (Base Rate).
		// probability of genuine
		final BigDecimal pGenProb = pGenBD.divide(togetherBD, MC);
		final BigDecimal nGenProb = nGenBD.divide(togetherBD, MC);

		// Posterior Probability.
		// probability of received
		final BigDecimal pRecProb = pRecBD.divide(togetherBD, MC);
		final BigDecimal nRecProb = nRecBD.divide(togetherBD, MC);

		// Specificity - True Negative Rate.
		// TNR = TN / Ngen = TN / ( TN + FP )
		final BigDecimal tnr = tnBD.divide(nGenBD, MC);

		// Sensitivity - True Positive Rate.
		// TPR = TP / Pgen = TP / ( TP + FN )
		final BigDecimal tpr = tpBD.divide(pGenBD, MC);

		// Negative predictive value.
		// NPV = TN / Nrec = TN / ( TN + FN )
		final BigDecimal npv = tnBD.divide(nRecBD, MC);

		// Precision - positive predictive value.
		// PPV = TP / Prec = TP / ( TP + FP )
		final BigDecimal ppv = tpBD.divide(pRecBD, MC);

		Printer.printf("▼▼▼▼ index[%2d] ▼▼▼▼", index);
		Printer.printf("True Neg.[%2d], False Neg.[%2d], False Pos.[%2d], True Pos.[%2d] → ", tn, fn, fp, tp);
		Printer.printf("Neg.Genuine[%2d], Pos.Genuine[%2d], Neg.Received[%2d], Pos.Received[%2d]", nGen, pGen, nRec,
				pRec);
		Printer.printf("  «GENUINE» Negative Probability[%6.2f]%% —        «GENUINE» Positive Probability[%6.2f]%%",
				100 * nGenProb.doubleValue(), 100 * pGenProb.doubleValue());
		Printer.printf(" «received» Negative Probability[%6.2f]%% —       «received» Positive Probability[%6.2f]%%",
				100 * nRecProb.doubleValue(), 100 * pRecProb.doubleValue());
		Printer.printf("«Specificity» True Negative Rate[%6.2f]%% —      «Sensitivity» True Positive Rate[%6.2f]%%",
				100 * tnr.doubleValue(), 100 * tpr.doubleValue());
		Printer.printf("       Negative Predictive Value[%6.2f]%% — «Precision» Positive Predictive Value[%6.2f]%%",
				100 * npv.doubleValue(), 100 * ppv.doubleValue());
		Printer.print("▲▲▲▲ ▲▲▲▲ ▲▲▲▲ ▲▲▲▲");
		/*
		 * Compute Bayes Formula
		 */
		final BigDecimal nBayes = tnr.multiply(nGenProb, MC).divide(nRecProb, MC);
		final BigDecimal pBayes = tpr.multiply(pGenProb, MC).divide(pRecProb, MC);
		boolean nFlag = npv.compareTo(nBayes) == 0;
		boolean pFlag = ppv.compareTo(pBayes) == 0;
		if (!nFlag || !pFlag) {
			// impossible
			Printer.printf("Computed from Bayes Formula were NOT equal: negative[%b], positive[%b]", nFlag, pFlag);
		}
	}

}
