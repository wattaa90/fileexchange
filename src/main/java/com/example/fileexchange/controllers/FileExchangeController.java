package com.example.fileexchange.controllers;


import com.example.fileexchange.services.FileExchangeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@Slf4j
public class FileExchangeController {
    private final FileExchangeService fileExchangeService;
    private final RestTemplate restTemplate;


    public FileExchangeController(FileExchangeService fileExchangeService, RestTemplate restTemplate) {
        this.fileExchangeService = fileExchangeService;
        this.restTemplate = restTemplate;
    }

    @GetMapping(value = "/download")
    public void dowload(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.fileExchangeService.download(response);
    }


}
