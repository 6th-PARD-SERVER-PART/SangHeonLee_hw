package pard.server.com.seminar6.controller;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import pard.server.com.seminar6.req.Req;
import pard.server.com.seminar6.res.Res;
import pard.server.com.seminar6.service.ProductService;
import java.util.List;
@Slf4j
@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @PostMapping("") //상품 등록 성공
    public void addProduct(@RequestBody Req.Req1 req1) {
        productService.addProduct(req1);
        log.info("새 사용자 생성 요청 수신: {}", req1.getName() + " " + req1.getColor());
    }

    @PatchMapping("inventory") //성공
    public void updateQuantity(@RequestBody Req.Req2 req2) {
        productService.updateProduct(req2);
        log.info("재고변경 요청 수신: {}", req2.getColor()+ " 색상 " + req2.getCount() + "개로 일괄변경");
    }

    @GetMapping("/{id}") //성공
    public Res.Res1 getProduct(@PathVariable int id) {
        return productService.getProductById(id);
    }

    @GetMapping("all") //성공
    public List<Res.Res1> getAllProduct() {
        return productService.findAllProduct();
    }

    @GetMapping("colorNumber") //성공
    public Res.Res2 getColorNumber() {
        return productService.getColorNumber();
    }

    @GetMapping("recentProduct")
    public String recentProduct() {
        return productService.recentProduct();
    }

    @PatchMapping("change") //성공
    public void changeInfo(@RequestBody Req.Req3 req3) {
        productService.changeInfo(req3);
        log.info("정보변경 요청 수신: {}", req3.getName() + " " + req3.getColor() + " " +  req3.getPrice() + " " +  req3.getCount());
    }
}
