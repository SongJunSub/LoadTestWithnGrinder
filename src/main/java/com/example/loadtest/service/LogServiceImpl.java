package com.example.loadtest.service;

import com.example.loadtest.dto.Notice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

@Service
@RequiredArgsConstructor
@Slf4j
public class LogServiceImpl implements LogService{

    private final NoticeService noticeService;

    // 원하는 스레드 풀 크기 설정
    int processors = Runtime.getRuntime().availableProcessors();
    // 최소한 2개의 스레드는 사용
    int threadPoolSize = Math.max(2, processors);
    ExecutorService customThreadPool = Executors.newWorkStealingPool(threadPoolSize);

    @Override
    public long sendAllUsingSync() {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        // common pool의 스레드 수를 확인한다.
        int commonPoolSize = commonPool.getParallelism();

        System.out.println("Common Pool Size: " + commonPoolSize);

        List<Notice> notices = noticeService.findAllNotices();
        long beforeTime = System.currentTimeMillis();

        // 동기 방식
        notices.forEach(notice -> sendLog(notice.getTitle()));

        long afterTime = System.currentTimeMillis();
        long diffTime = afterTime - beforeTime;

        log.info("실행 시간: " + diffTime + "ms");

        return diffTime;
    }

    @Override
    public long sendAllUsingAsync() {
        ForkJoinPool commonPool = ForkJoinPool.commonPool();
        // common pool의 스레드 수를 확인한다.
        int commonPoolSize = commonPool.getParallelism();

        System.out.println("Common Pool Size: " + commonPoolSize);

        List<Notice> notices = noticeService.findAllNotices();
        long beforeTime = System.currentTimeMillis();

        // 비동기 방식
        notices.forEach(notice ->
                CompletableFuture.runAsync(() ->
                        sendLog(notice.getTitle()), customThreadPool)
                        .exceptionally(throwable -> {
                            log.error("Exception Occurred: " + throwable.getMessage());

                            // 이슈 발생을 담당자가 인지할 수 있도록 추후 추가적인 코드가 필요하다.
                            return null;
                        })
        );

        long afterTime = System.currentTimeMillis();
        long diffTime = afterTime - beforeTime;

        log.info("실행 시간: " + diffTime + "ms");

        return diffTime;
    }

    public void sendLog(String message) {
        try {
            // 임의의 작업 시간을 주기위해 설정한다.
            // 각 작업의 소요시간을 Thread.sleep(1) 1ms로 대체한다.
            Thread.sleep(1);

            log.info("Message: " + message);
        }
        catch (Exception e) {
            log.error("Error Message: {}", e.getMessage());
        }
    }

}