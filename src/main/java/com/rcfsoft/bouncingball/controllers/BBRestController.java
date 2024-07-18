package com.rcfsoft.bouncingball.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.ByteBuffer;

@RestController
public class BBRestController {
    @GetMapping(path = "/api/v1", produces = MediaType.IMAGE_PNG_VALUE)
    public byte[] bbGet(HttpServletResponse response){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        byte[] rawBuffer = new byte[1024];
        for (int i=0; i<1024; i++ ) {
            byteBuffer.put(i, (byte) (256 * Math.random()));
        }
        byteBuffer.get(rawBuffer);
        response.setHeader("Content-Disposition", "inline; filename=data.png");
        return rawBuffer;
    }
}
