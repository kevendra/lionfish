package xyz.lionfish.edge.service.push;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import me.parakh.core.model.util.JsonUtility;
import me.parakh.core.service.common.PushService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import xyz.lionfish.edge.api.model.NotificationMessageIonicModelVo;
import xyz.lionfish.edge.api.model.NotificationMessageModelVo;

import com.google.appengine.api.urlfetch.HTTPHeader;
import com.google.appengine.api.urlfetch.HTTPMethod;
import com.google.appengine.api.urlfetch.HTTPRequest;
import com.google.appengine.api.urlfetch.URLFetchServiceFactory;

/**
 * The Activity controller.
 *
 * @author Kevendra Patidar
 */
@Service
public class PushServiceImpl implements PushService {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(PushServiceImpl.class);
	//Public Server API Key (google-api-key)
	private static final String AUTHORIZATION_KEY = "key=AIzaSyCMc0TH9vpK9Tb2hCsMu0Y29j7UQYrKgKw";//AIzaSyDCpzOBZTZMugUJinfmE354-3fE3XVc8WM AIzaSyDgJvS9L1WMVbPIZoqfcRdylLFdPO6_V5I";
	//private static final String PRIVATE_KEY = "552594d547bbe269267639ae10be2e7418461bf089953c03";//"590219c58bed9c2418ad6858be1f8e64bc521181f2c2cec4";
	private static final String ENCODED_PRIVATE_KEY = "NTUyNTk0ZDU0N2JiZTI2OTI2NzYzOWFlMTBiZTJlNzQxODQ2MWJmMDg5OTUzYzAzOg==";//NTkwMjE5YzU4YmVkOWMyNDE4YWQ2ODU4YmUxZjhlNjRiYzUyMTE4MWYyYzJjZWM0Og==";
	private static final String APP_ID_VALET_PARKING = "4299de61";//"b425d3c5";
	//private static final String APP_ID_VALET_PARTNER = "2b022729";//"af0cbadb";
	private static final String LINK_PUSH = "https://gcm-http.googleapis.com/gcm/send";//GCM_URL
	private static final String LINK_IONIC_PUSH = "https://push.ionic.io/api/v1/push";
	//v2 https://api.ionic.io/push/notifications
	/* ************************************ Instance Fields ************************************ */
	@Resource
	private HttpExecutor httpExecutor;

	/* ************************************ Public Methods ************************************ */
	@Override
	public String notifyUser(String deviceTokens, String message, String title, String username) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String notifyUserViaIonic(String deviceTokens, String message, String title, String username) {
		// TODO Auto-generated method stub
		return null;
	}	
	/**
	 *
	 * @param deviceTokens - Android device tokens - iOS device tokens
	 * @param message - Hi {{first_name}}! Here's a coupon for $25 off!  {{user}} user {{tkey}} tkey
	 * @param title
	 * @return
	 */
	public String notifyUser(final String deviceTokens, final String message, final String title){
		final Map<String, String> header = new HashMap<String, String>();
		header.put("Authorization", AUTHORIZATION_KEY);
		final String payload = getPayload(deviceTokens, message, title);
		/*
		 * error
		 * xyz.share.codex.web.service.HttpExecutor post: error post link https://gcm-http.googleapis.com/gcm/send
		 * java.net.SocketException: Permission denied: Attempt to access a blocked recipient without permission. (mapped-IPv4)
		 * 
		 * return this.httpExecutor.post(LINK_PUSH, MediaType.APPLICATION_JSON_VALUE, payload, header);
		 */
		return sendNotificationRequestToGcm(payload);
	}
	/**
	 * ionicGenUid - 6b4936ee-b763-4a83-9b96-20fee1159c27
	 * @param deviceId - eaaa51e55c80333b XXXXXX
	 * @param deviceTokens - Android device tokens - iOS device tokens
	 * @param message
	 * @param title
	 * @return
	 */
	public String notifyUserViaIonic(final String deviceTokens, final String message, final String title){
		final Map<String, String> header = new HashMap<String, String>();
		header.put("X-Ionic-Application-Id", APP_ID_VALET_PARKING);
        //encoding  byte array into base 64
        //final byte[] encoded = Base64.encode(PRIVATE_KEY.getBytes());
        //System.out.println("encoded "+encoded);
		header.put("Authorization", "Basic " + ENCODED_PRIVATE_KEY);

		final String payload = getPayloadForIonic(deviceTokens, message, title);

		return this.httpExecutor.post(LINK_IONIC_PUSH, MediaType.APPLICATION_JSON_VALUE, payload, header);
	}

