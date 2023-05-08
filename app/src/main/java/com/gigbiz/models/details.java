package com.gigbiz.models;

public class details {
    String status;
    String msg;
    String team_id;
    String team_name;
    String team_token;
    String no_of_project;

    public details(String status, String msg, String team_id, String team_name, String team_token, String no_of_project) {
        this.status = status;
        this.msg = msg;
        this.team_id = team_id;
        this.team_name = team_name;
        this.team_token = team_token;
        this.no_of_project = no_of_project;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTeam_id() {
        return team_id;
    }

    public void setTeam_id(String team_id) {
        this.team_id = team_id;
    }

    public String getTeam_name() {
        return team_name;
    }

    public void setTeam_name(String team_name) {
        this.team_name = team_name;
    }

    public String getTeam_token() {
        return team_token;
    }

    public void setTeam_token(String team_token) {
        this.team_token = team_token;
    }

    public String getNo_of_project() {
        return no_of_project;
    }

    public void setNo_of_project(String no_of_project) {
        this.no_of_project = no_of_project;
    }
}
