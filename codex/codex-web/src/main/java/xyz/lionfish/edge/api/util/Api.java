package xyz.lionfish.edge.api.util;


/**
 * MappingConstants
 *
 * @author Kevendra Patidar
 */
public interface Api extends me.parakh.core.api.util.Api{

	// ************************************************************************************************
	//major operations
	public static final String ACTIVITY				= BASE + "/activity";
	public static final String CUSTOMER 			= BASE + "/customer";
	public static final String CHANNEL 				= BASE + "/channel";
	public static final String DATA_CLEAN 			= BASE + "/data-clean";
	public static final String DEVICE 				= BASE + "/device";
	public static final String EMPLOYEE 			= BASE + "/employee";
	public static final String GROUP_TX				= BASE + "/group-tx";
	public static final String PERSON 				= BASE + "/person";
	public static final String OPR_SITE 			= BASE + "/site";
	public static final String REPORT				= BASE + "/report";

	public static final String AUTH_TOKEN			= BASE + "/auth-token";

	// ************************************************************************************************
	//AUTH_TOKEN operations api/auth-token/...
	public static final String TOKEN_GENERATE				= "/generate.json";
	public static final String TOKEN_GENERATE_SHORT_LIVED	= "/generate-short-lived.json";
	public static final String TOKEN_REVOKE					= "/revoke.json";
	public static final String TOKEN_VALIDATE				= "/validate.json";
	public static final String TOKEN_LIST					= "/tokens.json";
	public static final String TOKEN_UPDATE					= "/update.json";
		

	// ************************************************************************************************
	//APP_DATA operations api/app-data/...
//	public static final String TEST 						= "/test.json";
//	public static final String SHARE_DATA 					= "/share-data.json";

	//ASSET operations api/asset/...
//	public static final String ASSET_UPLOAD_HANDLER			= "upload-handler-url.json";
//	public static final String ASSET_UPLOAD					= "upload";//FIXME upload.json see api-service.js
//	public static final String ASSET_SERVE					= "serve";

	//ACTIVITY operations api/activity/...
	public static final String CREATE_SESSION				= "/create-session.json";
	public static final String UPDATE_SESSION				= "/update-session.json";
	public static final String LIST							= "/list.json";
	public static final String LATEST						= "/latest.json";
	public static final String CHECK_IN_VERIFY				= "/check-in-verify.json";
	public static final String REQUEST_TO_BRING_CAR			= "/request-to-bring-car.json";
	public static final String ACTIVITY_ADD_UPDATE			= "/add-update-activity.json";
//	public static final String ACTIVITY_DELETE				= "/delete-activity.json";
	public static final String SITE_PARKING_STATUS			= "/site-parking-status.json";
	public static final String ROOM_NO_ADD_UPDATE			= "/add-update-room-no.json";
	public static final String ROOM_NO_DELETE				= "/delete-room-no.json";
	public static final String SLOT_NO_ADD_UPDATE			= "/add-update-slot-no.json";
	public static final String SLOT_NO_DELETE				= "/delete-slot-no.json";
	public static final String CUSTOMER_NAME_ADD_UPDATE		= "/add-update-customer-name.json";
	public static final String CUSTOMER_NAME_DELETE			= "/delete-customer-name.json";
	public static final String NOTIFY_CUSTOMER_FOR_KEY		= "/notify-customer-for-key.json";
	
	//DATA_CLEAN operations api/channel/...
	public static final String CLEAN_ALL					= "/clean-all.json";
	
	//CHANNEL operations api/channel/...
	public static final String TOKEN						= "/token.json";
	
	//CUSTOMER operations api/customer/...
	//EMPLOYEE operations api/employee/...
	public static final String PERSON_PROFILE				= "/profile.json";
	public static final String DETAIL						= "/detail.json";
	public static final String CAR							= "/car.json";
	public static final String SITE							= "/site.json";
	public static final String SITE_ACCESS					= "/site-access.json";
	public static final String SITE_ACTIVATE				= "/site-activate.json";
	public static final String AREA							= "/area.json";
	public static final String AREA_ACTIVATE				= "/area-activate.json";

	//OPR_SITE operations api/site/...
//	public static final String SITE							= "/site.json";
//	public static final String SITE_ACCESS					= "/site-access.json";
//	public static final String SITE_ACTIVATE				= "/site-activate.json";
	public static final String CUSTOMIZE_AREA_PICKER		= "/customize-area-picker.json";
	
	//DEVICE operations api/device/...
	public static final String REGISTRATION					= "/registration.json";
	public static final String APP_ACTIVATE					= "/app-activate.json";
	
	//GROUP_TX operations api/group-tx/...
	public static final String GROUP_TX_STATUS				= "/group-tx-status.json";
	public static final String INACTIVATE					= "/inactivate.json";

	//PERSON operations api/person/...
	public static final String EMPLOYEE_INFO				= "/employee-info.json";

	//REPORT operations api/report/...
	public static final String DAILY_TRANSACTION			= "/daily-transaction";
	
}
