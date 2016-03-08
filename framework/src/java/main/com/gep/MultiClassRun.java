package com.gep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class MultiClassRun {

	public GepProcess GepPro;
	public static int nCheck;
	
	public Individual[] BestIndivs;
	
	public int[] nAttri=null;

	/**
	 * ï؟½ï؟½ب،ï؟½ï؟½ï؟½ï؟½
	 * @param sPath
	 * @return
	 * @throws IOException
	 */
	public double[][] ReadData(String sPath)  {

		double[][] digital = new double[1000][1000];

		File file = new File(sPath);
		if (!file.exists() || file.isDirectory())
			try {
				throw new FileNotFoundException();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String temp = null;
		String[] tempArray = null;
		int i = 0;
		try {
			temp = br.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (temp != null) {

			tempArray = temp.split(",");
			
			GepProcess.FeatureNum =tempArray.length-1;  //==========================================================
			
			for (int j = 0; j < tempArray.length; j++) {
				digital[i][j] = Double.parseDouble(tempArray[j]);

			}
			try {
				temp = br.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			i++;
		}

		int nRow = i;
		int nCol = tempArray.length;
		
		
		

		double[][] Res = new double[nRow][nCol];
		for (i = 0; i < nRow; ++i) {
			for (int j = 0; j < nCol; ++j) {
				Res[i][j] = digital[i][j];
			}
		}
		return Res;

	}

	/**
	 * ï؟½أ²ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ف²ï؟½ï؟½ï؟½ ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½nAttri[] ï؟½ï؟½ï؟½ï؟½
	 * 
	 * @param nAttri
	 */
	public double[][] SelectAttri(int[] nAttri, double[][] Data) {
		int nRow = Data.length;
		int nCol = Data[0].length;
		double[][] dRes = new double[nRow][nAttri.length + 1]; // ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½

		int i, j, k;
		int nIndex;
		for (i = 0; i < nAttri.length; ++i) {
			nIndex = nAttri[i];
			for (j = 0; j < nRow; ++j) {
				dRes[j][i] = Data[j][nIndex];
			}
		}

		for (j = 0; j < nRow; ++j) {
			dRes[j][nAttri.length] = Data[j][nCol - 1];
		}

		return dRes;

	}

	/**
	 * ï؟½ï؟½ï؟½أ²ï؟½ï؟½ï؟½+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	 */
	public void SetValue() {

		GepPro.FitnessFunType = "SampleClassify"; // ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½س¦ضµï؟½ï؟½ï؟½ï؟½ ï؟½ï؟½ر،
													// SampleClassify:ï؟½ٍµ¥·ï؟½ï؟½à؛¯ï؟½ï؟½ ,
													// SenSepClassify:ï؟½ï؟½س¦ï؟½ï؟½*ï؟½ï؟½ï؟½ذ¶ï؟½
													// ConciseClassify ï؟½ï؟½ï؟½ؤ£ï؟½ï؟½
		GepPro.MaxGeneration = 100;
		GepPro.HeadLength = 8;
		GepProcess.GeneCount = 3;
		GepPro.PopulationSize = 50;
		GepProcess.FeatureNum = 35; // ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½------------------------------------------------
		GepPro.nClassCount=3;   //ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ ï؟½ï؟½ؤ؟
		
		
		
		GepPro.MutationRate = 0.0051;
		GepPro.ISRate = 0.1;
		GepPro.RISRate = 0.1;
		int[] nLen = { 1, 2, 3 };
		GepPro.ISElemLength = nLen;
		GepPro.RISRate = 0.1;
		GepPro.RISElemLength = nLen;
		GepPro.GeneTransRate = 0.1;

		GepPro.OnePRecomRate = 0.3;
		GepPro.TwoPRecomRate = 0.31;
		GepPro.GeneRecomRate = 0.1;
		

		GepPro.TrainData = ReadData("data/WineTrain.txt");// ----ï؟½ï؟½ï؟½ï؟½رµï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½فµï؟½آ·ï؟½ï؟½----------------------------------

		GepPro.TestData = ReadData("data/WineTest.txt");// ----ï؟½ï؟½ï؟½أ²ï؟½ï؟½ش¼ï؟½ï؟½ï؟½ï؟½فµï؟½آ·ï؟½ï؟½---------------------------------------------

		
		

	}

	
	/**
	 * GEP ï؟½ï؟½ï؟½ذµؤ¹ï؟½ï؟½ï؟½
	 * @param GepPro
	 */
	public void GEPrunning(GepProcess GepPro){
		
		int nGeneration = 0;
		
		GepPro.InitialPopulation();

		do {

			GepPro.EvalutePopulaton();
			Print();

			GepPro.Select();

			GepPro.Mutation();

			GepPro.TransPosIS();

			GepPro.TransPosRIS();

			GepPro.TransPosGene();

			GepPro.RecomOnePoint();

			GepPro.RecomTwoPoint();

			GepPro.ReComGene();

			++nGeneration;
		

		} while (((1 - GepPro.BestIndividual.Fitness) > 0.03)
				&& nGeneration < GepPro.MaxGeneration);
	}
	
	
	public void RunGep() {

		GepPro = new GepProcess();
		SetValue();
		BestIndivs=new Individual[GepPro.nClassCount];
		int i;
		//رµï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½
		for(i=1;i<=GepPro.nClassCount;++i){
			GepPro.nCurrentClass=i;
			GEPrunning(GepPro);
			BestIndivs[i-1]=GepPro.BestIndividual;
		}
		
	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public static void main(String[] args) {
		

		   MultiClassRun gep = new MultiClassRun();
		    gep.RunGep();
		    
		    for(int i=0;i<gep.BestIndivs.length;++i){
		    	System.out.println(gep.BestIndivs[i].Fitness);
		    }
		    
		    System.out.println("ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½×¼ب·ï؟½ï؟½  ï؟½ï؟½"+gep.Test());

	}

	public double Test(){
		
		int nRow = GepPro.TestData.length;
		int nCol =  GepPro.TestData[0].length;
		double[] dValue=new double[GepPro.nClassCount];
		int i, j,k;
		Expression Exp = new Expression();
		int tp = 0, fp = 0;
		for (j = 0; j < nRow; ++j) {
			
			for(i=0;i<GepPro.nClassCount;++i){
				 dValue[i] = Exp.GetValue(BestIndivs[i], GepPro.TestData[j]);  //ï؟½ï؟½ï؟½ï؟½ï؟½ف·إµï؟½أ؟ز»ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½
			}
			
			int DataClass=(int) GepPro.TestData[j][nCol-1];   //ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ؤ·ï؟½ï؟½ï؟½
			
		   
			if(DataClass==2){
			
			if(dValue[DataClass-1]>0){
					++tp;
				}
				else{
					++fp;
				}
			}	
	
		}
		GepPro.TestAccuracy = (tp ) / (double) (tp+fp);
		return GepPro.TestAccuracy;
	}
	
	
	public void Print() {
		for (int i = 0; i < GepPro.PopulationSize; ++i) {

			for (int j = 0; j < GepProcess.GeneCount; ++j) {

				for (int k = 0; k < GepProcess.GeneLength; ++k) {
					System.out.print(GepPro.Pop.Get(i).Get(
							j * GepProcess.GeneLength + k)
							+ " ");
				}
				System.out.print("        ");
			}
			System.out.print("  :  " + GepPro.Fitness[i] + " value"
					+ GepPro.Pop.Get(i).Value);
			System.out.println();
		}
		System.out.println();

	}

}
