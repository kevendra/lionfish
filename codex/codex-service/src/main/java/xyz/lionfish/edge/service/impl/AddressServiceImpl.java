package xyz.lionfish.edge.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import me.parakh.core.model.converter.ModelMapper;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import xyz.lionfish.edge.dto.common.AddressDto;
import xyz.lionfish.edge.service.common.AddressService;
import xyz.lionfish.edge.service.dao.AddressDao;
import xyz.lionfish.edge.service.domain.Address;

/**
 *
 * @author Kevendra Patidar
 */
@Repository
public class AddressServiceImpl implements AddressService {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(AddressServiceImpl.class);

	/* ************************************ Instance Fields ************************************ */
	@Resource
	private AddressDao addressDao;
	@Resource
	protected ModelMapper modelMapper;

	/* ************************************ Public Methods ************************************ */
	@Override
	public Map<Long, AddressDto> fetch(final List<Long> ids){
		Map<Long, Address> address = this.addressDao.get(ids);
		if(CollectionUtils.isEmpty(address)){
			return null;
		}
		return map(address);
	}
	@Override
	public AddressDto fetch(final Long id){
		Address address = this.addressDao.get(id);
		if(null == address){
			LOG.debug("address not found");
			return null;
		}
		return this.modelMapper.map(address, AddressDto.class);
	}
	@Override
	public void save(final AddressDto addressVo){
		Address address = this.modelMapper.map(addressVo, Address.class);
		this.addressDao.putAsync(address);
	}
	@Override
	public AddressDto saveSync(final AddressDto addressVo){
		Address address = this.modelMapper.map(addressVo, Address.class);
		address.setId(this.addressDao.put(address).getId());
		return this.modelMapper.map(address, AddressDto.class);
	}	
	/* ************************************ Private Methods ************************************ */
	private Map<Long, AddressDto> map(Map<Long, Address> address) {
		Map<Long, AddressDto> addressMap = new HashMap<Long, AddressDto>();
		for(Entry<Long, Address> entry : address.entrySet()){
			addressMap.put(entry.getKey(), this.modelMapper.map(entry.getValue(), AddressDto.class));
		}
		return addressMap;
	}

}