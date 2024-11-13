
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

#덤프 백업 및 세팅 하는법
덤프 세팅
1. C:\Program Files\PostgreSQL\14\bin  (덤프는 해당 경로로 이동 시켜야함)
-> 관리자 cmd로 pg_restore -U postgres -h localhost -p 5432 -d DONATE_DB1 -c -F c DONATE_DB1_backup.dump
비번 입력1111

덤프 백업
1. C:\Program Files\PostgreSQL\14\bin    (덤프는 내스토리지에 옮겨야함)
->관리자 cmd로 pg_dump -U postgres -h localhost -p 5432 -d DONATE_DB1 -F c -f DONATE_DB1_backup.dump
비번 입력1111



