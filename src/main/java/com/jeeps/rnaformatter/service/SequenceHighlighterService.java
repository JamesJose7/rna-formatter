package com.jeeps.rnaformatter.service;

import com.jeeps.rnaformatter.core.GeneSeqScrapper;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SequenceHighlighterService {

    public String getApeContent(String name, String url) throws IOException {
        GeneSeqScrapper geneSeqScrapper = new GeneSeqScrapper();
        return geneSeqScrapper.extractSequence(name, url);
    }
}
