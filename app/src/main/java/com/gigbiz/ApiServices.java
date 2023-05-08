package com.gigbiz;

import com.gigbiz.models.AirtelReportListResponse;
import com.gigbiz.models.AmazonReportListResponse;
import com.gigbiz.models.AssignedModel;
import com.gigbiz.models.BannerResponse;
import com.gigbiz.models.ChangePasswordOtp;
import com.gigbiz.models.ChangePasswordOtpResponse;
import com.gigbiz.models.CreditCardCommonRequest;
import com.gigbiz.models.CreditCardCommonResponse;
import com.gigbiz.models.CreditCardReportListResponse;
import com.gigbiz.models.FlipkartReportListResponse;
import com.gigbiz.models.FydoReportListResponse;
import com.gigbiz.models.GoodWorkerFinalRequest;
import com.gigbiz.models.GoodWorkerFinalResponse;
import com.gigbiz.models.GoodWorkerFirstRequest;
import com.gigbiz.models.GoodWorkerFirstResponse;
import com.gigbiz.models.GoodWorkerReportListResponse;
import com.gigbiz.models.GoodWorkerSecondRequest;
import com.gigbiz.models.GoodWorkerSecondResponse;
import com.gigbiz.models.IdCardResponseModel;
import com.gigbiz.models.InsuranceCommonRequest;
import com.gigbiz.models.InsuranceCommonResponse;
import com.gigbiz.models.InsuranceReportListResponse;
import com.gigbiz.models.JoinedLiveTrainingRequest;
import com.gigbiz.models.JoinedLiveTrainingResponse;
import com.gigbiz.models.KycRequestModel;
import com.gigbiz.models.KycResponseModel;
import com.gigbiz.models.LiveTrainingResponse;
import com.gigbiz.models.LoanCommonRequest;
import com.gigbiz.models.LoanCommonResponse;
import com.gigbiz.models.LoanReportListResponse;
import com.gigbiz.models.LoginRequest;
import com.gigbiz.models.LoginResponse;
import com.gigbiz.models.OfferLetterResponseModel;
import com.gigbiz.models.OtpCheckRequest;
import com.gigbiz.models.OtpCheckResponse;
import com.gigbiz.models.OtpSendRequest;
import com.gigbiz.models.OtpSendResponse;
import com.gigbiz.models.PayoutReportRequest;
import com.gigbiz.models.PayoutReportResponse;
import com.gigbiz.models.ProfileDetailResponse;
import com.gigbiz.models.PwFinalFormRequest;
import com.gigbiz.models.PwFinalFormResponse;
import com.gigbiz.models.PwFirstFormRequest;
import com.gigbiz.models.PwFirstFormResponse;
import com.gigbiz.models.PwPhotoFormRequest;
import com.gigbiz.models.PwPhotoFormResponse;
import com.gigbiz.models.PwReportListRequest;
import com.gigbiz.models.PwReportListResponse;
import com.gigbiz.models.RedeemRequest;
import com.gigbiz.models.RedeemResponse;
import com.gigbiz.models.ReportListResponse;
import com.gigbiz.models.ReportListSelectionResponse;
import com.gigbiz.models.ResetPasswordRequest;
import com.gigbiz.models.ResetPasswordResponse;
import com.gigbiz.models.SendBankRequest;
import com.gigbiz.models.SendBankResponse;
import com.gigbiz.models.SignUpRequest;
import com.gigbiz.models.StateResponse;
import com.gigbiz.models.SubmitAirtelReportFirstRequest;
import com.gigbiz.models.SubmitAirtelReportFirstResponse;
import com.gigbiz.models.SubmitAirtelReportSecondRequest;
import com.gigbiz.models.SubmitAirtelReportSecondResponse;
import com.gigbiz.models.SubmitAirtelReportThirdRequest;
import com.gigbiz.models.SubmitAirtelReportThirdResponse;
import com.gigbiz.models.SubmitAmazonFirstRequest;
import com.gigbiz.models.SubmitAmazonFirstResponse;
import com.gigbiz.models.SubmitAmazonSecondRequest;
import com.gigbiz.models.SubmitAmazonSecondResponse;
import com.gigbiz.models.SubmitFirstReportRequest;
import com.gigbiz.models.SubmitFirstReportResponse;
import com.gigbiz.models.SubmitFlipKartFinalRequest;
import com.gigbiz.models.SubmitFlipKartFinalResponse;
import com.gigbiz.models.SubmitFlipKartFirstRequest;
import com.gigbiz.models.SubmitFlipKartFirstResponse;
import com.gigbiz.models.SubmitFlipKartSecondRequest;
import com.gigbiz.models.SubmitFlipKartSecondResponse;
import com.gigbiz.models.SubmitFydoFirstRequest;
import com.gigbiz.models.SubmitFydoFirstResponse;
import com.gigbiz.models.SubmitFydoSecondRequest;
import com.gigbiz.models.SubmitFydoSecondResponse;
import com.gigbiz.models.SubmitFydoThirdRequest;
import com.gigbiz.models.SubmitFydoThirdResponse;
import com.gigbiz.models.SubmitReportFinalRequest;
import com.gigbiz.models.SubmitReportFinalResponse;
import com.gigbiz.models.SubmitReportSecondRequest;
import com.gigbiz.models.SubmitReportSecondResponse;
import com.gigbiz.models.TransactionHistoryRequest;
import com.gigbiz.models.TransactionHistoryResponse;
import com.gigbiz.models.UpdateProfilePicRequest;
import com.gigbiz.models.UpdateProfilePicResponse;
import com.gigbiz.models.UploadKycRequest;
import com.gigbiz.models.UploadKycResponse;
import com.gigbiz.models.UserProfileRequest;
import com.gigbiz.models.UserTaskRequest;
import com.gigbiz.models.UserTaskRequestReport;
import com.gigbiz.models.UserTaskResponse;
import com.gigbiz.models.UserWalletDetailResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServices {

//    @POST("signin")
//    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @POST("signin")
    Call<LoginResponse> getLoginResponse(@Body LoginRequest loginRequest);

    @POST("user_task")
    Call<UserTaskResponse> getUserTaskResponse(@Body UserTaskRequest userTask);

    @POST("insert_shop")
    Call<OtpSendResponse> getOtpSendResponse(@Body OtpSendRequest otpSendRequest);

    @POST("checkShopOtp")
    Call<OtpCheckResponse> getOtpCheckResponse(@Body OtpCheckRequest otpCheckRequest);

    @POST("submit_report_first")
    Call<SubmitFirstReportResponse> getSubmitFirstReportResponse(@Body SubmitFirstReportRequest submitFirstReportRequest);

    @POST("submit_report_second")
    Call<SubmitReportSecondResponse> getSubmitReportSecondResponse(@Body SubmitReportSecondRequest submitReportSecondRequest);

    @POST("submit_report_final_flipkart")
    Call<SubmitReportFinalResponse> getSubmitReportFinalResponse(@Body SubmitReportFinalRequest submitReportFinalRequest);

    @POST("report_list")
    Call<ReportListResponse> getReportListResponse(@Body UserTaskRequest userTaskRequest);

    @POST("user_profile")
    Call<ProfileDetailResponse> getProfileDetails(@Body UserProfileRequest userProfileRequest);

    @POST("insert_goodworker")
    Call<GoodWorkerFirstResponse> sendFirstFormGoodWorker(@Body GoodWorkerFirstRequest goodWorkerFirstRequest);

    @POST("insert_goodworker_second")
    Call<GoodWorkerSecondResponse> sendSecondFormGoodWorker(@Body GoodWorkerSecondRequest goodWorkerSecondRequest);

    @POST("insert_goodworker_third")
    Call<GoodWorkerFinalResponse> sendFinalFormGoodWorker(@Body GoodWorkerFinalRequest goodWorkerFinalRequest);

    @POST("report_list_goodworker")
    Call<GoodWorkerReportListResponse> getGoodReportListResponse(@Body UserTaskRequest userTaskRequest);

    @POST("signup")
    Call<LoginResponse> getSignUpResponse(@Body SignUpRequest signUpRequest);

    @POST("uploadkyc")
    Call<UploadKycResponse> getUploadKycResponse(@Body UploadKycRequest uploadKycRequest);

    @POST("updatebankdetails")
    Call<SendBankResponse> getBankDetailResponse(@Body SendBankRequest sendBankRequest);

    @POST("insert_flipkart")
    Call<SubmitFlipKartFirstResponse> getFlipKartFirstResponse(@Body SubmitFlipKartFirstRequest submitFlipKartFirstRequest);

    @POST("insert_flipkart_second")
    Call<SubmitFlipKartSecondResponse> getFlipKartSecondResponse(@Body SubmitFlipKartSecondRequest submitFlipKartSecondRequest);

    @POST("insert_flipkart_third")
    Call<SubmitFlipKartFinalResponse> getFlipKartFinalResponse(@Body SubmitFlipKartFinalRequest submitFlipKartFinalRequest);

    @POST("insert_amazon")
    Call<SubmitAmazonFirstResponse> getAmazonFirstResponse(@Body SubmitAmazonFirstRequest submitAmazonFirstRequest);

    @POST("insert_amazon_second")
    Call<SubmitAmazonSecondResponse> getAmazonSecondResponse(@Body SubmitAmazonSecondRequest submitAmazonSecondRequest);

    @POST("insert_airtel")
    Call<SubmitAirtelReportFirstResponse> getAirtelReportFirstResponse(@Body SubmitAirtelReportFirstRequest submitAirtelReportFirstRequest);

    @POST("insert_airtel_second")
    Call<SubmitAirtelReportSecondResponse> getAirtelReportSecondResponse(@Body SubmitAirtelReportSecondRequest submitAirtelReportSecondRequest);

    @POST("insert_airtel_third")
    Call<SubmitAirtelReportThirdResponse> getAirtelReportThirdResponse(@Body SubmitAirtelReportThirdRequest submitAirtelReportThirdRequest);

    @POST("insert_fydo")
    Call<SubmitFydoFirstResponse> getFydoReportFirstResponse(@Body SubmitFydoFirstRequest submitFydoFirstRequest);

    @POST("insert_fydo_second")
    Call<SubmitFydoSecondResponse> getFydoReportSecondResponse(@Body SubmitFydoSecondRequest submitFydoSecondRequest);

    @POST("insert_fydo_third")
    Call<SubmitFydoThirdResponse> getFydoReportThirdResponse(@Body SubmitFydoThirdRequest submitFydoThirdRequest);

    @POST("report_list_flipkart")
    Call<FlipkartReportListResponse> getFlipkartReportListResponse(@Body UserTaskRequest userTaskRequest);

    @POST("report_list_amazon")
    Call<AmazonReportListResponse> getAmazonReportListResponse(@Body UserTaskRequest userTaskRequest);

    @POST("report_list_airtel")
    Call<AirtelReportListResponse> getAirtelReportListResponse(@Body UserTaskRequest userTaskRequest);

    @POST("report_list_fydo")
    Call<FydoReportListResponse> getFydoReportListResponse(@Body UserTaskRequest userTaskRequest);

    @POST("update_user_profile")
    Call<UpdateProfilePicResponse> getUpdateProfilePicResponse(@Body UpdateProfilePicRequest updateProfilePicRequest);

    @POST("kycstatus")
    Call<KycResponseModel> getKycResponseModel(@Body KycRequestModel kycRequestModel);

    @GET("banner")
    Call<BannerResponse> getBannerResponseModel();

    @POST("paymentredeem")
    Call<RedeemResponse> getRedeemRequest(@Body RedeemRequest redeemRequest);

    @POST("redeemrequestlist")
    Call<TransactionHistoryResponse> getTransactionHistoryRequest(@Body TransactionHistoryRequest transactionHistoryRequest);

    @POST("userwallet")
    Call<UserWalletDetailResponse> getWalletRequest(@Body TransactionHistoryRequest transactionHistoryRequest);

    @POST("reportlist")
    Call<ReportListSelectionResponse> getReportListRequest(@Body TransactionHistoryRequest transactionHistoryRequest);

    @POST("insert_pw")
    Call<PwFirstFormResponse> getPwFirstFormResponse(@Body PwFirstFormRequest pwFirstFormRequest);

    @POST("insert_pw_second")
    Call<PwPhotoFormResponse> getPwPhotoFormResponse(@Body PwPhotoFormRequest pwPhotoFormRequest);

    @POST("insert_pw_third")
    Call<PwFinalFormResponse> getPwFinalFormResponse(@Body PwFinalFormRequest pwFinalFormRequest);

    @POST("report_list_pw")
    Call<PwReportListResponse> getPwReportListResponse(@Body PwReportListRequest pwReportListRequest);

    @POST("insert_creditcard")
    Call<CreditCardCommonResponse> getCreditCardCommonResponse(@Body CreditCardCommonRequest creditCardCommonRequest);

    @POST("insert_insurance")
    Call<InsuranceCommonResponse> getInsuranceCommonResponse(@Body InsuranceCommonRequest insuranceCommonRequest);

    @POST("insert_loan")
    Call<LoanCommonResponse> getLoanCommonResponse(@Body LoanCommonRequest loanCommonRequest);

    @POST("report_list_loan")
    Call<LoanReportListResponse> getLoanReportListResponse(@Body UserTaskRequestReport userTaskRequest);

    @POST("report_list_insurance")
    Call<InsuranceReportListResponse> getInsuranceReportListResponse(@Body UserTaskRequestReport userTaskRequest);

    @POST("report_list_creditcard")
    Call<CreditCardReportListResponse> getCreditCardReportListResponse(@Body UserTaskRequestReport userTaskRequest);

    @POST("training_online")
    Call<LiveTrainingResponse> getLiveTrainingResponse(@Body PwReportListRequest pwReportListRequest);

    @POST("user_training_joined")
    Call<JoinedLiveTrainingResponse> getJoinedLiveTrainingResponse(@Body JoinedLiveTrainingRequest joinedLiveTrainingRequest);

    @GET("statelist")
    Call<StateResponse> getStateResponse();

    @POST("forgot_password")
    Call<ResetPasswordResponse> getResetPasswordResponse(@Body ResetPasswordRequest resetPasswordRequest);

    @POST("checkMobileOTP")
    Call<ChangePasswordOtpResponse> getChangePasswordOtpResponse(@Body ChangePasswordOtp changePasswordOtp);

    @POST("project_reports")
    Call<PayoutReportResponse> getPayoutReportResponse(@Body PayoutReportRequest payoutReportRequest);

    @POST("team_reports")
    Call<AssignedModel> getTeamReportResponse(@Body PayoutReportRequest payoutReportRequest);

    @POST("offer_letter")
    Call<OfferLetterResponseModel> getOfferLetterResponseModel(@Body UserTaskRequest userTaskRequest);

    @POST("employee_icard")
    Call<IdCardResponseModel> getIdCardResponseModel(@Body UserTaskRequest userTaskRequest);
}
