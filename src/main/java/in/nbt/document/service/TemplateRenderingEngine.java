package in.nbt.document.service;

import in.nbt.document.dto.DocumentDetails;

public interface TemplateRenderingEngine {
    String processTemplate(String data, DocumentDetails documentDetails);
}
