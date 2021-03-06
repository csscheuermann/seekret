package com.seekret.html;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.seekret.persistence.datastore.common.PropertiesSpotToCrawl;
import com.seekret.persistence.datastore.save.PFSaverSpotToCrawl;
import com.seekret.pojo.SpotToCrawl;

public class SpotToQueue extends HttpServlet{

	private static final long serialVersionUID = 1L;


	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		
		String name = req.getParameter(PropertiesSpotToCrawl.NAME.toString());
		double latitude = Double.parseDouble(req.getParameter(PropertiesSpotToCrawl.LATITUDE.toString()));
		double longitude =  Double.parseDouble(req.getParameter(PropertiesSpotToCrawl.LONGITUDE.toString()));
		String description = req.getParameter(PropertiesSpotToCrawl.DESCRIPTION.toString());
		double spotRadius =  Double.parseDouble(req.getParameter(PropertiesSpotToCrawl.SPOT_RADIUS_IN_KM.toString()));
		double clusterRadius =  Double.parseDouble(req.getParameter(PropertiesSpotToCrawl.CLUSTER_RADIUS_IN_KM.toString()));

		SpotToCrawl spot = new SpotToCrawl(latitude, longitude,name,description,clusterRadius,spotRadius, null,0,0, false);
		
		PFSaverSpotToCrawl.saveSpotToDatastore(spot);

	}


}
