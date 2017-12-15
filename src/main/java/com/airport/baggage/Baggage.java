package com.airport.baggage;

public class Baggage {

	private final String bagNumber;
	private final String entryPoint;
	private final String flightId;
	
	public Baggage(String bagNumber, String entryPoint, String flightId) {
		this.bagNumber = bagNumber;
		this.entryPoint = entryPoint;
		this.flightId = flightId;
	}

	public String getBagNumber() {
		return bagNumber;
	}

	public String getEntryPoint() {
		return entryPoint;
	}

	public String getFlightId() {
		return flightId;
	}
	
	@Override
	public String toString() {
		return "Baggage :"+bagNumber+","+entryPoint+","+flightId;
	}
	
}
