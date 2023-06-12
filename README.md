# board

## 최재강 개발자의 포트폴리오
🔥AWS로 서버배포한 환경 바로 가보기 클릭: 
http://ec2-43-200-120-235.ap-northeast-2.compute.amazonaws.com

<img width="500"  height="500"alt="Snipaste_2023-06-12_18-46-18" src="https://github.com/JaeKang20/board/assets/100588597/d4af4d4f-c4b9-40e4-8d4a-a82a5b335a15">
<img width="500"  height="500" alt="Snipaste_2023-06-01_22-09-23" src="https://github.com/JaeKang20/board/assets/100588597/78bee8df-b7a8-4267-9ba0-d0ef048a8dc4">



**🔥기획의도**

```
- 이력서 활용 (포트폴리오 및 게시판)
- 프론트·백엔드 기술환경 세팅 및 전체 기능 구현
■  Restful API를 사용한 백엔드 간의 데이터 송수신
■  MVC 패턴 학습

```
**🔥기능 분석**

``` 
◼ 회원 기능
·회원가입,로그인 기능

◼ 게시글 기능
·게시글 CRUD
·게시글 검색 기능 (제목, 내용, 글쓴이)
·게시글 등록 날짜 확인 가능
·게시글 좋아요 기능
·게시글 조회수 기능
·게시글 페이지 기능
·게시글 조회수 기능

◼ 관리자 기능
·관리자 공지 기능
·사용 규정 위반 포스팅 및 댓글 삭제 기능
·관리자 페이지 (TOP 10 게시글 확인)

◼ 신고센터 운영
· 신고 & 모니터링 기능

◼ 댓글 기능
· 댓글 CRUD 기능
· 댓글 수정 권한은 글쓴이만 가능
· 댓글 삭제 권한은 글쓴이와 관리자만 가능

```


**🔥시스템 구성**

```
1.프로젝트 - 최재강
1) 포트폴리오 pdf
2) 깃허브 URL
3) 프로젝트

2.프로젝트
  -회원가입, 로그인
  -일반 회원, 관리자 회원 구분
  
  ... 로그인 이후 ...
  - 게시판 CRUD -
  - 댓글 CRUD -
  - 게시판 공지 -  
  - 게시판 검색 -
  - 게시판 페이징 -
  - 게시판 신고하기 -
```

**🔥적용 기술 및 라이브러리**

```
- JAVA 17
- Spring Boot 2.5
- Junit 4
- DB: MySQL
- html
- JS
- BootStrap
- AWS EC2
- AWS RDS
```

**🔥테이블**

|      <b>    구분         |                                                                                                       <b>내용                                                                                                     |
|:---------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|        로그인 관리    |  ·HandlerMethodArgumentResolver를 이용한 회원가입,<br>◦특정 페이지는 로그인한 회원만 접속                                                                            |
|        게시글 관리    |     · CRUD, 조회수 기능    · 좋아요 기능 <br>    · 페이징 기능   <br>· 신고 기능                                                                                                                                                    · 댓글 기능|
|      관리자 페이지    |      ◦특정 페이지는 관리자만 접속  <br>        ○게시글 조회수 top10 리스트      <br>      ○게시글 추천수 top10 리스트  <br>  ○게시글 신고 top10 리스트  <br>  · ADMIN 계정은 공지글 게시 가능  <br>   · ADMIN 계정은 모든 게시물 및<br> 댓글 삭제 가능    |



**🔥ERD**

<img width="800"  height="500" alt="데이터베이스" src="https://github.com/JaeKang20/board/assets/100588597/d50c96ad-b91c-4e3c-a316-0ec98e5358d7">

