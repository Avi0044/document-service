package in.nbt.document.service;

import in.nbt.document.dto.Template;
import in.nbt.document.dto.enums.Status;

public interface TemplateValidator {

    void validateTemplate(String templateId, String appId);

}
