package com.jeeps.rnaformatter.model;

public class RnaResult {
    private String sequenceName;
    private TargetSite targetSite;
    private String result;
    private String wrappedResult;
    private boolean[] changedCharacters = new boolean[2];
    private int type;
    private String errors;

    public RnaResult() {}

    public RnaResult(String sequenceName, TargetSite targetSite, String result, String wrappedResult,
                     boolean[] changedCharacters, int type) {
        this.sequenceName = sequenceName;
        this.targetSite = targetSite;
        this.result = result;
        this.wrappedResult = wrappedResult;
        this.changedCharacters = changedCharacters;
        this.type = type;
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

    public String getWrappedResult() {
        return wrappedResult;
    }

    public void setWrappedResult(String wrappedResult) {
        this.wrappedResult = wrappedResult;
    }

    public boolean[] getChangedCharacters() {
        return changedCharacters;
    }

    public void setChangedCharacters(boolean[] changedCharacters) {
        this.changedCharacters = changedCharacters;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }
}
