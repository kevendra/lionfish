package xyz.lionfish.edge.service.impl.security;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import me.parakh.core.security.jwt.JwtClaimEdge;
import me.parakh.core.service.security.AuthenticateService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.cdk.core.security.dto.OAuthTokenDto;
import com.cdk.core.security.dto.OAuthValidateDto;
import com.cdk.core.security.dto.UserCdkDto;
import com.cdk.core.security.dto.UserCredentialDto;
import com.cdk.core.security.service.impl.AuthenticationServiceProviderImpl;
import com.cdk.core.security.service.impl.CobaltUserServiceImpl;
import com.cdk.core.security.service.impl.IdentityServiceImpl;
import com.cdk.core.security.service.impl.SiteminderServiceImpl;

/**
 * 
 * @author Kevendra Patidar
 */
@Service
public class AuthenticateCdkServiceImpl implements AuthenticateService {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(AuthenticateCdkServiceImpl.class);
//	public static final String IAM_TOKEN = "iamToken";
//	public static final String IAM_TOKEN_EXPIRATION = "iamTokenExpiration";

	/* ************************************ Instance Fields ************************************ */    
    @Resource
    private SiteminderServiceImpl siteminderService;
    @Resource
    private IdentityServiceImpl identityService;
    @Resource
    private AuthenticationServiceProviderImpl authenticationServiceProvider;
    @Resource
    private CobaltUserServiceImpl cobaltUserService;
    
	/* ************************************ Public Methods ************************************ */
	@Override
	public boolean authenticate(final String userName, final String password, final JwtClaimEdge claims) {
		UserCredentialDto userCredentials = new UserCredentialDto(userName, password, claims.getDid(), claims.getDtp());
		
		OAuthTokenDto oAuthTokenDto = authenticationServiceProvider.getToken(userCredentials, false);

		if(null != oAuthTokenDto){
			claims.setIamToken(oAuthTokenDto.getTokenId());
			claims.setIamTokenExpiration(oAuthTokenDto.getExpirationTimestamp());
			
			//TODO chain of CDK services will update user claim info
			updateUserClaimDetail(userName, claims);//cobaltUserService
			
			return true;
		}
		return false;
	}
	/**
	 * IAM token verify
	 * 
	 * authenticateService.tokenVerify(claims)
	 * only iamToken require to pass, but this module not aware against which key iamToken store
	 */	
	@Override
	public boolean tokenVerify(final String iamToken) {
        if(StringUtils.isEmpty(iamToken)){
        	return true;//no iamToken found, so identity service validation not require
        }				
		try {
			OAuthValidateDto oauthValidateResponse = identityService.validateToken(iamToken);
			if("success".equalsIgnoreCase(oauthValidateResponse.getStatus())){
				return true;
			}
		} catch (Exception e) {
			LOG.error("identityService validate token failed", e);
		}	
		return false;
	}
	/**
	 * Logouts the user - token is invalidated/forgotten. 
	 */
	@Override
	public void tokenRevoke(final String iamToken){
        if(StringUtils.isEmpty(iamToken)){
        	return;//no iamToken found to remove
        }		
		LOG.debug("tokenRevoke token {}", iamToken);
		try {
			identityService.deleteToken(iamToken);
		} catch (Exception e) {
			LOG.warn("token is invalidated/forgotten");
		}  
    }
	
	/* ************************************ Private Methods ************************************ */
	private void updateUserClaimDetail(final String userName, final JwtClaimEdge claims) {
		List<String> securableTypes = claims.getSecTp(); //check NPE
		try{
			UserCdkDto user = cobaltUserService.getUser(userName);
	    	if(user != null) {
	    		/* dealerAuthorities key = securableType, value = authorities/capabilities */
	    		Map<String, List<String>> dealerAuthorities = cobaltUserService.populateSecurables(userName, securableTypes);	    		
	    		claims.setUserId(user.getId());
	    		claims.setFirstName(user.getFirstName());
	    		claims.setLastName(user.getLastName());
	    		claims.setEmail(user.getEmail());
	    		claims.setCobaltUser(user.isCobaltUser());
	    		claims.setAuthorities(dealerAuthorities);		
	        }			
		}catch(Exception e){
			LOG.error("error in user service", e);
		}
	}
	
}
