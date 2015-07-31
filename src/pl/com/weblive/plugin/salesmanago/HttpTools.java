package pl.com.weblive.plugin.salesmanago;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class HttpTools 
{
	
	public static <T> HttpResponse httpPost(String url, T req)
	{
		CloseableHttpClient client = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		post.setHeader("Accept", "application/json, application/json");
        post.setHeader("Content-Type", "application/json;charset=UTF-8");
        
        HttpResponse response = null;
        Gson gson = new GsonBuilder().setDateFormat("yyyyyyyyy").create();
        try 
        {
			StringEntity stringEntity = new StringEntity(gson.toJson(req));
			post.setEntity(stringEntity);
			response = client.execute(post);
		} catch (IOException e) 
        {
			e.printStackTrace();
		}
        return response;
	}
	
	
	public static <T> T getResponse(HttpResponse resp, Class<T> c)
	{
		 T retresp = null;
		 try 
		 {
			BufferedReader br = new BufferedReader(
			           new InputStreamReader((resp.getEntity().getContent())));
			
			Gson gson = new GsonBuilder().setDateFormat("yyyyyyyyy").create();
			retresp = (T) gson.fromJson(br, c);			
		 } catch (IOException e) 
		 {
			e.printStackTrace();
		 }
		 
		 return retresp;
	}
}
