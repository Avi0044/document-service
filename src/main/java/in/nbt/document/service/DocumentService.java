package in.nbt.document.service;

import in.nbt.document.dto.enums.DocType;
import in.nbt.document.dto.DocumentDetails;
import in.nbt.document.dto.responses.DocumentResponse;

public interface DocumentService {
    DocumentResponse generateDocument(String templateId, String appId, DocType docType, DocumentDetails documentDetails);
}
