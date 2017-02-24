package hash2017.model;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

import hash2017.CachePriorityEntry;

public class Cache {
	public Integer id;
	public Integer capacity; // in MB
	private PriorityQueue<CachePriorityEntry> priorityQueue;

	public Cache(Integer id, Integer capacity) {
		this.id = id;
		this.capacity = capacity;
		priorityQueue = new PriorityQueue<>(new Comparator<CachePriorityEntry>() {
			@Override
			public int compare(CachePriorityEntry entry1, CachePriorityEntry entry2) {
				return entry2.getTotalTimeSavings()/entry2.getVideo().size - entry1.getTotalTimeSavings()/entry1.getVideo().size; // TODO maybe reverse if wrong
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
		
		// Entry doesn't exist yet
		CachePriorityEntry newEntry = new CachePriorityEntry(video);
		newEntry.addToHashMap(endpoint, timeSaving);
		priorityQueue.add(newEntry);
	}
	
	public void removeFromPriorityQueue(Endpoint endpoint, Video video) {
		for (CachePriorityEntry entry : priorityQueue) {
			if (entry.getVideo().id == video.id) {
				entry.removeFromHashMap(endpoint);
				return;
			}
		}
	}
	
	public List<Video> getStoredVideos() {
		List<Video> videos = new LinkedList<>();
		Integer curSize = 0;
		for (CachePriorityEntry entry : priorityQueue) {
			if (curSize + entry.getVideo().size <= this.capacity) {
				curSize += entry.getVideo().size;
				videos.add(entry.getVideo());
			}
		}
		
		return videos;
	}
	
	public List<CachePriorityEntry> getVideosOutOfMemory() {
		List<CachePriorityEntry> outOfMemoryVideos = new LinkedList<>();
		Integer curSize = 0;
		for (CachePriorityEntry entry : priorityQueue) {
			if (curSize + entry.getVideo().size > this.capacity) {
				outOfMemoryVideos.add(entry);
			} else {
				curSize += entry.getVideo().size;
			}
		}
		
		return outOfMemoryVideos;
	}
	
	public List<CachePriorityEntry> getAndRemoveVideosOutOfMemory() {
		List<CachePriorityEntry> outOfMemoryVideos = getVideosOutOfMemory();
		for (CachePriorityEntry entry : outOfMemoryVideos) {
			priorityQueue.remove(entry);
		}
		return outOfMemoryVideos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cache other = (Cache) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
