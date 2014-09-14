//
//  CustomerSeekretUIViewController.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 9/3/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.



import Foundation
import CoreLocation

// This class handles the complete login mechanism for all view controller within the app.
// Just inherit from this class and have fun with it :)

// REMEMBER!!! In Order to use the location manager we must add plist key value:
//<key>NSLocationAlwaysUsageDescription</key>
//<string>We need the location in order to find Seekrets nearby. In case you dont want to share you location app functionalities might be limited.</string>

class AbstractSeekretViewController: UIViewController, GPPSignInDelegate, CLLocationManagerDelegate{
    
    //Login Handler handels the entire login mechanism for us. It uses closures the simulate kind
    // of abstract methods for the subclasses.
    var abstractAuthenticationManager: AbstractAuthenticationManager!
    
    var uiHelper:MBProgressHUD!
    var auth: GTMOAuth2Authentication!
    var locationManager:CLLocationManager!;

    var usersCurrentlatitude:Double!
    var usersCurrentlongitude:Double!
    
    override func viewDidLoad() {
        self.abstractAuthenticationManager = AbstractAuthenticationManager(handleSucessfullLogin)
        self.abstractAuthenticationManager.performSilentLogin(self);
        locationManager = CLLocationManager()
    }
    
    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
    }
    
    func finishedWithAuth(auth: GTMOAuth2Authentication,  error: NSError? ) -> Void{
        self.auth = auth
        self.abstractAuthenticationManager.handleLogin(auth, error: error)
    }
    
    func handleSucessfullLogin(auth: GTMOAuth2Authentication) -> Void {
        fatalError("Must Override the function handleSucessfullLogin in the subclass.")
    }
    
    
     func didDisconnectWithError(error: NSError!) {
        if error != nil {
           NSLog("ERROR %@", error)
        }else{
             NSLog("LOGOUT WENT OKAY ")
            GPPSignIn.sharedInstance().signOut()
        }
    }
    
    
    func updateLocation() {
        locationManager.delegate = self
        locationManager.desiredAccuracy = kCLLocationAccuracyBest
        locationManager.requestAlwaysAuthorization()
        locationManager.startUpdatingLocation()
    }
    
     func locationManager(manager: CLLocationManager!, didUpdateLocations locations: [AnyObject]!) {
        println("locations = \(locationManager)")
        var latValue = locationManager.location.coordinate.latitude
        var lonValue = locationManager.location.coordinate.longitude
        self.usersCurrentlatitude = latValue
        self.usersCurrentlongitude = lonValue
        
        NSLog("STOPPING LOCATION UPDATES")
        locationManager.stopUpdatingLocation()
        
        NSLog("LOCATION: Latitude %f, Longitude %f ", latValue, lonValue)
    }
   

    
}