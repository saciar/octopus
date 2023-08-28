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

import model.Event;
import propiedades.PropertiesManager;

public class EventNameManager {
	
	private HttpClient httpClient = HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build();
	private ObjectMapper mapper = new ObjectMapper();

	public Event getEventData(String server, String codigo) {
		if (server!=null && codigo !=null) {
			final HttpRequest requestPosts;

			requestPosts = HttpRequest.newBuilder().GET().uri(URI.create(server + "/api/" + codigo)).build(); 

			try { 
				final HttpResponse<String> response = httpClient.send(requestPosts,HttpResponse.BodyHandlers.ofString()); //obtengo el arbol completo del JSON y creo un iterador 
				JsonNode root = mapper.readTree(response.body()); 
				
				Event evento = new Event();
				
				evento.setName(root.get("nombre").asText());
				evento.setLogo(root.get("logo").asText());
				evento.setCode(root.get("codigo").asText());
				
				//Iterator<JsonNode> rootIterator = root.elements();

				//ArrayList<Room> roomList = new ArrayList<Room>();
				//while (rootIterator.hasNext()) { 
				//	Room room = new Room(); 
				//	JsonNode node = rootIterator.next(); 
				//	room.setId(node.get("id").asInt());
				//	room.setName(node.get("nombre").asText()); 
				//	roomList.add(room); 
				//}

				return evento; 
			} catch (IOException | InterruptedException e) {
				e.printStackTrace(); 
				JOptionPane.showMessageDialog(null, e.getMessage(),	"Error", JOptionPane.ERROR_MESSAGE); 
				return null; 
			} 
		} else 
			return null;

	}

}
