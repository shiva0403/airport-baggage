package com.airport.baggage;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BaggageRouter {

	 private final static String SINGLE_WHITE_SPACE=" ";

	    Map<String, GraphMap> visitedMap=new ConcurrentHashMap<>(); 

	    public String findShortestPath(String entryGate, String destGate, List<Edge> edges) throws GraphMapException {
	        GraphMap graphMap;
	        if(visitedMap.containsKey(entryGate)){
	            graphMap = visitedMap.get(entryGate);
	        }
	        else {
	            graphMap = new GraphMap(edges);
	            graphMap.graphMap(entryGate);
	            visitedMap.put(entryGate,graphMap);
	        }

	        List<Node> shortestPath= graphMap.getShortestPath(destGate);
	        return generatePathLine(shortestPath);
	    }

	    private String generatePathLine(List<Node> path){
	        StringBuffer line = new StringBuffer();

	        for(Node node:path){
	            line.append(node.getNodeName()).append(SINGLE_WHITE_SPACE);
	        }
	        line.append(": ").append(path.get(path.size()-1).getTime());
	        return line.toString();
	    }

}
