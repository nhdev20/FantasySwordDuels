package fsd.model;

import java.time.LocalDateTime;

public class Run {
    private int runId;
    private LocalDateTime dateTime;
    private int highestLevelCompleted;

    public Run() {

    }
    public Run(LocalDateTime dateTime, int highestLevelComplete) {
        this.dateTime = dateTime;
        this.highestLevelCompleted = highestLevelComplete;
    }

    public int getRunId() {
        return runId;
    }

    public void setRunId(int runId) {
        this.runId = runId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getHighestLevelComplete() {
        return highestLevelCompleted;
    }

    public void setHighestLevelComplete(int highestLevelComplete) {
        this.highestLevelCompleted = highestLevelComplete;
    }
}
