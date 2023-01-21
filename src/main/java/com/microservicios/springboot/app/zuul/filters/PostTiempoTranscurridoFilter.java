package com.microservicios.springboot.app.zuul.filters;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class PostTiempoTranscurridoFilter extends ZuulFilter {
private static final Logger log = LoggerFactory.getLogger(PostTiempoTranscurridoFilter.class);
    @Override
    public String filterType() {
        return "post";
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
        // Para calcular el tiempo de Final del request, obtenemos el tiempo inicial y lo restamos. Y asi obtenemos el tiempo que se demora en realizar
        // el request y toda la comunicacion con el servicio.
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();
        log.info("Entrando a Post filter");
        Long tiempoInicio = (Long) request.getAttribute("tiempoInicio");
        Long tiempoFinal  = System.currentTimeMillis();
        Long tiempoTranscurrido = tiempoFinal - tiempoInicio;
        log.info(String.format("tiempo Transcurrido en segundos %s seg.", tiempoTranscurrido.doubleValue()/1000.00));
        log.info(String.format("tiempo Transcurrido en Milesegundos %s ms.", tiempoTranscurrido));


        return null;
    }
}
