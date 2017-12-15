package com.airport.baggage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node implements Comparable<Node>{

	private final String nodeName;
	private int time = Integer.MAX_VALUE;
	private Node prevNode = null;
	private final Map<Node, Integer> linkedNodes = new HashMap<>();
	
	public Node(String nodeName){
		this.nodeName = nodeName;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public Node getPrevNode() {
		return prevNode;
	}

	public void setPrevNode(Node prevNode) {
		this.prevNode = prevNode;
	}

	public String getNodeName() {
		return nodeName;
	}

	public Map<Node, Integer> getLinkedNodes() {
		return linkedNodes;
	}
	
	public int compareTo(Node other){
		if(time == other.time){
			return nodeName.compareTo(other.nodeName);
		}
		return Integer.compare(time, other.time);
	}
	
	@Override
	public String toString() {
		return "Node name :"+this.nodeName+" time : "+this.time;
	}
	
	public List<Node> getShortestPathTo()
    {
        List<Node> path = new ArrayList<>();
        path.add(this);
        Node node=this.getPrevNode();
        while (node!=null && !path.contains(node)) {
            path.add(node);
            node=node.getPrevNode();
        }

        Collections.reverse(path);
        return path;
    }
}
