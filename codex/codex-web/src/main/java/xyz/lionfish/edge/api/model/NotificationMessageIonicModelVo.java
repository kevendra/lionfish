package xyz.lionfish.edge.api.model;

import java.util.List;
import java.util.Map;

/**
 *
{
  "to": "APA91bHqpDwyTXcQHmJw6bmTl8WvGakgAnGHOiSSymtr504IgE-xGkf2qEaEn7j8fau8qViCCq3vNdaXZafonuaN8QsJdX7GJ-IQXjXLcgY0tJLPbfhSx4E-qRj0JytTk5j5SmOuPIBEsq33cXagYA57fqoXvP3umA",
  "data": {
    "message": "great match!",
    "title": "Portugal vs. Denmark111111"
  }
}


 * https://api.ionic.io/push/notifications POST payload -
// Build the request object
var req = {
  method: 'POST',
  url: 'https://api.ionic.io/push/notifications',
  headers: {
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + jwt
  },
  data: {
    "tokens": ["your", "device", "tokens"],
    "profile": "my-security-profile",
    "notification": {
      "title": "Hi",
      "message": "Hello world!",
      "android": {//can be overwritten for each platform individually.
        "title": "Hey", 
        "message": "Hello Android!",
        "payload" {//adding custom data to your notifications
          "baz": "boo"
      	},
      	"data": { //phonegap push plugin options - use of specific keys sent directly with APNs or GCM
          "image": "https://pbs.twimg.com/profile_images/617058765167329280/9BkeDJlV.png" //for android only
      	}
      },
      "ios": {
        "title": "Howdy",
        "message": "Hello iOS!"
      }
    }
  }
};

OR
{
  "user_ids": ["Your", "ionic user", "ids"],
  "notification": {
    "message":"Hello World!"
  }
}
 *
 * @author Kevendra Patidar
 */
public class NotificationMessageIonicModelVo {

	/* ************************************ Instance Fields ************************************ */
	private String profile;
	private List<String> tokens;
	private Map<String, Object> notification;
	private boolean production;

	/* ************************************ Public Methods ************************************ */
	@Override
	public String toString() {
		return "NotificationMessageIonicModelVo [tokens=" + tokens
				+ ", notification=" + notification + ", production="
				+ production + "]";
	}

	/* ************************************ Getters and Setters ************************************ */
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public List<String> getTokens() {
		return tokens;
	}
	public void setTokens(List<String> tokens) {
		this.tokens = tokens;
	}
	public Map<String, Object> getNotification() {
		return notification;
	}
	public void setNotification(Map<String, Object> notification) {
		this.notification = notification;
	}
	public boolean isProduction() {
		return production;
	}
	public void setProduction(boolean production) {
		this.production = production;
	}

}
