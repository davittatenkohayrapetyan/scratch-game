package com.davithayrapetyan.scratchgame.data;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class WinCombination {
    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;

    @JsonProperty("when")
    private String whenCondition;

    private Integer count;
    private String group;

    @JsonProperty("covered_areas")
    private List<List<String>> coveredAreas;

    // Getters and Setters
    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getWhenCondition() {
        return whenCondition;
    }

    public void setWhenCondition(String whenCondition) {
        this.whenCondition = whenCondition;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<List<String>> getCoveredAreas() {
        return coveredAreas;
    }

    public void setCoveredAreas(List<List<String>> coveredAreas) {
        this.coveredAreas = coveredAreas;
    }
}
