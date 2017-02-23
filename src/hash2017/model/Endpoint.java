package hash2017.model;

import java.util.HashMap;

public class Endpoint {
	public int id;
	public int datacenterLatency;
	public HashMap<Cache, Integer> cachesLatency;
	public HashMap<Video, Integer> videoRequests;

	public Endpoint(int id) {
		cachesLatency = new HashMap<>();
		videoRequests = new HashMap<>();
		
		this.id = id;
	}

}
