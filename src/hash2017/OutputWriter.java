package hash2017;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import hash2017.model.Cache;
import hash2017.model.Structure;
import hash2017.model.Video;

public class OutputWriter {
	
	public static void writeOutput(Structure structure){
		List<Cache> caches = Arrays.asList(structure.caches).stream().filter(cache -> !cache.getStoredVideos().isEmpty()).collect(Collectors.toList());
		System.out.println(caches.size());
		caches.stream().forEach(cache -> System.out.println(cacheOutput(cache)));
	}

	private static String cacheOutput(Cache cache) {
		StringBuilder output = new StringBuilder(cache.id + " ");
		for(Video video : cache.getStoredVideos()){
			output.append(video.id + " ");
		}
		return output.toString();
	}
	
}
