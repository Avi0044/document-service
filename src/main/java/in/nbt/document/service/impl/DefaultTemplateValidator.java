package in.nbt.document.service.impl;

import in.nbt.document.dto.enums.ContentType;
import in.nbt.document.dto.Template;
import in.nbt.document.dto.enums.Status;
import in.nbt.document.exception.BadRequestException;
import in.nbt.document.service.TemplateValidator;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DefaultTemplateValidator implements TemplateValidator {
    protected static final Logger log = LogManager.getLogger(DefaultTemplateValidator.class);

    @Override
    public void validateTemplate(String templateId, String appId) {
        if (StringUtils.isBlank(templateId)) {
            log.error("Template id must not be null!");
            throw new BadRequestException("Template id must not be null!");
        }
        if (StringUtils.isBlank(appId)) {
            log.error("AppId id must not be null!");
            throw new BadRequestException("AppId id must not be null!");
        }
    }
}
