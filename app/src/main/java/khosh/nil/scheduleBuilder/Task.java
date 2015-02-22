package khosh.nil.scheduleBuilder;

/**
 * Created by Nil on 2015-02-21.
 */
public class Task {
    public static enum Priority {
        HIGH,
        MID,
        LOW;
    }
    private String name = "Untitled Task";
    private Priority priority;
    private int duration;
    private int scheduledStartTime;
    private boolean noBreak;

    public Task(String name, int duration, Priority priority, boolean noBreak) {
        this.name = name;
        this.priority = priority;
        this.duration = duration;
        this.noBreak = noBreak;
    }

    public String getName() {
        return name;
    }

    public Priority getPriority() {
        return priority;
    }

    public int getDuration() {
        return duration;
    }

    public boolean hasNoBreak() {
        return noBreak;
    }

    public void setStartTime(int startTime) {
        this.scheduledStartTime = startTime;
    }
}
