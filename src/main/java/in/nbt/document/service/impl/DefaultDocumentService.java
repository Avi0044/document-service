package in.nbt.document.service.impl;

import in.nbt.document.dto.DocumentDetails;
import in.nbt.document.dto.enums.DocType;
import in.nbt.document.dto.enums.Status;
import in.nbt.document.dto.responses.DocumentResponse;
import in.nbt.document.dto.responses.Template;
import in.nbt.document.exception.BadRequestException;
import in.nbt.document.model.DocumentAudit;
import in.nbt.document.repository.DocumentAuditRepository;
import in.nbt.document.service.DocumentGeneratorStrategy;
import in.nbt.document.service.DocumentService;
import in.nbt.document.service.DocumentStrategyFactory;
import in.nbt.document.service.TemplateService;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Base64;
import java.util.Date;

@Service
public class DefaultDocumentService implements DocumentService {

    protected static final Logger log = LogManager.getLogger(DefaultDocumentService.class);
    private DocumentStrategyFactory documentStrategyFactory;
    private TemplateService templateService;
    private DocumentAuditRepository documentAuditRepository;

    @Autowired
    public DefaultDocumentService(DocumentStrategyFactory documentStrategyFactory, TemplateService templateService,DocumentAuditRepository documentAuditRepository) {
        this.documentStrategyFactory = documentStrategyFactory;
        this.templateService = templateService;
        this.documentAuditRepository = documentAuditRepository;
    }

    @Override
    public DocumentResponse generateDocument(String templateId, String appId, DocType docType, DocumentDetails documentDetails) {

        if (StringUtils.isBlank(templateId)) {
            log.error("Template id must not be null!");
            throw new BadRequestException("Template id must not be null!");
        }
        if (StringUtils.isBlank(appId)) {
            log.error("AppId id must not be null!");
            throw new BadRequestException("AppId id must not be null!");
        }

        Template template = templateService.getTemplateByIdAndAppId(templateId, appId);
        if(Status.INACTIVE.equals(template.getStatus())){
            throw new BadRequestException("Sorry! Template That You Are Sending Is INACTIVE, Please Send Active Template");
        }
        DocumentGeneratorStrategy documentGeneratorStrategy = documentStrategyFactory.findStrategy(docType);
        byte[] bytes = documentGeneratorStrategy.generateDocument(decodeBase64ToString(template.getTemplateContent().getData()), documentDetails);
        DocumentResponse documentResponse = new DocumentResponse(bytes);
        //createDocumentAudit(template,docType);
        log.info("Document Generation Completed");
        return documentResponse;
    }

    public String decodeBase64ToString(String decodesString) {
        byte[] decodedBytes = Base64.getDecoder().decode(decodesString);
        return new String(decodedBytes);
    }
    public void createDocumentAudit(Template template,DocType docType){
        try {
            DocumentAudit documentAudit = new DocumentAudit();
            documentAudit.setAppId(template.getAppId());
            documentAudit.setTemplateId(template.getId());
            documentAudit.setDocType(docType);
            documentAudit.setTag(template.getTag());
            documentAudit.setCreatedDate(LocalDateTime.now());
            documentAudit.setLastModifiedDate(LocalDateTime.now());
            documentAuditRepository.save(documentAudit);
        }catch (Exception ex){
            log.info("Exception occured while saving document audit for templateId : {} , and appId : {}",template.getId(),template.getAppId());
        }
    }
}
