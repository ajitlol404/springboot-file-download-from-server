package com.filedownload;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@Controller
public class AppController {

    @GetMapping("/")
    public String home()
    {
        return "index";
    }

    @GetMapping("/downloadFile")
    public void downloadFile(HttpServletResponse response) throws IOException {
        File file=new File("file\\hello.txt");

        response.setContentType("application/octet-stream");
        String headerKey="Content-Disposition";
        String headerValue="attachment; filename="+file.getName();

        response.setHeader(headerKey,headerValue);

        ServletOutputStream outputStream = response.getOutputStream();

        BufferedInputStream inputStream=new BufferedInputStream(new FileInputStream(file));

        byte[] buffer=new byte[8192];
        int bytesRead=-1;
        while ((bytesRead=inputStream.read(buffer))!=-1){
            outputStream.write(buffer, 0,bytesRead);
        }
        inputStream.close();
        outputStream.close();
    }

}
