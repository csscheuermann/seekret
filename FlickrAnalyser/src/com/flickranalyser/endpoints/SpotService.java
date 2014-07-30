package com.flickranalyser.endpoints;

import javax.ws.rs.core.Response;

import com.flickranalyser.businesslogic.spotfinder.ISpotFinder;
import com.flickranalyser.businesslogic.spotfinder.impl.NearestSpotFinder;
import com.flickranalyser.memcache.MemcacheSpot;
import com.flickranalyser.persistence.datastore.get.PFGetterSpot;
import com.flickranalyser.pojo.Spot;
import com.flickranalyser.pojo.SpotResultList;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;

@Api(name="spotAPI", version="v1", description="API for Spots.")
public class SpotService
{
  private final ISpotFinder spotFinder;

  public SpotService()
  {
    this.spotFinder = new NearestSpotFinder();
  }

  @ApiMethod(name="getNearestSpotByAddress")
  public Spot getNearestSpotByAddress(@Named("spotName") String spotName) {
    return this.spotFinder.findSpotByName(spotName);
  }
  
  @ApiMethod(name="getSpotById", path="getSpotById")
  public Spot getSpotById(@Named("spotId") String spotId) {
    return MemcacheSpot.getSpotForSpotName(spotId);
  }

  @ApiMethod(name="getSpotByNamePutToCrawlQueue")
  public Response getSpotByNamePutToCrawlQueue(@Named("spotName") String spotName, @Named("onlyExcludedPictures") boolean onlyExcludedPictures) {
    return this.spotFinder.getSpotByNamePutToCrawlQueue(spotName, onlyExcludedPictures);
  }

  @ApiMethod(name="getTopSpots", scopes={"https://www.googleapis.com/auth/userinfo.email"}, clientIds={"1099379908084-erlt14509li8acjpd7m20770t9gi5c0g.apps.googleusercontent.com", "292824132082.apps.googleusercontent.com"})
  public SpotResultList getTopSpots(User user)
    throws UnauthorizedException
  {
    if (user == null) {
      throw new UnauthorizedException("User is Not Valid");
    }
    return MemcacheSpot.getTopSpots();
  }
}