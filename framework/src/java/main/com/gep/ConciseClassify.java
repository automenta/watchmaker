/**
 * 
 */
package com.gep;

/**
 * @author shenzhan
 *
 */
public class ConciseClassify extends FitnessFunction {

	/**
	 * ¼ٍµ¥ؤ£ذح·ضہà·¨
	 * شعSenSepµؤ»ù´،ةد¼سبë³ح·£ 
	 * تاµؤ¸ِجه¸ü¼س¼ٍ½à
	 */
	public ConciseClassify() {
		// TODO Auto-generated constructor stub
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
			
			double se,sp,ss;
			
			if((tp+fn)<0.1){
				se = 0;
			}
			else
			    se = tp/(double)(tp+fn);//أô¸ذذش
			
			if((tn+fp)<0.1){
				sp = 0;
			} else {
				sp = tn/(double)(tn+fp);//جطذ§ذش
			}
			ss = se*sp;//أô¸ذ/جطذ§ذش
			
			Expression Exp=new Expression();
			double dValidLen=Exp.GetIndivValidLen(Pop.Get(i));
			double dIndivLen=Pop.Get(i).Chrom.size();
			
			ss=ss*1000-(dValidLen/dIndivLen)*10 ;  //¼سبë³ح·£زٍ×س
			
			Pop.Get(i).Fitness = ss;
			Fitness[i] = ss;
		}
		
	}

}
