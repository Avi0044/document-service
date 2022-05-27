package in.nbt.document.dto.responses;


import com.fasterxml.jackson.annotation.JsonFormat;
import in.nbt.document.dto.enums.Status;
import in.nbt.document.dto.enums.Tag;
import in.nbt.document.dto.TemplateContent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Template {
    public String appId;
    private String name;
    public TemplateContent templateContent;
    public Status status;
    private String id;
    private String type;
    private Tag tag;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdDate;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime lastModifiedDate;
}
