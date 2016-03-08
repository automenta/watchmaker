package com.lab;

import java.util.LinkedList;
import java.util.List;

import com.gep.*;

/**
 * ï؟½ï؟½ï؟½ï؟½ر،ï؟½ï؟½
 * @author shenzhan
 *
 */
public class FeatureSelect {

	/**
	 * @param args
	 */
	//ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½
	List<Individual> listIndivSet=new LinkedList<Individual>();
	FeatStru[] FeatureSta=null;
	GEPRun GepRun;
	
	double[] Accuracy;
	int[] nAttri;
	
	/**
	 * ï؟½ï؟½ب،ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½
	 */
	public void GetSuperSet(){
		int i,j,k;
		for(i=0;i<4;++i){
		    GepRun=new GEPRun();
			GepRun.RunGep();
			Population Pop=GepRun.GepPro.Pop;
			for(j=0;j<Pop.Size;++j){
				listIndivSet.add(Pop.Get(j));
			}
		}
	}
	
	/**
	 * ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ضµï؟½ئµï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½
	 */
	public void GetOrder(){
		this.FeatureSta=new FeatStru[GepProcess.FeatureNum];
		for(int i = 0; i< GepProcess.FeatureNum; ++i){
			FeatureSta[i]=new FeatStru();
			FeatureSta[i].nNO=i;
			FeatureSta[i].nCount=0;
		}
		
		Expression Exp=new Expression();
		FunctionSet Fun=new FunctionSet();
		
		
		//ï؟½ï؟½ï؟½ï؟½ئµï؟½ï؟½ï؟½ï؟½ح³ï؟½ï؟½
		
		for(int i=0;i<listIndivSet.size();++i){
			
			 Individual Indiv=listIndivSet.get(i);
			 
			 for(int j=0;j<GepProcess.GeneCount;++j){
				 
				  List<String> Gene=Indiv.GetGene(j);
				   int nLen=Exp.GetValidLength(Gene);
				   
				   for(int k=0;k<nLen;++k){
						 String str=Gene.get(k);
						 int n=Fun.GetParamCount(str);
						 if(0==n){
							  int index=Integer.parseInt(str);
							  FeatureSta[index].nCount++;
						 }
					 }
				 
			 }
			 	 
		}
		
		
		// ï؟½ï؟½ï؟½ï؟½ ر،ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½
		int i,j = 0,k;
		FeatStru Max;
		for(i=0;i<FeatureSta.length-1;++i){
			k=i;
			Max=FeatureSta[i];
			for(j=i+1;j<FeatureSta.length;++j){
				if(FeatureSta[j].nCount>Max.nCount){
					k=j;
					Max=FeatureSta[j];
				}
			}
			if(i!=k){
				FeatureSta[k]=FeatureSta[i];
				FeatureSta[i]=Max;
			}
		}
		
		//ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½
		for(i=0;i<FeatureSta.length;++i){
			String str=String.format("%3d ", FeatureSta[i].nNO);//,FeatureSta[i].nCount);
			System.out.print(str);
		}
		System.out.println();
		for(i=0;i<FeatureSta.length;++i){
			String str=String.format("%3d ", FeatureSta[i].nCount);//,FeatureSta[i].nCount);
			System.out.print(str);
		}
		
		
	}
	
	/**
	 * ï؟½ï؟½ج½ï؟½ï؟½ ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½
	 */
	public void TryFeatureCount(){
		int i,j,k;
		Accuracy=new double[FeatureSta.length];
		for(i=1;i<=FeatureSta.length;++i){
            //ï؟½ï؟½ا°iï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ï؟½ëµ½ï؟½ï؟½ï؟½ï؟½nAttriï؟½ï؟½
			 nAttri=new int[i];
			for(j=0;j<i;++j){
				nAttri[j]=FeatureSta[j].nNO;
			}
			
			//ï؟½ï؟½ï؟½ï؟½3ï؟½ï؟½gep 
			double dTemp=0;
			for(k=0;k<3;++k){
				GEPRun gep=new GEPRun();
				gep.nAttri=nAttri;
				gep.RunGep();
				dTemp+=gep.GepPro.TestAccuracy;
			}
			Accuracy[i-1]=dTemp/3;
		}
		
		//
		System.out.println();
		for(i=0;i<FeatureSta.length;++i){
			System.out.print("  "+Accuracy[i]);
		}
		
	}
	
	//ï؟½ï؟½ب،ï؟½ï؟½ï؟½ï؟½×¼ب·ï؟½ï؟½ï؟½ï؟½كµï؟½ ï؟½ï؟½ï؟½ï؟½ï؟½س¼ï؟½
	public void GetSubFeatureSet(){
		int i,j,k;
		double max=Accuracy[0];
		k=0;
		for(i=1;i<FeatureSta.length;++i){
			 if(Accuracy[i]>max){
				 max=Accuracy[i];
				 k=i;
			 }
		}
		
		System.out.println();
		for(j=0;j<=k;++j){
			System.out.print("  "+FeatureSta[j].nNO);
		}
		
	}
	
	
	public static void main(String[] args) {
		FeatureSelect fs=new FeatureSelect();
		fs.GetSuperSet();
		fs.GetOrder();
		//fs.TryFeatureCount();
		//fs.GetSubFeatureSet();

	}

}

class FeatStru{
	int nNO;
	int nCount;
}
