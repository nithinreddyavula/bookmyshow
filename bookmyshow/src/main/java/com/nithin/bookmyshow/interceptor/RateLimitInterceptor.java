package com.nithin.bookmyshow.interceptor;

import com.nithin.bookmyshow.security.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {

    private final StringRedisTemplate redisTemplate;
    private final JwtUtil jwtUtil;

    public RateLimitInterceptor(StringRedisTemplate redisTemplate, JwtUtil jwtUtil) {
        this.redisTemplate = redisTemplate;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authHeader = request.getHeader("Authorization");
        String userKey;

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            userKey = jwtUtil.extractEmail(token);
        } else {
            userKey = request.getRemoteAddr();
        }

        String key = "rate_limit:" + userKey;
        int capacity = 3;
        //int refillTokens = (int) (timePassed / 10);

        long now = System.currentTimeMillis() / 1000;

        String value = redisTemplate.opsForValue().get(key);

        int tokens = capacity;
        long lastRefill = now;

        if (value != null) {
            String[] parts = value.split(":");
            tokens = Integer.parseInt(parts[0]);
            lastRefill = Long.parseLong(parts[1]);
            long timePassed = now - lastRefill; //error cannot resolev
            int refillTokens = (int) (timePassed / 10);
            tokens = Math.min(capacity, tokens + refillTokens);
        }
        System.out.println("Rate limit key: " + key + " | Tokens: " + tokens);

        if (tokens <= 0) {
            response.setStatus(429);
            response.getWriter().write("Rate limit exceeded. Please try again later.");
            return false;
        }

        tokens--;

        redisTemplate.opsForValue().set(key, tokens + ":" + now, 1, TimeUnit.HOURS);

        return true;
    }
    }
