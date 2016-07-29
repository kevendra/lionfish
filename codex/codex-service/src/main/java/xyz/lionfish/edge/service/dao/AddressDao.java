package xyz.lionfish.edge.service.dao;

import xyz.lionfish.edge.service.domain.Address;
import me.parakh.core.service.dao.ObjectifyDao;


/**
 *
 * @author Kevendra Patidar
 */
public interface AddressDao extends ObjectifyDao<Address> {

	public Long saveSync(Address address);
	
}
