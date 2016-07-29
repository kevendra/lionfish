package xyz.lionfish.edge.service.common;

import java.util.List;
import java.util.Map;

import xyz.lionfish.edge.dto.common.PersonDto;


/**
 *
 * @author Kevendra Patidar
 */
public interface PersonService {

	public PersonDto getCustomer(String deviceId);

	public PersonDto getCustomer(Long customerId);

	public PersonDto save(PersonDto person);
	
	public Map<Long, PersonDto> get(List<Long> personIds);
	
	public List<PersonDto> getEmployee(final List<Long> siteIds);

	
	public String getDeviceId(final Long personId);

	public List<String> getDeviceIds(final List<Long> personIds);
}