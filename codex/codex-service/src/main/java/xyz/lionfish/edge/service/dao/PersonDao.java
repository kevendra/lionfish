package xyz.lionfish.edge.service.dao;

import java.util.List;

import xyz.lionfish.edge.service.domain.Person;
import me.parakh.core.service.dao.ObjectifyDao;

public interface PersonDao extends ObjectifyDao<Person>{

	public List<Person> findByDeviceId(String deviceId);

}