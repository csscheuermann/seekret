package com.flickranalyser.html.common;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import com.flickranalyser.html.webfrontend.HtmlRequestProcessor;
import com.flickranalyser.html.webfrontend.HtmlStarterServlet;
import com.flickranalyser.pojo.User;

public class HelperMethods {

	public static final String PAGE_TITLE = "SECRET PLACES";
	private static final Logger LOGGER = Logger.getLogger(HtmlStarterServlet.class.getName());
	private HttpSession session;

	
	public static final String MESSAGE_SUCCESSFUL_CRON = "successfulCron";
	public static final String MESSAGE_ERROR_CRON = "errorCron";
	public static final String MESSAGE_SUCCESSFUL = "successful";
	public static final String MESSAGE_ERROR = "error";
	public static final String CRAWL_LONGITUDE = "crawlLongitude";
	public static final String CRAWL_LATITUDE = "crawlLatitude";
	public static final String CRAWL_ADDRESS = "crawlAddress";
	public static final String ADDRESS_PARAM = "address";
	public static final String LONGITUDE_PARAM = "longitude";
	public static final String LATITUDE_PARAM = "latitude";
	public static final String SPOT = "spot";


	public HelperMethods(final HttpSession session){
		this.session = session;

	}

	public static <T> T instantiate(final String className, final Class<T> type){
		try{
			return type.cast(Class.forName(className).newInstance());
		} catch(final InstantiationException e){
			LOGGER.log(Level.INFO, "COULD NOT INITIATE " + e.getMessage());
		} catch(final IllegalAccessException e){
			LOGGER.log(Level.INFO, "ILLEGALACCESSEXCEPTION" + e.getMessage());
		} catch(final ClassNotFoundException e){
			LOGGER.log(Level.INFO, "CLASSNOTFOUNDEXCEPTION" + e.getMessage());
		}
		return null;
	}




	public String getHTMLHeader(){
		StringBuilder header = new StringBuilder();
		header.append(getHTMLHeaderUnclosed());
		header.append("</head>");
		return header.toString();

	}


	public String getHTMLHeaderUnclosed(){

		StringBuilder header = new StringBuilder();

		header.append("<head>");
		header.append("<meta charset='utf-8'>");
		header.append("<meta http-equiv='X-UA-Compatible' content='IE=edge'>");
		header.append("<meta name='viewport' content='width=device-width, initial-scale=1'>");
		header.append("<meta name='description' content=''>");
		header.append("<meta name='author' content=''>");
		header.append("<link rel='icon' href='../../favicon.ico'>");

		header.append("<title>SEEKRET</title>");

		header.append("<!-- Bootstrap core CSS -->");
		header.append("<link href='/res_html/bootstrap-3.2.0-dist/css/bootstrap.min.css' rel='stylesheet'>");

		header.append("<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->");
		header.append("<!--[if lt IE 9]>");
		header.append("<script src='https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js'></script>");
		header.append("<script src='https://oss.maxcdn.com/respond/1.4.2/respond.min.js'></script>");
		header.append("<![endif]-->");

		header.append("<!-- Custom styles for this template -->");
		header.append("<link href='/res_html/bootstrap-3.2.0-dist/css/carousel.css' rel='stylesheet'>");
		return header.toString();

	}
	public String createCarusel(){
		StringBuilder bodyBegin = new StringBuilder();

		bodyBegin.append("<div id='myCarousel' class='carousel slide' data-ride='carousel'>");
		bodyBegin.append("<!-- Indicators -->");
		bodyBegin.append("<ol class='carousel-indicators'>");
		bodyBegin.append("<li data-target='#myCarousel' data-slide-to='0' class='active'></li>");
		bodyBegin.append("<li data-target='#myCarousel' data-slide-to='1'></li>");
		bodyBegin.append("<li data-target='#myCarousel' data-slide-to='2'></li>");
		bodyBegin.append("</ol>");
		bodyBegin.append("<div class='carousel-inner'>");
		bodyBegin.append("<div class='item active'>");
		bodyBegin.append("<div class='carousel_img'>");
		bodyBegin.append("<div class='carousel-caption'>");
		bodyBegin.append("<div class='container'>");

		bodyBegin.append("<div class='row'>");
		bodyBegin.append("  <div class='col-xs-6 col-md-4'>&nbsp;</div>");
		bodyBegin.append(" <div class='col-xs-12 col-md-8 image-iphone'>&nbsp;</div>");
		bodyBegin.append("</div>");

		bodyBegin.append("</div>");

		bodyBegin.append("</div>");
		bodyBegin.append("</div>");
		bodyBegin.append("</div>");
		bodyBegin.append("</div>");
		bodyBegin.append("<a class='left carousel-control' href='#myCarousel' role='button' data-slide='prev'><span class='glyphicon glyphicon-chevron-left'></span></a>");
		bodyBegin.append("<a class='right carousel-control' href='#myCarousel' role='button' data-slide='next'><span class='glyphicon glyphicon-chevron-right'></span></a>");
		bodyBegin.append("</div><!-- /.carousel -->");


		return bodyBegin.toString();
	}


	public String createMap(){
		StringBuilder bodyBegin = new StringBuilder();

		bodyBegin.append("<div class='container'>");
		bodyBegin.append("<div class='row'>");
		bodyBegin.append("<div class='col-xs-12'><div id='map-canvas' style='height:500px; width:100%'></div></div>");
		bodyBegin.append("</div>");
		bodyBegin.append("</div>");

		return bodyBegin.toString();
	}




	public  String createBodyBegin(){
		StringBuilder bodyBegin = new StringBuilder();
		bodyBegin.append("<body>");
		return bodyBegin.toString();
	}



