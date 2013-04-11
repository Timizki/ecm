package net.vksn.fileservice.dao.impl;

import java.util.HashSet;
import java.util.Set;

import net.vksn.fileservice.dao.FileDAO;
import net.vksn.fileservice.model.File;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class HibernateFileDAO implements FileDAO {

	@Autowired
	private SessionFactory factory;
	
	public File getFile(FileQuery query) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(File.class);
		if(query.getFileName() != null) {
			criteria.add(Restrictions.eq("fileName", query.getFileName()));
		}
		if(query.getFileSuffix() != null) {
			criteria.add(Restrictions.eq("fileSuffix", query.getFileName()));
		}
		if(query.getFileType() != null) {
			//TODO: what to todo with file type;
		}
		
		return (File) criteria.uniqueResult();
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public File getFile(String fileName) {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(File.class);
		criteria.add(Restrictions.eq("fileName", fileName));
		return (File) criteria.uniqueResult();
	}

	@Override
	@Transactional
	public void store(File file) {
		Session session = factory.openSession();
		session.saveOrUpdate(file);		
		session.flush();
	}

	@Override
	public Set<File> getAllFiles() {
		Session session = factory.openSession();
		Criteria criteria = session.createCriteria(File.class);
		criteria.add(Restrictions.isNull("deleted"));
		return new HashSet<File>(criteria.list());
	}

}
