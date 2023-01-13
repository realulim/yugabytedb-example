package example;

import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.logging.Logger;

import javax.transaction.Transactional;

@Service
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepo;

    public ApiKeyService(ApiKeyRepository apiKeyRepo) {
        this.apiKeyRepo = apiKeyRepo;
    }

    @Transactional
    public ApiKey create(UUID id, String apiKeyHash) {
        ApiKey apiKey = apiKeyRepo.save(new ApiKey(id, apiKeyHash));
        Logger.getAnonymousLogger().info("Created ApiKey: " + apiKey);
        return apiKey;
    }

}
