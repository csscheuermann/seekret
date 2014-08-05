/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLSpotAPICluster.m
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   spotAPI/v1
// Description:
//   API for Spots.
// Classes:
//   GTLSpotAPICluster (0 custom class methods, 12 custom properties)

#import "GTLSpotAPICluster.h"

#import "GTLSpotAPIPointOfInterest.h"

// ----------------------------------------------------------------------------
//
//   GTLSpotAPICluster
//

@implementation GTLSpotAPICluster
@dynamic datastoreClusterKey, descriptionProperty, dismissCounter, latitude,
         longitude, name, numberOfPOIs, overallTouristicnessInPointsFrom1To10,
         overallTouristicnessVotes, overallViews, pointOfInterestList,
         urlOfMostViewedPicture;

+ (NSDictionary *)propertyToJSONKeyMap {
  NSDictionary *map =
    [NSDictionary dictionaryWithObject:@"description"
                                forKey:@"descriptionProperty"];
  return map;
}

+ (NSDictionary *)arrayPropertyToClassMap {
  NSDictionary *map =
    [NSDictionary dictionaryWithObjectsAndKeys:
      [GTLSpotAPIPointOfInterest class], @"pointOfInterestList",
      [NSString class], @"urlOfMostViewedPicture",
      nil];
  return map;
}

@end
