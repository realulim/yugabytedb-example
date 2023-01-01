package example;

import com.datastax.oss.driver.api.core.CqlSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@AllArgsConstructor
public class DataPreloader {

    @Autowired
    private final CqlSession session;

    @Autowired
    private final ApiKeyService apiKeyService;

    @EventListener(ContextRefreshedEvent.class)
    public void doWheneverContextIsRefreshedOrInitialized() throws IOException {

       createKeyspace();

       createStandardApiKey();

    }

    private void createKeyspace() {
        String keyspace = "example";
        String createKeyspace = "CREATE KEYSPACE IF NOT EXISTS " + keyspace + ";";
        session.execute(createKeyspace);
        log.info("Created Keyspace: " + keyspace);
    }

    private void createStandardApiKey() {
        String apiKeyHash = "Some Hash";
        apiKeyService.create(UUID.randomUUID(), apiKeyHash);
    }

}