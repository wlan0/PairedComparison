package edu.cmu.pairedComparison2_0;

import org.apache.commons.math3.distribution.TDistribution;

/**
 * This class contains the calculations required for the paired comparison
 * estimation.
 * 
 * @author Shigeru Sasao
 */
public class Calculator {

	/**
	 * Singleton instance of this class
	 */
	private static Calculator singleton;

	/**
	 * Constructor Note, constructor should be protected for singleton, but
	 * needs to be public for the Matrix Table Model
	 */
	public Calculator() {

	}

	/**
	 * Return singleton instance of this object.
	 * 
	 * @return The singleton of Calculator.
	 */
	public static Calculator getInstance() {
		if (singleton == null) {
			singleton = new Calculator();
		}
		return singleton;
	}

	/**
	 * Calculate reciprocal values in the judgment matrix according to design.
	 * 
	 * @param designMatrix
	 *            The design of the judgment matrix.
	 * @param judgmentMatrix
	 *            The judgment matrix.
	 * @param artifactCount
	 *            Number of artifacts.
	 * @return Judgment matrix with calculated reciprocals.
	 */
	public double[][] calcReciprocals(boolean[][] designMatrix,
			double[][] judgmentMatrix, int artifactCount) {
		double[][] retMatrix = new double[judgmentMatrix.length][judgmentMatrix.length];
		for (int i = 0; i < artifactCount; i++) {
			for (int j = 0; j < artifactCount; j++) {
				if (designMatrix[i][j]) {
					retMatrix[i][j] = judgmentMatrix[i][j];
					retMatrix[j][i] = 1.0 / judgmentMatrix[i][j];
				}
			}
		}
		return retMatrix;
	}

	/**
	 * Fill incomplete judgment matrix.
	 * 
	 * @param judgmentMatrix
	 *            The judgment matrix to complete.
	 * @param artifactCount
	 *            Number of artifacts.
	 * @return Filled judgment matrix.
	 */
	public double[][] fillJudgmentMatrix(double[][] judgmentMatrix,
			int artifactCount) {

		for (int i = 0; i < artifactCount; i++) {
			for (int j = 0; j < artifactCount; j++) {
				if (i == j) {
					judgmentMatrix[i][j] = 1.0;

				// fill missing value
				} else {
					if (judgmentMatrix[i][j] != 0) {
						for (int k = 0; k < artifactCount; k++) {
							if (judgmentMatrix[j][k] != 0) {
								if (judgmentMatrix[i][k] == 0) {
									judgmentMatrix[i][k] = judgmentMatrix[i][j]
											* judgmentMatrix[j][k];
									judgmentMatrix[k][i] = 1.0 / judgmentMatrix[i][k];
								}
							}
						}
					}
				}
			}
		}

		return judgmentMatrix;
	}

	/**
	 * Calculate relative size ratios from judgment matrix.
	 * 
	 * @param judgmentMatrix
	 *            The judgment matrix to use.
	 * @param artifactCount
	 *            Number of artifacts.
	 * @return Calculated ratio
	 */
	public double[] calcRatio(double[][] judgmentMatrix, int artifactCount) {

		double relativeSize[] = new double[artifactCount];
		double sum = 0;

		// parse for values
		for (int i = 0; i < artifactCount; i++) {
			relativeSize[i] = 1.0;
			for (int j = 0; j < artifactCount; j++) {
				relativeSize[i] = relativeSize[i] * judgmentMatrix[i][j];
			}
			relativeSize[i] = Math.pow(relativeSize[i],
					(1.0 / (double) artifactCount));
			sum += relativeSize[i];
		}

		// parse again and divide by sum
		for (int i = 0; i < artifactCount; i++) {
			relativeSize[i] = relativeSize[i] / sum;
		}
		return relativeSize;
	}

