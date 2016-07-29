package xyz.lionfish.edge.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import me.parakh.core.model.converter.ModelMapper;
import me.parakh.core.service.dao.SocketDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import xyz.lionfish.edge.dto.common.PersonDto;
import xyz.lionfish.edge.service.common.PersonService;
import xyz.lionfish.edge.service.dao.PersonDao;
import xyz.lionfish.edge.service.domain.Person;

/**
 *
 * @author Kevendra Patidar
 */
@Repository
public class PersonServiceImpl implements PersonService {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(PersonServiceImpl.class);

	/* ************************************ Instance Fields ************************************ */
	@Resource
	private PersonDao personDao;
	@Resource
	private SocketDao socketDao;
	@Resource
	private  ModelMapper modelMapper;
	
	/* ************************************ Public Methods ************************************ */
	@Override
	public PersonDto getCustomer(String deviceId){
		List<Person> persons = personDao.findByDeviceId(deviceId);
		if(CollectionUtils.isEmpty(persons)){
			return null;
		}
		return modelMapper.map(persons.get(0), PersonDto.class);
	}
	@Override
	public PersonDto getCustomer(Long customerId){
		Person person = personDao.get(customerId);
		if(null == person){
			return null;
		}
		return modelMapper.map(person, PersonDto.class);
	}
	@Override
	public PersonDto save(PersonDto personVo){
		//only one person per deviceID allowed //double hit problem at the time of registration
		if(null == personVo.getId()){
			List<Person> persons = personDao.findByDeviceId(personVo.getDeviceId());
			if(! CollectionUtils.isEmpty(persons)){
				if(persons.size() > 1){
					LOG.error("multiple people for device id {} only one person per deviceID allowed", personVo.getDeviceId());
				}
				Person person = persons.get(0);
				personVo.setId(person.getId());
			}
		}
		Person entity = modelMapper.map(personVo, Person.class);
		Long personId = personDao.put(entity).getId();
		personVo.setId(personId);
		return personVo;
	}
	@Override
	public Map<Long, PersonDto> get(List<Long> personIds){
		Map<Long, Person> persons = personDao.get(personIds);
		if(CollectionUtils.isEmpty(persons)){
			return null;
		}
		Map<Long, PersonDto> personMap = new HashMap<Long, PersonDto>();
		Person person;
		for(Entry<Long, Person> entry : persons.entrySet()){
			person = entry.getValue();
			personMap.put(entry.getKey(), modelMapper.map(person, PersonDto.class));
		}
		return personMap;
	}
	@Override
	public List<PersonDto> getEmployee(final List<Long> siteIds){
		
		List<Long> personIds = new ArrayList<Long>();
		
		Map<Long, PersonDto> persons = this.get(personIds);
		return new ArrayList<PersonDto>(persons.values());
	}
	
	@Override
	public String getDeviceId(final Long personId){
		Person person = personDao.get(personId);
		if(null == person){
			return null;
		}
		return person.getDeviceId();
	}
	@Override
	public List<String> getDeviceIds(final List<Long> personIds){
		Map<Long, Person> persons = personDao.get(personIds);
		if(CollectionUtils.isEmpty(persons)){
			return null;
		}		
		List<String> deviceIds = new ArrayList<String>();
		for(Person person : persons.values()){
			deviceIds.add(person.getDeviceId());
		}
		return deviceIds;
	}
}