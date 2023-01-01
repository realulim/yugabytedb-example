package example;

import com.yugabyte.data.jdbc.repository.YsqlRepository;

import java.util.UUID;

public interface ApiKeyRepository extends YsqlRepository<ApiKey, UUID> {

}
