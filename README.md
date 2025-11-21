# 기숙사 공실 예약 시스템 API 명세서

## 📋 Base URL

```
http://localhost:8080/reservations
```

## 🏢 엔티티 구조

### EmptyRoom (공실)

```json
{
  "roomId": "Long (PK)",
  "roomNumber": "int"
}

```

**초기 데이터 (8개)**
은혜관 여자층 1,2,3층에 있는 공실 목록들

- 1층: 102, 104, 106
- 2층: 202, 204, 206
- 3층: 302, 304

### ReserveRoom (예약)

```json
{
  "reserveRoomId": "Long (PK)",
  "roomId": "Long (FK)",
  "date": "String (YYYY-MM-DD)",
  "floor": "int"
}

```

---

## 🔌 API 엔드포인트

## 1️⃣ 대시보드 페이지

### 전체 예약 내역 조회

```
GET /reservations/findAll

```

### Response

```json
[
  {
    "roomNumber": 102,
    "date": "2025-11-25"
  },
  {
    "roomNumber": 204,
    "date": "2025-11-26"
  },
  {
    "roomNumber": 302,
    "date": "2025-11-27"
  }
]

```

### Response (예약 내역이 없을 때)

```json
[]

```

---

## 2️⃣ 예약 페이지

### 예약 가능한 호실 조회 GET

```json
{
    "floor": 1,
    "date": "2025-11-25"
 }
```

### Request Parameters

| Parameter | Type | Required | Description |
| --- | --- | --- | --- |
| date | String | Yes | 예약 날짜 (YYYY-MM-DD) |
| floor | int | Yes | 층수 (1, 2, 3) |

### Example Request

### Response (예약 불가능한 호실들을 정수형 리스트로 리턴)

```json
[102, 104, 106] //roomNumber (예약 불가능한 호실을 담은 리스트)

```

### Response (예약 불가능한 방이 없을 때)

```json
[]

```

```json
{
    "floor": 1,
    "date": "2025-11-25"
 }
```

### Response (1층 일부 예약된 경우)

```json
[104, 106]

```

---

### 공실 예약하기

```
POST /reservations

```

### Request Body

```json
{
  "roomNumber": 102,
  "date": "2025-11-25",
  "floor": 1
}

```

### Response (성공)

```json
200
```

### Response (실패 - 이미 예약된 방)

```json

400
```

### Response (실패 - 존재하지 않는 호실)

```json
400
```

### Response (실패 - 층수 불일치)

```json
400
```

---

## 3️⃣ 마이 페이지

### 내 예약 내역 조회

```
GET /reservations/findAll

```

### Response

```json
[
  {
    "roomNumber": 102,
    "date": "2025-11-25"
  },
  {
    "roomNumber": 204,
    "date": "2025-11-26"
  }
]

```

### Response (예약 내역이 없을 때)

```json
[]

```

---

### 예약 취소하기

```
DELETE reservations

```

### Request Body

```json
{
  "roomNumber": 102,
  "date": "2025-11-25"
}

```

### Response (성공)

```json
200

```

### Response (실패 - 예약 내역 없음)

```json
400
```

### Swagger Link (스웨거 링크)

```json
"172.18.157.165:8080/swagger-ui/index.html#/product-controller/"

```

