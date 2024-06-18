# 60502
SELECT COUNT(*) FROM NOTICE;

SELECT * FROM NOTICE WHERE createDate BETWEEN '20230301' AND '20230430';

# createDate 컬럼에 대한 인덱스 생성
CREATE INDEX idx_notice_createDate ON NOTICE (createDate);

# 인덱스 확인
SHOW INDEX FROM NOTICE;

SELECT * FROM NOTICE WHERE createDate BETWEEN '20230301' AND '20230430' AND WHO = 'incu21';

# 컬럼별 카디널리티 수치 확인
SELECT
    CONCAT(ROUND(COUNT(DISTINCT id) / COUNT(*) * 100, 2), '%') AS ID_CARDINALITY,
    CONCAT(ROUND(COUNT(DISTINCT title) / COUNT(*) * 100, 2), '%') AS TITLE_CARDINALITY,
    CONCAT(ROUND(COUNT(DISTINCT content) / COUNT(*) * 100, 2), '%') AS CONTENT_CARDINALITY,
    CONCAT(ROUND(COUNT(DISTINCT who) / COUNT(*) * 100, 2), '%') AS WHO_CARDINALITY,
    CONCAT(ROUND(COUNT(DISTINCT createDate) / COUNT(*) * 100, 2), '%') AS CREATEDATE_CARDINALITY,
    CONCAT(ROUND(COUNT(DISTINCT updateDate) / COUNT(*) * 100, 2), '%') AS UPDATEDATE_CARDINALITY
FROM NOTICE;

# 실행 계획을 통해 인덱스 확인
EXPLAIN
SELECT * FROM NOTICE WHERE createDate BETWEEN '20230301' AND '20230430';

# who 컬럼에 대한 인덱스 생성
CREATE INDEX idx_notice_who ON NOTICE (who);

# 인덱스 확인
SHOW INDEX FROM NOTICE;

# 실행 계획 확인
EXPLAIN
SELECT * FROM NOTICE WHERE who = 'incu19' AND createDate BETWEEN '20230115' AND '20230115';

# HINT를 사용하여 인덱스 변경 후 실행 계획 확인
EXPLAIN
SELECT * FROM NOTICE USE INDEX (idx_notice_who) WHERE who = 'incu19' AND createDate BETWEEN '20230115' AND '20230115';

# 프로파일링 기능 활성화 여부 확인
SHOW VARIABLES LIKE '%profiling%';

# 프로파일링 기능 활성화
SET profiling = 1;

# 프로파일링 히스토리 사이즈 100으로 증가
SET profiling_history_size = 100;

# 프로파일링 기능을 통해 확인하고자 하는 쿼리를 실행
SELECT * FROM NOTICE WHERE createDate BETWEEN '20230115' AND '20230214';

# 명령어 실행 후 실행한 쿼리를 찾아서 Query_id 찾기
SHOW prifiles;