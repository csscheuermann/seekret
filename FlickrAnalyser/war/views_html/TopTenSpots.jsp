<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.flickranalyser.html.common.HelperMethods" %>
<%@ page import="com.flickranalyser.pojo.Spot" %>
<%@ page import="com.flickranalyser.pojo.TopTenSpots" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html lang="en">
	
	<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
	<% out.println(helperMethods.getHTMLHeader()); %>
	<% out.println(helperMethods.createBodyBegin()); %>
	<% out.println(helperMethods.createNavigation(false)); %>


   	<%  TopTenSpots topTenSpots = (TopTenSpots) request.getAttribute("topTenSpots"); %>
	

	
	<div class='container'>
		
		<% out.println("<div class='row'> " +
			"<div class='col-xs-3'> <h4>Name</h4> </div>" +
			"<div class='col-xs-3'><h4>OverallMaxPOINumber</h4></div>" +
			"<div class='col-xs-3'><h4>OverallMaxViewNumber</h4></div>" +
			"<div class='col-xs-3'><h4>Cluster Algos</h4></div></div>");
		
		for (Spot spot : topTenSpots.getTopTenSpots())	{
			out.println("<div class='row'> " +
				"<div class='col-xs-3'> " + spot.getName() + "</div>" +
				"<div class='col-xs-3'> " + spot.getOverallMaxPOINumberPerCluster() + "</div>" +
				"<div class='col-xs-3'> " + spot.getOverallMaxViewNumberPerCluster() + "</div>" +
				"<div class='col-xs-3'> " +
					"<a href='http://flickeranalyser.appspot.com/?showView=SpotMap&location=" +spot.getName()+ "&strategy=ManyViewsAndFewPOIsFilter'>1</a> | " +
			 		"<a href='http://flickeranalyser.appspot.com/?showView=SpotMap&location=" +spot.getName()+ "&strategy=ManyViewsAndFixedAmountOfPOIsFilter'>2</a>" +
				"</div></div>");
		} 
		%>
		
	</div>
	
	
	<% out.println(helperMethods.createBodyEnd());%>
	
	
	
	
	
	</html>