package com.example.fileexchange.services.impl;

import com.example.fileexchange.services.FileExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


@Service
@Slf4j
public class FileExchangeServiceImpl implements FileExchangeService {


    private final RestTemplate restTemplate;

    public FileExchangeServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public void download(HttpServletResponse response) throws IOException {

        log.info("Debut taitement Service :");

        HttpHeaders headers = new HttpHeaders();
        headers.add("applicationKey", "macle");
        RequestCallback requestCallback = new RequestCallback() {
            @Override
            public void doWithRequest(ClientHttpRequest clientHttpRequest) throws IOException {
                clientHttpRequest.getHeaders().addAll(headers);
            }
        };
        InputStream inputStream = restTemplate.execute("http://localhost:8081/download/1", HttpMethod.GET, requestCallback, clientHttpResponse -> {
            try (BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream())){
                IOUtils.copy( clientHttpResponse.getBody(), bos);
            }
            return clientHttpResponse.getBody();
        });
    }
}
