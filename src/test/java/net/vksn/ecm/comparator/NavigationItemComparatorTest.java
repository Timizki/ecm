package net.vksn.ecm.comparator;

import static org.junit.Assert.assertEquals;
import net.vksn.sitemap.model.SitemapItem;

import org.junit.Test;

import com.mchange.util.AssertException;

public class NavigationItemComparatorTest {
	
	
	@Test
	public void testIsBefore() {
		SitemapItem first = new SitemapItem();
		first.setPagePosition(0);
		
		SitemapItem other = new SitemapItem();
		other.setPagePosition(1);
		
		assertEquals(-1, new NavigationItemComparator().compare(first, other));
	}

	@Test
	public void testIsAfter() {
		SitemapItem first = new SitemapItem();
		first.setPagePosition(1);
		
		SitemapItem other = new SitemapItem();
		other.setPagePosition(0);
		
		assertEquals(1, new NavigationItemComparator().compare(first, other));
	}
	
	@Test
	public void testEquals() {
		SitemapItem first = new SitemapItem();
		first.setPagePosition(0);
		
		SitemapItem other = new SitemapItem();
		other.setPagePosition(0);
		
		assertEquals(0, new NavigationItemComparator().compare(first, other));
	}
	
}
