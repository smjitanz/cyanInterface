package com.cyanInterface;

import com.cyanParser.model.MeterSampleRequestModel;
import com.cyanParser.model.MeterSampleRequestModelParent;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import com.cyanParser.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping("/parsetoxml")
public class parseController {

    @Autowired
    private Environment env;

/*
    @GetMapping("/meterSamples")
    public String meterSamples(int startId, int count)
    {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = env.getProperty("metersample.url");

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // add basic authentication {will add encryption here or take this as parameter when running the code}
            headers.setBasicAuth("mdm", "qzqklTnb7BqVBdzb");


            // request body parameters
            Map<String, Object> map = new HashMap<>();

            map.put("startId", startId);
            map.put("count", count);

            // build the request
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);


            // send POST request
            assert url != null;
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                String filePath = env.getProperty("file.path");

                StringBuilder fileName = new StringBuilder(
                        filePath.concat("sceduledRead"+DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(LocalDateTime.now()).toString()+".json"));

                StringBuffer jsonResponse = new StringBuffer(Objects.requireNonNull(response.getBody()));
                jsonResponse.insert(0,"{usages:").append("}");
                CreateFile(fileName.toString());
                WriteToFile(fileName.toString(),jsonResponse);
                //api call to our parsing service
               // new jsonToXMLParser().parseMeasurementJson(jsonResponse,fileName,env);

                return response.getBody();
            } else {
                return "Login Failed";
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return ex.toString();
        }
    }
*/
    @PostMapping("/meterSamplesV2")
    public String meterSamplesV2(@RequestBody MeterSampleRequestModelParent requests)
    {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = env.getProperty("metersample.url");

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // add basic authentication {will add encryption here or take this as parameter when running the code}
            headers.setBasicAuth("mdm", "6yJLpWIi7qi8Mc7x");


            // request body parameters
            Map<String, Object> map = new HashMap<>();
            System.out.println("startID"+requests.getRequest().getStartId());
            System.out.println("count"+requests.getRequest().getCount());

            map.put("startId", requests.getRequest().getStartId());
            map.put("count", requests.getRequest().getCount());

            // build the request
            HttpEntity<Map<String, Object>> requestBody = new HttpEntity<>(map, headers);


            // send POST request
            assert url != null;
            ResponseEntity<String> response = restTemplate.postForEntity(url, requestBody, String.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                String filePath = env.getProperty("file.path");

                StringBuilder fileName = new StringBuilder(
                        filePath.concat("sceduledRead"+DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(LocalDateTime.now()).toString()+".json"));

                StringBuffer jsonResponse = new StringBuffer(Objects.requireNonNull(response.getBody()));
                jsonResponse.insert(0,"{usages:").append("}");
               // CreateFile(fileName.toString());
                // WriteToFile(fileName.toString(),jsonResponse);
                //api call to our parsing service
                jsonToXMLParser jsonToXmlParser = new jsonToXMLParser();
                //jsonToXmlParser.parseMeasurementJson(jsonResponse,fileName,env);
                jsonToXmlParser.parseMeasurementJsonV2(jsonResponse,fileName,env);
                String usageCount = jsonToXmlParser.GetUsagesCount(jsonResponse);
                //return response.getBody();
                return usageCount;
            } else {
                return "0";
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "ERROR:"+ex.toString();
        }
    }
/*
    @GetMapping("/events")
    public String events(int startId, int count)
    {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = env.getProperty("events.url");

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // add basic authentication {will add encryption here or take this as parameter when running the code}
            headers.setBasicAuth("mdm", "qzqklTnb7BqVBdzb");


            // request body parameters
            Map<String, Object> map = new HashMap<>();

            map.put("startId", startId);
            map.put("count", count);

            // build the request
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);


            // send POST request
            assert url != null;
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                String filePath = env.getProperty("file.path");

                StringBuilder fileName = new StringBuilder(
                        filePath.concat("sceduledEvents"+DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(LocalDateTime.now())+".json"));

                StringBuffer jsonResponse = new StringBuffer(Objects.requireNonNull(response.getBody()));
                jsonResponse.insert(0,"{events:").append("}");
                CreateFile(fileName.toString());
                WriteToFile(fileName.toString(),jsonResponse);
                new jsonToXMLParser().parseEventsJson(jsonResponse,fileName,env);

                return response.getBody();
            } else {
                //add more logic here
                return "Login Failed";
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return ex.toString();
        }
    }
*/
    @PostMapping("/eventsV2")
    public String eventsV2(@RequestBody MeterSampleRequestModelParent requests)
    {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = env.getProperty("events.url");

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // add basic authentication {will add encryption here or take this as parameter when running the code}
            headers.setBasicAuth("mdm", "6yJLpWIi7qi8Mc7x");


            // request body parameters
            Map<String, Object> map = new HashMap<>();

            map.put("startId", requests.getRequest().getStartId());
            map.put("count", requests.getRequest().getCount());

            // build the request
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);


