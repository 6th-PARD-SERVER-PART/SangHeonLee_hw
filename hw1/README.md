myController

- URL 파라미터로부터 메뉴이름과 그에 따른 가격을 입력받아
  저장하고, 삭제, 추가, 검색하는 컨트롤러를 만들었습니다.
- HashMap을 사용하여 Key-Value값을 메뉴이름(String)-가격(Integer)로 구성하였습니다.

사용된 어노테이션
- RestController
- RequestMapping
- PathVariable
- RequestParam
- GetMapping
- PatchMapping
- DeleteMapping

1. GetMapping (PathVariable로 변수 받음)
- 현재 저장된 메뉴-가격 출력
- "/menu"


>{
>  "Pasta": 17000
>}


2. RequestMapping
- 현재 메뉴에 새로운 메뉴-가격 추가 후 모든 메뉴 Map을 리턴
- "/{menu_name}/{price}"
- /Steak/19000 (스테이크 추가)
>{
>"Steak": 19000,
>"Pasta": 17000
>}

3. GetMapping (RequestParam으로 변수 받음)
- 찾고자하는 메뉴 이름 검색 -> 메뉴 가격 출력
- @GetMapping(params = "menu_name")
- http://localhost:8080/menu?menu_name=Steak (스테이크 가격 검색)
>{
> 19000
> }

4. PatchMapping
- 가격을 변경할때 해당 메뉴 이름-바뀐 가격 입력
- "/{menu_name}/{price}"
- /Pasta/100 (파스타 가격변경)
>{
>"Steak": 19000,
>"Pasta": 100
>}

5.DeleteMapping
- 삭제하고자 하는 메뉴 이름 입력
- "/{menu_name}"
- /Steak (스테이크 삭제)
>{
"Pasta": 100
}

서로 서버 접속해보기 인증 (성국형님 서버에 접속)
