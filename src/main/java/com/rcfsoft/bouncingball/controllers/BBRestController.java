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
    private final byte[] byteBuffer = new byte[800*600*4];
    private int f = 0;
    private byte[] getByteBuffer() {
        for(int y=0; y<600; y++) {
            for (int x = 0; x < 800; x++) {
                byteBuffer[(x + y * 800) * 4    ] = (byte) ((x + f) % 256);
                byteBuffer[(x + y * 800) * 4 + 1] = (byte) ((y + 3*f) % 256);
                byteBuffer[(x + y * 800) * 4 + 2] = (byte) ((x + y + 2*f) % 256);
                byteBuffer[(x + y * 800) * 4 + 3] = (byte) 255;
            }
        }
        f++;

        return byteBuffer;
    }

    @GetMapping(path = "/api/v1", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> bbGet() {
        return ResponseEntity.ok().header("Content-Disposition", "inline; name=test.raw").body(getByteBuffer());
    }
}
