package hash2017.model;

import java.util.HashMap;
import java.util.List;

public class Structure {
	public Cache[] caches;
	public Endpoint[] endpoints;
	public Video[] videos;
	
	public Integer[][] latenzy;
	
	public HashMap<Endpoint, HashMap<Video, List<Cache>>> cacheHistory = new HashMap<>();

}
