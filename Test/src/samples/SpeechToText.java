package samples;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;



public class SpeechToText {
	String url = "https://stream.watsonplatform.net/speech-to-text/api/v1/recognize";
	public static void main(String args[]){
		SpeechToText s2t= new SpeechToText();
		s2t.execute();		
	}
	void execute(){
 		//HttpClient  client = HttpClientBuilder.create().build();
 		//@SuppressWarnings("deprecation")
		HttpClient  client = new DefaultHttpClient();
 		
		HttpPost post = new HttpPost(url);

		// add header
		//post.setHeader("User-Agent", "Chrome");
		post.setHeader("Content-Type", "audio/flac");
		post.setHeader("Transfer-Encoding", "chunked");
		post.setHeader("Authorization", "Basic ZmRhMTVjNzUtMTllZC00NTMzLWE0YTYtNTQ0OTA1ZWI4NmNkOlhtTUhMUWlBV2Y2bw==");
		
		/*List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("text", "what is horlicks"));*/
		 File audioFile = new File("D:\\GM00512957\\project\\audio-file.flac");
		 //FileBody fileBody = new FileBody(audioFile, ContentType.DEFAULT_BINARY);
		 FileEntity entity = new FileEntity(audioFile);

		   
		try {
		//	post.setEntity(new UrlEncodedFormEntity(urlParameters));
		//	post.setEntity(new StringEntity(requestEntity));
			/*post.setEntity(new StringEntity(
					stringData,
				    ContentType.APPLICATION_JSON));*/
			
			 post.setEntity(entity);
			
			HttpResponse response = client.execute(post);
			System.out.println("Response Code : "
			                + response.getStatusLine().getStatusCode());

			BufferedReader rd = new BufferedReader(
			        new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = "";
			while ((line = rd.readLine()) != null) {
				result.append(line);
			}			
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	

		
	}
}
