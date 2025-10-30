# API 명세서

## 프로젝트 정보
- **프로젝트명**: Seminar 4 HW
- **버전**: 1.0.0
- **Base URL**: `/`

---

## 1. User API

### 1.1 사용자 생성
- **URL**: `/user`
- **Method**: `POST`
- **Description**: 새로운 사용자 계정을 생성합니다.

**Request Body**
```json
{
  "userName": "string"
}
```

**Response**
```
"계정 생성완료 "
```

---

### 1.2 사용자 조회
- **URL**: `/user/{id}`
- **Method**: `GET`
- **Description**: 특정 사용자의 정보와 작성한 게시글 목록을 조회합니다.

**Path Parameters**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id | Long | Yes | 사용자 ID |

**Response**
```json
{
  "userName": "string",
  "postTitles": ["string", "string"]
}
```

**Error Response**
- `IllegalArgumentException`: 사용자를 찾을 수 없습니다.

---

### 1.3 모든 사용자 조회
- **URL**: `/user/findAll`
- **Method**: `GET`
- **Description**: 모든 사용자의 ID와 이름을 조회합니다.

**Response**
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
- **Description**: 사용자를 삭제합니다. 해당 사용자가 작성한 모든 게시글과 좋아요도 함께 삭제됩니다.

**Path Parameters**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id | Long | Yes | 사용자 ID |

**Response**
```
"삭제완료"
```

---

## 2. Post API

### 2.1 게시글 생성
- **URL**: `/post`
- **Method**: `POST`
- **Description**: 새로운 게시글을 작성합니다.

**Request Body**
```json
{
  "postTitle": "string",
  "postContext": "string",
  "userId": 1
}
```

**Response**
```
"게시글 생성 완료"
```

**Error Response**
- `IllegalArgumentException`: 사용자를 찾을 수 없습니다.

---

### 2.2 게시글 상세 조회
- **URL**: `/post/{id}`
- **Method**: `GET`
- **Description**: 특정 게시글의 상세 정보(작성자, 제목, 내용, 좋아요 수)를 조회합니다.

**Path Parameters**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id | Long | Yes | 게시글 ID |

**Response**
```json
{
  "userName": "string",
  "postTitle": "string",
  "postContext": "string",
  "likesNum": 0
}
```

**Error Response**
- `IllegalArgumentException`: 게시글을 찾을 수 없습니다.

---

### 2.3 모든 게시글 조회
- **URL**: `/post/findAll`
- **Method**: `GET`
- **Description**: 모든 게시글의 ID와 제목을 조회합니다.

**Response**
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
- **Description**: 게시글을 삭제합니다. 해당 게시글에 달린 모든 좋아요도 함께 삭제됩니다.

**Path Parameters**
| Parameter | Type | Required | Description |
|-----------|------|----------|-------------|
| id | Long | Yes | 게시글 ID |

**Response**
```
"삭제완료"
```

---

## 3. Likes API

### 3.1 좋아요 추가/취소
- **URL**: `/likes`
- **Method**: `POST`
- **Description**: 게시글에 좋아요를 추가하거나 취소합니다. 이미 좋아요가 있으면 취소되고, 없으면 추가됩니다.

**Request Body**
```json
{
  "userId": 1,
  "postId": 1
}
```

**Response**
```
"좋아요를 달았습니다!"
```

**Error Response**
- `IllegalArgumentException`: 좋아요를 누르려는 계정을 찾을 수 없습니다.
- `IllegalArgumentException`: 좋아요를 누를 게시글을 찾을 수 없습니다.

**Note**:
- 이미 좋아요가 존재하는 경우 좋아요가 삭제됩니다 (토글 방식)
- 좋아요가 없는 경우 새로운 좋아요가 생성됩니다

---

### 3.2 모든 좋아요 조회
- **URL**: `/likes/findAll`
- **Method**: `GET`
- **Description**: 모든 좋아요 정보를 조회합니다.

**Response**
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

## ERD (Entity Relationship Diagram)

