package com.gep;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GEPRun {

	public GepProcess GepPro;
	public static int nCheck;

	public int[] nAttri=null;

	public double[][] ReadData(String sPath) throws IOException {


		double[][] digital = new double[1000][1000];

		sPath="/home/me/gep/Gene_Expression_Programming/" + sPath;
		File file = new File(sPath);
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		String[] tempArray = null;
		int i = 0;
		temp = br.readLine();
		while (temp != null) {

			tempArray = temp.split(",");

			GepProcess.FeatureNum =tempArray.length-1;  //==========================================================

			for (int j = 0; j < tempArray.length; j++) {
				digital[i][j] = Double.parseDouble(tempArray[j]);

			}
			temp = br.readLine();
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
	 * �ò��������������ݲ��� ����������nAttri[] ����
	 *
	 * @param nAttri
	 */
	public double[][] SelectAttri(int[] nAttri, double[][] Data) {
		int nRow = Data.length;
		int nCol = Data[0].length;
		double[][] dRes = new double[nRow][nAttri.length + 1]; // ���������

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
	 * ���ò���
	 */
	public void SetValue() {

		GepPro.FitnessFunType = "SampleClassify"; // ������Ӧֵ���� ��ѡ
		// SampleClassify:�򵥷��ຯ�� ,
		// SenSepClassify:��Ӧ��*���ж�
		// ConciseClassify ���ģ��
		GepPro.MaxGeneration = 100;
		GepPro.HeadLength = 8;
		GepProcess.GeneCount = 3;
		GepPro.PopulationSize = 50;
		GepProcess.FeatureNum = 35; // ��������------------------------------------------------

		// ---------------------------------------------------------
		int[] nAttri={7  ,  26   , 20   , 17  ,   5  ,   6    ,25   , 28   , 14  ,  24  ,  11    ,27   ,  1};

		try {
			GepPro.TrainData = ReadData("data/BreastCancerTrain.txt");// ----����ѵ�������ݵ�·��----------------------------------

		} catch (IOException e1) {
			e1.printStackTrace();
		}

		try {
			GepPro.TestData = ReadData("data/BreastCancerTest.txt");// ----���ò��Լ����ݵ�·��---------------------------------------------

		} catch (IOException e) {
			e.printStackTrace();
		}

		//���������Ӽ�----------------------------------------------------------------------------------------------------------------------------------------
		if(nAttri!=null){

			GepProcess.FeatureNum = nAttri.length;
			GepPro.TrainData = this.SelectAttri(nAttri, GepPro.TrainData);
			GepPro.TestData = this.SelectAttri(nAttri, GepPro.TestData);
		}

		//---------------------------------------------------------------------------------------------------------------------------------------------------

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

	}

	public void RunGep() {

		GepPro = new GepProcess();
		SetValue();
		int nGeneration = 0;
		GepPro.InitialPopulation();

		do {

			GepPro.EvalutePopulaton();

			//Print();
			// System.out.println("before average Fitness "+GepPro.AverageFitness());

			GepPro.Select();

			GepPro.Statictis();// ͳ��
			Print();

			GepPro.Mutation();

			GepPro.TransPosIS();

			GepPro.TransPosRIS();

			GepPro.TransPosGene();

			GepPro.RecomOnePoint();

			GepPro.RecomTwoPoint();

			GepPro.ReComGene();

			++nGeneration;
			// System.out.println(nGeneration
			// +":  "+GepPro.BestIndividual.Fitness +"\n");

		} while (((1 - GepPro.BestIndividual.Fitness) > 0.03)
				&& nGeneration < GepPro.MaxGeneration);

		// GepPro.Test();

		System.out.println(GepPro.BestIndividual.Fitness);



		System.out.println("����  " + GepPro.Test());

		GepPro.GetFeatureOrder();

	}

	// ++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
	public static void main(String[] args) {

//		double d=0;
//		for(int i=0;i<5;++i)
//		{
		GEPRun gep = new GEPRun();
		gep.RunGep();
//		  d+=gep.GepPro.TestAccuracy;
//		}
//		d=d/5;
//		System.out.println("ƽ��׼ȷ��Ϊ  "+d);
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
