package com.example.loadtest.controller;

import com.example.loadtest.dto.Notice;
import com.example.loadtest.service.NoticeService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class NoticeController {

    private final NoticeService noticeService;

    @GetMapping("/notice")
    public ResponseEntity<Object> findAll() {
        List<Notice> result = noticeService.findAllNotices();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/notice/{page}/{pageSize}")
    public ResponseEntity<Object> findByPage(HttpServletRequest request,
                                             @PathVariable("page") Integer page,
                                             @PathVariable("pageSize") Integer pageSize) {
        List<Notice> result = noticeService.findByPage(request, page, pageSize);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/notice/dates")
    public ResponseEntity<Object> findNoticesByDates(@RequestParam("startDate") String startDate,
                                                     @RequestParam("endDate") String endDate) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        List<Notice> result = noticeService.findNoticesByDates(
                LocalDateTime.parse(startDate, dateTimeFormatter),
                LocalDateTime.parse(endDate, dateTimeFormatter)
        );

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}