package hash2017;

import java.util.HashMap;

import hash2017.model.Endpoint;
import hash2017.model.Video;

public class CachePriorityEntry {
	private Video video;
	private HashMap<Endpoint, Integer> endpointTimeSavingMap;
	
	private Integer totalTimeSavings = 0;
	
	public CachePriorityEntry(Video video) {
		this.video = video;
		endpointTimeSavingMap = new HashMap<>();
	}
	
	public Video getVideo() {
		return video;
	}
	
	public void setVideo(Video video) {
		this.video = video;
	}
	
	public HashMap<Endpoint, Integer> getEndpointTimeSavingMap() {
		return endpointTimeSavingMap;
	}
	
	public void addToHashMap(Endpoint endpoint, Integer timeSaving) {
		if (endpointTimeSavingMap.get(endpoint) != null) {
			return;
		}
		endpointTimeSavingMap.put(endpoint, timeSaving);
		totalTimeSavings += timeSaving;
	}
	
	public void removeFromHashMap(Endpoint endpoint) {
		Integer timeSavingOfRemovedEndpoint = endpointTimeSavingMap.remove(endpoint);	
		if (timeSavingOfRemovedEndpoint == null) {
			return;
		}
		totalTimeSavings -= timeSavingOfRemovedEndpoint;
	}
	
	public Integer getTotalTimeSavings() {	
		return totalTimeSavings;
	}
}
