package in.nbt.document.repository;

import in.nbt.document.dto.enums.Status;
import in.nbt.document.model.Template;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemplateRepository extends MongoRepository<Template, String> {
    Template findByIdAndAppId(String templateId, String appId);

    List<Template> findAllByStatusAndAppId(Status status, String appId);

    List<Template> findAllByAppId(String appId);

    List<Template> findAllByTypeAndAppId(String type, String appId);

    List<Template> findAllByStatusAndTypeAndAppId(Status status, String type, String appId);

//    Template findByAppIdAndId(String appId, String id);
}
