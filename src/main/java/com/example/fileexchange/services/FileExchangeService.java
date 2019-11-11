package com.example.fileexchange.services;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface FileExchangeService {


    void  download(HttpServletResponse response) throws IOException;


}
