package pard.server.com.seminar6.req;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class Req {
    @AllArgsConstructor
    @Builder
    @Getter
    @NoArgsConstructor
    public static class Req1{
        private String name;
        private String color;
        private int price;
        private int count;
        private Boolean sellable;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    @NoArgsConstructor
    public static class Req2{
        private String color;
        private int count;
    }

    @AllArgsConstructor
    @Builder
    @Getter
    @NoArgsConstructor
    public static class Req3{
        private int id;
        private String name;
        private String color;
        private int price;
        private int count;
        private Boolean sellable;
    }
}
