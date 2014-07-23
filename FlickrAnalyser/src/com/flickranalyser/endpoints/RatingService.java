package com.flickranalyser.endpoints;

import javax.ws.rs.core.Response;

import com.flickranalyser.persistence.datastore.get.PFGetterRating;
import com.flickranalyser.persistence.datastore.get.PFGetterRatingDismissCounter;
import com.flickranalyser.persistence.datastore.save.PFSaverRating;
import com.flickranalyser.pojo.results.KeyResult;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;

@Api(name="ratingAPI", version="v1", description="This API serves everything needed for Ratings")
public class RatingService
{
  @ApiMethod(name="addNewRating")
  public Response addNewRating(@Named("userPrimaryKey") String userPrimaryKey, @Named("clusterPrimaryKey") String clusterPrimaryKey)
  {
    return PFSaverRating.saveRatingToDatastore(userPrimaryKey, clusterPrimaryKey);
  }

  @ApiMethod(name="hasUserAlreadyVoted")
  public Response hasUserAlreadyVoted(@Named("userPrimaryKey") String userPrimaryKey, @Named("clusterPrimaryKey") String clusterPrimaryKey)
  {
    return PFGetterRating.hasUserAlreadyRated(userPrimaryKey, clusterPrimaryKey);
  }
  
  @ApiMethod(name="getAllRatingKeysOfSpecifiedUser", path="getAllRatingKeysOfSpecifiedUser")
  public KeyResult getAllRatingKeysOfSpecifiedUser(@Named("userPrimaryKey") String userPrimaryKey){
    
	  return PFGetterRating.getAllRatingKeysOfSpecifiedUser(userPrimaryKey);
  }
  
  @ApiMethod(name="getAllDismissKeysOfSpecifiedUser", path="getAllDismissKeysOfSpecifiedUser")
  public KeyResult getAllDismissKeysOfSpecifiedUser(@Named("userPrimaryKey") String userPrimaryKey){
	  return PFGetterRatingDismissCounter.getAllDismissKeysOfSpecifiedUser(userPrimaryKey);
  }
  
  
  
}