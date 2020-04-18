package com.jeeps.rnaformatter.core;

import com.jeeps.rnaformatter.model.Exon;
import org.biojava.nbio.core.sequence.DNASequence;
import org.biojava.nbio.core.sequence.io.GenbankWriterHelper;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ApeWriter {
    private static final String HEADERS =
            "LOCUS       %s         9445 bp ds-DNA     linear       %s\n" +
            "DEFINITION  .\n" +
            "ACCESSION   \n" +
            "VERSION     \n" +
            "SOURCE      .\n" +
            "  ORGANISM  .\n" +
            "COMMENT     \n" +
            "COMMENT     ApEinfo:methylated:1\n" +
            "FEATURES             Location/Qualifiers\n";

    private static final String FEATURE_TEMPLATE =
            "     misc_feature    %d..%d\n" +
            "                     /locus_tag=\"exon\"\n" +
            "                     /label=\"exon\"\n" +
            "                     /ApEinfo_label=\"exon\"\n" +
            "                     /ApEinfo_fwdcolor=\"cyan\"\n" +
            "                     /ApEinfo_revcolor=\"cyan\"\n" +
            "                     /ApEinfo_graphicformat=\"arrow_data {{0 1 2 0 0 -1} {} 0}\n" +
            "                     width 5 offset 0\"\n";

    public static String buildContent(String name, List<Exon> features, String sequence) {
        StringBuilder contents = new StringBuilder();
        // Headers
        contents.append(buildHeaders(name));
        // Features
        features.forEach(feature -> contents.append(buildFeature(
                feature.getStartIndex(), feature.getEndIndex())));
        // Sequence
        try {
            ByteArrayOutputStream fragwriter = new ByteArrayOutputStream();
            ArrayList<DNASequence> seqs = new ArrayList<DNASequence>();
            DNASequence seq = new DNASequence(sequence);
            seqs.add(seq);

            GenbankWriterHelper.writeNucleotideSequence(fragwriter, seqs,
                    GenbankWriterHelper.LINEAR_DNA);

            String fragResult = fragwriter.toString().split("ORIGIN")[1].toUpperCase();
            contents.append("ORIGIN\n");
            contents.append(fragResult);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contents.toString();
    }

    private static String buildHeaders(String name) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String date = dateFormat.format(new Date()).toUpperCase();
        return String.format(HEADERS, name, date);
    }

    private static String buildFeature(int start, int end) {
        return String.format(FEATURE_TEMPLATE, start, end);
    }

}
