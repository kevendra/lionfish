package xyz.lionfish.edge.service.dao.impl;

import java.util.List;

import me.parakh.core.service.dao.impl.ObjectifyDaoImpl;

import org.springframework.stereotype.Repository;

import xyz.lionfish.edge.service.dao.PersonDao;
import xyz.lionfish.edge.service.domain.Person;

/**
 *
 * @author Kevendra Patidar
 */
@Repository
public class PersonDaoImpl extends ObjectifyDaoImpl<Person> implements PersonDao {

	/* ************************************ Public Methods ************************************ */
	public List<Person> findByDeviceId(String deviceId){
		return this.listByProperty("deviceId", deviceId);
	}

}
