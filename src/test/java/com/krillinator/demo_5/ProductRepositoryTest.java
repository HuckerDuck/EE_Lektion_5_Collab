    package com.krillinator.demo_5;

    import com.krillinator.demo_5.product.Product;
    import com.krillinator.demo_5.product.ProductRepository;
    import org.junit.jupiter.api.AfterEach;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.Timeout;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.boot.test.context.SpringBootTest;
    import org.springframework.context.annotation.Import;
    import reactor.test.StepVerifier;
    import java.math.BigDecimal;
    import java.util.concurrent.TimeUnit;

    @Import (TestcontainersConfiguration.class)
    @SpringBootTest
    public class ProductRepositoryTest {
        @Autowired
        private ProductRepository productRepository;

        @BeforeEach
        public void clearDatabase(){
            //? Block är viktigt
            //? Ser till att detta görs innan något annat
            productRepository.deleteAll().block();

        }

        @Test
        //?
        @Timeout(value = 5, unit = TimeUnit.SECONDS)
        public void shouldSaveProductAndRetrive(){
            Product product = new Product(
                    null,
                    "Apples",
                    "Apples from the Nörth",
                    BigDecimal.valueOf(19.9),
                    false,
                    null

            );

            //? Är likt JUNIT Assertions
            //?

            //? Test Saving Prodcut to Database
            StepVerifier.create(
                    productRepository.save(product)
            )
                    //? Här i expect NextMatches gör du din acersion
                    //? Vad du förväntar dig helt enkelt
                    .expectNextMatches(product1 -> product.name().equals("Apples"))
                    .verifyComplete();

            //? Test Does Product Exist as PK: 1??
            //StepVerifier.create(
            //        productRepository.findById()
            //)
            //        .expectNextMatches(product1 -> product.name().equals("Apples"))
            //        .verifyComplete();
        }


    }
