package app.restaurant.interceptors;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Component
public class TestInterceptorAdder implements WebMvcConfigurer {
    private final SomeTestInterceptor someTestInterceptor;

    public TestInterceptorAdder(SomeTestInterceptor someTestInterceptor) {
        this.someTestInterceptor = someTestInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(someTestInterceptor);
    }
}
