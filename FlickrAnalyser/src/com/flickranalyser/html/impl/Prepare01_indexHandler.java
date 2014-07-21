package com.flickranalyser.html.impl;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.flickranalyser.html.common.GoogleAuthHelper;
import com.flickranalyser.persistence.datastore.save.PFSaverUser;
import com.flickranalyser.pojo.User;

public class Prepare01_indexHandler extends AbstractHtmlRequestHandler
{
  private static final Logger LOGGER = Logger.getLogger(Prepare01_indexHandler.class.getName());

  protected boolean isLoginRequired()
  {
    return false;
  }

  public String performActionAndGetNextViewConcrete(HttpServletRequest pRequest, HttpServletResponse pResponse, HttpSession pSession)
  {
    if ((pRequest.getParameter("code") != null) && (pRequest.getParameter("state") != null) && (pRequest.getParameter("state").equals(pSession.getAttribute("state")))) {
      GoogleAuthHelper helper = new GoogleAuthHelper();
      pSession.removeAttribute("state");
      try
      {
        User user = helper.getGoogleUserInfo(pRequest.getParameter("code"));
        PFSaverUser.saveUserToDatastore(user);
        pSession.setAttribute("currentUser", user);
      } catch (IOException e) {
        LOGGER.log(Level.SEVERE, "COULD NOT GET USER INFO FROM GOOGLE. " + pRequest);
      }
    }
    return "01_index";
  }
}