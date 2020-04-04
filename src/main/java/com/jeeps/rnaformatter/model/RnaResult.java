package com.jeeps.rnaformatter.model;

public class RnaResult {
    private String sequenceName;
    private TargetSite targetSite;
    private String result;
    private boolean[] changedCharacters = new boolean[2];
    private String errors;

    public RnaResult() {}

    public RnaResult(String sequenceName, TargetSite targetSite, String result, boolean[] changedCharacters) {
        this.sequenceName = sequenceName;
        this.targetSite = targetSite;
        this.result = result;
        this.changedCharacters = changedCharacters;
    }

    public RnaResult(String sequenceName, String errors, TargetSite targetSite) {
        this.sequenceName = sequenceName;
        this.targetSite = targetSite;
        this.errors = errors;
    }

    public String getSequenceName() {
        return sequenceName;
    }

    public void setSequenceName(String sequenceName) {
        this.sequenceName = sequenceName;
    }

    public TargetSite getTargetSite() {
        return targetSite;
    }

    public void setTargetSite(TargetSite targetSite) {
        this.targetSite = targetSite;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public boolean[] getChangedCharacters() {
        return changedCharacters;
    }

    public void setChangedCharacters(boolean[] changedCharacters) {
        this.changedCharacters = changedCharacters;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
