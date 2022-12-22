package in.nbt.document.service;

import in.nbt.document.dto.enums.DocType;
import in.nbt.document.dto.DocumentDetails;

import java.io.InputStream;

public interface DocumentGeneratorStrategy {
    byte[] generateDocument(String data, DocumentDetails documentContent);
    DocType getDocumentType();
}
