package com.flickranalyser.businesslogic.filter.impl;

import java.util.LinkedList;
import java.util.List;

import com.flickranalyser.businesslogic.filter.IFilterStrategy;
import com.flickranalyser.pojo.Cluster;

public class ManyViewsAndFixedAmountOfPOIsFilter implements IFilterStrategy{

	@Override
	public List<Cluster> filterCluster(List<Cluster> clusterToFilter) {
		
		
		List<Cluster> topClusters = new LinkedList<Cluster>();
		
		
		for(Cluster currentCluster : clusterToFilter){
			if((currentCluster.getOverallViews() > 5000) && currentCluster.getNumberOfPOIs() < 50 && currentCluster.getNumberOfPOIs() > 10){
				topClusters.add(currentCluster);
			}
		}
		return topClusters;
	}

}
