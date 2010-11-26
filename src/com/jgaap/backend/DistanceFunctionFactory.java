package com.jgaap.backend;

import java.util.HashMap;
import java.util.Map;

import com.jgaap.generics.DistanceFunction;

public class DistanceFunctionFactory {

	private Map<String, DistanceFunction> distanceFunctions;
	
	public DistanceFunctionFactory() {
		// Load the distance functions dynamically
		distanceFunctions = new HashMap<String, DistanceFunction>();
		for(DistanceFunction distanceFunction: AutoPopulate.getDistanceFunctions()){
			distanceFunctions.put(distanceFunction.displayName().toLowerCase(), distanceFunction);
		}
	}
	
	public DistanceFunction getDistanceFunction(String action) throws Exception{
		DistanceFunction distanceFunction;
		action = action.toLowerCase();
		if(distanceFunctions.containsKey(action)){
			distanceFunction= distanceFunctions.get(action).getClass().newInstance();
		}else{
			throw new Exception("Distance Function "+action+" was not found!");
		}
		return distanceFunction;
	}
	
}
