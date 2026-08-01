package com.seek_with_sight.seed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeederRunner implements CommandLineRunner {

    private final ProductSeedService productDataSeeder;
    private final OrderSeedService orderDataSeeder;
    private final UserSeedService userDataSeeder;

    @Override
    public void run(String... args) throws Exception {
        log.info("Executing automatic startup data seeding...");

        productDataSeeder.seedProducts();
        userDataSeeder.seedUsers(10);
        orderDataSeeder.seedOrders(500);
    }
}