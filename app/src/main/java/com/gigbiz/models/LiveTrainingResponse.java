package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LiveTrainingResponse {

    @SerializedName("live_training")
    @Expose
    private List<LiveTraining> liveTraining = null;


    public LiveTrainingResponse(List<LiveTraining> liveTraining) {
        super();
        this.liveTraining = liveTraining;
    }

    public List<LiveTraining> getLiveTraining() {
        return liveTraining;
    }

    public void setLiveTraining(List<LiveTraining> liveTraining) {
        this.liveTraining = liveTraining;
    }

}
