package com.jeeps.rnaformatter.core;

import com.gargoylesoftware.htmlunit.NicelyResynchronizingAjaxController;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomNode;
import com.gargoylesoftware.htmlunit.html.DomNodeList;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSpan;
import com.jeeps.rnaformatter.model.Exon;
import org.w3c.dom.Node;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GeneSeqScrapper {

    public static final long TIMEOUT_MILLIS = 6000L;

    public String extractSequence(String name, String url) throws IOException {
        WebClient webClient = new WebClient();
        webClient.getOptions().setThrowExceptionOnScriptError(false);
        webClient.setAjaxController(new NicelyResynchronizingAjaxController());
        HtmlPage page = webClient.getPage(url);
        webClient.waitForBackgroundJavaScript(TIMEOUT_MILLIS);

        int characterCount = 0, startIndex = -1, endIndex;

        List<Exon> exons = new ArrayList<>();
        StringBuilder sequenceBuilder = new StringBuilder();

        DomNodeList<DomNode> sequences = page.querySelectorAll("span._seq");
        for (DomNode sequence : sequences) {
            // Get highlighted sequence
            if (((HtmlSpan) sequence).getChildElementCount() > 0) {
                for (DomNode childNode : sequence.getChildNodes()) {
                    // Highlighted
                    Node style = childNode.getAttributes().getNamedItem("style");
                    if (style != null) {
                        if (style.getNodeValue().contains("background-color:#ffebcd"))
                            if (startIndex < 0) // New exon
                                startIndex = characterCount;
                    } else { // Normal
                        if (startIndex >= 0) { // Mark exon end and save it
                            endIndex = characterCount;
                            exons.add(new Exon(startIndex + 1, endIndex));
                            startIndex = -1;
                        }
                    }
                    characterCount += childNode.getVisibleText().length();
                }
            } else { // Get normal sequence
                if (startIndex >= 0) { // Mark exon end and save it
                    endIndex = characterCount;
                    exons.add(new Exon(startIndex, endIndex));
                    startIndex = -1;
                }
                characterCount += sequence.getVisibleText().length();
            }

            // Append sequence content
            sequenceBuilder.append(sequence.getVisibleText());
        }

        // Return ape file format
        return ApeWriter.buildContent(name, exons, sequenceBuilder.toString());
    }
}
