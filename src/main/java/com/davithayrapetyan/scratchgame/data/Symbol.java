package com.davithayrapetyan.scratchgame.data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Symbol {

    private String name;

    @JsonProperty("reward_multiplier")
    private double rewardMultiplier;

    private String type;
    private String impact;
    private Integer extra;

    // Default constructor (required for Jackson)
    public Symbol() {}

    // Constructor with fields (optional, for convenience)
    public Symbol(String name, double rewardMultiplier, String type, String impact, Integer extra) {
        this.name = name;
        this.rewardMultiplier = rewardMultiplier;
        this.type = type;
        this.impact = impact;
        this.extra = extra;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRewardMultiplier() {
        return rewardMultiplier;
    }

    public void setRewardMultiplier(double rewardMultiplier) {
        this.rewardMultiplier = rewardMultiplier;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImpact() {
        return impact;
    }

    public void setImpact(String impact) {
        this.impact = impact;
    }

    public Integer getExtra() {
        return extra;
    }

    public void setExtra(Integer extra) {
        this.extra = extra;
    }
}
