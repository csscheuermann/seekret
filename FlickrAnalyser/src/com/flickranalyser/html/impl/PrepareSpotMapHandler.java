package com.flickranalyser.html.impl;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.businesslogic.filterstrategies.IFilterStrategy;
import com.flickranalyser.endpoints.SpotService;
import com.flickranalyser.html.common.HelperMethods;
import com.flickranalyser.pojo.Cluster;
import com.flickranalyser.pojo.Spot;

public class PrepareSpotMapHandler extends AbstractHtmlRequestHandler{
  private static final Logger LOGGER = Logger.getLogger(PrepareSpotMapHandler.class.getName());
  private final SpotService spotService;

  public PrepareSpotMapHandler(){
    this.spotService = new SpotService();
  }

  @Override
public String performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession){
    
	  //Get the needed Parameters
	String location = pRequest.getParameter("location");
    String filterStrategy = pRequest.getParameter("strategy");
    boolean dissmissCluster = Boolean.parseBoolean(pRequest.getParameter("dissmissCluster"));
    
    LOGGER.log(Level.INFO, "DISMISS CLUSTER: " + dissmissCluster);
    LOGGER.log(Level.INFO, "LOCATION: " + location);
    LOGGER.log(Level.INFO, "FILTER STRATEGY: " + filterStrategy);

    
    if (location == null || filterStrategy == null){
    	pRequest.setAttribute(HelperMethods.MESSAGE_ERROR, "Location or Filterstategy was not set");
    	return "TopSpots";
    }
    
    StringBuilder fullClassPath = new StringBuilder();
    fullClassPath.append("com.flickranalyser.businesslogic.filterstrategies.impl.");
    fullClassPath.append(filterStrategy);

    IFilterStrategy choosenFilterStrategy = HelperMethods.instantiate(fullClassPath.toString(), IFilterStrategy.class);
    LOGGER.log(Level.INFO, "INITIALIZED FILTER STRATEGY:" + choosenFilterStrategy.getClass().getName());

    Spot spot = this.spotService.getSpotById(location);
    if (spot != null){
      List<Cluster> cluster = spot.getCluster();
      List<Cluster> filteredCluster = choosenFilterStrategy.filterCluster(cluster, spot);
      spot.setCluster(filteredCluster);
      
      int numberOfFilteredClusters = cluster.size() - filteredCluster.size();
      LOGGER.log(Level.INFO, "NUMBER OF FILTERED CLUSTERS: " + numberOfFilteredClusters);

      pRequest.setAttribute("spot", spot);
    }
    return null;
  }
}