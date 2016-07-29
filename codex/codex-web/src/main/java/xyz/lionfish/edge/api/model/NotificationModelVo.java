package xyz.lionfish.edge.api.model;

/**
 *
 * @author Kevendra Patidar
 */
public class NotificationModelVo {

	/* ************************************ Instance Fields ************************************ */
	private boolean register;
	private String token;

	/* ************************************ Public Methods ************************************ */
	@Override
	public String toString() {
		return "NotificationVo [register=" + register + ", token=" + token
				+ "]";
	}

	/* ************************************ Getters and Setters ************************************ */
	public boolean isRegister() {
		return register;
	}
	public void setRegister(boolean register) {
		this.register = register;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