	/**
	 * Calculate inconsistency index for the judgment matrix.
	 * 
	 * @param judgmentMatrix
	 *            The judgment matrix to use.
	 * @param artifactCount
	 *            Number of artifacts.
	 * @param replicationFactor
	 *            Replication factor for incomplete cyclic design.
	 * @return The inconsistency index.
	 */
	public double calcInconsistencyIndex(double[][] judgmentMatrix,
			int artifactCount, int replicationFactor) {

		double val[] = new double[artifactCount];
		double inconsistencyIndex = 0.0;

		// parse for values
		for (int i = 0; i < artifactCount; i++) {
			val[i] = 1.0;
			for (int j = 0; j < artifactCount; j++) {
				val[i] = val[i] * judgmentMatrix[i][j];
			}
			val[i] = Math.pow(val[i], (1.0 / (double) artifactCount));
		}

		// calculate inconsistency index
		for (int i = 0; i < artifactCount; i++) {
			for (int j = i + 1; j < artifactCount; j++) {
				inconsistencyIndex = inconsistencyIndex
						+ Math.pow(
								Math.log(judgmentMatrix[i][j])
										- Math.log(val[i] / val[j]), 2.0);
			}
		}
		inconsistencyIndex = Math.pow((2.0 * inconsistencyIndex)
				/ ((double) replicationFactor * (double) artifactCount - 2
						* (double) artifactCount + 2), 0.5);

		return inconsistencyIndex;
	}

	/**
	 * Calculate the absolute size of the artifacts.
	 * 
	 * @param referenceSize
	 *            The size of the reference artifact.
	 * @param referenceId
	 *            The ID of the reference artifact.
	 * @param relativeSize
	 *            List of all artifact's relative size.
	 * @param artifactCount
	 *            Number of artifacts.
	 * @return The absolute size of the artifacts.
	 */
	public double[] calcAbsoluteSize(double referenceSize, int referenceId,
			double[] relativeSize, int artifactCount) {
		double[] absoluteSize = new double[artifactCount];
		for (int i = 0; i < artifactCount; i++) {
			absoluteSize[i] = (relativeSize[i] / relativeSize[referenceId])
					* referenceSize;
		}
		return absoluteSize;
	}

	/**
	 * Calculate the standard deviation of the artifacts' absolute size.
	 * 
	 * @param absoluteSize
	 *            List of the absolute size of the artifacts.
	 * @param inconsistencyIndex
	 *            Inconsistency index of the judgement matrix.
	 * @param artifactCount
	 *            Number of artifacts.
	 * @return The list of standard deviations of the artifacts' absolute size.
	 */
	public double[] calcAbsoluteSizeStdDev(double[] absoluteSize,
			double inconsistencyIndex, int artifactCount) {
		double[] absoluteSizeStdDev = new double[artifactCount];
		for (int i = 0; i < artifactCount; i++) {
			absoluteSizeStdDev[i] = inconsistencyIndex
					/ Math.pow((double) artifactCount - 1, 0.5)
					* absoluteSize[i];
		}
		return absoluteSizeStdDev;
	}

	/**
	 * Calculate the sum of the standard deviations of the artifacts' absolute
	 * size.
	 * 
	 * @param absoluteSizeStdDev
	 *            List of the standard deviations of the artifacts' absolute
	 *            size.
	 * @param artifactCount
	 *            Number of artifacts.
	 * @return The sum of the standard deviations of the artifacts' absolute
	 *         size.
	 */
	public double calcSumAbsoluteSizeStdDev(double[] absoluteSizeStdDev,
			int artifactCount) {
		double sumAbsoluteSizeStdDev = 0.0;
		for (int i = 0; i < artifactCount; i++) {
			sumAbsoluteSizeStdDev = sumAbsoluteSizeStdDev
					+ Math.pow(absoluteSizeStdDev[i], 2.0);
		}
		sumAbsoluteSizeStdDev = Math.pow(sumAbsoluteSizeStdDev, 0.5);
		return sumAbsoluteSizeStdDev;
	}

