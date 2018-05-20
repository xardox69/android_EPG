package redfest.myapplication.network.models;

/**
 * Created by usman on 3/20/18.
 */

public class EPGProgram {

    private String name;
    private int duration;
    private String startTime;

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
}
