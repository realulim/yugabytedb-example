package example;

import com.datastax.oss.driver.api.core.CqlSession;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;
import java.util.logging.Logger;

@Component
public class DataPreloader {

    @Autowired
    private final CqlSession session;

    @Autowired
    private final ApiKeyService apiKeyService;

    public DataPreloader(CqlSession session, ApiKeyService apiKeyService) {
        this.session = session;
        this.apiKeyService = apiKeyService;
    }

    @EventListener(ContextRefreshedEvent.class)
    public void doWheneverContextIsRefreshedOrInitialized() throws IOException {
        createKeyspace();

        createStandardApiKey();
    }

    private void createKeyspace() {
        String keyspace = "example";
        String createKeyspace = "CREATE KEYSPACE IF NOT EXISTS " + keyspace + ";";
        session.execute(createKeyspace);
        Logger.getAnonymousLogger().info("Created Keyspace: " + keyspace);
    }

    private void createStandardApiKey() {
        String apiKeyHash = RandomStringUtils.randomAlphabetic(12);
        ApiKey created = apiKeyService.create(UUID.randomUUID(), apiKeyHash);
        Logger.getAnonymousLogger().info("Created ApiKey: " + created);
        Logger.getAnonymousLogger().info("ApiKey found in DB: " + apiKeyService.getById(created.getId()).isPresent());
    }

}