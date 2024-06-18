package com.example.loadtest.controller;

import com.example.loadtest.service.LogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LogController {

    private final LogService logService;

    @GetMapping("/send/sync/logs")
    public ResponseEntity<Object> sendAllUsingSync() {
        long result = logService.sendAllUsingSync();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/send/async/logs")
    public ResponseEntity<Object> sendAllUsingAsync() {
        long result = logService.sendAllUsingAsync();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}