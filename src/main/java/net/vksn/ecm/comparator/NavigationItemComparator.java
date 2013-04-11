package net.vksn.ecm.comparator;

import java.util.Comparator;

import net.vksn.sitemap.model.SitemapItem;

public class NavigationItemComparator implements Comparator<SitemapItem> {

	@Override
	public int compare(SitemapItem first, SitemapItem second) {
		if(first.getPagePosition() > second.getPagePosition()) {
			return 1;
		}
		else if (first.getPagePosition() < second.getPagePosition()) {
			return -1;
		}
		else {
			return 0;
		}
	}

}
