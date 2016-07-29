package xyz.lionfish.edge.api.util;

import java.util.ArrayList;
import java.util.List;

import me.parakh.core.dto.common.NavigationModelDto;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 *
 * @author Kevendra Patidar
 */
public abstract class Navigation {

	/* ************************************ Static Fields ************************************ */
	private static final Logger LOG = LoggerFactory.getLogger(Navigation.class);

	/* ************************************ Public Methods ************************************ */
	/**
	 *
      {
        "label": "status",
        "link": "app.status",
        "icon": "ion-ios-compose-outline",
        "subNav": null
      },
      {
        "label": "park.car",
        "link": "app.park-car",
        "icon": "ion-model-s",
        "subNav": null
      },
      {
        "label": "settings",
        "link": "app.settings",
        "icon": "ion-ios-gear-outline",
        "subNav": null
      },
      {
        "label": "aboutus",
        "link": "app.about-us",
        "icon": "ion-ios-information-outline",
        "subNav": null
      },
      {
        "label": "todo",
        "link": "app.todo",
        "icon": "ion-ios-bookmarks-outline",
        "subNav": null
      } 	 *
	 * @return
	 */
	public static List<NavigationModelDto> navigationCustomer(){
		LOG.debug("in navList");
		final List<NavigationModelDto> navList = new ArrayList<NavigationModelDto>();

		navList.add(new NavigationModelDto("status.car", "app.car-status", "ion-ios-list-outline", null));//ion-ios-compose-outline
		navList.add(new NavigationModelDto("park.car", "app.park-car", "ion-model-s", null));
		navList.add(new NavigationModelDto("status.site", "app.site-status", "ion-ios-location-outline", null));
		navList.add(new NavigationModelDto("settings", "app.settings", "ion-ios-gear-outline", null));
		navList.add(new NavigationModelDto("aboutus", "app.about-us", "ion-ios-information-outline", null));
		navList.add(new NavigationModelDto("todo", "app.todo", "ion-ios-bookmarks-outline", null));

		return navList;
	}
	public static List<NavigationModelDto> navigationEmployee(){
		final List<NavigationModelDto> navList = new ArrayList<NavigationModelDto>();

		navList.add(new NavigationModelDto("status.activity", "app.activity-status", "ion-ios-list-outline", null));
		navList.add(new NavigationModelDto("park.car", "app.valet-park-car", "ion-model-s", null));
		navList.add(new NavigationModelDto("report", "app.report", "ion-ios-pie-outline", null));
		navList.add(new NavigationModelDto("settings", "app.settings", "ion-ios-gear-outline", null));
		navList.add(new NavigationModelDto("aboutus", "app.about-us", "ion-ios-information-outline", null));
		navList.add(new NavigationModelDto("todo", "app.todo", "ion-ios-bookmarks-outline", null));

		return navList;
	}

}
