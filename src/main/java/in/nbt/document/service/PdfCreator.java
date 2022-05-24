package in.nbt.document.service;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.helper.W3CDom;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
public class PdfCreator {

    private final static Logger LOG = LogManager.getLogger(PdfCreator.class);

    public byte[] createPDF(Document document) {
        try (ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(document.html(), document.baseUri());
            builder.toStream(os);
            builder.run();
            return os.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
