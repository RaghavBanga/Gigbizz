package com.gigbiz.models;

import java.util.List;

public class AssignedModel {
    List<details> team_name;

    public AssignedModel(List<details> team_name) {
        this.team_name = team_name;
    }

    public List<details> getTeam_name() {
        return team_name;
    }

    public void setTeam_name(List<details> team_name) {
        this.team_name = team_name;
    }
}
