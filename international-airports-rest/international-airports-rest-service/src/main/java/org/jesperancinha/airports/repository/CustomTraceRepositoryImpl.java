package org.jesperancinha.airports.repository;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class CustomTraceRepositoryImpl implements HttpTraceRepository {

    private AtomicReference<List<HttpTrace>> lastTrace = new AtomicReference<>(new ArrayList<>());

    @Override
    public List<HttpTrace> findAll() {
        return lastTrace.get();
    }

    @Override
    public void add(HttpTrace trace) {
        if ("GET".equals(trace.getRequest().getMethod())) {
            lastTrace.get().add(trace);
        }
    }

}