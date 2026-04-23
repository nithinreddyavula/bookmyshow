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
        String token = (authHeader != null && authHeader.startsWith("Bearer "))
                ? authHeader.substring(7) : null;
        String userId = jwtUtil.extractEmail(token);
        String key = "rate_limit:" + userId;
        int capacity = 10;
        int refillRate = 1;

        long now = System.currentTimeMillis() / 1000;

        String value = redisTemplate.opsForValue().get(key);

        int tokens = capacity;
        long lastRefill = now;

        if (value != null) {
            String[] parts = value.split(":");

            tokens = Integer.parseInt(parts[0]);
            lastRefill = Long.parseLong(parts[1]);

            long timePassed = now - lastRefill;

            int refill = (int) (timePassed * refillRate);

            tokens = Math.min(capacity, tokens + refill);
        }

        if (tokens <= 0) {
            response.setStatus(429);
            return false;
        }

        tokens--;

        redisTemplate.opsForValue().set(key, tokens + ":" + now, 1, TimeUnit.HOURS);

        return true;
    }
    }
