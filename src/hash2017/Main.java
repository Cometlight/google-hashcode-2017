package hash2017;

import java.io.File;
import java.io.IOException;

import hash2017.model.Cache;
import hash2017.model.Endpoint;
import hash2017.model.Structure;
import hash2017.model.Video;

public class Main {
	
	public static final int NUMBER_OF_ITERATIONS = 5;

	public static void main(String[] args) throws IOException {
		System.out.println("Team FH* Reloaded");
		String current = new java.io.File( "." ).getCanonicalPath();
		System.out.printf(current + "\n");
		String[] fileNames = {
				"kittens.in", 
				"me_at_the_zoo.in",
				"trending_today.in",
				"videos_worth_spreading.in"
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
		Structure structure = AssignmentParser.parseFile(file);
		doSimulation(structure);
	}
	
	public static void doSimulation(Structure structure) {
		for (Endpoint endpoint : structure.endpoints) {
			System.out.println("Working on endpoint " + endpoint.id + " of " + structure.endpoints.length);
			for (Video video : endpoint.videoRequests.keySet()) {
				insertInBestCache(endpoint, video, null);
			}
		}
		
		for (int i = 0; i < NUMBER_OF_ITERATIONS; ++i) {
			optimizeCacheContents(structure);
		}
		System.out.println("-----BEGIN---------------------------");
		OutputWriter.writeOutput(structure);
		System.out.println("-----END---------------------------");
	}

	private static void insertInBestCache(Endpoint endpoint, Video video, Cache cacheToExclude /*may be null*/) {
		Integer bestTimeSaving = 0;
		Cache bestCache = null;
		for (Cache cache : endpoint.cachesLatency.keySet()) {
			if (cacheToExclude != null && cache.id == cacheToExclude.id){
				continue;
			}
			Integer timeSaving = TimeSavingCalculator.getTotalTimeSaving(endpoint, video, cache);
			if (timeSaving > bestTimeSaving) {
				if (bestCache != null) { // Remove video from old cache if existing
					bestCache.removeFromPriorityQueue(endpoint, video);
				}
				bestTimeSaving = timeSaving;
				bestCache = cache;
				cache.insertPriorityQueueEntry(endpoint, video, timeSaving);
			}
		}
	}
	
	public static void optimizeCacheContents(Structure structure) {
		for (Cache cache : structure.caches) {
			for (CachePriorityEntry outOfMemoryEntry : cache.getVideosOutOfMemory()) {
				for (Endpoint endpoint : outOfMemoryEntry.getEndpointTimeSavingMap().keySet()) {
					Video video = outOfMemoryEntry.getVideo();
					insertInBestCache(endpoint, video, cache);
				}
			}
		}
	}

}
