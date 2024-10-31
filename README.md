
프론트는
1. 비쥬얼 스튜디오 설치
2. 깃 설치 
3. 노드 설치
4. 깃 허브 연결 
5. 비쥬얼 스튜디오에서 npm install ? 
6. npm run start  >


백엔드 연결 순서
1. 이클립스 다운로드  -> 스프맅부트 설치 (help > 마켓> 스프링 > 젤 인기많은걸로 )
2. windows > preferences -> java > installed jre 여기서 jdk11 넣어주자.
3. 깃허브 연결   - 유저 moonseongmin97 
5. postsql 설치 
6. postsql jdbc 연결 (프로젝트 우클릭 > 빌드패스> 익스터널 액티브 뭐시기> 자르 추가 후 빌드패스 젤 밑에꺼에서 라이브러리 추가된지 확인)
7. 데이터 복제는 나중에 하자 



쿼리

데이터베이스명 - DONATE_DB1


CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE users ALTER COLUMN id TYPE BIGINT;

INSERT INTO users (username, email) VALUES ('john_doe', 'john@example.com');
INSERT INTO users (username, email) VALUES ('jane_doe', 'jane@example.com');
INSERT INTO users (username, email) VALUES ('alice', 'alice@example.com');
