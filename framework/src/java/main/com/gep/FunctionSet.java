/**
 * 
 */
package com.gep;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

  

/**
 * @author shenzhan
 *
 */
public class FunctionSet {
  
	
	  public List<String> sFunction;
	  private Map<String, Integer>  FunMap;
	  public int MaxParamCount;
	  
	/**
	 *   ³ُت¼»¯ ةèضأ؛¯ت‎¼¯
	 */
	public FunctionSet() {
		
		  this.MaxParamCount=2;
		  sFunction=new LinkedList<String>();
		  String[] temp={"+","-","*","/","sin","cos","sqrt","tan","pow2","log","abs"};  //ةèضأ؛¯ت‎¼¯
		  for(int i=0;i<temp.length;++i){
			  sFunction.add(temp[i]);
		  }
		  
		  //سë²خت‎¸ِت‎ ½¨ء¢س³ةن¹طدµ
		  FunMap=new HashMap<String, Integer>();
		  for(int i=0;i<4;++i){
			  FunMap.put(temp[i], 2);
		  }
		  for(int i=4;i<temp.length;++i){
			  FunMap.put(temp[i],1);
		  }
		 	  
	}
	/**
	 * إذ¶دتا·ٌتا؛¯ت‎
	 * @param Operator
	 * @return
	 */
	public boolean IsFunction(String Operator){
		    return this.sFunction.contains(Operator);
	}

	/**
	 * ·µ»ط شثثم·û ²ظ×÷ت‎¸ِت‎
	 */
	public int GetParamCount(String Operator) {
		 Integer Inte= FunMap.get(Operator);
		if(Inte==null){
			return 0;
		}
		else{
			return Inte.intValue();
		}
	}

	/**
	 * »ٌب،¼ئثم½ل¹û
	 */
	public double GetResult(String Operator, double[] Data) {
		
		  if(Operator.equals("+")){
			    return Data[0]+Data[1];
		  }
		  else if(Operator.equals("-")){
			    return Data[0]-Data[1];
		  }
		  else if(Operator.equals("*")){
			    return Data[0]*Data[1];
		  }
		  else if(Operator.equals("/")){
			    return Data[0]/Data[1];  //--------------------------------
		  }
		  else if(Operator.equals("sin")){
			    return Math.sin(Data[0]);
		  }
		  else if(Operator.equals("cos")){
			     return Math.cos(Data[0]);
		  }
		  else if(Operator.equals("sqrt")){
			  return Math.sqrt(Data[0]);
		  }
		  else if(Operator.equals("tan")){
			  return Math.tan(Data[0]);
		  }
		  else if(Operator.equals("pow2")){
			    return Math.pow(Data[0], 2);
		  }
		  else if(Operator.equals("log")){
			    return Math.log(Data[0]);
		  }
		  else if(Operator.equals("abs")){
			  return Math.abs(Data[0]);
		  }
		  
		return 0;
	}

}
