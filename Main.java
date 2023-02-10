import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello World");

        final String API_KEY = "e683ba2e3fc040489e8222836230902";
        Scanner input = new Scanner(System.in);
        
        System.out.println("Enter a city or Zip code: ");
        
        String cityOrZipCode = input.next(); //Fill this in with whatever location to retrieve weather data from
        String URL_literal = "http://api.weatherapi.com/v1/current.json?key="+API_KEY+"&q="+cityOrZipCode+"&aqi=no";

        URL url = new URL(URL_literal);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Connection", "keep-alive");
        int responseCode = connection.getResponseCode();
        System.out.println("Reponse Code: "+ responseCode);
        if(responseCode == HttpURLConnection.HTTP_OK){
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			// print result
			System.out.println(response.toString());
		} else {
			System.out.println("GET request did not work.");
		}
        }
}
