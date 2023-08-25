package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JDialog;
import javax.swing.SwingWorker;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import downloaders.FileDownloader;
import model.Block;
import model.Date;
import model.Room;
import model.Speaker;
import propiedades.PropertiesManager;
import view.ErrorDialog;
import view.EventView;

public class EventManager2 extends SwingWorker<Void, String>{

	
	private HttpClient httpClient =	HttpClient.newBuilder().version(HttpClient.Version.HTTP_2).build(); 
	private ObjectMapper mapper = new ObjectMapper(); 
	private PptDownloader downloader;

	private ArrayList<Room> rooms = new ArrayList<Room>();
	
	private EventView view;
	//private Worker worker;
	
	//private ArrayList<Room> roomList = new ArrayList<Room>();

	public EventManager2(EventView v) { 
		mapper.registerModule(new Jdk8Module());
		mapper.registerModule(new JavaTimeModule()); 
		downloader = new PptDownloader(); 
		
		view = v;
		
		//worker = new Worker();
		this.execute();
	}

	
	public void getData() {
		HttpRequest request = createRequestData();
		if(request!=null) {
			String response = getResponse(request);
			if(response != null) {
				setData(response);
				
			}
		}
		
	}
	
	private HttpRequest createRequestData() {
		if(!PropertiesManager.getInstance().getProperty("server").isBlank() && !PropertiesManager.getInstance().getProperty("eventPrefix").isBlank() &&
				!PropertiesManager.getInstance().getProperty("salaId").isBlank()) { 
			final HttpRequest requestPosts = HttpRequest.newBuilder().GET().uri(URI.create(PropertiesManager.getInstance()
					.getProperty("server")+"/api/"+PropertiesManager.getInstance().getProperty("eventPrefix")+"/charlas/"+PropertiesManager.getInstance().getProperty("salaId")+".json")).build();
				return requestPosts;
		}else {
			return null;	
		}
	}
	
	private String getResponse(HttpRequest requestPosts){
		HttpResponse<String> response;
		try {
			response = httpClient.send(requestPosts, HttpResponse.BodyHandlers.ofString());
			return response.body();
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			ErrorDialog dialog = new ErrorDialog("Error de conexión");
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			return null;
		}
		
	}
	
	private void setData(String response) {
		JsonNode root;
		try {
			root = mapper.readTree(response);
			Iterator<String> rootFieldNames = root.fieldNames(); 
			Iterator<JsonNode> rootIterator = root.elements();
			ArrayList<Room> roomList = new ArrayList<Room>();
			setSalas(rootFieldNames,rootIterator, roomList);
			rooms = roomList;
			
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
	}

	private void setSalas(Iterator<String> rootFieldNames, Iterator<JsonNode> rootIterator, ArrayList<Room> roomList) {
		while(rootFieldNames.hasNext()) {
			//CREACION DE SALA 
			Room room = new Room();
			room.setId(Integer.parseInt(rootFieldNames.next())); //obtengo el nodo de Fechas y dos iteradores, uno para los nombres de campo (fecha de sala) y otro para el contenido 
			JsonNode dates = rootIterator.next(); 
			Iterator<JsonNode> dateIterator = dates.elements(); 
			Iterator<String> dateFieldNames = dates.fieldNames(); 
			//CREACION DE FECHAS 
			ArrayList<Date> datesList = new ArrayList<Date>();
			setFechas(dateIterator,dateFieldNames,datesList);
			room.setDates(datesList);
			roomList.add(room); 
		}
		
	}

	private void setFechas(Iterator<JsonNode> dateIterator, Iterator<String> dateFieldNames, ArrayList<Date> datesList) {
		while(dateFieldNames.hasNext()) { 
			Date date =	new Date(); 
			date.setName(dateFieldNames.next());
	
			//obtengo el nodo de Bloques y dos iteradores, uno para los nombres de campo (id de bloque) y otro para el contenido 
			JsonNode blocks = dateIterator.next(); 
			Iterator<JsonNode> blockIterator = blocks.elements();
							
			//CREACION DE BLOQUES 
			ArrayList<Block> blockList = new ArrayList<Block>();
			setBloques(blockIterator,blockList);
			date.setBlocks(blockList);
			datesList.add(date); 
		}
		
	}

	private void setBloques(Iterator<JsonNode> blockIterator, ArrayList<Block> blockList) {
		while(blockIterator.hasNext()) { 
			Block block = new Block();
			ArrayNode arraySpeakers = (ArrayNode) blockIterator.next(); 
			JsonNode auxBlock = arraySpeakers.findValue("bloque"); 
			block.setId(auxBlock.get("id").asInt());
			block.setName(auxBlock.get("nombre").asText());
			block.setInitBlock(LocalTime.parse(arraySpeakers.findValue("hora_ini").asText(), DateTimeFormatter.ofPattern("HH:mm:ss")));
			block.setEndBlock(LocalTime.parse(arraySpeakers.findValue("hora_fin").asText(), DateTimeFormatter.ofPattern("HH:mm:ss")));

			Iterator<JsonNode> speakersIterator = arraySpeakers.iterator();
			ArrayList<Speaker> speakersList = new ArrayList<Speaker>();
			setSpeakers(speakersIterator,speakersList);
			block.setSpeakers(speakersList);
			blockList.add(block);
		}
		
	}
	
	private void setProfileImage(Speaker speaker, JsonNode node) {
		if(!node.get("imagen").asText().equals("null") && !node.get("imagen").asText().equals("")) { 
			FileDownloader d = new FileDownloader(); 
			d.setFileName(node.get("imagen").asText());
			d.setSpeakerName(node.get("disertante").asText());
			d.setId(node.get("id").asInt());
			d.setFileUrl(PropertiesManager.getInstance().getProperty("server")+"/img/" + PropertiesManager.getInstance().getProperty("eventPrefix")+"/profile");
			try {
				speaker.setPicture(d.getFileFrom().getAbsolutePath());
			} catch (IOException e ) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				speaker.setPicture(EventManager.class.getResource("/user.png").getPath());
			}

		} else {
			speaker.setPicture(EventManager.class.getResource("/user.png").getPath()); 
		}
	}
	
