package fsd;

import java.time.LocalDateTime;
import java.util.Map;

public class User {
    private String gamerTag;
    private LocalDateTime endOfPlay;
    private String timeAndDateAtEndOfPlay;
    private int highestLevelCompleted;
    private Map<String, Integer> attributeDistribution; //key is name of Attribute, value is corresponding int value
    private Map<String, Integer> combatOptionDistribution; //key is name of combat option, value is corresponding number of times used

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }

    public LocalDateTime getEndOfPlay() {
        return endOfPlay;
    }

    public void setEndOfPlay(LocalDateTime endOfPlay) {
        this.endOfPlay = endOfPlay;
    }

    public String getTimeAndDateAtEndOfPlay() {
        return timeAndDateAtEndOfPlay;
    }

    public void setTimeAndDateAtEndOfPlay(String timeAndDateAtEndOfPlay) {
        this.timeAndDateAtEndOfPlay = timeAndDateAtEndOfPlay;
    }

    public int getHighestLevelCompleted() {
        return highestLevelCompleted;
    }

    public void setHighestLevelCompleted(int highestLevelCompleted) {
        this.highestLevelCompleted = highestLevelCompleted;
    }

    public Map<String, Integer> getAttributeDistribution() {
        return attributeDistribution;
    }

    public void setAttributeDistribution(Map<String, Integer> attributeDistribution) {
        this.attributeDistribution = attributeDistribution;
    }

    public Map<String, Integer> getCombatOptionDistribution() {
        return combatOptionDistribution;
    }

    public void setCombatOptionDistribution(Map<String, Integer> combatOptionDistribution) {
        this.combatOptionDistribution = combatOptionDistribution;
    }

    @Override
    public String toString() {
        return "User{" +
                "gamerTag='" + gamerTag + '\'' +
                ", endOfPlay=" + endOfPlay +
                ", timeAndDateAtEndOfPlay='" + timeAndDateAtEndOfPlay + '\'' +
                ", highestLevelCompleted=" + highestLevelCompleted +
                ", attributeDistribution=" + attributeDistribution +
                ", combatOptionDistribution=" + combatOptionDistribution +
                '}';
    }
}
