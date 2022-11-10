package in.nbt.document.filters;

import in.nbt.document.utils.CorrelationIds;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Component
@Order(2)
public class CorrelationIdFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(CorrelationIdFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        try {
            final HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String currentCorrId = httpServletRequest.getHeader(CorrelationIds.X_CORRELATION_ID_HEADER);

            if (StringUtils.isBlank(currentCorrId)) {
                //CorrelationIds.setContextCorrelationId(currentCorrId);
                currentCorrId = UUID.randomUUID().toString();
                MDC.put(CorrelationIds.X_CORRELATION_ID_HEADER, currentCorrId);
            } else {
                MDC.put(CorrelationIds.X_CORRELATION_ID_HEADER, currentCorrId);
            }
            filterChain.doFilter(httpServletRequest, servletResponse);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        }
    }

    public void destroy() {
        MDC.clear();
    }
}
