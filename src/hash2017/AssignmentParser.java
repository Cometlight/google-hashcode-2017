package hash2017;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.LinkedList;
import java.util.List;

import hash2017.model.Cache;
import hash2017.model.Endpoint;
import hash2017.model.Structure;
import hash2017.model.Video;

public class AssignmentParser {
	public static Structure parseFile(File file) throws IOException {
		System.out.print(file.getName() + "\n");

		// pares into tokens
		Charset charset = Charset.forName("US-ASCII");
		List<String> content = Files.readAllLines(file.toPath(), charset);

		// parse dims
		List<Integer> dims = GetTokens(content.get(0));
		int videosCount = dims.get(0);
		int endpointCount = dims.get(1);
		int requestCount = dims.get(2);
		int cachesCount = dims.get(3);
		int cacheSize = dims.get(4);
		
		Structure structure = new Structure();
	
		// videos
		Video[] videos = new Video[videosCount];
		List<Integer> videoSizes = GetTokens(content.get(1));
		for (int i = 0; i < videosCount; ++i) {
			videos[i] = new Video(i, videoSizes.get(i));
		}

		structure.videos = videos;
		
		// endpoints
		Endpoint[] endpoints = new Endpoint[endpointCount];  
		for(int i = 0; i < endpointCount; ++i) {
			endpoints[i] = new Endpoint(i);
		}
		structure.endpoints = endpoints;
		
		// caches
	    Cache[] caches = new Cache[cachesCount];
	    for (int i = 0; i < cachesCount; ++i) {
	      caches[i] = new Cache(i, cacheSize);
	    }
	    structure.caches = caches;
	    
		// latenzy
	    int endpointCurrentId = 0;
	    int endpointDescriptionEndOffset = content.size() - requestCount;
	    for(int i = 2; i < endpointDescriptionEndOffset	; ++i) {
			// endpoint to cache descriptor
			List<Integer> cacheDescriptor = GetTokens(content.get(i));
			Integer dataCenterLatenzy = cacheDescriptor.get(0);
			Integer connectioncount = cacheDescriptor.get(1);
			
			Endpoint endPoint = structure.endpoints[endpointCurrentId];
			endPoint.datacenterLatency = dataCenterLatenzy;
			
			i++;
			int endOfDescriptionOffset = i + connectioncount - 1; 
			
			for(int j = i; j < endOfDescriptionOffset; ++j) {
				List<Integer> latencyDescription = GetTokens(content.get(j));
				Integer cacheId = latencyDescription.get(0);
				Integer latenzy = latencyDescription.get(1);
				
				Cache cache = structure.caches[cacheId];
				endPoint.cachesLatency.put(cache, latenzy);

				i = j;
			}	
			i++;
			endpointCurrentId++;
		}
		
	    for(int i = endpointDescriptionEndOffset +1; i < content.size(); ++i) {
	    	List<Integer> requestDescription = GetTokens(content.get(i));
	    	
	    	int videoId = requestDescription.get(0);
	    	int endpointId = requestDescription.get(1);
	    	int requests = requestDescription.get(2);
	    	
	    	Endpoint endpoint = structure.endpoints[endpointId];
	    	Video video = structure.videos[videoId];
	    	
	    	endpoint.videoRequests.put(video, requests);
	    }
		
		return structure;
	}

	public static List<Integer> GetTokens(String line) {
		String[] split = line.split("\\s+");

		List<Integer> result = new LinkedList<Integer>();

		for (int i = 0; i < split.length; ++i) {
			result.add(Integer.parseInt(split[i]));
		}

		return result;

	}

}