            // send POST request
            assert url != null;
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                String filePath = env.getProperty("file.path");

                StringBuilder fileName = new StringBuilder(
                        filePath.concat("sceduledEvents"+DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(LocalDateTime.now())+".json"));

                StringBuffer jsonResponse = new StringBuffer(Objects.requireNonNull(response.getBody()));
                jsonResponse.insert(0,"{events:").append("}");
                CreateFile(fileName.toString());
                WriteToFile(fileName.toString(),jsonResponse);
                //api call to our parsing service
                jsonToXMLParser jsonToXmlParser = new jsonToXMLParser();
                jsonToXmlParser.parseEventsJson(jsonResponse,fileName,env);
                String eventsCount = jsonToXmlParser.GetEventsCount(jsonResponse);
                return eventsCount;
            } else {
                //add more logic here
                return "0";
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "ERROR:"+ex.toString();
        }
    }

    @PostMapping("/alarmsV2")
    public String alarmsV2(@RequestBody MeterSampleRequestModelParent requests)
    {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String url = env.getProperty("alarms.url");

            // create headers
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

            // add basic authentication {will add encryption here or take this as parameter when running the code}
            headers.setBasicAuth("mdm", "6yJLpWIi7qi8Mc7x");


            // request body parameters
            Map<String, Object> map = new HashMap<>();

            map.put("startId", requests.getRequest().getStartId());
            map.put("count", requests.getRequest().getCount());

            // build the request
            HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);


            // send POST request
            assert url != null;
            ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

            // check response
            if (response.getStatusCode() == HttpStatus.OK) {
                String filePath = env.getProperty("file.path");

                StringBuilder fileName = new StringBuilder(
                        filePath.concat("sceduledAlarms"+DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss").format(LocalDateTime.now())+".json"));

                StringBuffer jsonResponse = new StringBuffer(Objects.requireNonNull(response.getBody()));
                jsonResponse.insert(0,"{events:").append("}");
                CreateFile(fileName.toString());
                WriteToFile(fileName.toString(),jsonResponse);
                //api call to our parsing service
                jsonToXMLParser jsonToXmlParser = new jsonToXMLParser();
                jsonToXmlParser.parseAlarmsJson(jsonResponse,fileName,env);
                String alarmsCount = jsonToXmlParser.GetAlarmsCount(jsonResponse);
                return alarmsCount;
            } else {
                //add more logic here
                return "0";
            }
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return "ERROR:"+ex.toString();
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
    public void WriteToFile(String fileName, StringBuffer jsonBody) {
            try {
                FileWriter myWriter = new FileWriter(fileName);
                myWriter.write(jsonBody.toString());
                myWriter.close();
                System.out.println("Successfully wrote to the file.");
            } catch (IOException e) {
                System.out.println("An error occurred.");
                e.printStackTrace();
            }
    }
}

