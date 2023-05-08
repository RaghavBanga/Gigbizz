package com.gigbiz.models;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Project {

    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("msg")
    @Expose
    private String msg;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("comp_name")
    @Expose
    private String compName;
    @SerializedName("logo")
    @Expose
    private String logo;
    @SerializedName("project_title")
    @Expose
    private String projectTitle;
    @SerializedName("project_description")
    @Expose
    private String projectDescription;
    @SerializedName("start_time")
    @Expose
    private String startTime;
    @SerializedName("end_time")
    @Expose
    private String endTime;
    @SerializedName("break_start_time")
    @Expose
    private String breakStartTime;
    @SerializedName("break_end_time")
    @Expose
    private String breakEndTime;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("price_start")
    @Expose
    private String priceStart;
    @SerializedName("price_end")
    @Expose
    private String priceEnd;
    @SerializedName("terms_and_condition")
    @Expose
    private String termsAndCondition;
    @SerializedName("training_type")
    @Expose
    private String trainingType;
    @SerializedName("project_type")
    @Expose
    private String projectType;
    @SerializedName("project_status")
    @Expose
    private String projectStatus;
    @SerializedName("handler")
    @Expose
    private String handler;
    @SerializedName("eligibility")
    @Expose
    private String eligibility;
    @SerializedName("step_to_follow")
    @Expose
    private String stepToFollow;
    @SerializedName("faq")
    @Expose
    private String faq;
    @SerializedName("project_location")
    @Expose
    private String project_location;
    @SerializedName("project_payout")
    @Expose
    private String project_payout;
    @SerializedName("referral_url")
    @Expose
    private String referral_url;
    @SerializedName("project_banner")
    @Expose
    private String banner;
    @SerializedName("training")
    @Expose
    private List<TrainingContent> training = null;
    @SerializedName("media")
    @Expose
    private List<MediaContent> media = null;

    public Project(int status, String msg, String projectId, String compName, String logo, String projectTitle, String projectDescription, String startTime, String endTime, String breakStartTime, String breakEndTime, String education, String priceStart, String priceEnd, String termsAndCondition, String trainingType, String projectType, String projectStatus, String handler, String eligibility, String stepToFollow, String faq, String project_location, String project_payout, String referral_url, String banner, List<TrainingContent> training, List<MediaContent> media) {
        this.status = status;
        this.msg = msg;
        this.projectId = projectId;
        this.compName = compName;
        this.logo = logo;
        this.projectTitle = projectTitle;
        this.projectDescription = projectDescription;
        this.startTime = startTime;
        this.endTime = endTime;
        this.breakStartTime = breakStartTime;
        this.breakEndTime = breakEndTime;
        this.education = education;
        this.priceStart = priceStart;
        this.priceEnd = priceEnd;
        this.termsAndCondition = termsAndCondition;
        this.trainingType = trainingType;
        this.projectType = projectType;
        this.projectStatus = projectStatus;
        this.handler = handler;
        this.eligibility = eligibility;
        this.stepToFollow = stepToFollow;
        this.faq = faq;
        this.project_location = project_location;
        this.project_payout = project_payout;
        this.referral_url = referral_url;
        this.banner = banner;
        this.training = training;
        this.media = media;
    }

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getProject_payout() {
        return project_payout;
    }

    public void setProject_payout(String project_payout) {
        this.project_payout = project_payout;
    }

    public String getProject_location() {
        return project_location;
    }

    public void setProject_location(String project_location) {
        this.project_location = project_location;
    }

    public String getReferral_url() {
        return referral_url;
    }

    public void setReferral_url(String referral_url) {
        this.referral_url = referral_url;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getCompName() {
        return compName;
    }

    public void setCompName(String compName) {
        this.compName = compName;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getProjectTitle() {
        return projectTitle;
    }

    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getBreakStartTime() {
        return breakStartTime;
    }

    public void setBreakStartTime(String breakStartTime) {
        this.breakStartTime = breakStartTime;
    }

    public String getBreakEndTime() {
        return breakEndTime;
    }

    public void setBreakEndTime(String breakEndTime) {
        this.breakEndTime = breakEndTime;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getPriceStart() {
        return priceStart;
    }

    public void setPriceStart(String priceStart) {
        this.priceStart = priceStart;
    }

    public String getPriceEnd() {
        return priceEnd;
    }

    public void setPriceEnd(String priceEnd) {
        this.priceEnd = priceEnd;
    }

    public String getTermsAndCondition() {
        return termsAndCondition;
    }

    public void setTermsAndCondition(String termsAndCondition) {
        this.termsAndCondition = termsAndCondition;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getProjectType() {
        return projectType;
    }

    public void setProjectType(String projectType) {
        this.projectType = projectType;
    }

    public String getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(String projectStatus) {
        this.projectStatus = projectStatus;
    }

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public String getEligibility() {
        return eligibility;
    }

    public void setEligibility(String eligibility) {
        this.eligibility = eligibility;
    }

    public String getStepToFollow() {
        return stepToFollow;
    }

    public void setStepToFollow(String stepToFollow) {
        this.stepToFollow = stepToFollow;
    }

    public String getFaq() {
        return faq;
    }

    public void setFaq(String faq) {
        this.faq = faq;
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