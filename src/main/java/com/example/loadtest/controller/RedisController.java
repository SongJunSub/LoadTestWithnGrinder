package com.example.loadtest.controller;

import com.example.loadtest.dto.Notice;
import com.example.loadtest.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/redis")
public class RedisController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public ResponseEntity<Object> findAllUsingRedis() {
        List<Notice> result = noticeService.findAllNoticesUsingRedis();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/notice/{page}/{pageSize}")
    public ResponseEntity<Object> findByPageUsingRedis(HttpServletRequest request,
                                             @PathVariable("page") Integer page,
                                             @PathVariable("pageSize") Integer pageSize) {
        List<Notice> result = noticeService.findByPageUsingRedis(request, page, pageSize);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}