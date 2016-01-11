package unirio.pm.gpx.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.xml.sax.SAXException;

import unirio.pm.gpx.calc.TrackPointReducer;
import unirio.pm.gpx.model.Track;
import unirio.pm.gpx.model.TrackPoint;
import unirio.pm.gpx.parser.GPXReader;

public class TestReducer {

	@Test
	//Verify if TrackPoint list is not empty passing parameters
	public void testEmptyList() {
		
		//Adding TrackPoints to the list
		ArrayList<TrackPoint> trackPointlist = new ArrayList<TrackPoint>();
		TrackPoint trackPoint = createTrackPoint("42.050664", "-71.267238", "63.584351", "2002-03-06T04:23:07Z");
		trackPointlist.add(trackPoint);
		
		//Testing
		assertFalse(TrackPointReducer.emptyList(trackPointlist));
	}
	
	@Test
	//Verify if the file has TrackPoints
	public void testEmptyListFromFile() {
		
		//Reading File
		ArrayList<Track> trackList = new ArrayList<Track>();
		String testFileName = "../GPX_Project/src/unirio/pm/gpx/file/foxboroTest.gpx";
		ArrayList<TrackPoint> trackPointsRead = new ArrayList<TrackPoint>();
  		
	    //Read GPX File
		try {
			trackList = GPXReader.ReadGPX(testFileName);
		} catch (IOException | ParserConfigurationException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    //Getting the TrackPoint list read
	    trackPointsRead = trackList.get(0).getSegments().get(0).getTrackPoints();
	    
	    //Creating and adding TrackPoints to a list
	    ArrayList<TrackPoint> trackPointlist = new ArrayList<TrackPoint>();
	    TrackPoint firstTrackPoint = createTrackPoint("42.050664", "-71.267238", "63.584351", "2002-03-06T04:23:07Z");	
	    trackPointlist.add(firstTrackPoint);
	    TrackPoint secTrackPoint = createTrackPoint("43.050664", "-72.267238", "64.584351", "2002-03-06T05:20:07Z");	
	    trackPointlist.add(secTrackPoint);
	    
		//Testing
	    assertFalse(TrackPointReducer.emptyList(trackPointlist));
	}
	
	//Creating new TrackPoint
	static private TrackPoint createTrackPoint(String latitude, String longitude, String ele, String time) {
		TrackPoint trackPoint = new TrackPoint(Float.valueOf(latitude),Float.valueOf(longitude),Float.valueOf(ele),time);	
		return trackPoint;
	}
	
}
