package xyz.lionfish.edge.service.impl;

import static me.parakh.core.model.type.AppDataType.ST;

import java.util.List;

import javax.annotation.Resource;

import me.parakh.core.service.common.DataCleanUpCoreService;
import me.parakh.core.service.common.SocketService;
import me.parakh.core.service.dao.AppDataDao;
import me.parakh.core.service.dao.SocketDao;
import me.parakh.core.service.dao.TokenDao;
import me.parakh.core.service.domain.AppData;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import xyz.lionfish.edge.service.common.DataCleanUpService;
import xyz.lionfish.edge.service.dao.AddressDao;
import xyz.lionfish.edge.service.dao.PersonDao;
import xyz.lionfish.edge.service.domain.Person;

/**
 *
 * @author Kevendra Patidar
 */
@Repository
public class DataCleanUpServiceImpl implements DataCleanUpService {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(DataCleanUpServiceImpl.class);

	/* ************************************ Instance Fields ************************************ */
	@Resource
	private AppDataDao appDataDao;
	@Resource
	private PersonDao personDao;	
	@Resource
	private SocketService socketService;
	@Resource
	private TokenDao tokenDao;
	
	@Resource
	private AddressDao addressDao;
	@Resource
	private SocketDao socketDao;
	@Resource
	private DataCleanUpCoreService dataCleanUpCoreService;
	
	/* ************************************ Public Methods ************************************ */
	
	@Override
	public void cleanAll(String deviceId){
		dataCleanUpCoreService.cleanAllCore(deviceId);
		
		List<Person> persons = personDao.findByDeviceId(deviceId);

	    if(CollectionUtils.isEmpty(persons)){
	    	return;
	    }
	    
	    Person person = persons.get(0);
	    Long personId = person.getId();

	    tokenDao.delete(deviceId);
	    LOG.info("delete - token");
	    
	    List<AppData> appDatas = appDataDao.findByPersonId(personId, ST);//personId
		if( ! CollectionUtils.isEmpty(appDatas)){
			appDataDao.deleteAll(appDatas);
		    LOG.info("delete - appDatas");
	    }
		
	    personDao.deleteAll(persons);
	    LOG.info("delete - persons");
	}
	
	/* ************************************ Private Methods ************************************ */
//	private String getAppDataId(Long personId, AppDataType type) {
//		return personId + "-" + type;
//	}
}
