package com.jeeps.rnaformatter.core;

import com.jeeps.rnaformatter.model.RnaResult;
import com.jeeps.rnaformatter.model.TargetSite;
import org.apache.poi.xwpf.usermodel.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class WordDocWriter {

    private XWPFDocument document;
    private int size = 12;

    public WordDocWriter() {
        document = new XWPFDocument();
    }

    public void writeRnaResult(RnaResult result) {
        XWPFParagraph firstLine = document.createParagraph();

        // lgals2a-ts1	target site
        XWPFRun run1 = firstLine.createRun();
        run1.setText(result.getSequenceName());
        run1.addTab();
        run1.setText("target site");
        run1.addTab();
        run1.setFontSize(size);

        if (result.getType() == TargetSite.TYPE_NGG) { // NGG
            XWPFRun run2 = firstLine.createRun();
            String seg1 = result.getTargetSite().getRnaSegment().substring(0, 20);
            run2.setText(seg1);
            run2.setBold(true);
            run2.setFontSize(size);

            XWPFRun run3 = firstLine.createRun();
            String seg2 = result.getTargetSite().getRnaSegment().substring(20);
            run3.setText(seg2);
            run3.setUnderline(UnderlinePatterns.SINGLE);
            run3.setFontSize(size);
        } else if (result.getType() == TargetSite.TYPE_CCN) { // CCN
            XWPFRun run2 = firstLine.createRun();
            String seg1 = result.getTargetSite().getRnaSegment().substring(0, 3);
            run2.setText(seg1);
            run2.setUnderline(UnderlinePatterns.SINGLE);
            run2.setFontSize(size);

            XWPFRun run3 = firstLine.createRun();
            String seg2 = result.getTargetSite().getRnaSegment().substring(3);
            run3.setText(seg2);
            run3.setBold(true);
            run3.setFontSize(size);
        }

        // Results
        XWPFParagraph secondLine = document.createParagraph();

        // gRNA-lgals2a-ts1
        String name = "gRNA-";
        XWPFRun resultName = secondLine.createRun();
        resultName.setText(name + result.getSequenceName());
        resultName.addTab();
        resultName.setFontSize(size);

        // result left sequence
        XWPFRun seqLeft = secondLine.createRun();
        seqLeft.setText(TargetSite.FIRST_WRAP);
        seqLeft.setFontSize(size);

        // result center sequence
        XWPFRun seqCenter1 = secondLine.createRun();
        String firstChar = result.getResult().substring(0, 1);
        seqCenter1.setText(firstChar);
        seqCenter1.setBold(true);
        seqCenter1.setFontSize(size);
        if (result.getChangedCharacters()[0])
            seqCenter1.setColor("FF0000");

        XWPFRun seqCenter2 = secondLine.createRun();
        String secondChar = result.getResult().substring(1, 2);
        seqCenter2.setText(secondChar);
        seqCenter2.setBold(true);
        seqCenter2.setFontSize(size);
        if (result.getChangedCharacters()[1])
            seqCenter2.setColor("FF0000");

        XWPFRun seqCenter3 = secondLine.createRun();
        String rest = result.getResult().substring(2);
        seqCenter3.setText(rest);
        seqCenter3.setBold(true);
        seqCenter3.setFontSize(size);

        // result right sequence
        XWPFRun seqRight = secondLine.createRun();
        seqRight.setText(TargetSite.SECOND_WRAP);
        seqRight.setFontSize(size);
        seqRight.addBreak();
    }

    public InputStream getDoc() throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        document.write(b);
        return new ByteArrayInputStream(b.toByteArray());
    }
}
