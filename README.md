# board

## 최재강 개발자의 포트폴리오
🔥AWS로 서버배포한 환경 바로 가보기 클릭: 
http://ec2-43-200-120-235.ap-northeast-2.compute.amazonaws.com:8080/
<img width="671" alt="Snipaste_2023-05-30_12-18-36" src="https://github.com/JaeKang20/board/assets/100588597/9eeca09f-a241-43fa-850f-1522a7afe824">
<img width="656" alt="Snipaste_2023-05-30_12-18-44" src="https://github.com/JaeKang20/board/assets/100588597/bab67079-23d7-49bb-864d-ba81fde0fdc9">
<img width="615" alt="Snipaste_2023-05-30_12-19-01" src="https://github.com/JaeKang20/board/assets/100588597/0e60d853-55b4-4a33-8c13-89dc131ec72c">





**🔥기획의도**

```
- 게시판 활용
```
**🔥요구사항 분석**

``` 
◼ 회원 기능
·회원가입,로그인 기능

◼ 게시글 기능
·게시글 CRUD
·게시글 검색 기능 (제목, 내용, 글쓴이)
·게시글 좋아요 기능
·게시글 조회수 기능
·게시글 페이지 기능
·게시글 조회수 기능

◼ 관리자 기능
·관리자 공지 기능
·사용 규정 위반 포스팅 삭제 기능
·관리자 페이지 (TOP 10 게시글 확인)
```


**🔥시스템 구성**

```
1.DB Server: AWS RDS - MySQL
2.WEB Server: AWS EC2
  -Spring boot: Spring MVC (html,js,css)
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

**🔥**


|          구분         |                                                                                                       내용                                                                                                     |
|:---------------------:|:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
|        로그인 관리    |  ·HandlerMethodArgumentResolver를 이용한 회원가입,<br>◦특정 페이지는 로그인한 회원만 접속                                                                            |
|        게시글 관리    |     · CRUD, 조회수 기능<br>     · 좋아요 기능 <br>    · 페이징 기능                                                                                                                                                    |
|      관리자 페이지    |      ◦특정 페이지는 관리자만 접속  <br>        ○게시글 조회수 top10 리스트      <br>      ○게시글 추천수 top10 리스트  <br>   · ADMIN 계정은 공지글 게시 가능  <br>   · ADMIN 계정은 모든 게시물 삭제 가능    |



