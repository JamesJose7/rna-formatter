package com.jeeps.rnaformatter.core;

import com.jeeps.rnaformatter.model.RnaResult;
import com.jeeps.rnaformatter.model.TargetSite;
import com.jeeps.rnaformatter.utils.ErrorUtils;
import com.jeeps.rnaformatter.utils.RnaUtil;

public class RnaWrapper {

    public static RnaResult wrap(TargetSite targetSite, int n) {
        // Format sequence name
        String sequenceName = String.format("%s-ts%d", targetSite.getName(), n);
        // Check if it's a valid sequence
        if (!targetSite.getRnaSegment().matches("^[ACTG]+$"))
            return new RnaResult(sequenceName, ErrorUtils.INVALID_SEQUENCE, targetSite);
        if (targetSite.getRnaSegment().length() != 23)
            return new RnaResult(sequenceName, ErrorUtils.SEQUENCE_LENGTH, targetSite);

        String newSequence;
        // Check what type of sequence it is
        if (targetSite.getRnaSegment().matches(".*([ACTG])GG$")) { // NGG
            newSequence = targetSite.getRnaSegment().substring(0, 20);
        } else if (targetSite.getRnaSegment().matches("^CC([ACTG]).*")) { // CCN
            String seqFragment = targetSite.getRnaSegment().substring(3, 23);
            newSequence = RnaUtil.reverseComplement(seqFragment);
        } else
            return new RnaResult(sequenceName, ErrorUtils.INVALID_PATTERN, targetSite);

        // Replace the first characters
        boolean[] changedCharacters = new boolean[2];
        if (newSequence.charAt(0) != 'G') {
            changedCharacters[0] = true;
            newSequence = "G" + newSequence.substring(1);
        }
        if (newSequence.charAt(1) != 'G') {
            changedCharacters[1] = true;
            newSequence = newSequence.substring(0,1) + "G" + newSequence.substring(2);
        }

        // Wrap sequence
        newSequence = TargetSite.wrapSegment(newSequence);

        return new RnaResult(sequenceName, targetSite, newSequence, changedCharacters);
    }
}
