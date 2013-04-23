package net.vksn.ecm.controllers.form;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import net.vksn.ecm.model.TilesDefinition;
import net.vksn.sitemap.model.Sitemap;
import net.vksn.sitemap.model.SitemapItem;

public class SitemapItemForm {

	private SitemapItem sitemapItem;
	private Sitemap sitemap;
	private SitemapItem parent;
	private List<TilesDefinition> templates;
	private Set<SitemapItem> sitemapItems;
	private Set<SitemapItem> siblings;
	private Integer newPagePosition;

	public SitemapItem getSitemapItem() {
		return sitemapItem;
	}

	public void setSitemapItem(SitemapItem sitemapItem) {
		this.sitemapItem = sitemapItem;
	}

	public List<TilesDefinition> getTemplates() {
		if (this.templates == null) {
			this.templates = new ArrayList<TilesDefinition>();
		}
		return templates;
	}

	public void setTemplates(List<TilesDefinition> templates) {
		this.templates = templates;
	}

	public Set<SitemapItem> getSitemapItems() {
		if (this.sitemapItems == null) {
			this.sitemapItems = new TreeSet<SitemapItem>();
		}
		return sitemapItems;
	}

	public void setSitemapItems(Set<SitemapItem> sitemapItems) {
		this.sitemapItems = sitemapItems;
	}

	public Set<SitemapItem> getSiblings() {
		return siblings;
	}

	public void setSiblings(TreeSet<SitemapItem> siblings) {
		this.siblings = siblings;
	}

	public SitemapItem getParent() {
		return parent;
	}

	public void setParent(SitemapItem parent) {
		this.parent = parent;
	}

	public Sitemap getSitemap() {
		return sitemap;
	}

	public void setSitemap(Sitemap sitemap) {
		this.sitemap = sitemap;
	}

	public int getNewPagePosition() {
		if(this.newPagePosition == null) {
			this.newPagePosition = sitemapItem.getPagePosition();
		}
		return newPagePosition;
	}

	public void setNewPagePosition(int newPagePosition) {
		this.newPagePosition = newPagePosition;
	}

}
