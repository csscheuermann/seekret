<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.flickranalyser.html.common.HelperMethods" %>
<%@ page import="com.flickranalyser.html.common.GoogleAuthHelper" %>
<%@ page import="com.flickranalyser.persistence.datastore.save.PFSaverUser" %>

<!DOCTYPE html>
<html lang="en">

	<%  HelperMethods helperMethods = (HelperMethods) request.getAttribute("helperMethods"); %>
	
	<% out.println(helperMethods.getHTMLHeader()); %>
	<% out.println(helperMethods.createBodyBegin()); %>
	<% out.println(helperMethods.createNavigation(true)); %>
	<% out.println(helperMethods.createCarusel()); %>
	<% out.println(helperMethods.getMarketingForMainPage()); %>
	

	
	
	
	<% out.println(helperMethods.getFooter()); %>
	


	
	<% out.println(helperMethods.createBodyEnd());%>
	
	</html>