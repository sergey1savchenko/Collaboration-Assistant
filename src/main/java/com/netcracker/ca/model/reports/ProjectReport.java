package com.netcracker.ca.model.reports;

/**
 * Created by Oleksandr on 16.11.2016.
 */
public class ProjectReport {
    private String title;
    private int involved;
    private int expelled;
    private int finished;
    private int invited;
    private int offered;
    private int rejected;

    public ProjectReport() {
    }

    public ProjectReport(String title, int involved, int expelled, int finished, int invited, int offered, int rejected) {
        this.title = title;
        this.involved = involved;
        this.expelled = expelled;
        this.finished = finished;
        this.invited = invited;
        this.offered = offered;
        this.rejected = rejected;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getInvolved() {
        return involved;
    }

    public void setInvolved(int involved) {
        this.involved = involved;
    }

    public int getExpelled() {
        return expelled;
    }

    public void setExpelled(int expelled) {
        this.expelled = expelled;
    }

    public int getFinished() {
        return finished;
    }

    public void setFinished(int finished) {
        this.finished = finished;
    }

    public int getInvited() {
        return invited;
    }

    public void setInvited(int invited) {
        this.invited = invited;
    }

    public int getOffered() {
        return offered;
    }

    public void setOffered(int offered) {
        this.offered = offered;
    }

    public int getRejected() {
        return rejected;
    }

    public void setRejected(int rejected) {
        this.rejected = rejected;
    }

    @Override
    public String toString() {
        return "ProjectReport{" +
                "title='" + title + '\'' +
                ", involved=" + involved +
                ", expelled=" + expelled +
                ", finished=" + finished +
                ", invited=" + invited +
                ", offered=" + offered +
                ", rejected=" + rejected +
                '}';
    }
}
