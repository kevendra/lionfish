package xyz.lionfish.edge.service.dao.impl;


import me.parakh.core.service.dao.impl.ObjectifyDaoImpl;

import org.springframework.stereotype.Repository;

import xyz.lionfish.edge.service.dao.AddressDao;
import xyz.lionfish.edge.service.domain.Address;

import com.googlecode.objectify.Key;

/**
 *
 * @author Kevendra Patidar
 */
@Repository
public class AddressDaoImpl extends ObjectifyDaoImpl<Address> implements AddressDao {

	/* ************************************ Public Methods ************************************ */
	@Override
	public Long saveSync(final Address address) {
		Key<Address> addressKey = this.put(address);
		return addressKey.getId();
	}

}