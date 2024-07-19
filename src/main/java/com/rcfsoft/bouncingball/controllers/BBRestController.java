package com.rcfsoft.bouncingball.controllers;

import com.rcfsoft.bouncingball.BB;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BBRestController {
    private final int bufferSize = BB.xSize * BB.ySize * BB.bytesPerPixel;
    private final byte[] backgroundBuffer = initBackground();
    private final byte[] byteBuffer = new byte[bufferSize];
    private float xPos, yPos, xVel, yVel;

    private byte[] initBackground() {
        byte[] backBuffer = new byte[bufferSize];
        for(int y = 0; y < BB.ySize; y++) {
            for (int x = 0; x < BB.xSize; x++) {
                int offset = (x + y * BB.xSize) * BB.bytesPerPixel;
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
        int offset = 0;
        for(int y = 0; y < BB.ySize; y++) {
            for (int x = 0; x < BB.xSize; x++) {
                byteBuffer[offset++] = (byte) 123;
                byteBuffer[offset++] = (byte) 234;
                byteBuffer[offset++] = (byte) 77;
                byteBuffer[offset++] = (byte) 255;
            }
        }
        return byteBuffer;
    }

    @GetMapping(path = "/api/v1", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> bbGet() {
        return ResponseEntity.ok().header("Content-Disposition", "inline; name=test.raw").body(getByteBuffer());
    }
}
