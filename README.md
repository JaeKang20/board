# board

## 최재강 개발자의 포트폴리오
🔥AWS로 서버배포한 환경 바로 가보기 클릭: 
http://ec2-43-200-120-235.ap-northeast-2.compute.amazonaws.com:8080/

<img width="635" alt="Snipaste_2023-05-23_23-04-43" src="https://github.com/JaeKang20/board/assets/100588597/358c73c4-ef70-4930-8561-718e7f0775e1">


**🔥기획의도**

```
- 게시판 활용
```
**🔥요구사항 분석**

``` 
◼ 게시판 기능
·게시글 조회 
·게시글 등록
·게시글 수정
·게시글 삭제
·게시글 검색 (제목, 내용, 글쓴이)
·게시글 추천 기능
·게시글 조회수 기능

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

**🔥ERD**

<img width="506" alt="KakaoTalk_Photo_2023-05-24-12-51-43" src="https://github.com/JaeKang20/board/assets/100588597/21d41c85-8164-441d-a888-3b5d4747e780">

