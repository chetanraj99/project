package com.capstone.project.controller;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class RestconfController {
	
	@GetMapping("/getconfig")
	public void getConfig() throws Exception {
		// Replace with your device IP, username, and password
        String deviceIp = "172.20.0.89";
        String username = "admin";
        String password = "cisco123";

        // Set up credentials provider for basic authentication
        BasicCredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(
                new AuthScope(deviceIp, 830), // Default HTTP port
                new UsernamePasswordCredentials(username, password)
        );

        // Create an HTTP client with the credentials provider
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultCredentialsProvider(credentialsProvider)
                .build();

        // Create the HTTP GET request
        HttpGet request = new HttpGet("http://" + deviceIp + "/restconf/data/ietf-interfaces:interfaces");
        request.addHeader("Accept", "application/yang-data+json");

        // Execute the request and process the response
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            int status = response.getStatusLine().getStatusCode();
            if (status == 200) {
                String responseBody = EntityUtils.toString(response.getEntity());
                System.out.println("Response: " + responseBody);
            } else {
                System.err.println("Failed with HTTP error code: " + status);
            }
        } finally {
            // Close the HTTP client
            httpClient.close();
        }
    }
	
}
