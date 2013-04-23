package net.vksn.ecm.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.TreeSet;

import net.vksn.sitemap.model.SitemapItem;

import org.junit.Test;


public class OrganizatorTest {

	@Test
	public void testMoveFromFirstToLast() {
		TreeSet<SitemapItem> items = new TreeSet<SitemapItem>();
		SitemapItem first = new SitemapItem();
		first.setPagePosition(0);
		first.setName("first");
		items.add(first);
		
		SitemapItem second = new SitemapItem();
		second.setPagePosition(1);
		second.setName("second");
		items.add(second);
		
		SitemapItem last = new SitemapItem();
		last.setPagePosition(2);
		last.setName("last");
		items.add(last);
		
		new Organizator().updatePositions(items, 0, 2, first);
		assertEquals("first", items.last().getName());
		assertEquals("second", items.first().getName());
		assertTrue(items.contains(last));
		assertTrue(items.contains(first));
		assertTrue(items.contains(second));
		
		assertEquals(2, first.getPagePosition());
		assertEquals(0, second.getPagePosition());
		assertEquals(1, last.getPagePosition());
	}
	
	@Test
	public void testMoveFromLastToFirst() {
		TreeSet<SitemapItem> items = new TreeSet<SitemapItem>();
		SitemapItem first = new SitemapItem();
		first.setPagePosition(0);
		first.setName("first");
		items.add(first);
		
		SitemapItem second = new SitemapItem();
		second.setPagePosition(1);
		second.setName("second");
		items.add(second);
		
		SitemapItem last = new SitemapItem();
		last.setPagePosition(2);
		last.setName("last");
		items.add(last);
		
		new Organizator().updatePositions(items, 2, 0, last);
		assertEquals(0, last.getPagePosition());
		assertEquals("last", items.first().getName());
		assertEquals("second", items.last().getName());
		assertTrue(items.contains(last));
		assertTrue(items.contains(first));
		assertTrue(items.contains(second));
		
		assertEquals(1, first.getPagePosition());
		assertEquals(2, second.getPagePosition());
		assertEquals(0, last.getPagePosition());
	}

	@Test
	public void testMoveFromSecondToLast() {
		TreeSet<SitemapItem> items = new TreeSet<SitemapItem>();
		SitemapItem first = new SitemapItem();
		first.setPagePosition(0);
		first.setName("first");
		items.add(first);
		
		SitemapItem second = new SitemapItem();
		second.setPagePosition(1);
		second.setName("second");
		items.add(second);
		
		SitemapItem last = new SitemapItem();
		last.setPagePosition(2);
		last.setName("last");
		items.add(last);
		
		new Organizator().updatePositions(items, 1, 2, second);
		assertEquals(2, second.getPagePosition());
		assertEquals("second", items.last().getName());
		assertEquals("first", items.first().getName());
		assertTrue(items.contains(last));
		assertTrue(items.contains(first));
		assertTrue(items.contains(second));
		
		assertEquals(0, first.getPagePosition());
		assertEquals(2, second.getPagePosition());
		assertEquals(1, last.getPagePosition());
	}
	
