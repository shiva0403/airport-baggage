package com.airport.baggage;

import org.apache.log4j.Logger;

public class GraphMapException extends Exception {

	private static final long serialVersionUID = 1L;

	public GraphMapException(String message) {
		Logger.getLogger(GraphMapException.class).error("GraphMapException");
	}

	
}
