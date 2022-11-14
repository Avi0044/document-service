package in.nbt.document.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;

import java.util.UUID;

public class CorrelationIds {
    public static final String X_CORRELATION_ID_HEADER = "X-Correlation-Id";

    private CorrelationIds() {
    }

    public static String getNewCorrelationId() {
        String id = MDC.get(X_CORRELATION_ID_HEADER);
        if (StringUtils.isBlank(id)) {
            id = UUID.randomUUID().toString();
        }
        return id;
    }

    public static void setContextCorrelationId(String correlationId) {
        MDC.put(X_CORRELATION_ID_HEADER, correlationId);
    }

    public static String getxCorrelationIdFromContext() {
        return MDC.get(X_CORRELATION_ID_HEADER);
    }
}
