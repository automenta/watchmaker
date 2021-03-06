/*
 * Copyright 2010 KAT Software LLC. 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.gep4j.tree;

import java.util.Random;

import org.gep4j.INode;
import org.gep4j.INodeFactory;

public class IntegerDecisionNodeFactory implements INodeFactory {
	private int min;
	private int max;
	private ThreadLocal<Integer> value;
	private String name;
	
	public IntegerDecisionNodeFactory(String name, int min, int max, ThreadLocal<Integer> value) {
		this.min = min;
		this.max = max;
		this.value = value;
		this.name = name;
	}
	
	@Override
	public INode create(Random rng) {
		int rand = rng.nextInt(max - min + 1);
		return new IntegerDecisionNode(name, min + rand, value);
	}

	@Override
	public int getAirity() {
		return 2;
	}

}
