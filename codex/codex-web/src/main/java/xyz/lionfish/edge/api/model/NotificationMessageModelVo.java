package xyz.lionfish.edge.api.model;

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
 *
 * @author Kevendra Patidar
 */
public class NotificationMessageModelVo {

	/* ************************************ Instance Fields ************************************ */
	private String to;
	private Map<String, String> data;

	/* ************************************ Public Methods ************************************ */
	@Override
	public String toString() {
		return "NotificationMessageModelVo [to=" + to + ", data=" + data + "]";
	}

	/* ************************************ Getters and Setters ************************************ */
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}

}
