/* This file was generated by the ServiceGenerator.
 * The ServiceGenerator is Copyright (c) 2014 Google Inc.
 */

//
//  GTLServiceSpotAPI.m
//

// ----------------------------------------------------------------------------
// NOTE: This file is generated from Google APIs Discovery Service.
// Service:
//   spotAPI/v1
// Description:
//   API for Spots.
// Classes:
//   GTLServiceSpotAPI (0 custom class methods, 0 custom properties)

#import "GTLSpotAPI.h"

@implementation GTLServiceSpotAPI

#if DEBUG
// Method compiled in debug builds just to check that all the needed support
// classes are present at link time.
+ (NSArray *)checkClasses {
  NSArray *classes = [NSArray arrayWithObjects:
                      [GTLQuerySpotAPI class],
                      [GTLSpotAPICluster class],
                      [GTLSpotAPIJsonMap class],
                      [GTLSpotAPIKey class],
                      [GTLSpotAPILatLng class],
                      [GTLSpotAPIPointOfInterest class],
                      [GTLSpotAPIResponse class],
                      [GTLSpotAPISpot class],
                      [GTLSpotAPISpotResultList class],
                      nil];
  return classes;
}
#endif  // DEBUG

- (id)init {
  self = [super init];
  if (self) {
    // Version from discovery.
    self.apiVersion = @"v1";

    // From discovery.  Where to send JSON-RPC.
    // Turn off prettyPrint for this service to save bandwidth (especially on
    // mobile). The fetcher logging will pretty print.
    self.rpcURL = [NSURL URLWithString:@"https://seekret-dev.appspot.com/_ah/api/rpc?prettyPrint=false"];
  }
  return self;
}

@end
