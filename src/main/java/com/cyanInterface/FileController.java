package com.cyanInterface;
/*
import org.apache.tomcat.jni.Time;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RestController
@RequestMapping("/api/v1")*/
public class FileController {
/*
    @Value( "${file.path}" )
    String filePath;

    @GetMapping("/v2")
    public String v2(int startId, int count)
    {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = "https://mea-itanz-itg.dev.cyanconnode.com/ws/meterSamples/";

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // add basic authentication
            headers.setBasicAuth("mdm", "qzqklTnb7BqVBdzb");


            // request body parameters
            Map<String, Object> map = new HashMap<>();

            map.put("startId", startId);
            map.put("count", count);

// build the request
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);


// send POST request
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {

                StringBuilder fileName = new StringBuilder(
                        filePath.concat("sceduledRead"+DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(LocalDateTime.now()).toString()+".json"));
                ;
                CreateFile(fileName.toString());
                WriteToFile(fileName.toString(),response.getBody());

                return response.getBody();
            } else {
                return "Login Failed";
            }
        }
        catch (Exception ex)
        {
            return ex.toString();
        }
    }

    public void CreateFile(String fileName) {
        File myObj = new File(fileName);
        try {
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());


            } else {
                System.out.println("File already exists."+fileName);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void WriteToFile(String fileName, String json) {
            try {
                FileWriter myWriter = new FileWriter(fileName);
                myWriter.write(json);
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }

 */
}
