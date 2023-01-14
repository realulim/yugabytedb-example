package example;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

@Service
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepo;

    public ApiKeyService(ApiKeyRepository apiKeyRepo) {
        this.apiKeyRepo = apiKeyRepo;
    }

    @Transactional
    public ApiKey create(UUID id, String apiKeyHash) {
        return apiKeyRepo.save(new ApiKey(id, apiKeyHash));
    }

    public Optional<ApiKey> getById(UUID id) {
        return apiKeyRepo.findById(id);
    }

}
