package com.seek_with_sight.utils.sql;

import io.hypersistence.utils.jdbc.validator.SQLStatementCountValidator;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class SqlQueryCounterTestUtils {
    @Autowired
    private EntityManager entityManager;

    public void assertSelectQueriesCount(Runnable queryExecutor, int expectedSqlQueriesCount) {
        // Clear cache and remaining queries
        entityManager.flush();
        entityManager.clear();

        // Reset the query counter
        SQLStatementCountValidator.reset();

        // Execute the code that makes SQL queries
        queryExecutor.run();

        // Force execute remaining queries
        entityManager.flush();

        // Verify the count of sql queries
        SQLStatementCountValidator.assertSelectCount(expectedSqlQueriesCount);
    }
}