```
                    ┌─────────────────────────┐
                    │         User            │
                    ├─────────────────────────┤
                    │ PK: u_id (bigint)       │
                    │     u_name (varchar)    │
                    │     user_name (varchar) │
                    └─────────────────────────┘
                              │ 1
                 ┌────────────┼────────────┐
                 │                         │
                 │ N                       │ N
                 ▼                         ▼
    ┌─────────────────────────┐  ┌─────────────────────────┐
    │         Post            │  │        Likes            │
    ├─────────────────────────┤  ├─────────────────────────┤
    │ PK: p_id (bigint)       │  │ PK: l_id (bigint)       │
    │ FK: u_id (bigint)       │  │ FK: user_id (bigint)    │
    │     p_title (varchar)   │  │ FK: post_id (bigint)    │
    │     p_context (varchar) │  └─────────────────────────┘
    └─────────────────────────┘              │
                 │ 1                         │
                 │                           │
                 └───────────────────────────┘
                              N
```

### 관계 설명

#### User ─── Post (1:N)
- 한 사용자(User)는 여러 개의 게시글(Post)을 작성할 수 있습니다.
- Post 테이블의 **u_id**가 User 테이블의 **u_id**를 참조합니다.
- `@ManyToOne` 단방향 관계

#### User ─── Likes (1:N)
- 한 사용자(User)는 여러 개의 좋아요(Likes)를 누를 수 있습니다.
- Likes 테이블의 **user_id**가 User 테이블의 **u_id**를 참조합니다.
- `@ManyToOne` 단방향 관계

#### Post ─── Likes (1:N)
- 한 게시글(Post)은 여러 개의 좋아요(Likes)를 받을 수 있습니다.
- Likes 테이블의 **post_id**가 Post 테이블의 **p_id**를 참조합니다.
- `@ManyToOne` 단방향 관계

### 특징
- Likes 테이블은 User와 Post 두 테이블을 모두 참조하는 **연결 테이블(Junction Table)** 역할
- 한 사용자가 하나의 게시글에 좋아요를 한 번만 누를 수 있도록 설계 (토글 기능)

### 테이블 상세 정보

#### User 테이블
| Column | Type | Constraint | Description |
|--------|------|------------|-------------|
| u_id | Long | PRIMARY KEY, AUTO_INCREMENT | 사용자 고유 ID |
| u_name | String | NOT NULL | 사용자 이름 |

#### Post 테이블
| Column | Type | Constraint | Description |
|--------|------|------------|-------------|
| p_id | Long | PRIMARY KEY, AUTO_INCREMENT | 게시글 고유 ID |
| p_title | String | NOT NULL | 게시글 제목 |
| p_context | String | NOT NULL | 게시글 내용 |
| u_id | Long | FOREIGN KEY → User(u_id) | 작성자 ID |

#### Likes 테이블
| Column | Type | Constraint | Description |
|--------|------|------------|-------------|
| l_id | Long | PRIMARY KEY, AUTO_INCREMENT | 좋아요 고유 ID |
| u_id | Long | FOREIGN KEY → User(u_id) | 좋아요를 누른 사용자 ID |
| p_id | Long | FOREIGN KEY → Post(p_id) | 좋아요가 달린 게시글 ID |

### 삭제 정책 (Cascade)
- **User 삭제 시**:
    1. 해당 사용자가 작성한 모든 Post 삭제
    2. 해당 Post에 달린 모든 Likes 삭제
    3. 해당 사용자가 누른 모든 Likes 삭제
    4. User 삭제

- **Post 삭제 시**:
    1. 해당 게시글에 달린 모든 Likes 삭제
    2. Post 삭제

- **Likes 삭제 시**: 단독 삭제 가능 (토글 기능)

---

## 공통 응답 코드

| Status Code | Description |
|-------------|-------------|
| 200 | 요청 성공 |
| 400 | 잘못된 요청 (IllegalArgumentException) |
| 500 | 서버 내부 오류 |

---

## Swagger UI
- **URL**: `http://localhost:8080/swagger-ui.html`
- Swagger를 통해 API를 테스트할 수 있습니다.