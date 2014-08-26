package com.seekret.businesslogic.impl;

import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.seekret.businesslogic.ISecretPlacesFacade;
import com.seekret.data.flickr.FlickrRequestHandler;
import com.seekret.data.flickr.IFotoExcluder;
import com.seekret.data.flickr.TagBasedFotoExcluder;
import com.seekret.pojo.PointOfInterest;
import com.seekret.pojo.Spot;

public class SecretPlacesFacade implements ISecretPlacesFacade {

	private final Spot spot;
	private final FlickrRequestHandler flickrRequestHandler;
	private final SpotCalculationHandler spotCalculationHandler;

	private static final Logger LOGGER = Logger.getLogger(SecretPlacesFacade.class.getName());
	
	public SecretPlacesFacade(Spot spot, boolean onlyUnwantedTags) {
		this.spot = spot;
		IFotoExcluder fotoExcluder = new TagBasedFotoExcluder(onlyUnwantedTags);
		flickrRequestHandler = new FlickrRequestHandler(fotoExcluder);
		spotCalculationHandler = new SpotCalculationHandler();
	}

	@Override
	public Spot getSpotInformationForName(String name) {
		return getSpotInformation(spot);
	}

	@Override
	public Spot getSpotInformationForLocation(long latitude, long longitude) {
		return getSpotInformation(spot);
	}

	private Spot getSpotInformation(Spot spotToSearchFor) {
		
		Set<PointOfInterest> allPOIsForSpot = flickrRequestHandler.getPOIsForSpot(spotToSearchFor);
		LOGGER.log(Level.INFO, " Number of POIs: " + allPOIsForSpot.size());
		
		Spot spot = spotCalculationHandler.getSpot(allPOIsForSpot,spotToSearchFor);
		return spot;
	}

}