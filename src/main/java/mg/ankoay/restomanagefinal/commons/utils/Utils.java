package mg.ankoay.restomanagefinal.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import mg.ankoay.restomanagefinal.commons.model.User;

public class Utils {
	
	public static String capitalize(String txt) {
		return Character.toUpperCase(txt.charAt(0)) + txt.substring(1);
	}
	
	public static String putJSON(String url, String entity) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {
			HttpPut putRequest = new HttpPut(url);
			StringEntity strEntity = new StringEntity(entity);
			putRequest.setEntity(strEntity);
			putRequest.setHeader("Accept", "application/json");
			putRequest.setHeader("Content-type", "application/json");
			putRequest.setHeader("Authorization", "Bearer " + User.getInstance().getToken());


			HttpResponse response = httpClient.execute(putRequest);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}
			HttpEntity httpEntity = response.getEntity();
			String apiOutput = new String(EntityUtils.toByteArray(httpEntity), "UTF-8");
			return apiOutput;
		} catch (Exception ex) {
			throw ex;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}
	}

	public static String postJSON(String url, String entity) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();

		try {
			HttpPost postRequest = new HttpPost(url);
			StringEntity strEntity = new StringEntity(entity);
			postRequest.setEntity(strEntity);
			postRequest.setHeader("Accept", "application/json");
			postRequest.setHeader("Content-type", "application/json");
			postRequest.setHeader("Authorization", "Bearer " + User.getInstance().getToken());

			HttpResponse response = httpClient.execute(postRequest);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}
			HttpEntity httpEntity = response.getEntity();
			String apiOutput = new String(EntityUtils.toByteArray(httpEntity), "UTF-8");
			return apiOutput;
		} catch (Exception ex) {
			throw ex;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

	}

	public static String getJSON(String url) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		try {
			HttpGet getRequest = new HttpGet(url);
			getRequest.addHeader("accept", "application/json");
			getRequest.setHeader("Authorization", "Bearer " + User.getInstance().getToken());

			HttpResponse response = httpClient.execute(getRequest);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode != 200) {
				throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			}
			HttpEntity httpEntity = response.getEntity();
			String apiOutput = new String(EntityUtils.toByteArray(httpEntity), "UTF-8");

			return apiOutput;
		} catch (Exception ex) {
			throw ex;
		} finally {
			httpClient.getConnectionManager().shutdown();
		}

	}

}
