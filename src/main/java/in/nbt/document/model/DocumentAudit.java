package in.nbt.document.model;

import in.nbt.document.dto.TemplateContent;
import in.nbt.document.dto.enums.DocType;
import in.nbt.document.dto.enums.Status;
import in.nbt.document.dto.enums.Tag;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "document_audit")
public class DocumentAudit {
    @Id
    public String id;
    public String templateId;
    public String appId;
    public DocType docType;
    public Tag tag;
    private LocalDateTime createdDate;
    private LocalDateTime lastModifiedDate;
}
