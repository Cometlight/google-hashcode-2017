package hash2017.model;

import java.util.HashMap;

public class Endpoint {
	public int id;
	public int datacenterLatency;
	public HashMap<Integer, Integer> cachesLatency;

	public Endpoint(int id, int datacenterLatency) {
		this.id = id;
		this.datacenterLatency = datacenterLatency;
	}

}
