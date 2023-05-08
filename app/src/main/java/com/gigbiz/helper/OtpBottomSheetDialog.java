package com.gigbiz.helper;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.Nullable;

import com.gigbiz.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

public class OtpBottomSheetDialog extends BottomSheetDialogFragment {

    OtpVerify otpVerify;

    public OtpBottomSheetDialog(OtpVerify otpVerify) {
        this.otpVerify = otpVerify;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.otp_screen_bottom_sheet,
                container, false);

        Button verify_btn = v.findViewById(R.id.verify);
        OtpEditText otpEditText = v.findViewById(R.id.otp);

        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {

                otpVerify.onVerifyClick(otpEditText.getText().toString().trim());

            }
        });

        return v;
    }

    public interface OtpVerify{

        void onVerifyClick(String otp);

    }
}