package net.vksn.ecm.dao.impl;

import java.util.List;

import net.vksn.ecm.dao.TilesDefinitionDAO;
import net.vksn.ecm.model.TilesDefinition;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("tilesDefinitionDAO")
public class TilesDefinitionDAOImpl implements TilesDefinitionDAO {
	private static final Logger log = LogManager
			.getLogger(TilesDefinitionDAOImpl.class);
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<TilesDefinition> getTilesDefinitions() {
		log.entry();
		Session session = sessionFactory.openSession();
		Criteria criteria = session
				.createCriteria(TilesDefinition.class);
		criteria.add(Restrictions.eq("systemDefinition", false));
		@SuppressWarnings("unchecked")
		List<TilesDefinition> definitions = criteria.list();

		log.exit();
		return definitions;
	}
}
