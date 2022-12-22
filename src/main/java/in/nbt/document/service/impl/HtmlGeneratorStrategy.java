package in.nbt.document.service.impl;

import in.nbt.document.dto.DocumentDetails;
import in.nbt.document.dto.enums.DocType;
import in.nbt.document.exception.TemplateException;
import in.nbt.document.service.DocumentGeneratorStrategy;
import in.nbt.document.service.PdfCreator;
import in.nbt.document.service.TemplateRenderingEngine;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;

@Component
public class HtmlGeneratorStrategy implements DocumentGeneratorStrategy {
    final static Logger log = LogManager.getLogger(HtmlGeneratorStrategy.class);
    private TemplateRenderingEngine templateRenderingEngine;

    private PdfCreator pdfCreator;

    @Autowired
    public HtmlGeneratorStrategy(final TemplateRenderingEngine templateRenderingEngine,
                                 final PdfCreator pdfCreator) {
        this.templateRenderingEngine = templateRenderingEngine;
        this.pdfCreator = pdfCreator;
    }

    @Override
    public byte[] generateDocument(String data, DocumentDetails documentDetails) {
        log.info("Document Generation  Starting");
        File outputFile = null;
        try {
            String htmlContent = templateRenderingEngine.processTemplate(data, documentDetails);
            if (StringUtils.isBlank(htmlContent)) {
                log.error("Template might not exist or might not be accessible by any of the configured Template Resolvers");
                throw new TemplateException("Template might not exist or might not be accessible by any of the configured Template Resolvers");
            }
            org.jsoup.nodes.Document document = Jsoup.parse(htmlContent);
            document.outputSettings().syntax(org.jsoup.nodes.Document.OutputSettings.Syntax.xml);
            log.info("HtmlGeneratorStrategy - createWellFormedHtml HTML parsing done");
            return document.html().getBytes(StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("HtmlGeneratorStrategy - generateHtml Exception Occurred : {} ", e.getMessage(), e);
            throw new TemplateException(e.getMessage());
        }

    }

    @Override
    public DocType getDocumentType() {
        return DocType.HTML;
    }
}
