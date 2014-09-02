//
//  SpotViewController.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 8/29/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import UIKit

class SpotViewController: UIViewController,GPPSignInDelegate, EndpointControllerProtocol, UITableViewDelegate, UITableViewDataSource, UITabBarDelegate{
    
    @IBOutlet weak var spotNameLabel: UILabel!
    @IBOutlet weak var clusterTableView: UITableView!
  
    
    
    @IBOutlet weak var tabBar: UITabBar!
    
    var uiHelper:UIHelper!
    var spotName: String?
    var cluster: [GTLSpotAPICluster]!
    var currentUrl: String?
    var currentCell:ClusterImageCellView?
    var auth: GTMOAuth2Authentication!
    
    override func viewDidLoad() {
        super.viewDidLoad()
         self.automaticallyAdjustsScrollViewInsets = false;
        self.clusterTableView.delegate = self
        self.clusterTableView.dataSource = self
        self.navigationItem.title = spotName

        performSilentLogin();
    }
    
      override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    
    /// We have to do the Login here, because it is only possible in subclasses from UIViewController.
    /// Sad but true ...
    func performSilentLogin(){
        //Silent Sign In
        var signIn = GPPSignIn.sharedInstance()
        signIn.delegate = self
        if (!signIn.trySilentAuthentication()) {
            println("NOT LOGGED IN")
        } else {
            println("LOGGED IN")
        }
    }
    
    func tableView(tableView: UITableView!, numberOfRowsInSection section: Int) -> Int {
        if (cluster != nil){
            return self.cluster.count
        }
        return 0;
    }
    
    func tableView(tableView: UITableView!, cellForRowAtIndexPath indexPath: NSIndexPath!) -> UITableViewCell! {
        let cell: ClusterImageCellView = clusterTableView.dequeueReusableCellWithIdentifier("ClusterImageIdent") as ClusterImageCellView
        if (cluster != nil){
            var indexRow = indexPath.row
            
            debugPrintln("Current Row index \(indexRow)")
            debugPrintln("Current Cluster Count \(cluster.count)")
            
            assert(indexRow < cluster.count, "PROBLEM APPEARED")
            
            var currentCluster = cluster[indexRow]
            var currentClusterUrls = currentCluster.urlOfMostViewedPicture as? [String]
            
            if (currentClusterUrls != nil){
                cell.counter = 0
                cell.urls = currentClusterUrls
                cell.setCellViewPicture()
                cell.clusterDataStoreKey = currentCluster.datastoreClusterKey
                cell.touristicnessValue = currentCluster.overallTouristicnessInPointsFrom1To10
                if (currentCluster.name != nil){
                    cell.setAdress(currentCluster.name)
                }else{
                    cell.setAdress("")
                }
                if (currentCluster.overallTouristicnessInPointsFrom1To10 != nil){
                    cell.setTouristicnessValue()
                    
                }
            }
            
            var swipeRight = UISwipeGestureRecognizer(target: self, action: "respondToSwipeGesture:")
            swipeRight.direction = UISwipeGestureRecognizerDirection.Right
            cell.addGestureRecognizer(swipeRight)
            
            var swipeDown = UISwipeGestureRecognizer(target: self, action: "respondToSwipeGesture:")
            swipeDown.direction = UISwipeGestureRecognizerDirection.Left
            cell.addGestureRecognizer(swipeDown)
        }
        
        return cell
    }
    
    func respondToSwipeGesture(gesture: UIGestureRecognizer) {
        
        var swipeLocation: CGPoint = gesture.locationInView(self.clusterTableView)
        var swipedIndexPath: NSIndexPath = self.clusterTableView.indexPathForRowAtPoint(swipeLocation)
        var swipedCell: ClusterImageCellView = self.clusterTableView.cellForRowAtIndexPath(swipedIndexPath) as ClusterImageCellView
        
        
        
        
        if let swipeGesture = gesture as? UISwipeGestureRecognizer {
            
            switch swipeGesture.direction {
            case UISwipeGestureRecognizerDirection.Right:
                println("Swiped right")
                swipedCell.setPreviousPicture()
            case UISwipeGestureRecognizerDirection.Left:
                swipedCell.setNextPicture()
                println("Swiped Left")
            default:
                break
            }
        }
    }
    func finishedWithAuth(auth: GTMOAuth2Authentication,  error: NSError? ) -> Void{
        if error != nil{
            debugPrintln("AUTH WENT WRONG")
        }else{
            debugPrintln("FINISHED WITH AUTH")
            self.uiHelper = UIHelper(uiView: self.view)
            uiHelper.showSpinner("Fetching Cluster")
            let endpointController = EnpointController(delegate: self);
            endpointController.getCluster(spotName!, auth: auth)
            self.auth = auth
        }
    }
    
      
 
    
    func didRecieveCluster(cluster: [GTLSpotAPICluster], spotName: String){
        self.spotNameLabel.font = UIFont (name: "HelveticaNeue-UltraLight", size: 20)
        self.spotNameLabel.text = spotName
        self.cluster = cluster;
        self.clusterTableView.reloadData()
        uiHelper.stopSpinner()
    }
    
    override func prepareForSegue(segue: UIStoryboardSegue!, sender: AnyObject!) {
        
        if (segue.identifier == "showDetailedClusterView"){
            var indexPath: NSIndexPath = self.clusterTableView.indexPathForSelectedRow()
            var detailedClusterViewController:DetailedClusterViewController = segue.destinationViewController as DetailedClusterViewController
            var currentCluster = cluster[indexPath.row]
            detailedClusterViewController.cluster = currentCluster
            detailedClusterViewController.auth = self.auth

        }
    }
   

    
    
}
