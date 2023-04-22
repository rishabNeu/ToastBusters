package application;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RecipeApi {
	static String MEALDB_URL = "https://www.themealdb.com/api/json/v2/9973533/filter.php?i=";
	static String MEALDB_URL_SEARCH_BY_INGREDIENT = "https://www.themealdb.com/api/json/v2/9973533/filter.php?i=";
	static String MEALDB_URL_SEARCH_BY_MEAL = "https://www.themealdb.com/api/json/v2/9973533/search.php?s=";
	static String SPECIFIC_MEAL_URL = "https://www.themealdb.com/api/json/v2/9973533/lookup.php?i=";
	
	public String callRecipeApi(String url,String query) {
		System.out.println(url.concat(query));

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url.concat(query)))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return response.body();
	}
	
	
	
	public String callApiForItems(String url,String query) {
		System.out.println(url.concat(query));
		StringBuilder sb = new StringBuilder(url);
		
		String[] arr=query.split(",");
		sb.append(arr[0]);
		
		for (int i = 1; i < arr.length; i++) {
			sb.append("&&i="+arr[i]);

		}
		
		System.out.println(sb.toString());

		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(sb.toString()))
				.method("GET", HttpRequest.BodyPublishers.noBody())
				.build();
		HttpResponse<String> response = null;
		try {
			response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return response.body();
	}
	
}