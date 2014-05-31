package com.flickranalyser.businesslogic;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.PointOfInterest;
import com.flickranalyser.pojo.Spot;
import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;

public class SpotCalculationHandler {
	
	private static final String API_KEY = "";

	private static final Logger log = Logger.getLogger(SpotCalculationHandlerTest.class.getName());
	
	public Spot getSpot(List<PointOfInterest> pointOfInterests){
		
		//Munich 
		// Long: 	11.5667
		// Lat: 	48.1333
		Spot hardcodedSpot = new Spot(new LatLng(48.1333, 11.5667), "Munich", "This is our first try");
		//First ask FlickrRequestHandler 
		
		//Cluster List of Spot - Empty at first
		List<Cluster> clusterList = hardcodedSpot.getClusterList();
		
		
		
		
		
		for (PointOfInterest pointOfInterest : pointOfInterests) {
			if (!isPointIntrestInCluster(hardcodedSpot, clusterList, pointOfInterest)){
				log.log(Level.INFO, "Added new Cluster");
				//Add new Cluster, no Cluster found or List was empty
				Cluster cluster = new Cluster(pointOfInterest.getLocation(), "", "");
				cluster.addPointOfInterestToList(pointOfInterest);
				hardcodedSpot.addClusterToList(cluster);
			}
			
			
		}
		return hardcodedSpot;
		
		
	}

	private boolean isPointIntrestInCluster(Spot hardcodedSpot, List<Cluster> clusterList,
			PointOfInterest pointOfInterest) {
		for (Cluster currentCluster : clusterList) {
			double distance = LatLngTool.distance(currentCluster.getCenterOfCluster(), pointOfInterest.getLocation(), LengthUnit.KILOMETER);
			log.log(Level.INFO, "Distance " + distance);
			if (distance <= hardcodedSpot.getClusterRadiusInKm() ){
				currentCluster.addPointOfInterestToList(pointOfInterest);
				log.log(Level.INFO, "Put in existing Cluster");
				return true;
			}	
		}
		return false;
	}
}
