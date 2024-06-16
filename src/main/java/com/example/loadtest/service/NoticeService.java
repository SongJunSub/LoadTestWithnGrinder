package com.example.loadtest.service;

import com.example.loadtest.dto.Notice;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.util.List;

public interface NoticeService {

    List<Notice> findAllNotices();

    List<Notice> findByPage(HttpServletRequest request, int page, int pageSize);

    List<Notice> findNoticesByDates(LocalDateTime startDate, LocalDateTime endDate);

    List<Notice> findAllNoticesUsingRedis();

}