package ao.com.mauel.luminet.andromeda.controllers;

import ao.com.mauel.luminet.andromeda.products.Product;
import ao.com.mauel.luminet.andromeda.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @PostMapping
    public void postProduct(){

    }

    @GetMapping("produtos")
    public List<Product> getAllProducts(){
        List<Product> productDTOList = productRepository.findAll();
        return productDTOList;
    }

}
