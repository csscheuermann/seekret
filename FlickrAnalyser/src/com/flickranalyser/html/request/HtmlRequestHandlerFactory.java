package com.flickranalyser.html.request;

import com.flickranalyser.html.ActionNameEnum;
import com.flickranalyser.html.ViewNameEnum;
import com.flickranalyser.html.request.impl.ActionDismissClusterHandler;
import com.flickranalyser.html.request.impl.ActionEvaluateSpotHandler;
import com.flickranalyser.html.request.impl.ActionGetClusterAddressHandler;
import com.flickranalyser.html.request.impl.ActionSearchSpotsHandler;
import com.flickranalyser.html.request.impl.Prepare01_indexHandler;
import com.flickranalyser.html.request.impl.PrepareLogoutHandler;
import com.flickranalyser.html.request.impl.PrepareNothingHandler;
import com.flickranalyser.html.request.impl.PrepareSpotMapHandler;
import com.flickranalyser.html.request.impl.PrepareTopSpotsHandler;

public class HtmlRequestHandlerFactory {

	public IHtmlRequestHandler createActionHandler(ActionNameEnum actionName) {
		switch(actionName) {
		case DISMISS_CLUSTER:
			return new ActionDismissClusterHandler();
		case EVALULATE_SPOT:
			return new ActionEvaluateSpotHandler();
		case GET_CLUSTER_ADDRESS:
			return new ActionGetClusterAddressHandler();
		case SEARCH_SPOTS:
			return new ActionSearchSpotsHandler();
		default:
			return null;
		}
		
	}
	
	public IHtmlRequestHandler createViewPrepareHandler(ViewNameEnum viewName) {
		switch (viewName) {
		case INDEX:
			return new Prepare01_indexHandler();
		case LOGOUT:
			return new PrepareLogoutHandler();
		case SPOTMAP:
			return new PrepareSpotMapHandler();
		case TOPSPOTS:
			return new PrepareTopSpotsHandler();
		default:
			return new PrepareNothingHandler();
		}
	}
}
