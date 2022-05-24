package in.nbt.document.dto;

import in.nbt.document.dto.enums.Status;
import in.nbt.document.dto.enums.Tag;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Template {

    public String id;

    @NotBlank(message = "Application id is mandatory")
    public String appId;

    @NotBlank(message = "Template name is mandatory")
    @Size(min = 4, message = "Template name should be at least 4 characters")
    @Size(max = 10, message = "Template name should not be greater than 20 characters")
    private String name;

    private Status status;

    @Valid
    @NotNull(message = "Template content is mandatory")
    public TemplateContent templateContent;

    private Tag tag;
}
