package xyz.lionfish.edge.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import me.parakh.core.api.endpoint.RegistrationEndpoint;
import me.parakh.core.dto.common.AppDataDto;
import me.parakh.core.service.app.DisplayAndPresentationService;
import me.parakh.core.service.common.AppDataService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import xyz.lionfish.edge.api.util.Navigation;

/**
 * 
 * @author Kevendra Patidar
 */
@Service
public class DisplayAndPresentationServiceImpl implements DisplayAndPresentationService {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(RegistrationEndpoint.class);

	/* ************************************ Instance Fields ************************************ */
	@Resource
	private AppDataService appDataService;

	/* ************************************ Public Methods ************************************ */
	/**
	 * @return setting if anonymous usr(user) available
	 */
	@Override
	public Map<String, Object> getSetting(Long uid) {
		LOG.debug("get setting");
		
		if(null == uid){
			return null;
		}
		/*
		 * First time registration return null appData
		 */
		List<AppDataDto> appData = appDataService.getAppData(uid);			

		//androidAppVersion = AppConstant.PARKING_ANDROID_VER;
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("uid", uid);
		data.put("navigation", Navigation.navigationCustomer()); //This will come later
		data.put("appData", appData);
		
		return data;
	}

}
