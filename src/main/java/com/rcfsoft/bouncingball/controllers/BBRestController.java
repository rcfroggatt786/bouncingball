package com.rcfsoft.bouncingball.controllers;

import com.rcfsoft.bouncingball.BB;
import com.rcfsoft.bouncingball.BBGfx;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BBRestController {
    private static int imgCount = 0;
    @GetMapping(path = "/api/v1", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public static ResponseEntity<byte[]> bbGet() {
        imgCount++;
        return ResponseEntity
                .ok()
                .header("Content-Disposition", "inline; name=img" + imgCount + ".bin")
                .header("X-BB-xSize", String.valueOf(BB.X_SIZE))
                .header("X-BB-ySize", String.valueOf(BB.Y_SIZE))
                .body(BBGfx.getByteBuffer());
    }
    @GetMapping(path = "/api/v1/start")
    public static ResponseEntity<String> bbGetStart() {
        BBGfx.init();
        return ResponseEntity.ok().body("Let's Begin!");
    }
}
