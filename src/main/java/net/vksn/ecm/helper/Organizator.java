package net.vksn.ecm.helper;

import java.util.Iterator;
import java.util.TreeSet;

import net.vksn.sitemap.model.SitemapItem;

public class Organizator {
	
	public void updatePositions(TreeSet<SitemapItem> siblings, int oldPosition, int newPosition, SitemapItem updatedItem) {

		boolean moveDirectionToBigger = newPosition > oldPosition;
		
		for (Iterator<SitemapItem> i = siblings.iterator(); i.hasNext();) {
			SitemapItem item = i.next();
			if(item.equals(updatedItem)) {
				i.remove();
				updatedItem.setPagePosition(newPosition);
				continue;
			}

			if(moveDirectionToBigger) {
				if (item.getPagePosition() > oldPosition && item.getPagePosition() <= newPosition) {
					item.setPagePosition(item.getPagePosition() - 1);
				}
			}
			
			else {
				// 0 1 2 3 4
				// 0 3 2 1 4
				// 
				if (item.getPagePosition() < oldPosition &&item.getPagePosition() >= newPosition) {
					item.setPagePosition(item.getPagePosition() + 1);
				}
			}
		}
		siblings.add(updatedItem);
	}
}
