package com.seekret.businesslogic;

import com.seekret.pojo.Spot;


public interface ISecretPlacesFacade {

	Spot getSpotInformationForLocation(long latidute, long longitude);
	
	
	Spot getSpotInformationForName(String name);
}
