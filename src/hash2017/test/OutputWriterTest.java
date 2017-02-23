package hash2017.test;

import org.junit.Test;

import hash2017.OutputWriter;
import hash2017.model.Cache;
import hash2017.model.Endpoint;
import hash2017.model.Structure;
import hash2017.model.Video;

public class OutputWriterTest {

	@Test
	public void test() {
		Cache[] caches = new Cache[3];
		Endpoint endpoint = new Endpoint(0);
		Video video0 = new Video(0,100);
		Video video1 = new Video(1,100);
		Video video2 = new Video(0,100);
		Video video3 = new Video(1,100);
		caches[0] = new Cache(0,500);
		caches[1] = new Cache(1,500);
		caches[2] = new Cache(2,500);
		caches[0].insertPriorityQueueEntry(endpoint, video2, 100);
		caches[1].insertPriorityQueueEntry(endpoint, video3, 100);
		caches[1].insertPriorityQueueEntry(endpoint, video1, 100);
		caches[2].insertPriorityQueueEntry(endpoint, video0, 100);
		caches[2].insertPriorityQueueEntry(endpoint, video1, 100);
		
		Structure structure = new Structure();
		structure.caches = caches;
		
		OutputWriter.writeOutput(structure);
	}

}
