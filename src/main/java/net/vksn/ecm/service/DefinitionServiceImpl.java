package net.vksn.ecm.service;

import java.util.List;

import net.vksn.ecm.dao.TilesDefinitionDAO;
import net.vksn.ecm.model.TilesDefinition;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("definitionService")
public class DefinitionServiceImpl implements DefinitionService {

	@Autowired
	private TilesDefinitionDAO definitionDAO;
	
	@Override
	public List<TilesDefinition> getDefinitions() {
		List<TilesDefinition> definitions = definitionDAO.getTilesDefinitions();
		return definitions;
	}
	
	

}
