package org.jesperancinha.objects.repository;

import lombok.val;
import org.springframework.boot.actuate.web.exchanges.HttpExchange;
import org.springframework.boot.actuate.web.exchanges.HttpExchangeRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class CustomTraceRepository implements HttpExchangeRepository {
    private final AtomicReference<Queue<HttpExchange>> lastTrace = new AtomicReference<>(new LinkedList<>());

    @Override
    public List<HttpExchange> findAll() {
        try {
            return new ArrayList<>(lastTrace.get());
        } catch (Exception e) {
            return List.of();
        }
    }

    @Override
    public void add(HttpExchange trace) {
        val httpTraces = lastTrace.get();
        if (httpTraces.size() > 10) {
            while (httpTraces.size() > 10) {
                httpTraces.poll();
            }
        }
        if ("GET".equals(trace.getRequest().getMethod())) {
            httpTraces.add(trace);
        }
    }
}