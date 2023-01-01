package example;

import org.springframework.stereotype.Service;

import java.util.UUID;

import javax.transaction.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
@AllArgsConstructor
public class ApiKeyService {

    private final ApiKeyRepository apiKeyRepo;

    @Transactional
    public ApiKey create(UUID id, String apiKeyHash) {
        ApiKey apiKey = apiKeyRepo.save(new ApiKey(id, apiKeyHash));
        log.info("Created ApiKey: " + apiKey);
        return apiKey;
    }

}
