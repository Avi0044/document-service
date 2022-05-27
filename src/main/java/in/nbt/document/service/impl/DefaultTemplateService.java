package in.nbt.document.service.impl;

import in.nbt.document.dto.enums.Status;
import in.nbt.document.dto.responses.Template;
import in.nbt.document.dto.responses.TemplateResponse;
import in.nbt.document.exception.BadRequestException;
import in.nbt.document.exception.NotFoundException;
import in.nbt.document.mappers.TemplateMapper;
import in.nbt.document.repository.TemplateRepository;
import in.nbt.document.service.TemplateService;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class DefaultTemplateService implements TemplateService {
    protected static final Logger log = LogManager.getLogger(DefaultTemplateService.class);
    private TemplateRepository templateRepository;
    private TemplateMapper templateMapper;


    @Autowired
    public DefaultTemplateService(final TemplateRepository templateRepository, final TemplateMapper templateMapper) {
        this.templateRepository = templateRepository;
        this.templateMapper = templateMapper;
    }

    @Override
    public Template registerTemplate(in.nbt.document.dto.Template template) {
        log.info("Register Template Execution Started");
        //templateValidator.validateTemplate(template);
        in.nbt.document.model.Template templateModel = templateMapper.mapRequestToModel(template);
        if(templateModel.getStatus()==null){
            templateModel.setStatus(Status.ACTIVE);
        }
        in.nbt.document.model.Template savedTemplate = templateRepository.save(templateModel);
        Template responseToBeReturned = templateMapper.mapTemplateModelToResponse(savedTemplate);
        log.info("Template Registered Successfully With TemplateID : {}", responseToBeReturned.getId());
        return responseToBeReturned;
    }

    @Override
    public Template updateTemplate(in.nbt.document.dto.Template template) {
        log.info("Update Template Execution Started");
        in.nbt.document.model.Template templateModel = templateRepository.findByIdAndAppId(template.getId(), template.appId);
        if (templateModel == null) {
            throw new NotFoundException("Template Not Found");
        }
        if (template.getStatus() == null) {
            log.error("Status must not be null!");
            throw new BadRequestException("Status must not be null!");
        }
        templateModel.setStatus(template.getStatus());
        if (template.getTemplateContent() != null) {
            templateModel.setTemplateContent(template.getTemplateContent());
        }
        templateModel.setName(template.getName());
        in.nbt.document.model.Template savedTemplate = templateRepository.save(templateModel);
        Template responseToBeReturned = templateMapper.mapTemplateModelToResponse(savedTemplate);
        log.info("Template Updated Successfully With TemplateID : {}", responseToBeReturned.getId());
        return responseToBeReturned;
    }

    @Override
    public TemplateResponse getTemplates(String appId, Status status) {
        log.info("Get Templates Execution Started");
        if (StringUtils.isBlank(appId)) {
            log.error("AppId id must not be null!");
            throw new BadRequestException("AppId id must not be null!");
        }
        List<in.nbt.document.model.Template> templates = new LinkedList<>();
        if (status != null) {
            templates = templateRepository.findAllByStatusAndAppId(status, appId);
        } else {
            templates = templateRepository.findAllByAppId(appId);
        }
        if (templates.isEmpty()) {
            throw new NotFoundException("No Template Found for appId : " + appId);
        }
        List<Template> listOfTemplates = templateMapper.mapTemplateModelsToResponses(templates);
        log.info("Template list fetched Successfully, Total Count : {}", listOfTemplates.size());
        return new TemplateResponse(listOfTemplates);
    }

    @Override
    public Template getTemplateByIdAndAppId(String templateId, String appId) {
        log.info("Get Template Execution Started");
        if (StringUtils.isBlank(templateId) || StringUtils.isBlank(appId)) {
            log.info("Get templates for id:  {} & application id: {} ", templateId, appId);
            throw new BadRequestException("Either template id or app id is missing.");
        }

        in.nbt.document.model.Template savedTemplate = templateRepository.findByIdAndAppId(templateId, appId);
        if (savedTemplate == null) {
            log.error("Template not present");
            throw new NotFoundException("Template not present");
        }
        log.info("Template found, TemplateId : {}", savedTemplate.getId());
        return templateMapper.mapTemplateModelToResponse(savedTemplate);
    }
}
