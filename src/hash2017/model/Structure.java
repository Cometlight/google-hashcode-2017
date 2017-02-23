package hash2017.model;

public class Structure {
	public Cache[] caches;
	public Endpoint[] endpoints;
	public Video[] videos;

	public Structure(Cache[] caches, Endpoint[] endpoints, Video[] videos) {
		this.caches = caches;
		this.endpoints = endpoints;
		this.videos = videos;
	}

}
