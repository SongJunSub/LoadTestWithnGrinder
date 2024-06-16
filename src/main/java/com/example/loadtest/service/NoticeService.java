package com.example.loadtest.service;

import com.example.loadtest.dto.Notice;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface NoticeService {

    List<Notice> findAllNotices();

    List<Notice> findByPage(HttpServletRequest request, int page, int pageSize);

    List<Notice> findNoticesByDates(LocalDateTime startDate, LocalDateTime endDate);

    List<Notice> findAllNoticesUsingRedis();

    List<Notice> findByPageUsingRedis(HttpServletRequest request, int page, int pageSize);

    Notice saveNotice(Notice notice);

    void deleteNotice(Long id);
}