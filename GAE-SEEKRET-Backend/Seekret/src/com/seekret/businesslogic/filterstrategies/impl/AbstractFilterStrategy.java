package com.seekret.businesslogic.filterstrategies.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Assert;

import com.google.gwt.dev.jjs.impl.AssertionNormalizer;
import com.seekret.businesslogic.filterstrategies.IFilterStrategy;
import com.seekret.businesslogic.filterstrategies.common.ClusterScoreComparator;
import com.seekret.businesslogic.filterstrategies.common.ClusterScorePair;
import com.seekret.businesslogic.filterstrategies.filters.impl.HideDismissedPOIsFilter;
import com.seekret.businesslogic.filterstrategies.filters.impl.HidePicturelessPOIsFilter;
import com.seekret.businesslogic.filterstrategies.scoredecorator.IClusterScoreDecorator;
import com.seekret.pojo.Cluster;
import com.seekret.pojo.Spot;

public abstract class AbstractFilterStrategy implements IFilterStrategy {

	private static final int DEFAULT_MAX_NUMBER_OF_CLUSTERS = 60;
	private static final Logger LOGGER = Logger.getLogger(AbstractFilterStrategy.class.getName());
	private boolean ignoreDismissedClusters;
	private boolean ignorePicturelessClusters;

	private boolean limitResultSize;

	public AbstractFilterStrategy() {
		ignoreDismissedClusters = false;
		ignorePicturelessClusters = false;
		limitResultSize = true;
	}

	public AbstractFilterStrategy(boolean limitResultSize) {
		this.limitResultSize = limitResultSize;
	}

	@Override
	public final List<Cluster> filterCluster(List<Cluster> clusterToFilter, Spot spot) {
		List<Cluster> clusterToScore = preprocessClusterList(clusterToFilter, spot);

		List<ClusterScorePair> scoredClusters = scoreClusters(clusterToScore, spot);

		Collections.sort(scoredClusters, new ClusterScoreComparator());

		List<Cluster> clustersToReturn = new ArrayList<Cluster>();
		if (limitResultSize) {
			clustersToReturn = limitResultSize(scoredClusters);
		} else {
			clustersToReturn = getClustersOnly(scoredClusters);
		}

		postProcessClusters(clustersToReturn);
		return clustersToReturn;

	}

	private void postProcessClusters(List<Cluster> clustersToReturn) {
		addDefaultPictureIfPictureIsNotAvailable(clustersToReturn);

	}

	private void addDefaultPictureIfPictureIsNotAvailable(List<Cluster> clustersToReturn) {
		for (Cluster cluster : clustersToReturn) {
			if(cluster.getUrlOfMostViewedPicture() == null){
				LOGGER.log(Level.SEVERE, "SOMETHING IS WRONG HERE,  WHY COULD THIS BE NULL cluster.getUrlOfMostViewedPicture()");
			}else{
				if(cluster.getUrlOfMostViewedPicture().isEmpty()){
					cluster.addPictureUrl("http://de.jigzone.com/p/jz/jz1/The_Scream.jpg");
				}
			}
		}
	}

	private List<Cluster> getClustersOnly(List<ClusterScorePair> scoredClusters) {
		List<Cluster> result = new ArrayList<Cluster>(scoredClusters.size());
		for (ClusterScorePair clusterScorePair : scoredClusters) {
			result.add(clusterScorePair.getCluster());
		}
		return result;
	}

	private List<Cluster> preprocessClusterList(List<Cluster> clusterToFilter, Spot spot) {
		List<Cluster> clusterList = clusterToFilter;
		if (ignoreDismissedClusters) {
			HideDismissedPOIsFilter hideDismissedPOIsFilter = new HideDismissedPOIsFilter();
			clusterList = hideDismissedPOIsFilter.filterCluster(clusterList, spot);
		}
		if (ignorePicturelessClusters) {
			HidePicturelessPOIsFilter hidePicturelessPOIsFilter = new HidePicturelessPOIsFilter();
			clusterList = hidePicturelessPOIsFilter.filterCluster(clusterList, spot);
		}
		return clusterList;
	}

	@Override
	public void setIgnoreDismissedClustersFlag(boolean ignoreDismissedClusters) {
		this.ignoreDismissedClusters = ignoreDismissedClusters;
	}

	@Override
	public void setIgnorePictureLessClusters(boolean ignorePicturelessClusters) {
		this.ignorePicturelessClusters = ignorePicturelessClusters;
	}

	private List<Cluster> limitResultSize(List<ClusterScorePair> scoredClusters) {
		List<Cluster> topClusters = new LinkedList<Cluster>();
		for (ClusterScorePair clusterScorePair : scoredClusters) {
			Cluster cluster = clusterScorePair.getCluster();

			if (topClusters.size() < DEFAULT_MAX_NUMBER_OF_CLUSTERS) {
				// LOGGER.log(Level.SEVERE, "RANK" +
				// clusterScorePair.toString());
				// LOGGER.log(Level.SEVERE, "LATITUDE: " +
				// cluster.getCenterOfCluster().getLatitude());
				// LOGGER.log(Level.SEVERE, "LONGITUDE: " +
				// cluster.getCenterOfCluster().getLongitude());
				topClusters.add(cluster);
			} else {
				break;
			}

		}
		return topClusters;
	}

	private List<ClusterScorePair> scoreClusters(List<Cluster> clusterToFilter, Spot spot) {
		List<ClusterScorePair> sortedListOfCluster = new ArrayList<ClusterScorePair>(clusterToFilter.size());
		for (Cluster cluster : clusterToFilter) {

			double clusterScore = getClusterScoreDecorator(clusterToFilter, spot).scoreCluster(cluster);

			ClusterScorePair currentClusterScorePair = new ClusterScorePair(cluster, clusterScore);
			// LOGGER.log(Level.INFO, "ClusterScore: " +
			// currentClusterScorePair.toString());
			sortedListOfCluster.add(currentClusterScorePair);
		}
		return sortedListOfCluster;
	}

	protected abstract IClusterScoreDecorator getClusterScoreDecorator(List<Cluster> clustersToFilter, Spot spot);

}
