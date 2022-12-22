package in.nbt.document.model;

import in.nbt.document.dto.enums.Status;
import in.nbt.document.dto.enums.Tag;
import in.nbt.document.dto.TemplateContent;
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
@Document(collection = "template")
public class Template {
    @Id
    public String id;
    public String appId;
    public String name;
    public TemplateContent templateContent;
    public Status status = Status.ACTIVE;
    private String type;
    public Tag tag;
    @CreatedDate
    private LocalDateTime createdDate;
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;
}
