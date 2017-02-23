package hash2017.model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import hash2017.CachePriorityEntry;

public class Cache {
	private Integer id;
	private Integer capacity; // in MB
	private PriorityQueue<CachePriorityEntry> priorityQueue;

	public Cache(Integer id, Integer capacity) {
		this.id = id;
		this.capacity = capacity;
		priorityQueue = new PriorityQueue<>(new Comparator<CachePriorityEntry>() {
			@Override
			public int compare(CachePriorityEntry entry1, CachePriorityEntry entry2) {
				return entry1.getTotalTimeSavings() - entry2.getTotalTimeSavings(); // TODO maybe reverse if wrong
			}
		});
	}
	
	public Integer getTimeSaving(Video video, Endpoint endpoint) {
		for (CachePriorityEntry entry : priorityQueue) {
			if (entry.getVideo().id == video.id) {
				return entry.getEndpointTimeSavingMap().get(endpoint);
			}
		}
		return 0;
	}
	
	public void insertPriorityQueueEntry(Endpoint endpoint, Video video, Integer timeSaving) {
		for (CachePriorityEntry entry : priorityQueue) {
			if (entry.getVideo().id == video.id) {
				entry.addToHashMap(endpoint, timeSaving);
				return;
			}
		}
	}
	
	public List<Video> getStoredVideos() {
		List<Video> videos = new LinkedList<>();
		Integer curSize = 0;
		for (CachePriorityEntry entry : priorityQueue) {
			if (curSize + entry.getVideo().size > this.capacity) {
				break;
			}
			curSize += entry.getVideo().size;
			videos.add(entry.getVideo());
		}
		
		return videos;
	}

}
