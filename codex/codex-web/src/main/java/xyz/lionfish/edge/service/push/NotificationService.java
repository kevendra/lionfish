package xyz.lionfish.edge.service.push;

import java.util.List;

import javax.annotation.Resource;

import me.parakh.core.dto.common.TokenDto;
import me.parakh.core.model.type.MobilePlatform;
import me.parakh.core.service.common.TokenService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import xyz.lionfish.edge.service.common.PersonService;

/**
 * The Activity controller.
 *
 * @author Kevendra Patidar
 */
@Service
public class NotificationService {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(NotificationService.class);

	/* ************************************ Instance Fields ************************************ */
	@Resource
	private PersonService personService;
	@Resource
	private TokenService tokenService;
	@Resource
	private PushServiceImpl pushService;

	/* ************************************ Public Methods ************************************ */
	public void notifyCustomerForKey(final Long personId){
		final String title = "Car key";
		final String message = "Please handover car key to valet.";
		notify(personId, message, title);		
	}	
	//IN
	public void notifyCreateSession(final Long personId, final String siteName){
		LOG.debug("CreateSession for personId {}", personId);
		final String title = "Received";
		String message;//"Car checked-in and valet is in the process of finding parking slot for your car";
		if(StringUtils.hasText(siteName)){
			message = "Your car checked-in at " + siteName +".";
		}else{
			message = "Your car checked-in.";
		}		
		notify(personId, message, title);
	}
	//IN
	public void notifyDropOffStatus(final List<Long> empIds, final String siteName){
		final String title = "Drop-off";
		String message;
		if(StringUtils.hasText(siteName)){
			message = "The customer had dropped his/her car at " + siteName +".";
		}else{
			message = "The customer had dropped his/her car.";
		}		
		notify(empIds, message, title);
	}
	//PARKED
	public void notifyParkedStatus(final Long personId){
		final String title = "Parked";
		final String message = "Your car has been parked.";//"Car has been parked to the available sport";//we have found open slot for your car, and it has been parked now.
		notify(personId, message, title);		
	}
	//REQUESTED
	public void notifyRequestedStatus(final Long personId){
		final String title = "Requested";
		final String message = "Valet had requested to bring your car.";
		notify(personId, message, title);		
	}
	//REQUESTED
	public void notifyRequestedStatus(final List<Long> empIds){
		//TODO Notify Customer with ticket no and/or trackingId(personId/customerId)
		final String title = "Process Request";
		final String message = "Customer had requested to bring his/her car.";
		notify(empIds, message, title);
	}
	//PROCESSED
	public void notifyProcessedStatus(final Long personId){
		final String title = "Request In-Process";
		final String message = "Valet is in-process to bring your car.";
		notify(personId, message, title);
	}
	//AT_GATE
	public void notifyAtGateStatus(final Long personId){
		final String title = "At Gate";
		final String message = "Your car is ready to be picked up.";//"Car is at drop zone, you may come now.";
		notify(personId, message, title);
	}
	//OUT
	public void notifyOutStatus(final Long personId){
		final String title = "Thank You";
		final String message = "Car has been delivered. Wish you a safe drive!";
		notify(personId, message, title);
	}
	
	//Duration / Bill amount 
	/* ************************************ Private Methods ************************************ */
	private void notify(final List<Long> empIds, final String message, final String title) {
		List<TokenDto> tokens = tokenService.getToken(personService.getDeviceIds(empIds));
		if(CollectionUtils.isEmpty(tokens)){
			return;
		}
		for(TokenDto token : tokens){
			notify(token, message, title);
		}
	}
	private void notify(final Long empId, final String message, final String title) {
		TokenDto token = tokenService.getToken(personService.getDeviceId(empId));
		if(null == token){
			return;
		}
		notify(token, message, title);
	}
	private void notify(final TokenDto token, final String message, final String title) {
		final String deviceTokens = token.getToken();
		if(MobilePlatform.IOS == token.getOs()){
			pushService.notifyUserViaIonic(deviceTokens, message, title);
		}else{
			pushService.notifyUser(deviceTokens, message, title);
		}
	}

}
