# 인덱스 확인
SHOW INDEX FROM NOTICE;

# idx_notice_createDate 인덱스 삭제
DROP INDEX idx_notice_createDate ON NOTICE;

# idx_notice_createDate 인덱스 삭제되었는지 확인
SHOW INDEX FROM NOTICE;

# idx_notice_createDate 인덱스 삭제 후 적용 안되는지 실행 계획 확인
EXPLAIN
SELECT * FROM NOTICE WHERE createDate BETWEEN '20230115' AND '20230212';

# idx_notice_createDate 인덱스 생성
CREATE INDEX idx_notice_createDate ON NOTICE (createDate);

# idx_notice_createDate 인덱스 생성 후 적용되었는지 실행 계획 확인
EXPLAIN
SELECT * FROM NOTICE WHERE createDate BETWEEN '20230115' AND '20230212';