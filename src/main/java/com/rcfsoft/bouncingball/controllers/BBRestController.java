package com.rcfsoft.bouncingball.controllers;

import com.rcfsoft.bouncingball.BB;
import com.rcfsoft.bouncingball.BBGfx;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BBRestController {
    private static int counter = 0;
    @GetMapping(path = "/api/v1", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public static synchronized ResponseEntity<byte[]> bbGet() {
        counter++;
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "inline; name=img" + counter + ".bin")
                .header("X-BB-xSize", String.valueOf(BB.X_SIZE))
                .header("X-BB-ySize", String.valueOf(BB.Y_SIZE))
                .body(BBGfx.getByteBuffer());
    }
}
