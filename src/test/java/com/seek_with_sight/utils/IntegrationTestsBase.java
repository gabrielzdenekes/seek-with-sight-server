package com.seek_with_sight.utils;

import com.seek_with_sight.TestsConfiguration;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestsConfiguration.class)
@SpringBootTest
@Tag("integration-tests")
public abstract class IntegrationTestsBase extends TestsBase {
}
