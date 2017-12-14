package com.airport.baggage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;

public class GraphMap {

	private final Map<String, Node> graphMap;
	
    public GraphMap(List<Edge> edges) {

         graphMap = new HashMap<>(edges.size());

        //Populated all the vertices from the edges
        for (Edge e : edges) {
            if (!graphMap.containsKey(e.getSource().getNodeName())){
            	graphMap.put(e.getSource().getNodeName(), new Node(e.getSource().getNodeName()));
            }
            if (!graphMap.containsKey(e.getDestination().getNodeName())){
            	graphMap.put(e.getDestination().getNodeName(), new Node(e.getDestination().getNodeName()));
            }
        }

        //Set all the linked nodes
        for (Edge e : edges) {
            graphMap.get(e.getSource().getNodeName()).getLinkedNodes().put(graphMap.get(e.getDestination().getNodeName()), e.getTime());
        }
    }
    public void graphMap(String startName) throws GraphMapException {
        if (!graphMap.containsKey(startName)) {
            throw new GraphMapException("This DijkstraGraphMap does not contain the starting Vertex named:"+startName);
        }
        final Node source = graphMap.get(startName);
        NavigableSet<Node> queue = new TreeSet<>();

        // populate vertices to the queue
        for (Node v : graphMap.values()) {
            v.setPrevNode( v == source ? source : null);
            v.setTime(v == source ? 0 : Integer.MAX_VALUE);
            queue.add(v);
        }

        graphMap(queue);
    }
    
    public List<Node> getShortestPath(String endName) throws GraphMapException{
        if (!graphMap.containsKey(endName)) {
            throw new GraphMapException("Graph doesn't contain end vertex : "+endName);
        }

        return graphMap.get(endName).getShortestPathTo();
    }
    
    private void graphMap(final NavigableSet<Node> que) {
        Node source, neighbour;
        while (!que.isEmpty()) {

            source = que.pollFirst(); 
            if (source.getTime() == Integer.MAX_VALUE){
            	break;
            }

            for (Map.Entry<Node, Integer> a : source.getLinkedNodes().entrySet()) {
                neighbour = a.getKey();

                final int alternateTime = source.getTime() + a.getValue();
                if (alternateTime < neighbour.getTime()) { 
                    que.remove(neighbour);
                    neighbour.setTime(alternateTime);
                    neighbour.setPrevNode(source);
                    que.add(neighbour);
                }
            }
        }
    }

}
