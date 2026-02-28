package {{ .package }}.common.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.method.annotation.MapMethodProcessor;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.ViewNameMethodReturnValueHandler;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Configuration
@ComponentScan("com.vs")
public class
WebMvcConfiguration extends WebMvcConfigurerAdapter implements ApplicationContextAware {
    RequestMappingHandlerAdapter requestMappingHandlerAdapter;
    ApplicationContext applicationContext;

    void init() {
        List<HandlerMethodReturnValueHandler> returnValueHandlers = this.requestMappingHandlerAdapter.getReturnValueHandlers();
        Iterator<HandlerMethodReturnValueHandler> iterator = returnValueHandlers.iterator();
        List<HandlerMethodReturnValueHandler> newProcessors = new ArrayList<>();
        while (iterator.hasNext()) {
            HandlerMethodReturnValueHandler next = iterator.next();
            if (next instanceof MapMethodProcessor ||
                    next instanceof ViewNameMethodReturnValueHandler) {
                continue;
            } else {
                newProcessors.add(next);
            }
        }
        this.requestMappingHandlerAdapter.setReturnValueHandlers(newProcessors);
    }


    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        ResponseJsonExceptionResolver responseJsonExceptionResolver = new ResponseJsonExceptionResolver();
        try {
            responseJsonExceptionResolver.afterPropertiesSet();
        } catch (Exception e) {
            e.printStackTrace();
        }
        exceptionResolvers.add(responseJsonExceptionResolver);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        ResponseJsonMethodReturnValueHandler responseJsonMethodReturnValueHandler = new ResponseJsonMethodReturnValueHandler();
        responseJsonMethodReturnValueHandler.afterPropertiesSet();
        returnValueHandlers.add(responseJsonMethodReturnValueHandler);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Autowired
    private Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
        ObjectMapper mapper = jackson2ObjectMapperBuilder.build();
        DateFormat dateFormat = mapper.getDateFormat();
        mapper.setDateFormat(new MyDateFormat(dateFormat));
        MappingJackson2HttpMessageConverter mappingJsonpHttpMessageConverter = new MappingJackson2HttpMessageConverter(mapper);
        return mappingJsonpHttpMessageConverter;
    }

    @Bean
    ServletContextListener listener1() {
        return new ServletContextListener() {
            @Override
            public void contextInitialized(ServletContextEvent sce) {
                requestMappingHandlerAdapter = applicationContext.getBean(RequestMappingHandlerAdapter.class);
                init();
                ServletContextListener.super.contextInitialized(sce);
            }

            @Override
            public void contextDestroyed(ServletContextEvent sce) {
                ServletContextListener.super.contextDestroyed(sce);
            }
        };
    }
}