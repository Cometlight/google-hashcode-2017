package hash2017;

import hash2017.model.Cache;
import hash2017.model.Endpoint;
import hash2017.model.Video;

public class TimeSavingCalculator {
	public static Integer getTotalTimeSaving(Endpoint endpoint, Video video, Cache cache) {
		Integer datacenterLatency = endpoint.datacenterLatency;
		Integer cacheLatency = endpoint.cachesLatency.get(cache);
		Integer savingPerRequest = datacenterLatency - cacheLatency;
		
		Integer requestCount = endpoint.videoRequests.get(video);
		if (requestCount == null) {
			return 0;
		}
		return savingPerRequest * requestCount;
	}
}
