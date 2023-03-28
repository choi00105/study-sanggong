package com.test.http;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class httpClientTest {
    public static void main(String[] args) throws Exception {
        
        String address = "http://127.0.0.1/jdbctest_memberRegistry";
        String address_get = "http://127.0.0.1/jdbctest_oracle.jsp";
        Map<String,String> params = new HashMap<>();
        
        
        params.put("userid","doommaker");
        params.put("username","김현미");
        params.put("age","24");
        String[] headers = {"content-type", "application/x-www-form-urlencoded"};
        String[] headers_get = {"content-type", "text/html"};

        HttpClientRun.post(address, params, headers);
        HttpClientRun.get(address_get, headers_get);
    }
}

class HttpClientRun {

    // GET
    public static void get(String address, String[] headers) throws InterruptedException, ExecutionException, URISyntaxException {
        HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).build();
        String result = client.sendAsync(
            HttpRequest.newBuilder(new URI(address)).GET().headers(headers).build(),
            HttpResponse.BodyHandlers.ofString()).thenApply(HttpResponse::body).get().toString();
        // System.out.println(result);
    }

    // POST
    public static void post(String address, Map<String,String> params, String[] header) throws Exception{
        BodyPublisher body = BodyPublishers.ofString(getFormDataAsString(params));
        HttpClient client = HttpClient.newBuilder().version(Version.HTTP_1_1).build();

        HttpResponse<String> response = client.send(
            HttpRequest.newBuilder(new URI(address)).POST(body).headers(header).build(),
            HttpResponse.BodyHandlers.ofString());

        System.out.println(response);
        System.out.println(address);
    }
    // Map으로 받은 값을 String으로 변경
    public static String getFormDataAsString(Map<String,String> formData) {
        StringBuilder formBuilder = new StringBuilder();
        for(Map.Entry<String,String> singleEntry : formData.entrySet()) {
            if(formBuilder.length() > 0) {
                formBuilder.append("&");
             }
             formBuilder.append(URLEncoder.encode(singleEntry.getKey(), StandardCharsets.UTF_8));
             formBuilder.append("=");
             formBuilder.append(URLEncoder.encode(singleEntry.getValue(), StandardCharsets.UTF_8));
             // userid=doommaker&username="김현미"&age=24
        }
        return formBuilder.toString();

    }
}