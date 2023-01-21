package com.microservicios.springboot.app.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PreTiempoTranscurridoFilter extends ZuulFilter {
private static final Logger log = LoggerFactory.getLogger(PreTiempoTranscurridoFilter.class);
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() throws ZuulException {
        // Para calcular el tiempo de inicio del tipo PRE antes de que se resuelva la ruta.
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Long tiempoInicio = System.currentTimeMillis();
        request.setAttribute("tiempoInicio",tiempoInicio);
        log.info(String.format("%srequest enrrutado a %s", request.getMethod(),request.getRequestURL().toString()));
        return null;
    }
}