	@Test
	public void testMoveFromSecondToFirst() {
		TreeSet<SitemapItem> items = new TreeSet<SitemapItem>();
		SitemapItem first = new SitemapItem();
		first.setPagePosition(0);
		first.setName("first");
		items.add(first);
		
		SitemapItem second = new SitemapItem();
		second.setPagePosition(1);
		second.setName("second");
		items.add(second);
		
		SitemapItem last = new SitemapItem();
		last.setPagePosition(2);
		last.setName("last");
		items.add(last);
		
		new Organizator().updatePositions(items, 1, 0, second);
		assertEquals(0, second.getPagePosition());
		assertEquals("second", items.first().getName());
		assertEquals("last", items.last().getName());
		assertTrue(items.contains(last));
		assertTrue(items.contains(first));
		assertTrue(items.contains(second));
	
		assertEquals(1, first.getPagePosition());
		assertEquals(0, second.getPagePosition());
		assertEquals(2, last.getPagePosition());
	}
	
	
	@Test
	public void testMoveSecondToThird() {
		TreeSet<SitemapItem> items = new TreeSet<SitemapItem>();
		SitemapItem first = new SitemapItem();
		first.setPagePosition(0);
		first.setName("first");
		items.add(first);
		
		SitemapItem second = new SitemapItem();
		second.setPagePosition(1);
		second.setName("second");
		items.add(second);
		
		SitemapItem third = new SitemapItem();
		third.setPagePosition(2);
		third.setName("third");
		items.add(third);
		
		SitemapItem last = new SitemapItem();
		last.setPagePosition(3);
		last.setName("last");
		items.add(last);
		
		new Organizator().updatePositions(items, 1, 2, second);
		assertEquals("first", items.first().getName());
		assertEquals("last", items.last().getName());
		
		assertTrue(items.contains(first));
		assertTrue(items.contains(third));
		assertTrue(items.contains(second));
		assertTrue(items.contains(last));
		
		assertEquals(0, first.getPagePosition());
		assertEquals(1, third.getPagePosition());
		assertEquals(2, second.getPagePosition());
		assertEquals(3, last.getPagePosition());
	}
	
	@Test
	public void testMoveSecondToFourth() {
		TreeSet<SitemapItem> items = new TreeSet<SitemapItem>();
		SitemapItem first = new SitemapItem();
		first.setPagePosition(0);
		first.setName("first");
		items.add(first);
		
		SitemapItem second = new SitemapItem();
		second.setPagePosition(1);
		second.setName("second");
		items.add(second);
		
		SitemapItem third = new SitemapItem();
		third.setPagePosition(2);
		third.setName("third");
		items.add(third);
		
		SitemapItem fourth = new SitemapItem();
		fourth.setPagePosition(3);
		fourth.setName("fourth");
		items.add(fourth);
		
		SitemapItem last = new SitemapItem();
		last.setPagePosition(4);
		last.setName("last");
		items.add(last);
		
		new Organizator().updatePositions(items, 1, 3, second);
		assertEquals("first", items.first().getName());
		assertEquals("last", items.last().getName());
		
		assertTrue(items.contains(first));
		assertTrue(items.contains(third));
		assertTrue(items.contains(fourth));
		assertTrue(items.contains(second));
		assertTrue(items.contains(last));
		
		assertEquals(0, first.getPagePosition());
		assertEquals(1, third.getPagePosition());
		assertEquals(2, fourth.getPagePosition());
		assertEquals(3, second.getPagePosition());
		assertEquals(4, last.getPagePosition());
	}
	
	@Test
	public void testMoveFourthToSecond() {
		TreeSet<SitemapItem> items = new TreeSet<SitemapItem>();
		SitemapItem first = new SitemapItem();
		first.setPagePosition(0);
		first.setName("first");
		items.add(first);
		
		SitemapItem second = new SitemapItem();
		second.setPagePosition(1);
		second.setName("second");
		items.add(second);
		
		SitemapItem third = new SitemapItem();
		third.setPagePosition(2);
		third.setName("third");
		items.add(third);
		
		SitemapItem fourth = new SitemapItem();
		fourth.setPagePosition(3);
		fourth.setName("fourth");
		items.add(fourth);
		
		SitemapItem last = new SitemapItem();
		last.setPagePosition(4);
		last.setName("last");
		items.add(last);
		
		new Organizator().updatePositions(items, 3, 1, fourth);
		assertEquals("first", items.first().getName());
		assertEquals("last", items.last().getName());
		
		assertTrue(items.contains(first));
		assertTrue(items.contains(fourth));
		assertTrue(items.contains(second));
		assertTrue(items.contains(third));
		assertTrue(items.contains(last));
		
		assertEquals(0, first.getPagePosition());
		assertEquals(1, fourth.getPagePosition());
		assertEquals(2, second.getPagePosition());
		assertEquals(3, third.getPagePosition());
		assertEquals(4, last.getPagePosition());
	}
}
