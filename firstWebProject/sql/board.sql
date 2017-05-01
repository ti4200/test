-- 답변형 게시글용 테이블
DROP TABLE BOARD CASCADE;

CREATE TABLE BOARD(
    BOARD_NUM NUMBER,
    BOARD_TITLE VARCHAR2(50) NOT NULL,
    BOARD_WRITER VARCHAR2(15) NOT NULL,
    BOARD_CONTENT VARCHAR2(2000),
    BOARD_DATE DATE DEFAULT SYSDATE,
    BOARD_READCOUNT NUMBER DEFAULT 0,
    ORIGINAL_FILENAME VARCHAR2(300),
    RENAME_FILENAME VARCHAR2(300),
    BOARD_REF NUMBER NOT NULL,
    BOARD_LEV NUMBER NOT NULL,
    BOARD_SEQ NUMBER NOT NULL,
    BOARD_DELETE_YN CHAR(1) DEFAULT 'N' NOT NULL,
    CONSTRAINT PK_BOARD PRIMARY KEY (BOARD_NUM),
    CONSTRAINT FK_BOARD_WRITER FOREIGN KEY (BOARD_WRITER) REFERENCES MEMBER (USERID),
    CONSTRAINT FK_BOARD_REF FOREIGN KEY (BOARD_REF) REFERENCES BOARD (BOARD_NUM)
);

-- 컬럼에 대한 설명
COMMENT ON COLUMN BOARD.BOARD_NUM IS '게시글 번호';
COMMENT ON COLUMN BOARD.BOARD_TITLE IS '게시글 제목';
COMMENT ON COLUMN BOARD.BOARD_WRITER IS '게시글 작성자아이디';
COMMENT ON COLUMN BOARD.BOARD_CONTENT IS '게시글 내용';
COMMENT ON COLUMN BOARD.BOARD_DATE IS '게시글 작성날짜';
COMMENT ON COLUMN BOARD.BOARD_READCOUNT IS '게시글 조회수';
COMMENT ON COLUMN BOARD.ORIGINAL_FILENAME IS '게시글 첨부파일 원래이름';
COMMENT ON COLUMN BOARD.RENAME_FILENAME IS '게시글 첨부파일 바뀐이름';
COMMENT ON COLUMN BOARD.BOARD_REF IS '참조할 원글 번호';
-- 원글, 답글 모두 다 참조하는 원글 번호를 기록함
COMMENT ON COLUMN BOARD.BOARD_LEV IS '답글 레벨';
-- 원글은 0, 원글의 답글은 1, 답글의 답글 2
COMMENT ON COLUMN BOARD.BOARD_SEQ IS '답글 순번';
-- 원글은 0, 답글은 1, 2, 3, ... 순으로 순번을 매김, 최근에 올린글이 무조건 1이 되게 해야함
-- 답글이 추가될 때 기존의 값을 1씩 증가시키는 처리가 필요함 : UPDATE 사용후 INSERT 함
COMMENT ON COLUMN BOARD.BOARD_DELETE_YN IS '게시글 삭제여부';

-- 샘플 데이터 생성함
INSERT INTO BOARD VALUES (1, '첫번째 게시물입니다.', 'admin', '답변형 게시판 첫번째 글입니다.', 
TO_DATE('2016/02/01', 'YYYY/MM/DD'), 12, NULL, NULL, 1, 0, 0, default);
INSERT INTO BOARD VALUES (2, '첫번째 게시물 답글입니다.', 'user01', '답변형 게시판 첫번째 글의 답글1 입니다.', 
TO_DATE('2016/02/01', 'YYYY/MM/DD'), 12, NULL, NULL, 1, 1, 3, default);
INSERT INTO BOARD VALUES (3, '두번째 게시물입니다.', 'user02', '답변형 게시판 두번째 글입니다.', 
TO_DATE('2016/02/03', 'YYYY/MM/DD'), 0, NULL, NULL, 3, 0, 0, default);
INSERT INTO BOARD VALUES (4, '첫번째 게시물 답글2입니다.', 'user02', '답변형 게시판 첫번째 글의 두번째 답글입니다.', 
TO_DATE('2016/02/05', 'YYYY/MM/DD'), 0, NULL, NULL, 1, 1, 1, default);
INSERT INTO BOARD VALUES (5, '첫번째 게시물 답글2의 답글입니다.', 'user01', '답변형 게시판 첫번째 글 두번째 답글의 답글입니다.', 
TO_DATE('2016/02/07', 'YYYY/MM/DD'), 1, NULL, NULL, 1, 2, 2, default);
INSERT INTO BOARD VALUES (6, '세번째 게시물입니다.', 'user01', '답변형 게시판 세번째 글입니다.', 
TO_DATE('2016/02/10', 'YYYY/MM/DD'), 5, NULL, NULL, 6, 0, 0, default);
INSERT INTO BOARD VALUES (7, '세번째 게시물 답글1입니다.', 'user02', '답변형 게시판 세번째 글의 첫번째 답글입니다.', 
TO_DATE('2016/02/20', 'YYYY/MM/DD'), 0, NULL, NULL, 6, 1, 1, default);
INSERT INTO BOARD VALUES (8, '네번째 게시물입니다.', 'user01', '답변형 게시판 네번째 글입니다.', 
TO_DATE('2016/02/23', 'YYYY/MM/DD'), 0, NULL, NULL, 8, 0, 0, default);
INSERT INTO BOARD VALUES (9, '다섯번째 게시물입니다.', 'user01', '답변형 게시판 다섯번째 글입니다.', 
TO_DATE('2016/02/29', 'YYYY/MM/DD'), 1, NULL, NULL, 9, 0, 0, default);
INSERT INTO BOARD VALUES (10, '네번째 게시물 답글입니다.', 'admin', '답변형 게시판 네번째 글의 답글입니다.', 
TO_DATE('2016/03/01', 'YYYY/MM/DD'), 2, NULL, NULL, 8, 1, 1, default);
INSERT INTO BOARD VALUES (11, '여섯번째 게시물입니다.', 'user01', '답변형 게시판 여섯번째 글입니다.', 
TO_DATE('2016/03/03', 'YYYY/MM/DD'), 7, NULL, NULL, 11, 0, 0, default);
INSERT INTO BOARD VALUES (12, '여섯번째 게시물의 답글입니다.', 'user01', '답변형 게시판 여섯번째 글의 답글입니다.', 
TO_DATE('2016/03/07', 'YYYY/MM/DD'), 5, NULL, NULL, 11, 1, 1, default);

DROP SEQUENCE SEQ_BNUM;

CREATE SEQUENCE SEQ_BNUM
START WITH 13
NOCACHE
NOCYCLE;

COMMIT;