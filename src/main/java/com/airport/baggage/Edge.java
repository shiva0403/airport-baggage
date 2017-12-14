package com.airport.baggage;

public class Edge {

	private final Node source;
	private final Node destination;
	private final int time;

	public Edge(Node source, Node destination, int time) {
		this.source = source;
		this.destination = destination;
		this.time = time;
	}

	public Edge(String sourceName, String destinationName, Integer time) {
        this.source = new Node(sourceName);
        this.destination = new Node(destinationName);
        this.time = time;
    }

	public Node getDestination() {
		return destination;
	}

	public Node getSource() {
		return source;
	}

	public int getTime() {
		return time;
	}

	@Override
	public String toString() {
		return source + " " + destination;
	}
}
