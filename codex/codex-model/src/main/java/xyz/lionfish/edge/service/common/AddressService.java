package xyz.lionfish.edge.service.common;

import java.util.List;
import java.util.Map;

import xyz.lionfish.edge.dto.common.AddressDto;


/**
 *
 * @author Kevendra Patidar
 */
public interface AddressService {

	public void save(AddressDto addressVo);

	public AddressDto saveSync(final AddressDto addressVo);
	
	public AddressDto fetch(final Long id);
	
	public Map<Long, AddressDto> fetch(final List<Long> ids);

}