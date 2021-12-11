package mg.ankoay.restomanagefinal.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class Utils {

	public static String getJSON(String url) throws Exception {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		getRequest.addHeader("accept", "application/json");
		HttpResponse response = httpClient.execute(getRequest);
		int statusCode = response.getStatusLine().getStatusCode();
		if (statusCode != 200) {
			throw new RuntimeException("Failed with HTTP error code : " + statusCode);
		}
		HttpEntity httpEntity = response.getEntity();
		String apiOutput = EntityUtils.toString(httpEntity);

		return apiOutput;
	}

}
