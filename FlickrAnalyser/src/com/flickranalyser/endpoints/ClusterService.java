package com.flickranalyser.endpoints;

import javax.ws.rs.core.Response;

import com.flickranalyser.businesslogic.spotfinder.impl.NearestSpotFinder;
import com.flickranalyser.persistence.datastore.save.PFSaverCluster;
import com.flickranalyser.pojo.responses.Address;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.users.User;

@Api(name="clusterAPI", version="v1", description="This API serves everything needed to update a cluster.")
public class ClusterService {



	@ApiMethod(name="evaluateCluster")
	public Response evaluateCluster(@Named("datastoreKeyOfCluster") String datastoreKeyOfCluster, 
			@Named("touristicnessRatingFrom1To10") int touristicnessRatingFrom1To10,
			@Named("spotName") String spotName
			) {
		return PFSaverCluster.evaluateTouristicness(KeyFactory.stringToKey(datastoreKeyOfCluster), touristicnessRatingFrom1To10, spotName);
	}

	@ApiMethod(name="getAddressFromLatLng",
			scopes = {Constants.EMAIL_SCOPE},
			clientIds = {Constants.WEB_CLIENT_ID,  
			com.google.api.server.spi.Constant.API_EXPLORER_CLIENT_ID})
			public Address getAddressFromLatLng( User user, @Named("latitude") double latitude, 
					@Named("longitude") double longitude) throws UnauthorizedException {
				if (user == null) throw new UnauthorizedException("User is Not Valid");
				
				NearestSpotFinder nsf = new NearestSpotFinder();
				String findAddressByLatLng = nsf.findAddressByLatLng(latitude, longitude);
				return new Address(findAddressByLatLng);
			}

}
