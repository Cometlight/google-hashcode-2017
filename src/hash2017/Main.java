package hash2017;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import hash2017.model.Cache;
import hash2017.model.Endpoint;
import hash2017.model.Structure;
import hash2017.model.Video;

public class Main {
	
	public static final int NUMBER_OF_ITERATIONS = 5;
	public static final boolean RANDOMIZE_CACHE_ITERATION = true;
//	public static final int RANDOMIZE_SEED = 1234;
	private static final Random randomGenerator = new Random();

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
		doSimulation(structure, file);
	}
	
	public static void doSimulation(Structure structure, File file) {
		System.out.println(getString(structure.endpoints.length/100, '.'));
		for (Endpoint endpoint : structure.endpoints) {
			if (endpoint.id % 100 == 0)
				System.out.print(".");
			for (Video video : endpoint.videoRequests.keySet()) {
				insertInBestCache(endpoint, video, null);
			}
		}
		
		System.out.println("Starting optimizations...");
		for (int i = 0; i < NUMBER_OF_ITERATIONS; ++i) {
			System.out.println("Optimization step " + (i+1) + " of " + NUMBER_OF_ITERATIONS);
			optimizeCacheContents(structure);
		}
		System.out.println("Writing file " + file.getName());
		OutputWriter.writeOutputToFile(structure, file.getName().concat(".out"));
		System.out.println("Done with file " + file.getName() + "\n\n- - -\n\n");
	}

	private static void insertInBestCache(Endpoint endpoint, Video video, List<Cache> cachesToExclude /*may be null*/) {
		Integer bestTimeSaving = 0;
		Cache bestCache = null;
		List<Cache> cachesToSearch = new LinkedList<Cache>( endpoint.cachesLatency.keySet());
		if (RANDOMIZE_CACHE_ITERATION) {
			Collections.shuffle(cachesToSearch, randomGenerator);
		}
		for (Cache cache : cachesToSearch) {
			if (cachesToExclude != null && cachesToExclude.contains(cache)){
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
		System.out.println(getString(structure.caches.length/100, '.'));
		for (Cache cache : structure.caches) {
			if (cache.id % 100 == 0)
				System.out.print(".");
			for (CachePriorityEntry outOfMemoryEntry : cache.getAndRemoveVideosOutOfMemory()) {
				for (Endpoint endpoint : outOfMemoryEntry.getEndpointTimeSavingMap().keySet()) {
					Video video = outOfMemoryEntry.getVideo();
					insertCacheIntoHistory(structure, endpoint, video, cache);
					insertInBestCache(endpoint, video, getCacheListFromHistory(structure, endpoint, video));
				}
			}
		}
	}
	
	private static void insertCacheIntoHistory(Structure structure, Endpoint endpoint, Video video, Cache cache) {
		HashMap<Video, List<Cache>> videoCachesMap = structure.cacheHistory.get(endpoint);
		if (videoCachesMap == null) {
			videoCachesMap = new HashMap<>();
			structure.cacheHistory.put(endpoint, videoCachesMap);
		}
		
		List<Cache> caches = videoCachesMap.get(video);
		if (caches == null) {
			caches = new LinkedList<>();
			videoCachesMap.put(video, caches);
		}
		
		caches.add(cache);
	}
	
	private static List<Cache> getCacheListFromHistory(Structure structure, Endpoint endpoint, Video video) {
		HashMap<Video, List<Cache>> videoCachesMap = structure.cacheHistory.get(endpoint);
		if (videoCachesMap == null) {
			return null;
		}
		
		return videoCachesMap.get(video);
	}

	private static String getString(int length, char charToFill) {
		if (length <= 1)
			return String.valueOf(charToFill);
		return String.format("%0" + length + "d", 0).replace('0', charToFill);
	}

}
