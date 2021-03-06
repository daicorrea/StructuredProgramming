package unirio.pm.gpx.rotine;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import unirio.pm.gpx.model.Track;
import unirio.pm.gpx.model.TrackPoint;
import unirio.pm.gpx.parser.GPXWriter;
import unirio.pm.gpx.parser.GPXReader;
import unirio.pm.gpx.rotine.TrackPointListFromTrack;
import unirio.pm.gpx.calc.TrackPointReducer;
import unirio.pm.gpx.rotine.TrackPointToTrack;

public class TrackReducer {
	
	static public void remover(String[] args) throws IOException, ParserConfigurationException, SAXException{
		
		String toReduce = args[0];
		String sourceFile = args[1];
		String destinationFile = args[2];
		
		//Creating object lists
		ArrayList<Track> trackList = new ArrayList<Track>();
		
		//Read GPX File
		trackList = GPXReader.ReadGPX(sourceFile);
		
		//Verify if the list is empty
		if(!emptyList(trackList)){
			System.out.println("There was an error while reading the file. Please verify it and try again later.");
			System.exit(0);
		}
		
		//Reducing the list
		trackList = reduceTrack(trackList, toReduce);
		
		//Write the new GPX File
		GPXWriter.gpxWriter(trackList, destinationFile);
		
		System.out.println("Your file is ready. Enjoy.");
	}
	
	//Verify if there is at least one Track in the list.
	static public boolean emptyList(ArrayList<Track> trackList){
		if (trackList.size() > 0){
			return true;
		} else {
			return false;
		}
	}
	
	//Reduce the Track
	static public ArrayList<Track> reduceTrack(ArrayList<Track> trackList, String toReduce) {
		
		//Creating list objects
		ArrayList<TrackPoint> trackPointList = new ArrayList<TrackPoint>();
		ArrayList<Track> newTrackList = new ArrayList<Track>();
		
		//Creating a loop to get into each Track
		for (Track track : trackList) { 
			
			//Count the TrackPoints from the Track before reducing
			track.countTrackPoints();
			
			//Get all the TrackPoints from the Track
			trackPointList = TrackPointListFromTrack.getTrackPointList(track);
					
			//Reduce TrackPoints
			//Verify if it's percentage
			if(toReduce.endsWith("%")) {
				
				//Change the characters to int without getting the %
				int percentageToReduce = Integer.parseInt(toReduce.substring(0, toReduce.length()-1));
				
				//Reducing using the percentage function
				trackPointList = TrackPointReducer.reducePointsByPercentage(trackPointList, percentageToReduce);
			
			} else {
				
				//If it's a distance in km, change it to double
				double distanceToReduce = Double.parseDouble(toReduce);
				
				//Reducing using the distance function
				trackPointList = TrackPointReducer.reducePointsByDistance(trackPointList, distanceToReduce);
			}
			
			//Adding the TrackPoints back to the Track
			track =  TrackPointToTrack.addingTrackPointsToTrack(track.getName(), trackPointList);
					
			//Count the TrackPoints from the track after reducing
			track.countTrackPoints();
				
			//Adding the changed Track to the new TrackList
			newTrackList.add(track);
		}
		
		return newTrackList;
	}
	

}