	/* ************************************ Private Methods ************************************ */
	private String sendNotificationRequestToGcm(String jsonString) {
//		List<String> registrationIds
//	    LOG.info("In sendNotificationRequestToGcm method!");
//	    JSONObject json = new JSONObject();
//	    JSONArray jasonArray = new JSONArray(registrationIds);
//	    try {
//	        json.put("registration_ids", jasonArray);
//	    } catch (JSONException e2) {
//	        LOG.error("JSONException: " + e2.getMessage());
//	    }
//	    String jsonString = json.toString();
//	    LOG.info("JSON payload: " + jsonString);

	    com.google.appengine.api.urlfetch.HTTPResponse response;
	    URL url;
	    HTTPRequest httpRequest;
	    try {
	        //GCM_URL = https://android.googleapis.com/gcm/send
	        url = new URL(LINK_PUSH);
	        httpRequest = new HTTPRequest(url, HTTPMethod.POST);
	        httpRequest.addHeader(new HTTPHeader("Content-Type","application/json"));
	        httpRequest.addHeader(new HTTPHeader("Authorization", AUTHORIZATION_KEY));
	        httpRequest.setPayload(jsonString.getBytes("UTF-8"));
	        LOG.info("Sending POST request to: " + LINK_PUSH);
	        response = URLFetchServiceFactory.getURLFetchService().fetch(httpRequest);
	        LOG.info("Status: " + response.getResponseCode());            
	        List<HTTPHeader> hdrs = response.getHeaders();
	        for(HTTPHeader header : hdrs) {
	            LOG.info("Header: " + header.getName());
	            LOG.info("Value:  " + header.getValue());
	        }
	        return response.getContent().toString();
	    } catch (UnsupportedEncodingException e) {
	        LOG.error("UnsupportedEncodingException" + e.getMessage());
	    } catch (MalformedURLException e) {
	        LOG.error("MalformedURLException" + e.getMessage());
	    } catch (IOException e) {
	        LOG.error("URLFETCH IOException" + e.getMessage());
	    } catch (Exception e) {
	        LOG.error("Exception Exception" + e.getMessage());
	    }
	    return null;
	}	
	
	/**
	 *
{
  "to": "APA91bEyWVS91EQgA68cyiO6TtdwfKSJ9ozJChLAEEfqd_ZKKKkKwLI89NUdehodMSHjDwk2he2pgLYbGZtDLnSuQQ3Li9sRSKfpAPLIq4DmEMa2YO-kGNo",
  "data": {
    "message": "great match!",
    "title": "Portugal vs. Denmark"
  }
}
	 */
	private String getPayload(String deviceTokens, String message, String title) {
		final Map<String, String> data = new HashMap<String, String>();
		data.put("message", message);
		if(StringUtils.hasText(title)){
			data.put("title", title);
		}
		final NotificationMessageModelVo msg = new NotificationMessageModelVo();
		msg.setTo(deviceTokens);
		msg.setData(data);

		return JsonUtility.toJson(msg);
	}
	/**
	 *
{
  "tokens":[
    "b284a6f7545368d2d3f753263e3e2f2b7795be5263ed7c95017f628730edeaad",
    "d609f7cba82fdd0a568d5ada649cddc5ebb65f08e7fc72599d8d47390bfc0f20"
  ],
  "notification":{
    "alert":"Hello World!",
    "ios":{
      "badge":1,
      "sound":"ping.aiff",
      "expiry": 1423238641,
      "priority": 10,
      "contentAvailable": true,
      "payload":{
        "key1":"value",
        "key2":"value"
      }
    },
    "android":{
      "collapseKey":"foo",
      "delayWhileIdle":true,
      "timeToLive":300,
      "payload":{
        "key1":"value",
        "key2":"value"
      }
    }
  }
}
	 */
	private String getPayloadForIonic(String deviceTokens, String message, String title) {
		final List<String> tokens = new ArrayList<String>();
		tokens.add(deviceTokens);

		final Map<String, Object> notification = new HashMap<String, Object>();
		notification.put("alert", message);
		notification.put("title", title);

		final Map<String, Object> iosmessage = new HashMap<String, Object>();
		/*
		 * specifying anything in the sound key — default, chime, or asdf, 
		 * will all play the system sound, unless the name of the sound is found in the main application bundle.
		 */
		iosmessage.put("sound", "default"); 
		//iosmessage.put("badge", new Integer(1));
		notification.put("ios", iosmessage);
		
		final NotificationMessageIonicModelVo msg = new NotificationMessageIonicModelVo();
		msg.setTokens(tokens);
		msg.setNotification(notification);
		msg.setProduction(true);

		return JsonUtility.toJson(msg);
	}


}
