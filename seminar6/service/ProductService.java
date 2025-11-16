package pard.server.com.seminar6.service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import pard.server.com.seminar6.entity.Product;
import pard.server.com.seminar6.repository.ProductRepository;
import pard.server.com.seminar6.req.Req;
import pard.server.com.seminar6.res.Res;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    private int recentGetId = 0; //가장 최근에 조회한 상품의 id

    public void addProduct(Req.Req1 req1){
        Product product = Product.builder()
                .name(req1.getName())
                .color(req1.getColor())
                .price(req1.getPrice())
                .count(req1.getCount())
                .sellable(req1.getSellable())
                .build();
        productRepository.save(product);
    }

    @Transactional
    public void updateProduct(Req.Req2 req2){
        List<Product> productList = productRepository.findAllByColor(req2.getColor());
        productList.forEach(product -> {
            product.updateQuantity(req2.getCount());
        });
    }

    public Res.Res1 getProductById(int id){
        Long productId = (long)id;
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        this.recentGetId = product.getId();

        return Res.Res1.builder()
                .name(product.getName())
                .color(product.getColor())
                .price(product.getPrice())
                .count(product.getCount())
                .sellable(product.getSellable())
                .build();
    }

    public List<Res.Res1> findAllProduct(){
        List<Res.Res1> resDto = new ArrayList<>();

        productRepository.findAll().forEach(product -> {
            Res.Res1 resDtos = Res.Res1.builder()
                    .id(product.getId())
                    .name(product.getName())
                    .color(product.getColor())
                    .count(product.getCount())
                    .price(product.getPrice())
                    .sellable(product.getSellable())
                    .build();
            resDto.add(resDtos);
        });
        return resDto;
    }

    public Res.Res2 getColorNumber(){
        int redNum = productRepository.countAllByColor("빨강");
        int greenNum = productRepository.countAllByColor("초록");
        int blueNum = productRepository.countAllByColor("파랑");

        Res.Res2 resDto = Res.Res2.builder()
                .redColor(redNum)
                .greenColor(greenNum)
                .blueColor(blueNum)
                .build();
        return resDto;
    }

    public String recentProduct(){
        try{
            if(recentGetId == 0){
                throw new RuntimeException("아직 아무 상품도 조회 안했습니다.");
            }
            Long productId = (long)recentGetId;
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            return product.getName();
        }
        catch(Exception e){
            return e.getMessage();
        }
    }

    @Transactional
    public void changeInfo(Req.Req3 req3){
        Long productId = (long)req3.getId();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        product = Product.builder()
                .id(req3.getId())
                .name(req3.getName())
                .color(req3.getColor())
                .price(req3.getPrice())
                .count(req3.getCount())
                .sellable(req3.getSellable())
                .build();
        productRepository.save(product);
    }


}
