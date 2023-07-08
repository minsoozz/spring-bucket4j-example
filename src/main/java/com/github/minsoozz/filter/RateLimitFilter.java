package com.github.minsoozz.filter;

import com.github.minsoozz.adapter.Bucket4jRateLimiter;
import com.github.minsoozz.adapter.RateLimiter;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class RateLimitFilter extends OncePerRequestFilter {

    private final Logger logger = LoggerFactory.getLogger(RateLimitFilter.class);
    private final RateLimiter bucket4jRateLimiter;

    public RateLimitFilter(Bucket4jRateLimiter bucket4jRateLimiter) {
        this.bucket4jRateLimiter = bucket4jRateLimiter;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 특정 사용자의 식별 정보를 추출하여 key 생성
        String key = extractUserIdentifier(request);
        Bucket bucket = bucket4jRateLimiter.resolve(key);

        // ucket4jRateLimiter를 사용하여 처리율 제한 적용
        if (bucket.tryConsume(1)) {
            logger.info("key: {}", key);
            logger.info("available tokens: {}", bucket.getAvailableTokens());
            filterChain.doFilter(request, response);
            return;
        }
        // 처리율 제한에 걸렸을 때 처리
        response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
        response.getWriter().write("Too many requests");
    }

    private String extractUserIdentifier(HttpServletRequest request) {
        // 사용자를 식별할 수 있는 정보 추출 (예: IP 주소, 사용자 ID 등)
        String ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null) {
            return request.getRemoteAddr(); // 예시로 IP 주소 사용
        }
        return ip;
    }
}
