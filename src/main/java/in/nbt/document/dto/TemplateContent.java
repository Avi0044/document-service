package in.nbt.document.dto;

import in.nbt.document.dto.enums.ContentType;
import in.nbt.document.utils.EnumValueRegexPattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateContent {

    @NotNull(message = "Content type is mandatory i.e BASE64")
    @EnumValueRegexPattern(regexp = "BASE64")
    public ContentType contentType;

    @NotBlank(message = "Template data/content should not be null or empty")
    public String data;
}
