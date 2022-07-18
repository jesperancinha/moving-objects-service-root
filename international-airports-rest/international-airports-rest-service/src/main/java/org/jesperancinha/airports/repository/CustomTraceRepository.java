package org.jesperancinha.airports.repository;

import org.springframework.boot.actuate.trace.http.HttpTrace;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

@Repository
public class CustomTraceRepository implements HttpTraceRepository {

    private AtomicReference<Queue<HttpTrace>> lastTrace = new AtomicReference<>(new LinkedList<>());


    @Override
    public List<HttpTrace> findAll() {
        return new ArrayList<>(lastTrace.get());
    }

    @Override
    public void add(HttpTrace trace) {
        Queue<HttpTrace> httpTraces = lastTrace.get();
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