package com.rcfsoft.bouncingball.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.ByteBuffer;

@RestController
public class BBRestController {
    @GetMapping(path = "/api/v1", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    @ResponseBody
    public byte[] bbGet(){
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        byte[] rawBuffer = new byte[1024];
        for (int i=0; i<1024; i++ ) {
            byteBuffer.put(i, (byte) (256 * Math.random()));
        }
        byteBuffer.get(rawBuffer);
        return rawBuffer;
    }
}
