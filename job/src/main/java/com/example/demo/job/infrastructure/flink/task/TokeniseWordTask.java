package com.example.demo.job.infrastructure.flink.task;

import com.example.demo.core.usecase.TokenizeLine;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.util.Collector;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TokeniseWordTask implements FlatMapFunction<String, List<String>> {

    private final TokenizeLine wordTokenizer;

    public TokeniseWordTask(TokenizeLine wordTokenizer) {
        this.wordTokenizer = wordTokenizer;
    }

    @Override
    public void flatMap(String value, Collector<List<String>> out) throws Exception {
        out.collect(wordTokenizer.execute(value));
    }
}
