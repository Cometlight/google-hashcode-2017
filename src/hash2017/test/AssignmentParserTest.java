package hash2017.test;
import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import hash2017.AssignmentParser;
import hash2017.model.*;

public class AssignmentParserTest {

	@Test
	public void test() throws IOException {
		Structure testStructure = AssignmentParser.parseFile(new File("kittens.in"));
		assertNotNull("structure not null", testStructure);
		assertTrue("caches exist", testStructure.caches.length > 0);
		assertTrue("endpoints exist", testStructure.endpoints.length > 0);
		assertTrue("videos exist", testStructure.videos.length > 0);
		assertTrue("all caches loaded", testStructure.caches[testStructure.caches.length-1].capacity > 0);
		assertTrue("all endpoints loaded", testStructure.endpoints[testStructure.endpoints.length-1].datacenterLatency > 0);
		assertTrue("all videos loaded", testStructure.videos[testStructure.videos.length-1].size > 0);
	}
	
	@Test
	public void test1() throws IOException {
		Structure testStructure = AssignmentParser.parseFile(new File("me_at_the_zoo.in"));
		assertNotNull("structure not null", testStructure);
		assertTrue("caches exist", testStructure.caches.length > 0);
		assertTrue("endpoints exist", testStructure.endpoints.length > 0);
		assertTrue("videos exist", testStructure.videos.length > 0);
		assertTrue("all caches loaded", testStructure.caches[testStructure.caches.length-1].capacity > 0);
		assertTrue("all endpoints loaded", testStructure.endpoints[testStructure.endpoints.length-1].datacenterLatency > 0);
		assertTrue("all videos loaded", testStructure.videos[testStructure.videos.length-1].size > 0);
	}
	
	@Test
	public void test2() throws IOException {
		Structure testStructure = AssignmentParser.parseFile(new File("trending_today.in"));
		assertNotNull("structure not null", testStructure);
		assertTrue("caches exist", testStructure.caches.length > 0);
		assertTrue("endpoints exist", testStructure.endpoints.length > 0);
		assertTrue("videos exist", testStructure.videos.length > 0);
		assertTrue("all caches loaded", testStructure.caches[testStructure.caches.length-1].capacity > 0);
		assertTrue("all endpoints loaded", testStructure.endpoints[testStructure.endpoints.length-1].datacenterLatency > 0);
		assertTrue("all videos loaded", testStructure.videos[testStructure.videos.length-1].size > 0);
	}
	
	@Test
	public void test3() throws IOException {
		Structure testStructure = AssignmentParser.parseFile(new File("videos_worth_spreading.in"));
		assertNotNull("structure not null", testStructure);
		assertTrue("caches exist", testStructure.caches.length > 0);
		assertTrue("endpoints exist", testStructure.endpoints.length > 0);
		assertTrue("videos exist", testStructure.videos.length > 0);
		assertTrue("all caches loaded", testStructure.caches[testStructure.caches.length-1].capacity > 0);
		assertTrue("all endpoints loaded", testStructure.endpoints[testStructure.endpoints.length-1].datacenterLatency > 0);
		assertTrue("all videos loaded", testStructure.videos[testStructure.videos.length-1].size > 0);
	}

}
