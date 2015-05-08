# code-chobo-study
코드초보스터디 사이트 개편 프로젝트

#### Requirement
1. JDK 1.8
2. scala 2.11.x
3. gradle 2.x

#### 실행방법
```
# 프로젝트 루트 폴더에서 해당 명령어 실행
gradle bootRun  또는   gradle backend:bootRun
```

#### Git 브랜치 전략 및 참여방법
----
##### Branch
1. master는 태그처럼 관리하며 사이클(주기) 별로 배포된다.
2. develop 브랜치는 개발서버로 배포되는 브랜치이다.
3. feature 브랜치는 각 기능이 개발되는 브랜치이다.
4. bugfix 브랜치는 develop, master 브랜치에서 생성될 수 있고 버그 수정이다.
5. release 브랜치는 사용유무는 회의를 통해 결정한다.

##### 참여방법
1. develop 브랜치를 기준으로 각 feature 브랜치를 생성한다.
2. 또는 develop 브랜치로 Fork를 한다.
3. github이슈에 자신을 등록하고 ing 라벨을 붙인다.
4. 개발이 완료 되면 develop 브랜치로 Pull Request를 보낸다.
5. 반드시 최소 2인 이상의 TF,명예팀 코드리뷰를 통과해야한다
   만약 TF, 명예팀이 보낸 Pull Request는 본인을 제외한 2인이다.
6. 브랜치를 병합하고 삭제한다.
7. develop 브랜치에 병합되면 정상적으로 동작하는지 확인한다.
8. 완류 후 이슈를 close 하고 ing 라벨을 제거한다.
9. 가능한 pull 을 받는 경우는 --rebase 옵션을 활용한다.
10. 그외 방법은 [angularjs Contribute Guide](https://github.com/angular/angular.js/blob/master/CONTRIBUTING.md#submitting-a-pull-request) 를 참고한다.

##### 커밋로그작성방법
1. 기본형태
    ```
    commit type (관련 기능, 이슈번호등) : 요약메세지 (짧을 경우 전체기입가능)
    1. 자세한 메세지 내용
    2. 자세한 메세지 내용
    3. 자세한 메세지 내용

	ex) 회원기능의 CRUD 개발 후 메세지
    feature(user) : 회원 CRUD 개발
    1. 회원가입,수정,탈퇴
    2. 스프링 시큐리티 설정
    ```
2. 가능한 커밋은 해당 메세지에 해당하는 항목만 커밋되도록한다. (커밋합치기, 쪼개기 활용)
3. 경우에 따라 리뷰시 커밋관련 메세지나 잘못끼어든 파일등으로 반려될 수 있다.
4. 커밋타입은 다음과 같다. (필요에 따라 추가될 수 있다.)
 - feature : 기능
 - bugfix : 버그 패치
 - doc : 문서가 변경된 경우
 - style : 코드 영향을 주지 않는 공백, 코드스타일 등을 수정한 경우
 - refactor : 코드 리팩토링
 - test : 테스트코드를 추가할때 (가능한 커밋시 테스트코드는 따로 하지말고 같이보낸다)
 - enhancement : 개발완료된 기능을 개선하는 경우 (성능등)
 - framework : 프레임워크 관련 수정 또는 프로젝트 설정 수정등
