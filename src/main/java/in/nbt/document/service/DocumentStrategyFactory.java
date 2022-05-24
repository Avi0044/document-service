package in.nbt.document.service;

import in.nbt.document.dto.enums.DocType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class DocumentStrategyFactory {

    private Map<DocType, DocumentGeneratorStrategy> documentGeneratorStrategyMap;

    @Autowired
    public DocumentStrategyFactory(Set<DocumentGeneratorStrategy> documentGeneratorStrategies) {
        createStrategy(documentGeneratorStrategies);
    }

    public DocumentGeneratorStrategy findStrategy(DocType docType) {
        return documentGeneratorStrategyMap.get(docType);
    }

    private void createStrategy(Set<DocumentGeneratorStrategy> documentGeneratorStrategies) {
        documentGeneratorStrategyMap = new HashMap<>();
        documentGeneratorStrategies.forEach(
                documentGeneratorStrategy -> documentGeneratorStrategyMap.put(documentGeneratorStrategy.getDocumentType(), documentGeneratorStrategy));
    }
}
