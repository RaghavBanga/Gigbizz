package com.gigbiz.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PwFirstFormRequest {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("usertype")
    @Expose
    private String usertype;
    @SerializedName("project_id")
    @Expose
    private String projectId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("seller_email_id")
    @Expose
    private String sellerEmailId;
    @SerializedName("phone_no")
    @Expose
    private String phoneNo;
    @SerializedName("alt_phone_no")
    @Expose
    private String altPhoneNo;
    @SerializedName("education")
    @Expose
    private String education;
    @SerializedName("state")
    @Expose
    private String state;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("book_store_name")
    @Expose
    private String bookStoreName;
    @SerializedName("store_address")
    @Expose
    private String storeAddress;
    @SerializedName("pincode")
    @Expose
    private String pincode;
    @SerializedName("shop_location_link")
    @Expose
    private String shopLocationLink;
    @SerializedName("seller_supplying_book_school")
    @Expose
    private String sellerSupplyingBookSchool;
    @SerializedName("school_name")
    @Expose
    private String schoolName;
    @SerializedName("no_of_staff")
    @Expose
    private String noOfStaff;
    @SerializedName("montly_income")
    @Expose
    private String montlyIncome;
    @SerializedName("discount_coupon_code")
    @Expose
    private String discount_coupon_code;

    public PwFirstFormRequest(String userId, String token, String usertype, String projectId, String name, String email, String age, String gender, String sellerEmailId, String phoneNo, String altPhoneNo, String education, String state, String city, String bookStoreName, String storeAddress, String pincode, String shopLocationLink, String sellerSupplyingBookSchool, String schoolName, String noOfStaff, String montlyIncome, String discount_coupon_code) {
        this.userId = userId;
        this.token = token;
        this.usertype = usertype;
        this.projectId = projectId;
        this.name = name;
        this.email = email;
        this.age = age;
        this.gender = gender;
        this.sellerEmailId = sellerEmailId;
        this.phoneNo = phoneNo;
        this.altPhoneNo = altPhoneNo;
        this.education = education;
        this.state = state;
        this.city = city;
        this.bookStoreName = bookStoreName;
        this.storeAddress = storeAddress;
        this.pincode = pincode;
        this.shopLocationLink = shopLocationLink;
        this.sellerSupplyingBookSchool = sellerSupplyingBookSchool;
        this.schoolName = schoolName;
        this.noOfStaff = noOfStaff;
        this.montlyIncome = montlyIncome;
        this.discount_coupon_code = discount_coupon_code;
    }

    public String getDiscount_coupon_code() {
        return discount_coupon_code;
    }

    public void setDiscount_coupon_code(String discount_coupon_code) {
        this.discount_coupon_code = discount_coupon_code;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsertype() {
        return usertype;
    }

    public void setUsertype(String usertype) {
        this.usertype = usertype;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSellerEmailId() {
        return sellerEmailId;
    }

    public void setSellerEmailId(String sellerEmailId) {
        this.sellerEmailId = sellerEmailId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getAltPhoneNo() {
        return altPhoneNo;
    }

    public void setAltPhoneNo(String altPhoneNo) {
        this.altPhoneNo = altPhoneNo;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getBookStoreName() {
        return bookStoreName;
    }

    public void setBookStoreName(String bookStoreName) {
        this.bookStoreName = bookStoreName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getShopLocationLink() {
        return shopLocationLink;
    }

    public void setShopLocationLink(String shopLocationLink) {
        this.shopLocationLink = shopLocationLink;
    }

    public String getSellerSupplyingBookSchool() {
        return sellerSupplyingBookSchool;
    }

    public void setSellerSupplyingBookSchool(String sellerSupplyingBookSchool) {
        this.sellerSupplyingBookSchool = sellerSupplyingBookSchool;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getNoOfStaff() {
        return noOfStaff;
    }

    public void setNoOfStaff(String noOfStaff) {
        this.noOfStaff = noOfStaff;
    }

    public String getMontlyIncome() {
        return montlyIncome;
    }

    public void setMontlyIncome(String montlyIncome) {
        this.montlyIncome = montlyIncome;
    }

}