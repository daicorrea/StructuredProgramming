package unirio.pm.gpx.view;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import unirio.pm.gpx.rotine.TrackReducer;


public class Main {
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		
		//Adicionar verificacao dos args da main
		TrackReducer.remover("../GPX_Project/src/unirio/pm/gpx/file/foxboro.gpx", "../GPX_Project/src/unirio/pm/gpx/file/NewFilefoxboro.gpx", "10%");
		
	}
}
