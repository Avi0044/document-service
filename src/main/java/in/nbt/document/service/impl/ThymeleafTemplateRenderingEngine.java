package in.nbt.document.service.impl;

import in.nbt.document.dto.DocumentContent;
import in.nbt.document.dto.DocumentDetails;
import in.nbt.document.exception.BadRequestException;
import in.nbt.document.exception.TemplateException;
import in.nbt.document.service.TemplateRenderingEngine;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.HashMap;
import java.util.Map;

@Component
public class ThymeleafTemplateRenderingEngine implements TemplateRenderingEngine {
    final static Logger log = LogManager.getLogger(ThymeleafTemplateRenderingEngine.class);

    @Override
    public String processTemplate(String data, DocumentDetails documentDetails) {
        try {
            Map<String, Object> model = new HashMap<>();
            for (DocumentContent documentContent : documentDetails.getDocumentContent()) {
                model.put(documentContent.getKey(), documentContent.getValue());
            }
            ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ThymeleafTemplateRenderingEngine.class);
            TemplateEngine templateEngine = (TemplateEngine) applicationContext.getBean("stringTemplateEngine");
            Context context = new Context();
            context.setVariables(model);
            return templateEngine.process(data, context);
        } catch (Exception ex) {
            log.error("Template might not exist or might not be accessible by any of the configured Template Resolvers");
            throw new TemplateException("Template might not exist or might not be accessible by any of the configured Template Resolvers");
        }

    }

    private ITemplateResolver stringTemplateResolver() {
        final StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setTemplateMode(TemplateMode.HTML);
        return templateResolver;
    }

    @Bean
    public TemplateEngine stringTemplateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(stringTemplateResolver());
        return templateEngine;
    }
}
