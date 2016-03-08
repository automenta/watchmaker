/**
 * 
 */
package com.gep;

/**
 * @author shenzhan
 *
 */
public class SampleClassify extends FitnessFunction {

	/**
	 * ¼ٍµ¥µؤت‎¾ف·ضہà·½·¨
	 * ص‎ب··ضہà¸ِت‎/(×ـت‎)
	 */
	public SampleClassify() {
		
	}

	@Override
	public void GetFitness(Population Pop, double[][] Data, double[] Fitness) {
		int nRow = Data.length;
		int nCol = Data[0].length;
	
		int i, j, k;

		for (i = 0; i < Pop.Size; ++i) {
			int tp = 0, fp = 0, tn = 0, fn = 0;
			for (j = 0; j < nRow; ++j) {
				double dValue = super.Exp.GetValue(Pop.Get(i), Data[j]);
				Pop.Get(i).Value=dValue;
				// ¶‏·ضہà 0 ہà
				if (Data[j][nCol-1] == 1) {
					if (dValue < 0) {
						tp++;
					} else {
						fp++;
					}
				} else if (Data[j][nCol-1] == 2) {
					if (dValue >= 0) {
						tn++;
					} else {
						fn++;
					}
				}

			}
			Pop.Get(i).Fitness = (double)(tp + tn) / (double)nRow;
			Fitness[i] = (double)(tp + tn) / (double)nRow;
		}
		
	}


}
