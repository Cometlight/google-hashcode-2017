package hash2017;

import java.io.File;
import java.io.IOException;

import hash2017.model.Cache;
import hash2017.model.Endpoint;
import hash2017.model.Structure;
import hash2017.model.Video;

public class Main {

	public static void main(String[] args) throws IOException {
		System.out.println("Team FH* Reloaded");
		String current = new java.io.File( "." ).getCanonicalPath();
		System.out.printf(current + "\n");
	/*	String[] fileNames = {
				"kittens.in", 
				"me_at_the_zoo.in",
				"trending_today.in",
				"videos_worth_spreading.in"
			};*/
		
		String[] fileNames = {
				"kittens.in"
			};
		
		
		for (String fileName : fileNames) {
			solveTaskUsingInput(new File(fileName));
		}
		
	}
	
	public static void solveTaskUsingInput(File file) throws IOException {
		if(!file.exists())
		{
			throw new IOException();
		}
		AssignmentParser.parseFile(file);
	}
	
	public static void doSimulation(Structure structure) {
		for (Endpoint endpoint : structure.endpoints) {
			for (Video video : endpoint.videoRequests.keySet()) {
				for (Cache cache : endpoint.cachesLatency.keySet()) {
					Integer timeSaving = TimeSavingCalculator.getTotalTimeSaving(endpoint, video, cache);
					cache.insertPriorityQueueEntry(endpoint, video, timeSaving);
				}
			}
		}
	}

}
