package in.nbt.document.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import in.nbt.document.dto.responses.ApiError;
import in.nbt.document.exception.UnAuthorizedException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.html.HTML;
import java.io.IOException;
@Profile({"PROD","UAT"})
@Component
@Order(1)
public class ApiSecretKeyFilter implements Filter {
    private final static Logger log = LoggerFactory.getLogger(ApiSecretKeyFilter.class);
    private final String API_KEY_HEADER = "X-Api-Key";
    @Autowired
    ObjectMapper objectMapper;
    //TODO: Get this value from properties file for now
    @Value("${api.key}")
    private String apiKey;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        try {
        HttpServletRequest req = (HttpServletRequest) request;
        final String apiKeyInRequest = req.getHeader(API_KEY_HEADER);

        if(!StringUtils.equalsAnyIgnoreCase(apiKeyInRequest,apiKey)){
            throw new UnAuthorizedException("UNAUTHORIZED");
        }
        // continue down the chain.
        filterChain.doFilter(request, response);
        } catch (UnAuthorizedException e) {
            log.error("Authorization header is invalid/not found", e);
            ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED);
            apiError.setMessage(e.getMessage());
            response.setContentType("application/json");
            HttpServletResponse hsr = (HttpServletResponse) response;
            hsr.setStatus(HttpStatus.UNAUTHORIZED.value());
            response.getWriter().write(objectMapper.writeValueAsString(apiError));
        }

    }
    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}

