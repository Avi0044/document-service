package in.nbt.document.repository;

import in.nbt.document.model.DocumentAudit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentAuditRepository extends MongoRepository<DocumentAudit, String> {
}
