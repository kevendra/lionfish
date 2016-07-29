package xyz.lionfish.edge.api.endpoint;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;
import static xyz.lionfish.edge.api.util.Api.APP_ACTIVATE;
import static xyz.lionfish.edge.api.util.Api.DEVICE;
import static xyz.lionfish.edge.api.util.Api.REGISTRATION;

import javax.annotation.Resource;

import me.parakh.core.api.endpoint.AbstractCommonUtilityController;
import me.parakh.core.api.response.ApiResponse;
import me.parakh.core.dto.common.TokenDto;
import me.parakh.core.service.common.TokenService;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * The Activity controller.
 *
 * @author Kevendra Patidar
 */
@RestController
@RequestMapping(DEVICE)
public class DeviceController extends AbstractCommonUtilityController {

	/* ************************************ Static Fields ************************************ */
	//private static final Logger LOG = LoggerFactory.getLogger(DeviceController.class);

	/* ************************************ Instance Fields ************************************ */
	@Resource
	private TokenService tokenService;

	/* ************************************ Public Methods ************************************ */
//	@RequestMapping(value=REGISTRATION, method= GET)
//	public ApiResponse pushNotificationRegistration(@RequestParam final String deviceId, @RequestParam final String token){
	@RequestMapping(value=REGISTRATION, method= POST)
	public ApiResponse pushNotificationRegistration(@RequestBody final TokenDto tokenDto){
		tokenService.save(tokenDto);
		return this.successResponse(null);
	}
	/**
	 * /api/device/app-activate.json?activationCode=xxxxx
	 * 
	 */
	@RequestMapping(value=APP_ACTIVATE, method= GET)
	public ApiResponse pushNotificationRegistration(@RequestParam final Long activationCode){
		if(activationCode != 741313l){//432217l 374891l
			return this.errorResponse(null, "msg.error.app.activate");
		}
		return this.successResponse(null);
	}
}
