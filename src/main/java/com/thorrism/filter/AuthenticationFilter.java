package com.thorrism.filter;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import java.io.IOException;

/**
 * Created by Hercules on 5/7/2017.
 */
@Component
public class AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private HttpServletRequest servletRequest;

    @Value("${access.token}")
    private String accessToken;

    private static final String ACCESS_TOKEN_HEADER = "X-Access-Token";

    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        if (requiresAuthCheck()) {
            String token = servletRequest.getHeader(ACCESS_TOKEN_HEADER);
            if (StringUtils.equals(token, accessToken)) {
                return;
            }

            requestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED)
                    .build()
            );
        }
    }

    /**
     * Check if the current request requires an authentication check.
     *
     * @return  true if authentication check is needed, false otherwise
     */
    private final boolean requiresAuthCheck() {
        return StringUtils.startsWith(servletRequest.getPathInfo(), "/realm/server/serverStatus");
    }
}
