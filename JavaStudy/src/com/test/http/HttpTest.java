package com.test.http;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpTest {

    public static void main(String[] args) {
        String trgetURL = "http://127.0.0.1/jdbctest_memberRegistry";
        String parameters = "userid=mysky&username=최희주&age=34";
        TestHttpRequest.testHttpRequest(trgetURL, parameters);

    }    
}

class TestHttpRequest {

    public static void testHttpRequest(String targetURL, String parameters) {

        HttpURLConnection connection = null;

        try {
            URL url = new URL(targetURL);
            connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Language", "ko-KR"); //locale 표기법
            connection.setUseCaches(false);

            //OutputStream으로 POST 데이터를 전송
            connection.setDoOutput(true);

            //Request
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.write(parameters.getBytes("utf-8"));
            wr.flush();
            wr.close();

            //Response
            int responseCode = connection.getResponseCode();
            BufferedReader rd = 
             new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));

            StringBuilder response = new StringBuilder();
            String line;
            while((line = rd.readLine())!=null) {
                response.append(line).toString();
                response.append("\r\n");
            }
            rd.close();

            System.out.println("HTTP 응답 코드 : " + responseCode);
            System.out.println("HTTP 응답 헤더 : " + connection.getHeaderFields());
            System.out.println("HTTP Body : " + response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(connection != null)
                connection.disconnect();
        }


    }


}
