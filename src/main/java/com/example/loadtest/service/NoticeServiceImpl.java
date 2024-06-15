package com.example.loadtest.service;

import com.example.loadtest.dto.Notice;
import com.example.loadtest.mapper.NoticeReadMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {

    private final NoticeReadMapper noticeReadMapper;

    @Override
    public List<Notice> findAllNotices() {
        return noticeReadMapper.findAll();
    }

    @Override
    public List<Notice> findByPage(HttpServletRequest request, int page, int pageSize) {
        page = (page - 1) * pageSize;

        return noticeReadMapper.findByPage(page, pageSize);
    }

    @Override
    public List<Notice> findNoticesByDates(LocalDateTime startDate, LocalDateTime endDate) {
        return noticeReadMapper.findNoticesByDates(startDate, endDate);
    }

}