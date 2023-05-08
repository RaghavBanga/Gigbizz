package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserTaskResponse {

    @SerializedName("projects")
    @Expose
    private List<Project> projects = null;
    @SerializedName("training")
    @Expose
    private List<TrainingContent> training = null;
    @SerializedName("media")
    @Expose
    private List<MediaContent> media = null;
    @SerializedName("credit_card")
    @Expose
    private List<Project> credit_card = null;
    @SerializedName("more_arr")
    @Expose
    private List<Project> more_arr = null;
    @SerializedName("health_insurance")
    @Expose
    private List<Project> health_insurance = null;
    @SerializedName("car_insurance")
    @Expose
    private List<Project> car_insurance = null;
    @SerializedName("life_insurance")
    @Expose
    private List<Project> life_insurance = null;
    @SerializedName("saving_account")
    @Expose
    private List<Project> saving_account = null;
    @SerializedName("demat_account")
    @Expose
    private List<Project> demat_account = null;
    @SerializedName("personal_loan")
    @Expose
    private List<Project> personal_loan = null;
    @SerializedName("business_loan")
    @Expose
    private List<Project> business_loan = null;
    @SerializedName("home_loan")
    @Expose
    private List<Project> home_loan = null;
    @SerializedName("car_loan")
    @Expose
    private List<Project> car_loan = null;

    public UserTaskResponse(List<Project> projects, List<Project> credit_card, List<Project> more_arr, List<Project> health_insurance, List<Project> car_insurance, List<Project> life_insurance, List<Project> saving_account, List<Project> demat_account, List<Project> personal_loan, List<Project> business_loan, List<Project> home_loan, List<Project> car_loan) {
        this.projects = projects;
        this.credit_card = credit_card;
        this.more_arr = more_arr;
        this.health_insurance = health_insurance;
        this.car_insurance = car_insurance;
        this.life_insurance = life_insurance;
        this.saving_account = saving_account;
        this.demat_account = demat_account;
        this.personal_loan = personal_loan;
        this.business_loan = business_loan;
        this.home_loan = home_loan;
        this.car_loan = car_loan;
    }

    public List<Project> getSaving_account() {
        return saving_account;
    }

    public void setSaving_account(List<Project> saving_account) {
        this.saving_account = saving_account;
    }

    public List<Project> getDemat_account() {
        return demat_account;
    }

    public void setDemat_account(List<Project> demat_account) {
        this.demat_account = demat_account;
    }

    public List<Project> getPersonal_loan() {
        return personal_loan;
    }

    public void setPersonal_loan(List<Project> personal_loan) {
        this.personal_loan = personal_loan;
    }

    public List<Project> getBusiness_loan() {
        return business_loan;
    }

    public void setBusiness_loan(List<Project> business_loan) {
        this.business_loan = business_loan;
    }

    public List<Project> getHome_loan() {
        return home_loan;
    }

    public void setHome_loan(List<Project> home_loan) {
        this.home_loan = home_loan;
    }

    public List<Project> getCar_loan() {
        return car_loan;
    }

    public void setCar_loan(List<Project> car_loan) {
        this.car_loan = car_loan;
    }

    public List<Project> getCredit_card() {
        return credit_card;
    }

    public void setCredit_card(List<Project> credit_card) {
        this.credit_card = credit_card;
    }

    public List<Project> getMore_arr() {
        return more_arr;
    }

    public void setMore_arr(List<Project> more_arr) {
        this.more_arr = more_arr;
    }

    public List<Project> getHealth_insurance() {
        return health_insurance;
    }

    public void setHealth_insurance(List<Project> health_insurance) {
        this.health_insurance = health_insurance;
    }

    public List<Project> getCar_insurance() {
        return car_insurance;
    }

    public void setCar_insurance(List<Project> car_insurance) {
        this.car_insurance = car_insurance;
    }

    public List<Project> getLife_insurance() {
        return life_insurance;
    }

    public void setLife_insurance(List<Project> life_insurance) {
        this.life_insurance = life_insurance;
    }

    public List<Project> getProjects() {
        return projects;
    }

    public void setProjects(List<Project> projects) {
        this.projects = projects;
    }

    public List<TrainingContent> getTraining() {
        return training;
    }

    public void setTraining(List<TrainingContent> training) {
        this.training = training;
    }

    public List<MediaContent> getMedia() {
        return media;
    }

    public void setMedia(List<MediaContent> media) {
        this.media = media;
    }
}
