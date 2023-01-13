package example;

import com.datastax.oss.driver.api.core.CqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

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
        String apiKeyHash = "Some Hash";
        apiKeyService.create(UUID.randomUUID(), apiKeyHash);
    }

}