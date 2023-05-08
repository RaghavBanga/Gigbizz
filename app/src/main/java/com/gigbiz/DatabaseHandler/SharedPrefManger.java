package com.gigbiz.DatabaseHandler;

import android.content.Context;
import android.content.SharedPreferences;

import com.gigbiz.Utilities;
import com.gigbiz.models.Approved;
import com.gigbiz.models.Project;
import com.gigbiz.models.Rejected;
import com.gigbiz.models.SignUpResponse;
import com.gigbiz.models.Submitted;
import com.gigbiz.models.UserData;
import com.gigbiz.models.UserDatum;
import com.google.gson.Gson;

import java.util.List;

public class SharedPrefManger {

    public static String SHARED_PREF_NAME = "gigbiz";
    private SharedPreferences sharedPreferences;
    Context context;
    private SharedPreferences.Editor editor;

    public SharedPrefManger(Context context, SharedPreferences sharedPreferences) {
        this.context = context;
        this.sharedPreferences = sharedPreferences;
    }

    public void setListUserData(List<UserData> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserData(json);
    }
    public void setListUserDataSignup(List<UserDatum> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserData(json);
    }

    public void setListUserTaskDetails(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetails(json);
    }

    public void setListUserTaskDetails_creditcard(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetailscreditcard(json);
    }

    private void setUserTaskDetailscreditcard(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL_CREDIT_CARD, value);
        editor.apply();
    }

    public void setListUserTaskDetails_personalloans(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetailspersonalloans(json);
    }

    private void setUserTaskDetailspersonalloans(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL_PERSONAL_LOANS, value);
        editor.apply();
    }

    public void setListUserTaskDetails_businessloans(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetailsbusinessloans(json);
    }

    private void setUserTaskDetailsbusinessloans(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL_BUSINESS_LOANS, value);
        editor.apply();
    }

    public void setListUserTaskDetails_homeloans(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetailshomeloans(json);
    }

    private void setUserTaskDetailshomeloans(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL_HOME_LOANS, value);
        editor.apply();
    }

    public void setListUserTaskDetails_carloans(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetailscarloans(json);
    }

    private void setUserTaskDetailscarloans(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL_CAR_LOANS, value);
        editor.apply();
    }

    public void setListUserTaskDetails_healthinsurance(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetailshealthinsurance(json);
    }

    private void setUserTaskDetailshealthinsurance(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL_HEALTH, value);
        editor.apply();
    }

    public void setListUserTaskDetails_carinsurance(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetailscarinsurance(json);
    }

    private void setUserTaskDetailscarinsurance(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL_CAR, value);
        editor.apply();
    }

    public void setListUserTaskDetails_lifeinsurance(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetailslifeinsurance(json);
    }

    private void setUserTaskDetailslifeinsurance(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL_LIFE, value);
        editor.apply();
    }

    public void setListUserTaskDetails_savingaccount(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetailssavingaccount(json);
    }

    private void setUserTaskDetailssavingaccount(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL_SAVING, value);
        editor.apply();
    }

    public void setListUserTaskDetails_demataccount(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetailsdemataccount(json);
    }

    private void setUserTaskDetailsdemataccount(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL_DEMAT, value);
        editor.apply();
    }

    public void setListUserTaskDetails_more(List<Project> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setUserTaskDetailsmore(json);
    }

    private void setUserTaskDetailsmore(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL_MORE, value);
        editor.apply();
    }

    public void setReportListSubmittedDetails(List<Submitted> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setListSubmittedDetails(json);
    }

    public void setReportListRejectedDetails(List<Rejected> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setListRejectedDetails(json);
    }

    public void setReportListApprovedDetails(List<Approved> list) {
        Gson gson = new Gson();
        String json = gson.toJson(list);

        setListApprovedDetails(json);
    }

    public void setUserData(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.LOGIN_DATA_OF_USER, value);
        editor.putBoolean(Utilities.IS_LOGIN, true);
        editor.apply();
    }

    public void setUserDataForSignUp(SignUpResponse value) {
        Gson gson = new Gson();
        String json = gson.toJson(value);
        editor = sharedPreferences.edit();
        editor.putString(Utilities.SIGNUP_DATA_OF_USER, json);
//        editor.putBoolean(Utilities.IS_SIGNUP, true);
        editor.apply();
    }

    public void setUserTaskDetails(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.USER_TASK_DETAIL, value);
        editor.apply();
    }

    public void setListSubmittedDetails(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.SUBMITTED_LIST_DETAIL, value);
        editor.apply();
    }

    public void setListRejectedDetails(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.REJECTED_LIST_DETAIL, value);
        editor.apply();
    }

    public void setListApprovedDetails(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.APPROVED_LIST_DETAIL, value);
        editor.apply();
    }

    public void setCodeExecuted() {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.IS_CODE_EXECUTED, "1");
        editor.apply();
    }

    public void setGoodWorkerType(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.GOOD_WORKER_TYPE, value);
        editor.apply();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor = sharedPreferences.edit();
        editor.putBoolean(Utilities.IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return sharedPreferences.getBoolean(Utilities.IS_FIRST_TIME_LAUNCH, true);
    }

    public boolean isFirstTimeSignup() {
        return sharedPreferences.getBoolean(Utilities.IS_FIRST_TIME_SIGNUP, true);
    }

    public void setFirstTimeSignup(boolean isFirstTime) {
        editor = sharedPreferences.edit();
        editor.putBoolean(Utilities.IS_FIRST_TIME_SIGNUP, isFirstTime);
        editor.commit();
    }


//
//    public List<UserData> getUserDataList(){
//        List<UserData> arrayItems = new ArrayList<>();
//        String serializedObject = sharedPreferences.getString(Utilities.LOGIN_DATA_OF_USER, null);
//        if (serializedObject != null) {
//            Gson gson = new Gson();
//            Type type = new TypeToken<List<UserData>>(){}.getType();
//            arrayItems = gson.fromJson(serializedObject, type);
//        }
//
//        return arrayItems;
//    }

    public void setEmpTypeChoose(String value) {
        editor = sharedPreferences.edit();
        editor.putString(Utilities.TYPE_CHOOSE, value);
        editor.apply();
    }

    public String getEmpTypeChoose() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Utilities.TYPE_CHOOSE, "freelancer");
    }

    public String isCodeExecuted() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(Utilities.IS_CODE_EXECUTED, " ");
    }

    public boolean isLoggedIn() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(Utilities.IS_LOGIN, false);
    }

    /**
     * currently not in use
     */
    public boolean isSignup() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        return sharedPreferences.getBoolean(Utilities.IS_SIGNUP, false);
    }

    public void logout() {
        sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
