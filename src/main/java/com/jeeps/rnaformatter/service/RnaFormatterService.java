package com.jeeps.rnaformatter.service;

import com.jeeps.rnaformatter.core.RnaWrapper;
import com.jeeps.rnaformatter.core.WordDocWriter;
import com.jeeps.rnaformatter.model.RnaResult;
import com.jeeps.rnaformatter.model.TargetSite;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class RnaFormatterService {
    public InputStream getRnaResultDoc(List<TargetSite> targetSites) throws IOException {
        WordDocWriter wordDocWriter = new WordDocWriter();

        IntStream.range(0, targetSites.size())
                .mapToObj(i -> RnaWrapper.wrap(targetSites.get(i), i))
                .forEach(wordDocWriter::writeRnaResult);
        return wordDocWriter.getDoc();
    }
}
