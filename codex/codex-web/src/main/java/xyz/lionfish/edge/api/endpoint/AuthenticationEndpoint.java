package xyz.lionfish.edge.api.endpoint;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static xyz.lionfish.edge.api.util.Api.AUTH_TOKEN;
import static xyz.lionfish.edge.api.util.Api.TOKEN_GENERATE;
import static xyz.lionfish.edge.api.util.Api.TOKEN_GENERATE_SHORT_LIVED;
import static xyz.lionfish.edge.api.util.Api.TOKEN_LIST;
import static xyz.lionfish.edge.api.util.Api.TOKEN_REVOKE;
import static xyz.lionfish.edge.api.util.Api.TOKEN_VALIDATE;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import me.parakh.core.api.endpoint.AbstractCommonUtilityController;
import me.parakh.core.api.response.ApiResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cdk.core.security.dto.OAuthListTokenDto;
import com.cdk.core.security.dto.OAuthTokenDto;
import com.cdk.core.security.dto.OAuthValidateDto;
import com.cdk.core.security.dto.UserCredentialDto;
import com.cdk.core.security.service.AuthTestType;
import com.cdk.core.security.service.AuthenticationServiceProvider;
import com.cdk.core.security.service.CobaltUserService;
import com.cdk.core.security.service.IdentityService;
import com.cdk.core.security.service.SiteminderService;

/**
 * Auhentication Service provider
 * 
 * @author Kevendra Patidar
 */
@RestController
@RequestMapping(AUTH_TOKEN)
public class AuthenticationEndpoint extends AbstractCommonUtilityController {

	/* ************************************ Static Fields ************************************ */
    private static Logger LOG = LoggerFactory.getLogger(AuthenticationEndpoint.class);
    
	/* ************************************ Instance Fields ************************************ */    
    @Resource
    private SiteminderService siteminderService;
    @Resource
    private IdentityService identityService;
    @Resource
    private AuthenticationServiceProvider authenticationServiceProvider;
    @Resource
    private CobaltUserService cobaltUserService;

	/* ************************************ Public Methods ************************************ */
    @RequestMapping(value="test-user-service", method=GET)
    public ApiResponse testUserService(){
    	Map<String, Object> data = new HashMap<String, Object>();
    	data.put("textFromUserService", cobaltUserService.test());
    	return successResponse(data);
    }
//    @RequestMapping(value="find-cobalt-user", method=GET)
//    public ApiResponse getUser(@RequestParam String userName){
//    	Map<String, Object> data = new HashMap<String, Object>();
//    	try {
//			data.put("getUser", cobaltUserService.getUser(userName));
//		} catch (ServiceException e) {
//			e.printStackTrace();
//		}
//    	return successResponse(data);
//    }
    /**
     * Test siteminderService and identityService in one go
     */
    @RequestMapping(value="test", method=GET)
    public ApiResponse test(@RequestParam AuthTestType type, 
    		@RequestParam(required=false) String token,
    		@RequestParam(required=false) String userName,
    		@RequestParam(required=false) String deviceId,
    		@RequestParam(required=false) String deviceType
    		){
    	Map<String, Object> data = authenticationServiceProvider.test(type, token, userName, deviceId, deviceType);
		return successResponse(data);
	}    
	/**
     * Authenticate the user and create long lived oauth token
     *
     * Sample JSON Input for Authentication api
        {
        "id": "myid",
        "password": "secret-password",
        "device": "iPhone"
        }
     * 
     * "deviceId": "52548590-9962-4277-9C37-95C8A8A27B7C" OR random if null "-1510237893"
     * "deviceType": "MOBILE" OR "ANDROID"
     */
    @RequestMapping(value=TOKEN_GENERATE, method=GET)
    public ApiResponse authenticateAndGenLongLivedToken(
    		@RequestParam final String userName,
    		@RequestParam final String password, 
			@RequestParam(required=false) final String deviceId, 
			@RequestParam(required=false) String deviceType, 
			final HttpServletResponse response) throws Exception {
        Assert.notNull(userName, "User Credentials cannot be - userName cannot be NULL");
        Assert.notNull(password, "User Credentials cannot be - Password cannot be NULL");
		try {
			boolean genSortLivedToken = false;//gen Long lived token
	       	return getToken(userName, password, deviceId, deviceType, genSortLivedToken);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
	       	return errorResponse();
		}  
    }
	
    /**
     * Create oauth token, the user is already authenticated.  
     *
     * Sample JSON Input for Authentication api
        {
        "id": "myid",
        "device": "browser"
        }
     */
    @RequestMapping(value=TOKEN_GENERATE_SHORT_LIVED, method=GET)
    public ApiResponse getShortLivedToken(
    		@RequestParam final String userName,
			@RequestParam(required=false) String deviceId, 
			@RequestParam(required=false) String deviceType,
			final HttpServletResponse response) throws Exception {
        Assert.notNull(userName, "User Credentials cannot be - Username cannot be NULL");
		try {
			boolean genSortLivedToken = true;//Sort lived token
	       	return getToken(userName, null, deviceId, deviceType, genSortLivedToken);
		} catch (Exception e) {
			response.sendError(HttpServletResponse.SC_FORBIDDEN);
	       	return errorResponse();
		}  
    }
    @RequestMapping(value=TOKEN_REVOKE, method=DELETE)
    public ApiResponse tokenRevoke(@RequestParam final String token){
		/** Logouts the user - token is invalidated/forgotten. */
		try {
			identityService.deleteToken(token);
			return successResponse();
		} catch (Exception e) {
			LOG.warn("token is invalidated/forgotten");
	       	return errorResponse();
		}  
    }
    @RequestMapping(value=TOKEN_VALIDATE, method=GET)
    public ApiResponse tokenValidate(@RequestParam final String token, final HttpServletResponse response) throws Exception{
    	try {
    		//406 Not Acceptable  500 - no suitable HttpMessageConverter found  	
    		OAuthValidateDto oAuthValidate = identityService.validateToken(token);
    		Map<String, Object> data = new HashMap<String, Object>();    		
    		data.put("token", oAuthValidate);
    		return successResponse(data);
	    } catch (Exception e) {
	    	response.sendError(HttpServletResponse.SC_UNAUTHORIZED);	    	
	       	return errorResponse();
		} 
    }
    @RequestMapping(value=TOKEN_LIST, method=GET)    
    public ApiResponse tokenList(@RequestParam final String userName){
		OAuthListTokenDto oauthListTokenResponse = identityService.getAllTokens(userName);
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("tokens", oauthListTokenResponse);
		return successResponse(data);
    }
	
	/* ************************************ Private Methods ************************************ */
    private ApiResponse getToken(final String userName, final String password, String deviceId, String deviceType, boolean genSortLivedToken){
    	UserCredentialDto userCredentials = new UserCredentialDto(userName, password, deviceId, deviceType);
       	OAuthTokenDto oAuthTokenDto = authenticationServiceProvider.getToken(userCredentials, genSortLivedToken);
    	Map<String, Object> data = new HashMap<String, Object>();
    	data.put("token", oAuthTokenDto);
       	return successResponse(data);
    }
       
}
