package pard.server.com.seminar6.res;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Res {
    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Res1{ //해당상품조회 (상세페이지), findAll
        private int id;
        private String name;
        private String color;
        private int price;
        private int count;
        private Boolean sellable;
    }

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Res2{ //색상별 개수 리턴
        private int redColor;
        private int greenColor;
        private int blueColor;
    }
}
