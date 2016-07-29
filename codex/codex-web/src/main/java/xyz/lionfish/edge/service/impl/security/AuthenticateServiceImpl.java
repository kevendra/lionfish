package xyz.lionfish.edge.service.impl.security;

import javax.annotation.Resource;

import me.parakh.core.dto.common.DeviceDto;
import me.parakh.core.dto.common.UserDto;
import me.parakh.core.security.jwt.JwtClaimEdge;
import me.parakh.core.service.common.DeviceService;
import me.parakh.core.service.common.UserService;
import me.parakh.core.service.security.AuthenticateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * 
 * @author Kevendra Patidar
 */
//@Service
public class AuthenticateServiceImpl implements AuthenticateService {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticateServiceImpl.class);

	/* ************************************ Instance Fields ************************************ */
	@Resource
	private DeviceService deviceService;
	@Resource
	private UserService userService;
	
    
	/* ************************************ Public Methods ************************************ */
	@Override
	public boolean authenticate(final String userName, final String password, final JwtClaimEdge claims) {
		LOG.error("FIXME userName: {} password: {}",userName, password);
		/*
		 * add Usr if not found for current user
		 * update device with this Usr.id (new owner of this device) 
		 */
		//deviceService.get
		//Long uid = claims.getUid();//userName
		String did = claims.getDid();//deviceId
		DeviceDto deviceDto = deviceService.fetch(did);
		UserDto userDto = userService.fetch(userName);
		Long uid;
		if(null == userDto){
			userDto = new UserDto();
			if(StringUtils.isEmpty(userName)){
				userDto.setUsername(did);
			}else{
				userDto.setUsername(userName);
			}
			userDto.setPassword(password);
			uid = userService.saveSync(userDto);
		}else{
			uid = userDto.getId();
		}
		if(null != deviceDto){
			deviceDto.setUid(uid);
			deviceService.save(deviceDto);
		}
		claims.setUid(uid);
		claims.setFirstName("test firstName");

		return true;
	}
	/**
	 * IAM token verify
	 * 
	 * authenticateService.tokenVerify(claims)
	 * only iamToken require to pass, but this module not aware against which key iamToken store
	 */	
	@Override
	public boolean tokenVerify(final String iamToken) {
		return true;
	}
	/**
	 * Logouts the user - token is invalidated/forgotten. 
	 */
	@Override
	public void tokenRevoke(final String iamToken){
		LOG.debug("tokenRevoke token {}", iamToken);
		  
    }
	
	/* ************************************ Private Methods ************************************ */
	
	
}