	public  String createBodyEnd(){
		StringBuilder bodyEnd = new StringBuilder();

		bodyEnd.append("<!-- Bootstrap core JavaScript");
		bodyEnd.append("================================================== -->");
		bodyEnd.append("<!-- Placed at the end of the document so the pages load faster -->");
		bodyEnd.append("<script src='https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js'></script>");
		bodyEnd.append("<script src='/res_html/bootstrap-3.2.0-dist/js/bootstrap.min.js'></script>");
		bodyEnd.append("</body>");

		return bodyEnd.toString();
	}


	public  String createNavigation(boolean isIndexPage){
		User currentUser = (User) session.getAttribute(HtmlRequestProcessor.CURRENT_USER);
		StringBuilder navigation = new StringBuilder();

		if (isIndexPage){
			navigation.append("<div class='navbar-wrapper'>");
		}else{
			navigation.append("<div class='navbar-wrapper-normal'>");

		}
		navigation.append("<div class='container'>");

		navigation.append("<div class='navbar navbar-inverse navbar-static-top' role='navigation'>");
		navigation.append("<div class='container'>");
		navigation.append("<div class='navbar-header'>");
		navigation.append("<button type='button' class='navbar-toggle' data-toggle='collapse' data-target='.navbar-collapse'>");
		navigation.append("<span class='sr-only'>Toggle navigation</span>");
		navigation.append("<span class='icon-bar'></span>");
		navigation.append("<span class='icon-bar'></span>");
		navigation.append("<span class='icon-bar'></span>");
		navigation.append("</button>");
		navigation.append("<a class='navbar-brand' href='#'><img src='/res_html/img/banner.png' width='150px' alt='SEEKRET'/></a>");
		navigation.append("</div>");
		navigation.append("<div class='navbar-collapse collapse'>");
		navigation.append("<ul class='nav navbar-nav'>");
		navigation.append("<li class='active'><a href='/'>Home</a></li>");

		if(currentUser.getEmail().equals(HtmlRequestProcessor.GUEST_USER.getEmail())){
			navigation.append("<li><a href='?showView=Login'>Login</a></li>");
		}else{
			navigation.append("<li><a href='?showView=TopSpots'>Top Spots</a></li>");
			navigation.append("<li><a href='?showView=SearchSpots'>Search Spot</a></li>");
			navigation.append("<li><a href='?showView=Logout'>Logout</a></li>");
		}
		navigation.append("</li>");
		navigation.append("</ul>");
		navigation.append("</div>");
		navigation.append("</div>");
		navigation.append("</div>");
		navigation.append("</div>");
		navigation.append("</div>");

		return navigation.toString();
	}



	public String getMarketingForMainPage(){

		StringBuilder marketing = new StringBuilder();

		marketing.append("  <!-- Marketing messaging and featurettes");
		marketing.append("================================================== -->");
		marketing.append("<!-- Wrap the rest of the page in another container to center all the content. -->");
		marketing.append("<div class='container marketing'>");
		marketing.append("<!-- Three columns of text below the carousel -->");
		marketing.append("<div class='row'>");
		marketing.append("<div class='col-lg-4'>");
		marketing.append("<img class='img-circle' src='data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==' alt='Generic placeholder image' style='width: 140px; height: 140px;'>");
		marketing.append("<h2>Heading</h2>");
		marketing.append("<p>Donec sed odio dui. Etiam porta sem malesuada magna mollis euismod. Nullam id dolor id nibh ultricies vehicula ut id elit. Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Praesent commodo cursus magna.</p>");
		marketing.append("<p><a class='btn btn-default' href='#' role='button'>View details &raquo;</a></p>");
		marketing.append("</div><!-- /.col-lg-4 -->");
		marketing.append("<div class='col-lg-4'>");
		marketing.append("<img class='img-circle' src='data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==' alt='Generic placeholder image' style='width: 140px; height: 140px;'>");
		marketing.append("<h2>Heading</h2>");
		marketing.append("<p>Duis mollis, est non commodo luctus, nisi erat porttitor ligula, eget lacinia odio sem nec elit. Cras mattis consectetur purus sit amet fermentum. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh.</p>");
		marketing.append("<p><a class='btn btn-default' href='#' role='button'>View details &raquo;</a></p>");
		marketing.append("</div><!-- /.col-lg-4 -->");
		marketing.append("<div class='col-lg-4'>");
		marketing.append("<img class='img-circle' src='data:image/gif;base64,R0lGODlhAQABAIAAAHd3dwAAACH5BAAAAAAALAAAAAABAAEAAAICRAEAOw==' alt='Generic placeholder image' style='width: 140px; height: 140px;'>");
		marketing.append("<h2>Heading</h2>");
		marketing.append("<p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>");
		marketing.append("<p><a class='btn btn-default' href='#' role='button'>View details &raquo;</a></p>");
		marketing.append("</div><!-- /.col-lg-4 -->");
		marketing.append("  </div><!-- /.row -->");
		
		return marketing.toString();
	}


	public String getFooter(){
		StringBuilder footerBuilder = new StringBuilder();
		footerBuilder.append("<!-- FOOTER -->");
		footerBuilder.append("  <footer>");
		footerBuilder.append("   <p class='pull-right'><a href='#'>Back to top</a></p>");
		footerBuilder.append("   <p>DVCS 2014 <a href='#'>Privacy</a> &middot; <a href='#'>Terms</a></p>");
		footerBuilder.append(" </footer>");
		footerBuilder.append(" </div><!-- /.container -->");
		
		return footerBuilder.toString();
	}









}