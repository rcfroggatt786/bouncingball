package com.rcfsoft.bouncingball.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BBRestController {
    private final int xSize = 800;
    private final int ySize = 600;
    private final int bytesPerPixel = 4;
    private final int bufferSize = xSize * ySize * bytesPerPixel;
    private final byte[] backgroundBuffer = initBackground();
    private final byte[] byteBuffer = new byte[bufferSize];
    private float xPos, yPos, xVel, yVel;

    private byte[] initBackground() {
        byte[] backBuffer = new byte[bufferSize];
        for(int y = 0; y < ySize; y++) {
            for (int x = 0; x < xSize; x++) {
                int offset = (x + y * xSize) << 2;
                backBuffer[offset++] = (byte) 0;
                backBuffer[offset++] = (byte) 0;
                backBuffer[offset++] = (byte) 0;
                backBuffer[offset  ] = (byte) 255;
            }
        }
        return backBuffer;
    }

    private byte[] getByteBuffer() {
        System.arraycopy(backgroundBuffer, 0, byteBuffer, 0, bufferSize );
        for(int y=0; y<600; y+=33) {
            for (int x = 0; x < 800; x+=17) {
                int offset = (x + y * 800) << 2;
//                byteBuffer[offset++] = (byte) 123;
//                byteBuffer[offset++] = (byte) 234;
//                byteBuffer[offset++] = (byte) 77;
//                byteBuffer[offset] = (byte) 255;
            }
        }
        return byteBuffer;
    }

    @GetMapping(path = "/api/v1", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> bbGet() {
        return ResponseEntity.ok().header("Content-Disposition", "inline; name=test.raw").body(getByteBuffer());
    }
}
