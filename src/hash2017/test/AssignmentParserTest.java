package hash2017.test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

import hash2017.AssignmentParser;
import hash2017.model.Structure;

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
		assertEquals("check first endpoint value", testStructure.endpoints[0].datacenterLatency, 1645);
		assertEquals("check first video value", testStructure.videos[0].size, 936);
		assertEquals("check last endpoint value", testStructure.endpoints[testStructure.endpoints.length-1].datacenterLatency, 2157);
		assertEquals("check last video value", testStructure.videos[testStructure.videos.length-1].size, 764);
		assertEquals("check last requests value", testStructure.endpoints[308].videoRequests.get(testStructure.videos[7055]).intValue(), 1947);
		assertEquals("check first requests value", testStructure.endpoints[781].videoRequests.get(testStructure.videos[9031]).intValue(), 8367);
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
