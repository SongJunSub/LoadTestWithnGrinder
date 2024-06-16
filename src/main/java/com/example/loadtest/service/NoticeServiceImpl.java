package com.example.loadtest.service;

import com.example.loadtest.dto.Notice;
import com.example.loadtest.mapper.NoticeReadMapper;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Cacheable(value = "NoticeReadMapper.findAll")
    @Transactional
    public List<Notice> findAllNoticesUsingRedis() {
        return noticeReadMapper.findAll();
    }

    @Override
    @Cacheable(value = "NoticeReadMapper.findByPageUsingRedis", key = "#request.requestURI + '-' + #page + '-' + #pageSize", condition = "#page <= 5")
    public List<Notice> findByPageUsingRedis(HttpServletRequest request, int page, int pageSize) {
        page = (page - 1) * pageSize;

        return noticeReadMapper.findByPage(page, pageSize);
    }

    @Override
    @CachePut(value = "NoticeReadMapper.findAll", key = "#result.id")
    @Transactional
    public Notice saveNotice(Notice notice) {
        // noticeReadMapper.save(notice); // DB에 공지사항 저장 또는 업데이트

        // return notice;
        return null;
    }

    @Override
    @CacheEvict(value = "NoticeReadMapper.findAll", allEntries = true)
    @Transactional
    public void deleteNotice(Long id) {
        //noticeReadMapper.deleteById(id); // DB에서 공지사항 삭제
    }

}