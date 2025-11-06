# API 명세서

## 프로젝트 정보
- **프로젝트명**: Hw4 Application
- **버전**: 1.0.0
- **설명**: User, Post, Likes 관리 및 Google OAuth2 로그인 기능을 제공하는 REST API

---

## 1. User API

### 1.1 계정 생성
- **URL**: `/user`
- **Method**: `POST`
- **Description**: 새로운 사용자 계정을 생성합니다.

#### Request Body
```json
{
  "userName": "string"
}
```

#### Response
```
계정 생성완료
```

---

### 1.2 특정 사용자 조회
- **URL**: `/user/{id}`
- **Method**: `GET`
- **Description**: 특정 사용자의 이름과 작성한 게시글 목록을 조회합니다.

#### Path Parameters
| Parameter | Type | Description |
|-----------|------|-------------|
| id | Long | 사용자 ID |

#### Response
```json
{
  "userName": "string",
  "postTitles": [
    "string",
    "string"
  ]
}
```

---

### 1.3 전체 사용자 조회
- **URL**: `/user/findAll`
- **Method**: `GET`
- **Description**: 모든 사용자의 ID와 이름을 조회합니다.

#### Response
```json
[
  {
    "userId": 1,
    "userName": "string"
  },
  {
    "userId": 2,
    "userName": "string"
  }
]
```

---

### 1.4 사용자 삭제
- **URL**: `/user/{id}`
- **Method**: `DELETE`
- **Description**: 특정 사용자를 삭제합니다. 해당 사용자가 작성한 모든 게시글과 좋아요도 함께 삭제됩니다.

#### Path Parameters
| Parameter | Type | Description |
|-----------|------|-------------|
| id | Long | 사용자 ID |

#### Response
```
삭제완료
```

---

## 2. Post API

### 2.1 게시글 생성
- **URL**: `/post`
- **Method**: `POST`
- **Description**: 새로운 게시글을 생성합니다.
- **Swagger Tag**: Post

#### Request Body
```json
{
  "postTitle": "string",
  "postContext": "string",
  "userId": 1
}
```

#### Response
```
게시글 생성 완료
```

---

### 2.2 특정 게시물 조회
- **URL**: `/post/{id}`
- **Method**: `GET`
- **Description**: 특정 게시물의 상세 정보를 조회합니다.
- **Swagger Tag**: Post

#### Path Parameters
| Parameter | Type | Description |
|-----------|------|-------------|
| id | Long | 게시글 ID |

#### Response
```json
{
  "userName": "string",
  "postTitle": "string",
  "postContext": "string",
  "likesNum": 5
}
```

---

### 2.3 전체 게시물 조회
- **URL**: `/post/findAll`
- **Method**: `GET`
- **Description**: 모든 게시물의 제목과 ID를 조회합니다.
- **Swagger Tag**: Post

#### Response
```json
[
  {
    "postTitle": "string",
    "postId": 1
  },
  {
    "postTitle": "string",
    "postId": 2
  }
]
```

---

### 2.4 게시글 삭제
- **URL**: `/post/{id}`
- **Method**: `DELETE`
- **Description**: 특정 게시글을 삭제합니다. 해당 게시글에 달린 모든 좋아요도 함께 삭제됩니다.
- **Swagger Tag**: Post

#### Path Parameters
| Parameter | Type | Description |
|-----------|------|-------------|
| id | Long | 게시글 ID |

#### Response
```
삭제완료
```

---

## 3. Likes API

### 3.1 좋아요 추가/취소
- **URL**: `/likes`
- **Method**: `POST`
- **Description**: 게시글에 좋아요를 추가하거나 취소합니다. (토글 방식)
  - 이미 좋아요를 누른 경우: 좋아요 취소
  - 좋아요를 누르지 않은 경우: 좋아요 추가

#### Request Body
```json
{
  "userId": 1,
  "postId": 1
}
```

#### Response
```
좋아요를 달았습니다!
```

---

### 3.2 전체 좋아요 조회
- **URL**: `/likes/findAll`
- **Method**: `GET`
- **Description**: 모든 좋아요 정보를 조회합니다.

#### Response
```json
[
  {
    "likesId": 1,
    "userName": "string",
    "postTitle": "string"
  },
  {
    "likesId": 2,
    "userName": "string",
    "postTitle": "string"
  }
]
```

---

## 4. Authentication

### 4.1 로그인 페이지
- **URL**: `/loginform`
- **Method**: `GET`
- **Description**: 로그인 페이지를 반환합니다.

#### Response
- HTML 페이지 (loginform.html)

---

### 4.2 Google OAuth2 로그인
- **URL**: `/oauth2/authorization/google`
- **Method**: `GET`
- **Description**: Google OAuth2 로그인을 시작합니다.
- **Success Redirect**: `/home`

#### OAuth2 처리 흐름
1. Google 인증 페이지로 리다이렉트
2. 사용자 인증 완료
3. Google에서 사용자 정보 수신 (email, name, sub)
4. Member 테이블에 자동 저장 (없는 경우)
5. `/home`으로 리다이렉트

---

## 5. 데이터 모델

### 5.1 User
```json
{
  "id": "Long (PK)",
  "userName": "String"
}
```

### 5.2 Post
```json
{
  "id": "Long (PK)",
  "postTitle": "String",
  "postContext": "String",
  "user": "User (FK)"
}
```

### 5.3 Likes
```json
{
  "id": "Long (PK)",
  "user": "User (FK)",
  "post": "Post (FK)"
}
```

### 5.4 Member (OAuth2)
```json
{
  "id": "Long (PK)",
  "name": "String",
  "email": "String (Unique)",
  "role": "Enum (ADMIN, USER)",
  "socialID": "String"
}
```

---

## 6. 주요 비즈니스 로직

### 6.1 Cascade 삭제
- **User 삭제 시**: 해당 사용자의 모든 Post와 Likes 자동 삭제
- **Post 삭제 시**: 해당 게시글의 모든 Likes 자동 삭제

### 6.2 좋아요 토글
- 같은 사용자가 같은 게시글에 좋아요를 다시 요청하면 좋아요가 취소됩니다.

---

## 7. Error Handling

모든 API는 다음과 같은 경우 예외를 발생시킵니다:

- **사용자를 찾을 수 없습니다**: 존재하지 않는 User ID 조회 시
- **게시글을 찾을 수 없습니다**: 존재하지 않는 Post ID 조회 시
- **좋아요를 누르려는 계정을 찾을 수 없습니다**: 존재하지 않는 User ID로 좋아요 시도 시
- **좋아요를 누를 게시글을 찾을 수 없습니다**: 존재하지 않는 Post ID에 좋아요 시도 시

---

## 8. CORS 설정

- **허용 도메인**: 모든 도메인 (`*`)
- **허용 헤더**: 모든 헤더
- **허용 메소드**: 모든 HTTP 메소드
- **쿠키**: 허용

---

## 9. Swagger UI

- **접속 URL**: `/swagger-ui/index.html`
- **설명**: API 테스트 및 문서화를 위한 Swagger UI 제공