	/**
	 * Generate the design according to parameters.
	 * 
	 * @param artifactCount
	 *            Number of artifacts.
	 * @param referenceValue
	 *            The size of the reference artifact.
	 * @return Truth table with the design.
	 */
	public boolean[][] generateDesign(int artifactCount, int replicationFactor) {
		boolean[][] designMatrix = new boolean[artifactCount][artifactCount];
		for (int i = 0; i < designMatrix.length; i++) {
			for (int j = 0; j < designMatrix.length; j++) {
				designMatrix[i][j] = false;
			}
		}

		int k;
		for (int i = 0; i < (int) Math.ceil((double) replicationFactor / 2.0); i++) {
			for (int j = 0; j < artifactCount; j++) {
				k = (i + j + 1) % artifactCount;
				if (!designMatrix[k][j]) {
					designMatrix[j][k] = true;
				}
			}
		}
		return designMatrix;
	}
	
	/**
	 * Calculates the degrees of freedom as a function of the replication factor
	 * 
	 */
	
	private int calcDegreesOfFreedom(int replicationFactor)
	{
		if(replicationFactor % 2 ==0)
			return replicationFactor - 1;
		return replicationFactor;
	}
	
	public double[] calcGeometricStdDev(int artifactCount, double[][] judgmentMatrix, int replicationFactor)
	{
		double geometricMeanMatrix[] = new double[artifactCount];
		boolean designMatrix[][] = generateDesign(artifactCount, replicationFactor);
		double wi,wj;
		for(int i=0;i<artifactCount;i++)
		{
			for(int j=0;j<artifactCount;j++)
			{
				if(designMatrix[i][j] && i!=j)
				{
					wi = geometricMean(i, artifactCount, judgmentMatrix);
					wj = geometricMean(j, artifactCount, judgmentMatrix);
					geometricMeanMatrix[i] += calculateGeometricStandardDev(judgmentMatrix[i][j], wi, wj);
				}
			}
			geometricMeanMatrix[i] = Math.pow(Math.E, Math.pow(geometricMeanMatrix[i]/calcDegreesOfFreedom(replicationFactor), 0.5));
		}
		return geometricMeanMatrix;
	}
	
	private double geometricMean(int row, int artifactCount, double[][] judgmentMatrix)
	{
		double geometricMean = 1.0;
		for(int j =0; j<artifactCount;j++)
		{
			geometricMean *= judgmentMatrix[row][j];
		}
		geometricMean = Math.pow(geometricMean, 1.0/artifactCount);
		return geometricMean;
	}
	
	private double calculateGeometricStandardDev(double aij, double wi, double wj)
	{
		return Math.pow(Math.log(aij * wj/wi),2);
	}
	
	public double[] calcLowerLimit(double[] absoluteSize, int artifactCount, double[][] judgmentMatrix, int replicationFactor, double confidence)
	{
		double[] lowerLimit = new double[artifactCount];
		double[] geometricMean = calcGeometricStdDev(artifactCount,judgmentMatrix, replicationFactor);
		TDistribution tDistribution = new TDistribution(calcDegreesOfFreedom(replicationFactor));
		for(int i=0;i<artifactCount;i++)
		{
			lowerLimit[i] = absoluteSize[i] / Math.pow(Math.E, tDistribution.inverseCumulativeProbability(confidence) * (Math.log(geometricMean[i]) / Math.pow(calcDegreesOfFreedom(replicationFactor),0.5)));
		}
		return lowerLimit;
	}
	
	public double[] calcUpperLimit(double[] absoluteSize, int artifactCount, double[][] judgmentMatrix, int replicationFactor, double confidence)
	{
		double[] upperLimit = new double[artifactCount];
		double[] geometricMean = calcGeometricStdDev(artifactCount,judgmentMatrix, replicationFactor);
		TDistribution tDistribution = new TDistribution(calcDegreesOfFreedom(replicationFactor),0.0);
		for(int i=0;i<artifactCount;i++)
		{
			upperLimit[i] = absoluteSize[i] * Math.pow(Math.E, tDistribution.inverseCumulativeProbability(confidence) * (Math.log(geometricMean[i]) / Math.pow(calcDegreesOfFreedom(replicationFactor),0.5)));
		}
		return upperLimit;
	}
	
}
