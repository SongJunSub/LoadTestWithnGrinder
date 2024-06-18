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
SHOW PROFILES;

# 해당 쿼리문의 수행 시간을 더 상세한 단위로 확인
SHOW PROFILE FOR QUERY 25;

# 해당 쿼리의 CPU 사용량을 분석
SHOW PROFILE CPU FOR QUERY 25;

# 쿼리에서 컬럼 값에 함수나 연산을 적용하면 인덱스가 효과적으로 사용되지 않을 수 있다.
EXPLAIN
SELECT * FROM NOTICE WHERE DATE_FORMAT(createDate, '%y-%m-%d') BETWEEN '20230115' AND '20230212';

# 인덱스 미적용
EXPLAIN
SELECT * FROM NOTICE WHERE createDate LIKE '%2023-12%';

# 인덱스 미적용
EXPLAIN
SELECT * FROM NOTICE WHERE createDate LIKE '%2023-12';

# 인덱스 적용
EXPLAIN
SELECT * FROM NOTICE WHERE createDate LIKE '2023-12%';

# NULL 값을 비교하는 경우 인덱스 미적용
EXPLAIN
SELECT * FROM NOTICE WHERE createDate IS NULL;

# IN 연산자를 사용하여 검색하는 경우, IN 목록의 개수가 많을수록 인덱스의 효과가 감소할 수 있다.
EXPLAIN
SELECT * FROM NOTICE WHERE createDate IN ('2023-01-15', '2023-03-01', '2023-03-15', ..., '2023-05-07', '2023-05-15', '2023-05-28');