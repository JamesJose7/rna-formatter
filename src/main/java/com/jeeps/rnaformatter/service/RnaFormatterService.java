package com.jeeps.rnaformatter.service;

import com.jeeps.rnaformatter.core.RnaWrapper;
import com.jeeps.rnaformatter.core.WordDocWriter;
import com.jeeps.rnaformatter.model.TargetSite;
import com.jeeps.rnaformatter.model.TargetSitesForm;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.IntStream;

import static com.jeeps.rnaformatter.utils.RnaUtil.reverseComplement;

@Service
public class RnaFormatterService {
    public InputStream getRnaResultDoc(TargetSitesForm targetSitesForm) throws IOException {
        boolean isGrna = targetSitesForm.getDownloadType().equals("grna");
        WordDocWriter wordDocWriter;
        if (isGrna) {
            wordDocWriter = new WordDocWriter(targetSitesForm.getName(), isGrna);
        } else {
            wordDocWriter = new WordDocWriter(
                    targetSitesForm.getName(),
                    isGrna,
                    targetSitesForm.getForward5(), reverseComplement(targetSitesForm.getReverse5()),
                    targetSitesForm.getForward3(), reverseComplement(targetSitesForm.getReverse3()));
        }
        List<TargetSite> targetSites = targetSitesForm.getTargetSites();
        IntStream.range(0, targetSites.size())
                .mapToObj(i -> RnaWrapper.wrap(targetSites.get(i), i + 1))
                .forEach(wordDocWriter::writeRnaResult);
        return wordDocWriter.getDoc();
    }
}
