/**
 * 
 */
package com.gep;

/**
 * @author shenzhan
 * 
 */
public class MultClassify extends FitnessFunction {

	/**
	 * 
	 */

	/**
	 * µ±ا°µؤ·ضہà±à؛إ
	 */
	public int nCurrentClass;

	public MultClassify() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gep.FitnessFunction#GetFitness(com.gep.Population, double[][],
	 * double[])
	 */
	@Override
	public void GetFitness(Population Pop, double[][] Data, double[] Fitness) {
		int nRow = Data.length;
		int nCol = Data[0].length;

		int i, j, k;

		for (i = 0; i < Pop.Size; ++i) {
			int tp = 0, fp = 0, tn = 0, fn = 0;
			for (j = 0; j < nRow; ++j) {
				double dValue = super.Exp.GetValue(Pop.Get(i), Data[j]);
				Pop.Get(i).Value = dValue;
				
				if (Data[j][nCol - 1] == nCurrentClass ) {   //بç¹ûتاµ±ا°·ضہà
					if (dValue > 0) {
						tp++;
					} else {
						fp++;
					}
				} else if (Data[j][nCol - 1] !=nCurrentClass) {  //بç¹û²»تاµ±ا°·ضہà
					if (dValue <= 0) {
						tn++;
					} else {
						fn++;
					}
				}

			}
			Pop.Get(i).Fitness = (double) (tp + tn) / (double) nRow;
			Fitness[i] = (double) (tp + tn) / (double) nRow;

		}
	}

}
