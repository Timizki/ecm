package org.apache.tiles.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tiles.request.ApplicationContext;
import org.apache.tiles.startup.AbstractTilesInitializer;

public class ECMTilesInitializer extends AbstractTilesInitializer {
	public static final Logger log = LogManager.getLogger(ECMTilesInitializer.class);

	private AbstractTilesContainerFactory tilesContainerFactory;
	private String containerKey;
	
	public void setContainerKey(String containerKey) {
		this.containerKey = containerKey;
	}
	
	public void setTilesContainerFactory(
			AbstractTilesContainerFactory tilesContainerFactory) {
		this.tilesContainerFactory = tilesContainerFactory;
	}
	@Override
	protected String getContainerKey(ApplicationContext applicationContext) {
		return containerKey;
	}
	@Override
	protected AbstractTilesContainerFactory createContainerFactory(
			ApplicationContext context) {
		log.entry();
		if(this.tilesContainerFactory == null) {
			return new ECMTilesContainerFactory();
		}
		log.exit();
		return tilesContainerFactory;
	}
}
