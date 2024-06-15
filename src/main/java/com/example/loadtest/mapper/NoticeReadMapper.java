package com.example.loadtest.mapper;

import com.example.loadtest.dto.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface NoticeReadMapper {

    List<Notice> findAll();

    List<Notice> findByPage(int page, int pageSize);

    List<Notice> findNoticesByDates(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}