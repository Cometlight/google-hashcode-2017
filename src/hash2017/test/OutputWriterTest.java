package hash2017.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hash2017.OutputWriter;
import hash2017.model.Cache;
import hash2017.model.Endpoint;
import hash2017.model.Structure;
import hash2017.model.Video;

public class OutputWriterTest {
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	@Test
	public void test() {
		Cache[] caches = new Cache[3];
		Endpoint endpoint = new Endpoint(0);
		Video video0 = new Video(0,100);
		Video video1 = new Video(1,100);
		Video video2 = new Video(2,100);
		Video video3 = new Video(3,100);
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
		
		String expected = "3\n0 2\n1 3 1\n2 0 1\n";
		
		assertEquals("check output", expected, outContent.toString());
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}

}
