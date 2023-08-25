package controller;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JOptionPane;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Room;
import propiedades.PropertiesManager;

public class EventNameManager {
	
	private HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
	private ObjectMapper mapper = new ObjectMapper();

	public ArrayList<Room> getAllRooms() {
		if (!PropertiesManager.getInstance().getProperty("server").isBlank() && !PropertiesManager.getInstance().getProperty("eventPrefix").isBlank()) {
			final HttpRequest requestPosts;
			if(PropertiesManager.getInstance().getProperty("server").equals("http://crm.congressrental.com:8888/idea")) { 
				requestPosts = HttpRequest.newBuilder().GET().uri(URI.create(PropertiesManager.getInstance().getProperty("server") + "/api/" + PropertiesManager.getInstance().getProperty("eventPrefix") +	"/salas/salas.json")).build(); 
			} 
			else { 
				requestPosts = HttpRequest.newBuilder().GET().uri(URI.create(PropertiesManager.getInstance().getProperty("server") + "/api/" + PropertiesManager.getInstance().getProperty("eventPrefix"))).build(); 
			} 
			try { 
				final HttpResponse<String> response = httpClient.send(requestPosts,HttpResponse.BodyHandlers.ofString()); //obtengo el arbol completo del JSON y creo un iterador 
				JsonNode root = mapper.readTree(response.body()); 
				Iterator<JsonNode> rootIterator = root.elements();

				// CREACION LISTA DE SALASs 
				ArrayList<Room> roomList = new ArrayList<Room>();
				while (rootIterator.hasNext()) { 
					Room room = new Room(); 
					JsonNode node = rootIterator.next(); 
					room.setId(node.get("id").asInt());
					room.setName(node.get("nombre").asText()); 
					roomList.add(room); 
				}

				return roomList; 
			} catch (IOException | InterruptedException e) {
				e.printStackTrace(); 
				JOptionPane.showMessageDialog(null, e.getMessage(),	"Error", JOptionPane.ERROR_MESSAGE); 
				return null; 
			} 
		} else 
			return null;

	}

}
