package com.jinvindia.inventory;

import com.jinvindia.inventory.entities.Product;
import com.jinvindia.inventory.repositories.ProductRepository;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Configuration
public class InventoryApplication implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(InventoryApplication.class);

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args) {
        SpringApplication.run(InventoryApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        Product fanta = createTestProduct("Fanta", 1.2, 10);
        Product coke = createTestProduct("Coke", 1.5, 20);
        Product drPepper = createTestProduct("Pepper", 1.8, 30);

        // save single entity instance
        fanta = productRepository.save(fanta);

        // save multiple entity instances at a time
        List<Product> insertedSoftDrinks
                = productRepository.saveAll(List.of(coke, drPepper));

        // make sure all entities are actually saved to the database
        productRepository.flush();

        // update coke and drPepper to have rating 0 in the database
        for (Product sd : insertedSoftDrinks) {
            productRepository.save(sd);
        }

        System.out.println(
                "ALL PRODUCTS IN DESCENDING ORDER BASED ON ID");
        // get all soft drinks in descending order 
        // and print toString() to the console
        productRepository.findAll(Sort.by(Sort.Direction.DESC, "id"))
                .forEach(System.out::println);

        // find all using an example
        System.out.println("FINDING ALL USING EXAMPLE");
        productRepository.findAll(Example.of(
                // probe soft drink to match results with
                Product.builder().build(),
                // ask that database entries that match any 
                // of the fields in the probe be returned
                ExampleMatcher.matchingAny())
        )
                .forEach(System.out::println);

        // create page request to paginate through these three
        // soft drinks. note that the first page is indicated using 0
        PageRequest pageRequest = PageRequest.of(0, 2);

        System.out.println("FIRST PAGE");
        // get first page
        Page<Product> page = productRepository.findAll(pageRequest);
        page.getContent().forEach(System.out::println);

        System.out.println("SECOND PAGE");
        // get second page
        page = productRepository.findAll(pageRequest.next());
        page.getContent().forEach(System.out::println);

        // delete all 3 soft drinks in a batch
        productRepository.deleteAllInBatch();
    }

    private Product createTestProduct(String productName, Double price, Integer quantity) {
        return Product.builder()
                .name(productName)
                .price(price)
                .quantity(quantity)
                .build();
    }    
}
