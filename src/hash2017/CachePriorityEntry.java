package hash2017;

import java.util.HashMap;

import hash2017.model.Endpoint;
import hash2017.model.Video;

public class CachePriorityEntry {
	private Video video;
	private HashMap<Endpoint, Integer> endpointTimeSavingMap;
	
	
	public Video getVideo() {
		return video;
	}
	
	public void setVideo(Video video) {
		this.video = video;
	}
	
	public HashMap<Endpoint, Integer> getEndpointTimeSavingMap() {
		return endpointTimeSavingMap;
	}
	
	public void setEndpointTimeSavingMap(HashMap<Endpoint, Integer> endpointTimeSavingMap) {
		this.endpointTimeSavingMap = endpointTimeSavingMap;
	}
}
