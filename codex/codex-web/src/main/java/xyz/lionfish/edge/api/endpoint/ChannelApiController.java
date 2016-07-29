package xyz.lionfish.edge.api.endpoint;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static xyz.lionfish.edge.api.util.Api.CHANNEL;
import static xyz.lionfish.edge.api.util.Api.TOKEN;
import io.swagger.annotations.Api;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import me.parakh.core.api.endpoint.AbstractCommonUtilityController;
import me.parakh.core.api.response.ApiResponse;
import me.parakh.core.service.common.SocketService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import xyz.lionfish.edge.service.common.PersonService;

import com.google.appengine.api.channel.ChannelService;
import com.google.appengine.api.channel.ChannelServiceFactory;

/**
 * Google Appengine Channel
 * 
 * @author Kevendra Patidar
 */
@RestController
@RequestMapping(CHANNEL)
@Api(value = "Channel", description = "Google Appengine Channel API related actions")
public class ChannelApiController extends AbstractCommonUtilityController {

	/* ************************************ Static Fields ************************************ */
	//private static final Logger LOG = LoggerFactory.getLogger(ChannelApiController.class);

	/* ************************************ Instance Fields ************************************ */
	@Resource
	private SocketService socketService;
	@Resource
	private PersonService personService;

	/* ************************************ Public Methods ************************************ */
	/**
	 * ../api/channel/token.json?deviceId=XXXXX
	 * personId or deviceId
	 * 
	 * add deviceId/personId, token and modified Date in DB
	 * if modified Date > 2 hr assume device is inactive
	 */
	@RequestMapping(value=TOKEN, method= GET)
	public ApiResponse getToken(@RequestParam final String deviceId, 
			@RequestParam final boolean isPartnerApp,
			@RequestParam(required=false) Long siteId){
		ChannelService channelService = ChannelServiceFactory.getChannelService();
		String token = channelService.createChannel(deviceId);
		
		final Map<String,Object> data = new HashMap<String, Object>();
		data.put("token", token);
		
		//FIXME
		/*
		 * find siteId for the customer if not provided
		 * TODO move from core to app specific project
		 */
//		if( ! isPartnerApp && null == siteId){
//			siteId = personService.getSiteId(deviceId);
//		}		
		
		socketService.save(deviceId, token, isPartnerApp, siteId);
		
		return successResponse(data);
	}
	
	
}
