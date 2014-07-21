package com.flickranalyser.pojo;

import java.io.Serializable;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.javadocmd.simplelatlng.LatLng;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PointOfInterest
  implements Serializable, Comparable<PointOfInterest>
{
  private static final long serialVersionUID = 1L;
  private int countOfViews;
  private LatLng location;
  private String pictureUrl;
  private Set<String> tags;

  public PointOfInterest(int countOfViews, LatLng location, String pictureUrl, Set<String> tags)
  {
    this.pictureUrl = pictureUrl;
    this.countOfViews = countOfViews;
    this.location = location;
    this.tags = tags;
  }

  public String getPictureUrl() {
    return this.pictureUrl;
  }

  public void setPictureUrl(String pictureUrl) {
    this.pictureUrl = pictureUrl;
  }
  public int getCountOfViews() {
    return this.countOfViews;
  }
  public void setCountOfViews(int countOfViews) {
    this.countOfViews = countOfViews;
  }
  public LatLng getLocation() {
    return this.location;
  }
  public void setLocation(LatLng location) {
    this.location = location;
  }

  public int compareTo(PointOfInterest comparePOI)
  {
    int compareCountOfViews = comparePOI.getCountOfViews();
    return compareCountOfViews - this.countOfViews;
  }

  public Set<String> getTags() {
    return this.tags;
  }
}