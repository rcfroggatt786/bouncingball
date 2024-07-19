package com.rcfsoft.bouncingball.controllers;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.ByteBuffer;

@RestController
public class BBRestController {

    private byte[] getRawBuffer() {
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024);
        byte[] rawBuffer = new byte[1024];
        for (int i=0; i<1024; i++ ) {
            byteBuffer.put(i, (byte) (256 * Math.random()));
        }
        byteBuffer.rewind();
        byteBuffer.get(rawBuffer);
        return rawBuffer;
    }

    @GetMapping(path = "/api/v1", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> bbGet() {
        return ResponseEntity.ok().header("Content-Disposition", "inline; name=test.raw").body(getRawBuffer());
    }
}
