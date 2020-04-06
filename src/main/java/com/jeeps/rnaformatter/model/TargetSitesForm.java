package com.jeeps.rnaformatter.model;

import java.util.List;

public class TargetSitesForm {
    private String name;
    private List<TargetSite> targetSites;
    private String forward5;
    private String reverse5;
    private String forward3;
    private String reverse3;
    private String downloadType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TargetSite> getTargetSites() {
        return targetSites;
    }

    public void setTargetSites(List<TargetSite> targetSites) {
        this.targetSites = targetSites;
    }

    public String getForward5() {
        return forward5;
    }

    public void setForward5(String forward5) {
        this.forward5 = forward5;
    }

    public String getReverse5() {
        return reverse5;
    }

    public void setReverse5(String reverse5) {
        this.reverse5 = reverse5;
    }

    public String getForward3() {
        return forward3;
    }

    public void setForward3(String forward3) {
        this.forward3 = forward3;
    }

    public String getReverse3() {
        return reverse3;
    }

    public void setReverse3(String reverse3) {
        this.reverse3 = reverse3;
    }

    public String getDownloadType() {
        return downloadType;
    }

    public void setDownloadType(String downloadType) {
        this.downloadType = downloadType;
    }
}
