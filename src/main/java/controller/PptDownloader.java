package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.Dsl;
import org.asynchttpclient.HttpResponseBodyPart;
import org.asynchttpclient.Response;

import view.EventView;


public class PptDownloader  {
	
	private String fileURL;
	private File localFile;
	private EventView view;
	
	private String idFile;

	public void setFileURL(String fileURL) {
		this.fileURL = fileURL;
	}

	public void setLocalFile(File localFile) {
		this.localFile = localFile;
	}
	
	public void setIdFile(String idFile) {
		this.idFile = idFile;
	}

	public void downloadFile() {
		
		try {
            //archivo a descargar
            final String ORIGEN = fileURL;
			/*
			 * File dataDirectory = new File(TO_FOLDER); if(!dataDirectory.exists())
			 * dataDirectory.mkdir(); File newDirectory = new File(TO_FOLDER+"/"+
			 * fileName.substring(0, fileName.indexOf("."))); newDirectory.mkdir();
			 * FileOutputStream stream = new FileOutputStream(TO_FOLDER+"/"+
			 * fileName.substring(0, fileName.indexOf(".")) +"/"+fileName);
			 */
            
            FileOutputStream stream = new FileOutputStream(localFile);
            
            Long fileLength = getLengthFile(ORIGEN);
            
            AsyncHttpClient client = Dsl.asyncHttpClient(Dsl.config().setRequestTimeout(-1));  
            
            AsyncCompletionHandler<FileOutputStream> asyncHandler = new AsyncCompletionHandler<FileOutputStream>() {
            	Long downloaded= 0l;
            	
            	
                @Override
                public AsyncHandler.State onBodyPartReceived(HttpResponseBodyPart bodyPart)
                        throws Exception {

                    //escribe en archivo parte por parte                   
                    stream.getChannel().write(bodyPart.getBodyByteBuffer());
                    Long num= (long) (bodyPart.length()*100000);
                    Long percent = 0l;
                    if((double)num/fileLength>0d && (double)num/fileLength<1d) {
                    	percent=1l;
                    }
                    else {
                    	percent=(long)num/fileLength;
                    }
                    System.out.println("descargando de : "+idFile+" "+ downloaded);
                    Long[] array = new Long[]{downloaded+percent, downloaded,fileLength};
                    
                   
                    downloaded = downloaded+percent;
                    return AsyncHandler.State.CONTINUE;
                }

                @Override
                public FileOutputStream onCompleted(Response response)
                        throws Exception {
                    System.out.println("Descarga completa");
                    return stream;
                }
                
                
            };

            FileOutputStream fos = client.prepareGet(ORIGEN).execute(asyncHandler).get();
            fos.close();
            
            client.close();
        } catch (FileNotFoundException ex) {
            System.err.println(ex.getMessage());
        } catch (InterruptedException | ExecutionException | IOException ex) {
            System.err.println(ex.getMessage());
        }
		
	}
	
	// Devuelve la longitud del archivo especificado en el string
		private Long getLengthFile(String origen) {
	    	Long size ;
	        URL url;        
			try {
				//genero URL y creo la conexion
				url = new URL(origen);
				HttpURLConnection conn = (HttpURLConnection)url.openConnection();
				conn.setRequestMethod("HEAD");
				conn.getInputStream();
				
				//obtengo la longitud en Long
	            size = conn.getContentLengthLong();
	            
	            //cierro el stream
	            conn.getInputStream().close();
	            return size;

			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				System.out.println("Malformed url exception");
				return 0l;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Malformed io exception");
				e.printStackTrace();

				return 0l;
			}

		}
		
		
}
