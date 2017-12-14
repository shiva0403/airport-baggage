package com.airport.baggage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

public class Main {

	private final static String INPUT_DATA_SECTION_HEAD = "# Section:";
	private final static String FLIGHT_ARRIVAL = "ARRIVAL";
	private final static String DEST_BAGGAGE_CLAIM = "BaggageClaim";
	private final static String SINGLE_WHITE_SPACE = " ";
	private static Logger logger = Logger.getLogger(Main.class);

	 public static void main(String [] args) throws GraphMapException{
		 	logger.info("Main Method called");;
	        Scanner scan=promptAndParse();
	        if(scan != null){
	            List<Edge> edges= parseInputConveyorSystem(scan);
	            BaggageRouter baggageRouter = new BaggageRouter();
	            Map<String,String> departuresMap=parseInputDepartures(scan); 
	            List<Baggage> bagList = parseInputBags(scan);
	            scan.close();
	            for(Baggage bag:bagList){
	                String bagId=bag.getBagNumber();
	                String entryGate=bag.getEntryPoint();
	                String flight = bag.getFlightId();

	                String destGate;
	                if(flight.equals(FLIGHT_ARRIVAL)){
	                    destGate=DEST_BAGGAGE_CLAIM;
	                }else{
	                    destGate=departuresMap.get(flight);
	                }
	                String pathLine=baggageRouter.findShortestPath(entryGate,destGate,edges);

	                logger.info(bagId+SINGLE_WHITE_SPACE+pathLine);
	            }
	        }

	    }

	 
	private static Scanner promptAndParse() {
		logger.info("Please input the data here:");
		Scanner scan = new Scanner(System.in);
		return scan;
	}

	private static List<Edge> parseInputConveyorSystem(Scanner scanner) {
		logger.info("Loading Conveyor System");
		String graphSection = scanner.nextLine();
		if (!graphSection.startsWith(INPUT_DATA_SECTION_HEAD)) {
			throw new IllegalArgumentException(
					"Illegal arguments or inputs.");
		}
		String next = scanner.nextLine();
		logger.info("parseInputGraph -->"+next);
		List<Edge> edges = new ArrayList<>();
		while (!next.startsWith(INPUT_DATA_SECTION_HEAD)) {
			String[] parts = next.trim().split("\\s+");
			if (parts.length >= 3) {
				Edge directedEdge = new Edge(parts[0], parts[1], Integer.valueOf(parts[2]));
				edges.add(directedEdge);
				
				Edge rDirectedEdge = new Edge(parts[1], parts[0], Integer.valueOf(parts[2]));
				edges.add(rDirectedEdge);
			} else {
				throw new IllegalArgumentException(
						"Illegal arguments or inputs.");
			}
			next = scanner.nextLine();
		}
		logger.info(edges);
		return edges;
	}

	private static Map<String, String> parseInputDepartures(Scanner scanner) {
		logger.info("Loading Departures");
		String next = scanner.nextLine();
		Map<String, String> departuresMap = new HashMap<>();
		while (!next.startsWith(INPUT_DATA_SECTION_HEAD)) {
			String[] parts = next.trim().split("\\s+");
			if (parts.length >= 2) {
				departuresMap.put(parts[0], parts[1]);
			} else {
				throw new IllegalArgumentException(
						"Illegal arguments or inputs. Please refer to readme for the input data format.");
			}
			next = scanner.nextLine();
		}
		logger.info(departuresMap);
		return departuresMap;
	}

	private static List<Baggage> parseInputBags(Scanner scanner) {
		logger.info("Loading Input Bags");
		String next;
		List<Baggage> bagList = new ArrayList<>();
		do {
			next = scanner.nextLine();
			String[] parts = next.trim().split("\\s+");
			if (parts.length >= 3) {
				Baggage bag = new Baggage(parts[0], parts[1], parts[2]);
				bagList.add(bag);
			} else {
				scanner.close();
				break;
			}
		} while (scanner.hasNextLine());
		return bagList;
	}
}
