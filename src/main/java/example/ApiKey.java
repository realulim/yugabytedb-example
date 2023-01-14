package example;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "apikey")
public class ApiKey implements Serializable {

    @Id
    private UUID id;

    @Column(nullable = false)
    private String keyHash;

    public ApiKey() {
    }

    public ApiKey(UUID id, String keyHash) {
        this.id = id;
        this.keyHash = keyHash;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.keyHash);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ApiKey other = (ApiKey) obj;
        if (!Objects.equals(this.keyHash, other.keyHash)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getKeyHash() {
        return keyHash;
    }

    public void setKeyHash(String keyHash) {
        this.keyHash = keyHash;
    }

    @Override
    public String toString() {
        return "ApiKey{" + "id=" + id + ", keyHash=" + keyHash + '}';
    }

}