	private void setQrImage(Speaker speaker, JsonNode node) {
		if(!node.get("qr").asText().equals("null")) { 
			QrDownloader qrdown = new QrDownloader(); 
			qrdown.setFileName(node.get("qr").asText());
			qrdown.setSpeakerName(node.get("disertante").asText());
			qrdown.setId(node.get("id").asInt());
			qrdown.setFileUrl(PropertiesManager.getInstance().getProperty("server") + "/img/" + PropertiesManager.getInstance().getProperty("eventPrefix")+"/qr");
			File dest;
			try {
				dest = qrdown.downloadImage();
				speaker.setQr(dest.getAbsolutePath()); 
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			
		}
		else { 
			speaker.setQr(null);								
		}
	}

	private void setSpeakers(Iterator<JsonNode> speakersIterator, ArrayList<Speaker> speakersList) {
		int cantleidos= 0;
		while(speakersIterator.hasNext()) { 
			cantleidos++;
			publish("Cargando "+cantleidos+" de "+speakersList.size());
			JsonNode node = speakersIterator.next();
			Speaker speaker = new Speaker(); 
			speaker.setId(node.get("id").asInt());
			speaker.setName(node.get("disertante").asText());
			speaker.setBio(node.get("bio").asText());
			speaker.setEmail(node.get("email").asText());
			speaker.setRole(node.get("rol").asText());

			setProfileImage(speaker, node);

			setQrImage(speaker, node);

			if(!node.get("imagenSponsor").asText().equals("null") && !node.get("imagenSponsor").asText().equals("")) { 
				SponsorDownloader sponsordown = new SponsorDownloader();
				sponsordown.setFileName(node.get("imagenSponsor").asText());
				sponsordown.setSpeakerName(node.get("disertante").asText());
				sponsordown.setId(node.get("id").asInt());
				sponsordown.setFileUrl(PropertiesManager.getInstance().getProperty("server") + "/img/" + PropertiesManager.getInstance().getProperty("eventPrefix")+"/sponsor"); 
				File dest = sponsordown.downloadImage();
				speaker.setSponsorImage(dest.getAbsolutePath()); 
			} else {
				speaker.setSponsorImage(null); 
			}

			speaker.setSponsorName(node.get("nombreSponsor").asText());
			speaker.setTitle(node.get("titulo").asText());
			if(!node.get("facebook").asText().equals("null") && !node.get("facebook").asText().equals("")) {
				speaker.setFacebookLink(node.get("facebook").asText()); 
			} else
				speaker.setFacebookLink(null);

			if(!node.get("instagram").asText().equals("null") && !node.get("instagram").asText().equals("")) {
				speaker.setInstagramLink(node.get("instagram").asText()); 
			} else
				speaker.setInstagramLink(null);

			if(!node.get("linkedIn").asText().equals("null") && !node.get("linkedIn").asText().equals("")) {
				speaker.setLinkedinLink(node.get("linkedIn").asText()); 
			} else
				speaker.setLinkedinLink(null);

			if(!node.get("youtube").asText().equals("null") && !node.get("youtube").asText().equals("")) {
				speaker.setYoutubeLink(node.get("youtube").asText()); 
			} else
				speaker.setYoutubeLink(null);

			if(!node.get("twitter").asText().equals("null") && !node.get("twitter").asText().equals("")) {
				speaker.setTwitterLink(node.get("twitter").asText()); 
			} else
				speaker.setTwitterLink(null);

			if(node.get("presentacion").get("id") != null) {
				speaker.setIdPresenation(node.get("presentacion").get("id").asInt());
				speaker.setNamePresentation(node.get("presentacion").get("nombreArchivo").asText());
				speaker.setUpdate(LocalDateTime.parse(node.get("presentacion").get("actualizado").asText(),DateTimeFormatter.ISO_DATE_TIME));
				speaker.setVirtual(node.get("presentacion").get("esVirtual").asBoolean());
				speaker.setExtension(node.get("presentacion").get("extension").asText());
				
				File directoryLocal = new File(PropertiesManager.getInstance().getProperty("data")+"/Octopus/data/presentations/"+ speaker.getId());
				if(directoryLocal.exists()) {
					try {
					File fileLocal = new File(PropertiesManager.getInstance().getProperty("data")+"/Octopus/data/presentations/"+ speaker.getId()+"/"+speaker.getIdPresenation()+speaker.getExtension());
					LocalDateTime local_last_mod = LocalDateTime.ofInstant(Instant.ofEpochMilli(fileLocal.lastModified()), ZoneId.systemDefault());
					
					System.out.println("--------------------Fecha ult modificacion local: "+local_last_mod);
					
					if(local_last_mod.compareTo(speaker.getUpdate())<0) {
						
						System.out.println("Modificacion en presentacion id: "+speaker.getIdPresenation());
						ZonedDateTime zdt = speaker.getUpdate().atZone(ZoneId.of("UTC"));
						fileLocal.setLastModified(zdt.toInstant().toEpochMilli());
						URL fetchWebsite = new URL(PropertiesManager.getInstance().getProperty("server_storage")+"/sync/"+PropertiesManager.getInstance().getProperty("eventPrefix")+"/"+speaker.getNamePresentation());

						File fileServer = new File(speaker.getNamePresentation());
						
						FileUtils.copyURLToFile(fetchWebsite, fileServer);
						try {
							
							if(FileUtils.contentEquals(fileServer, fileLocal)) {
								System.out.println("archivo de speaker id "+ speaker.getId()+" "+speaker.getName()+" iguales"); 
							} else { 
								 												
								FileUtils.copyFile(fileServer, fileLocal);
								
							} 
						} catch (Exception e) { // TODO:handle exception

						} finally { 
							fileServer.delete(); 
						} 
					}
					else {
						System.out.println("No hubo modificaciones en los archivos");
					}
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				else {
					
					if(directoryLocal.mkdirs()) {
						System.out.println("Empezando la descarga");
						File localFile = new File(PropertiesManager.getInstance().getProperty("data")+"/Octopus/data/presentations/"+ speaker.getId()+"/"+speaker.getIdPresenation()+speaker.getExtension());
						ZonedDateTime zdt = speaker.getUpdate().atZone(ZoneId.of("UTC"));
						localFile.setLastModified(zdt.toInstant().toEpochMilli());
						downloader.setLocalFile(localFile);
						downloader.setFileURL((PropertiesManager.getInstance().getProperty("server_storage")+"/sync/"+PropertiesManager.getInstance().getProperty("eventPrefix")+"/"+speaker.getNamePresentation()));
						downloader.setIdFile(speaker.getNamePresentation());
						downloader.downloadFile();
						System.out.println("Fin de la descarga");
					}
					
					
				}
					
				
			}
			speakersList.add(speaker);
		}
	}

	public ArrayList<Room> getRooms(){ 
		return rooms; 
	}

	@Override
	protected Void doInBackground() throws Exception {
		view.getProgressBar().setVisible(true);
		view.getProgressBar().setString("Cargando...");
		getData();			
		view.setData(getRooms());
		
		return null;
	}
	

	@Override
	protected void process(List<String> chunks) {
		// TODO Auto-generated method stub
		//view.getLblNombreEvento().setText(chunks.get(chunks.size()-1));
		
		view.getProgressBar().setString(chunks.get(chunks.size()-1));
	}



	@Override
	protected void done() {
		int indexTabSelected = view.getTabbedPane().getSelectedIndex();
		view.getTabbedPane().removeAll();
		view.rellenarTabla();
		if(indexTabSelected>0)
			view.getTabbedPane().setSelectedIndex(indexTabSelected);
		view.getProgressBar().setVisible(false);
		
		System.out.println("Ult. revisión: "+LocalTime.now().toString());
		view.setRunning(false);
		super.done();
	}

}
