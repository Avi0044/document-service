package in.nbt.document.service;

import in.nbt.document.dto.enums.Status;
import in.nbt.document.dto.responses.Template;
import in.nbt.document.dto.responses.TemplateResponse;

public interface TemplateService {
    Template registerTemplate(in.nbt.document.dto.Template template);

    Template updateTemplate(in.nbt.document.dto.Template template);
    TemplateResponse getTemplates(String appId, Status status);

    Template getTemplateByIdAndAppId(String templateId, String appId);
}
