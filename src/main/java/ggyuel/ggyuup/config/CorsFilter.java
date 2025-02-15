package ggyuel.ggyuup.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class CorsFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        // Access-Control-Allow-Origin 헤더 추가
        httpResponse.setHeader("Access-Control-Allow-Origin", "https://ewhaqup.com");

        // Access-Control-Allow-Credentials 헤더 추가
        httpResponse.setHeader("Access-Control-Allow-Credentials", "true");

        // 필요한 메서드 허용 (GET, POST 등)
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE");

        // 요청 헤더 허용
        httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");

        chain.doFilter(request, response);  // 요청을 계속 처리
    }

    @Override
    public void destroy() {
    }
}
