package com.airport.baggage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main {

	private final static String INPUT_DATA_SECTION_HEAD = "# Section:";
	private final static String FLIGHT_ARRIVAL = "ARRIVAL";
	private final static String DEST_BAGGAGE_CLAIM = "BaggageClaim";
	private final static String SINGLE_WHITE_SPACE = " ";

	public static void main(String[] args) throws GraphMapException {
		Scanner scan = null;
		if (args.length > 0) {
			File inputFile = new File(args[0].trim());
			if (inputFile.exists()) {
				try {
					scan = new Scanner(inputFile);

				} catch (FileNotFoundException fnfex) {
					scan = promptAndParse();
				}
			} else {
				scan = promptAndParse();
			}
		} else {
			scan = promptAndParse();
		}
		if (scan != null) {
			List<Edge> edges = parseInputConveyorSystem(scan);
			BaggageRouter baggageRouter = new BaggageRouter();
			Map<String, String> departuresMap = parseInputDepartures(scan);
			List<Baggage> bagList = parseInputBags(scan);
			scan.close();
			for (Baggage bag : bagList) {
				String bagId = bag.getBagNumber();
				String entryGate = bag.getEntryPoint();
				String flight = bag.getFlightId();

				String destGate;
				if (flight.equals(FLIGHT_ARRIVAL)) {
					destGate = DEST_BAGGAGE_CLAIM;
				} else {
					destGate = departuresMap.get(flight);
				}
				String pathLine = baggageRouter.findShortestPath(entryGate, destGate, edges);

				System.out.println(bagId + SINGLE_WHITE_SPACE + pathLine);
			}

		}
	}

	private static Scanner promptAndParse() {
		// System.out.println("Please input the data here:");
		Scanner scan = new Scanner(System.in);
		return scan;
	}

	private static List<Edge> parseInputConveyorSystem(Scanner scanner) {
		// System.out.println("Loading Conveyor System");
		String graphSection = scanner.nextLine();
		if (!graphSection.startsWith(INPUT_DATA_SECTION_HEAD)) {
			throw new IllegalArgumentException("Illegal arguments or inputs.");
		}
		String next = scanner.nextLine();
		// System.out.println("parseInputGraph -->" + next);
		List<Edge> edges = new ArrayList<>();
		while (!next.startsWith(INPUT_DATA_SECTION_HEAD)) {
			String[] parts = next.trim().split("\\s+");
			if (parts.length >= 3) {
				Edge directedEdge = new Edge(parts[0], parts[1], Integer.valueOf(parts[2]));
				edges.add(directedEdge);

				Edge rDirectedEdge = new Edge(parts[1], parts[0], Integer.valueOf(parts[2]));
				edges.add(rDirectedEdge);
			} else {
				throw new IllegalArgumentException("Illegal arguments or inputs.");
			}
			next = scanner.nextLine();
		}
		//System.out.println(edges);
		return edges;
	}

	private static Map<String, String> parseInputDepartures(Scanner scanner) {
		// System.out.println("Loading Departures");
		String next = scanner.nextLine();
		Map<String, String> departuresMap = new HashMap<>();
		while (!next.startsWith(INPUT_DATA_SECTION_HEAD)) {
			String[] parts = next.trim().split("\\s+");
			if (parts.length >= 2) {
				departuresMap.put(parts[0], parts[1]);
			} else {
				throw new IllegalArgumentException("Illegal arguments or inputs.");
			}
			next = scanner.nextLine();
		}
		// System.out.println(departuresMap);
		return departuresMap;
	}

	private static List<Baggage> parseInputBags(Scanner scanner) {
		String next ;
        List<Baggage> bagList= new ArrayList<>();
        do{
            next = scanner.nextLine();
            String [] parts = next.trim().split("\\s+");
            if(parts.length >=3){
                Baggage baggage= new Baggage(parts[0],parts[1],parts[2]);
                bagList.add(baggage);
            }else{
                scanner.close();
                break;
            }
        }while(scanner.hasNextLine());
        return bagList;
	}
}
