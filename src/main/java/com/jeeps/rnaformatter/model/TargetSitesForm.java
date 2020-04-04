package com.jeeps.rnaformatter.model;

import java.util.List;

public class TargetSitesForm {
    private String name;
    private List<TargetSite> targetSites;

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
}
