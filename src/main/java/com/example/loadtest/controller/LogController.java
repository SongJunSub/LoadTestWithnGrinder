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

    @GetMapping("/send/logs")
    public ResponseEntity<Object> sendAll() {
        long result = logService.sendAll();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}