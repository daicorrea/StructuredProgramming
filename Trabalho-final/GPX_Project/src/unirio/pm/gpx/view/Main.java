package unirio.pm.gpx.view;
import unirio.pm.gpx.parser.gpxParser;
//import java.io.Writer;

public class Main {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		gpxParser reader = new gpxParser();
		reader.gpxReader("../GPX_Project/src/unirio/pm/gpx/file/Century-2007-02-18.gpx");
	}
}