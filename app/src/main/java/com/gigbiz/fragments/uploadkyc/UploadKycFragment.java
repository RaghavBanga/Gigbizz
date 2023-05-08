package com.gigbiz.fragments.uploadkyc;

import static android.app.Activity.RESULT_OK;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gigbiz.DatabaseHandler.SharedPrefManger;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.KycUploadActivity;
import com.gigbiz.databinding.FragmentUploadKycBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.UploadKycRequest;
import com.gigbiz.models.UploadKycResponse;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadKycFragment extends Fragment {

    private UploadKycViewModel mViewModel;
    FragmentUploadKycBinding binding;
    private KycUploadActivity kycUploadActivity;
    File compressedAadhaarCard;
    File compressedAadhaarCardPdf;
    Uri compressedAadhaarCarduri;
    File compressedPanCard;
    File compressedPanCardPdf;
    Uri compressedPanCarduri;
    File compressedDrivingLicense;
    File compressedDrivingLicensePdf;
    Uri compressedDrivingLicenseuri;
    File compressedPic;
    SharedPrefManger sharedPrefManger;
    SharedPreferences sh;
    public boolean check = false;


    public static UploadKycFragment newInstance() {
        return new UploadKycFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentUploadKycBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        sh = getContext().getSharedPreferences("gigbiz", Context.MODE_PRIVATE);
        kycUploadActivity = (KycUploadActivity) getActivity();

        try {
            sharedPrefManger = new SharedPrefManger(getActivity(), sh);
        } catch (Exception e) {
            e.printStackTrace();
        }

        binding.add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        final CharSequence[] options = {"Image From Gallery", "PDF", "Cancel"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Select Option");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                if (options[item].equals("Image From Gallery")) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                                    intent.setType("image/*");
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1000);
//                                    getAdhaarcardGallery.launch(intent);
                                } else if (options[item].equals("PDF")) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.setType("application/pdf");
//                                    getAdhaarcardPdf.launch(intent);
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1001);
                                } else if (options[item].equals("Cancel")) {
                                    dialog.dismiss();
                                }
                            }
                        });
                        builder.show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.add2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        final CharSequence[] options = {"Image From Gallery", "PDF", "Cancel"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Select Option");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                if (options[item].equals("Image From Gallery")) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                    intent.setType("image/*");
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2000);
//                                    getPanCardFromGallary.launch(intent);
                                } else if (options[item].equals("PDF")) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.setType("application/pdf");
//                                    getPanCardPdf.launch(intent);
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2001);

                                } else if (options[item].equals("Cancel")) {
                                    dialog.dismiss();
                                }
                            }
                        });
                        builder.show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.add3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        final CharSequence[] options = {"Image From Gallery", "PDF", "Cancel"};
                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                        builder.setTitle("Select Option");
                        builder.setItems(options, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int item) {
                                if (options[item].equals("Image From Gallery")) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                                    intent.setType("image/*");
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3000);
//                                    getDrivingLicenseFromGallery.launch(intent);
                                } else if (options[item].equals("PDF")) {
                                    dialog.dismiss();
                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                                    intent.setType("application/pdf");
//                                    getDrivingLicensePdf.launch(intent);
                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3001);

                                } else if (options[item].equals("Cancel")) {
                                    dialog.dismiss();
                                }
                            }
                        });
                        builder.show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        binding.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        //permission allowed

                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 4000);
//                        getProfileFromGallery.launch(intent);

                    }
//                    else{
//                        //permission not allowed
//                        MainHelper.requestPermission(getActivity());
//
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (compressedPic == null) {
                    Toast.makeText(getContext(), "please select Pic", Toast.LENGTH_SHORT).show();
                }else if (binding.aadhaarno.getText().toString().trim().length()!=12){
                    Toast.makeText(getContext(), "please fill correct aadhaar number", Toast.LENGTH_SHORT).show();
                }else if (binding.panno.getText().toString().trim().length()!=10){
                    Toast.makeText(getContext(), "please fill correct pan number", Toast.LENGTH_SHORT).show();
                } else if (compressedAadhaarCard == null && compressedAadhaarCardPdf == null) {
                    Toast.makeText(getContext(), "please select Aadhaar Card front", Toast.LENGTH_SHORT).show();
                } else if (compressedPanCard == null && compressedPanCardPdf == null) {
                    Toast.makeText(getContext(), "please select Aadhaar Card back", Toast.LENGTH_SHORT).show();
                } else if (compressedDrivingLicense == null && compressedDrivingLicensePdf == null) {
                    Toast.makeText(getContext(), "please select Pan Card", Toast.LENGTH_SHORT).show();
                } else {
                    sendKycDetails();
                    binding.progressbar.setVisibility(View.VISIBLE);

                }
            }
        });

//        binding.back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                try {
//                    getFragmentManager().beginTransaction()
//                            .remove(UploadKycFragment.this).commit();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });

        return root;
    }

//    private ActivityResultLauncher getAdhaarcardGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                try {
//                    if (result.getData() != null) {
//                        Uri selectedImageUri = result.getData().getData();
//                        compressedAadhaarCard = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
//                        Glide.with(getActivity())
//                                .load(compressedAadhaarCard.getAbsolutePath())
//                                .into(binding.aadhaarcard);
//                        Toast.makeText(getContext(), compressedAadhaarCard.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });

//    private ActivityResultLauncher getAdhaarcardPdf = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @SuppressLint("Range")
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                try {
//                    if (result.getData() != null) {
//                        Uri selectedPdfUri = result.getData().getData();
//                        File myFile = new File(String.valueOf(selectedPdfUri));
////                        String displayName = null;
////
////                        if (String.valueOf(selectedPdfUri).startsWith("content://")) {
////                            Cursor cursor = null;
////                            try {
////                                cursor = getActivity().getContentResolver().query(selectedPdfUri, null, null, null, null);
////                                if (cursor != null && cursor.moveToFirst()) {
////                                    displayName = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
////                                }
////                            } finally {
////                                cursor.close();
////                            }
////                        } else if (String.valueOf(selectedPdfUri).startsWith("file://")) {
////                            displayName = myFile.getAbsolutePath();
////                        }
//                        compressedAadhaarCardPdf = new File(myFile.getAbsolutePath());
//                        Glide.with(getActivity())
//                                .load(R.drawable.pdf)
//                                .into(binding.aadhaarcard);
//                        Toast.makeText(getContext(), compressedAadhaarCardPdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });

//    private ActivityResultLauncher getPanCardFromGallary = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                try {
//                    if (result.getData() != null) {
//                        Uri selectedImageUri = result.getData().getData();
//                        compressedPanCard = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
//                        Glide.with(getActivity())
//                                .load(compressedPanCard.getAbsolutePath())
//                                .into(binding.pancard);
//                        Toast.makeText(getContext(), compressedPanCard.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });

//    private ActivityResultLauncher getPanCardPdf = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                try {
//                    if (result.getData() != null) {
//                        Uri selectedPdfUri = result.getData().getData();
//                        File myFile = new File(String.valueOf(selectedPdfUri));
//                        String path = myFile.getAbsolutePath();
//                        compressedPanCardPdf = new File(myFile.getAbsolutePath());
//                        Glide.with(getActivity())
//                                .load(R.drawable.pdf)
//                                .into(binding.pancard);
//                        Toast.makeText(getContext(), compressedPanCardPdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });

//    private ActivityResultLauncher getDrivingLicenseFromGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                try {
//                    if (result.getData() != null) {
//                        Uri selectedImageUri = result.getData().getData();
//                        compressedDrivingLicense = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
//                        Glide.with(getActivity())
//                                .load(compressedDrivingLicense.getAbsolutePath())
//                                .into(binding.drivingLicense);
//                        Toast.makeText(getContext(), compressedDrivingLicense.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });

//    private ActivityResultLauncher getDrivingLicensePdf = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                try {
//                    if (result.getData() != null) {
//                        Uri selectedPdfUri = result.getData().getData();
//                        File myFile = new File(String.valueOf(selectedPdfUri));
//                        String path = myFile.getAbsolutePath();
//                        compressedDrivingLicensePdf = new File(myFile.getAbsolutePath());
//                        Glide.with(getActivity())
//                                .load(R.drawable.pdf)
//                                .into(binding.drivingLicense);
//                        Toast.makeText(getContext(), compressedDrivingLicensePdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });

//    private ActivityResultLauncher getProfileFromGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                try {
//                    if (result.getData() != null) {
//                        Uri selectedImageUri = result.getData().getData();
//                        compressedPic = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
//                        Glide.with(getActivity())
//                                .load(compressedPic.getAbsolutePath())
//                                .into(binding.pic);
//                        Toast.makeText(getContext(), compressedPic.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });

    private void sendKycDetails() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String userId = MainHelper.getUserDataList(sh).get(0).getUserId();
                String userToken = MainHelper.getUserDataList(sh).get(0).getToken();
//                String userType  = MainHelper.getUserDataList(sh).get(0).getType().toLowerCase();
                Call<UploadKycResponse> call = RetrofitClient.getInstance().getApi().getUploadKycResponse(
                        new UploadKycRequest(userId,
                                userToken,
                                MainHelper.getFileToByte(compressedPic.getAbsolutePath()),
                                compressedAadhaarCard != null ? MainHelper.getFileToByte(compressedAadhaarCard.getAbsolutePath()) : MainHelper.getStringPdf(compressedAadhaarCarduri,getContext()),
                                compressedPanCard != null ? MainHelper.getFileToByte(compressedPanCard.getAbsolutePath()) : MainHelper.getStringPdf(compressedPanCarduri,getContext()),
                                compressedDrivingLicense != null ? MainHelper.getFileToByte(compressedDrivingLicense.getAbsolutePath()) : MainHelper.getStringPdf(compressedDrivingLicenseuri,getContext()),
                                binding.aadhaarno.getText().toString(),
                                binding.panno.getText().toString()
                        ));

                call.enqueue(new Callback<UploadKycResponse>() {
                    @Override
                    public void onResponse(Call<UploadKycResponse> call, Response<UploadKycResponse> response) {

                        UploadKycResponse userTaskResponse = response.body();
                        if (response.isSuccessful()) {

                            try {
                                Toast.makeText(getContext(), userTaskResponse.getMsg(), Toast.LENGTH_SHORT).show();

                                check = true;
                            } catch (Exception e) {
                                e.printStackTrace();
                                try {
                                    check = false;
                                    binding.progressbar.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), "System Fail", Toast.LENGTH_SHORT).show();
                                } catch (Exception ex) {
                                    ex.printStackTrace();
                                }

                            } finally {

                                try {
                                    getActivity().runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                binding.progressbar.setVisibility(View.GONE);
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            if (check == true) {
                                try {
//                                    BankDetailFragment bankDetailFragment = new BankDetailFragment();
//                                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
//                                    ft.replace(R.id.container_other_frag, bankDetailFragment);
//                                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                                    ft.addToBackStack(null);
//                                    ft.commit();
                                    kycUploadActivity.binding.viewPager.setOnTouchListener(null);
                                    kycUploadActivity.binding.viewPager.setCurrentItem(1, true);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }


                        }

                    }

                    @Override
                    public void onFailure(Call<UploadKycResponse> call, Throwable t) {
                        binding.progressbar.setVisibility(View.GONE);
                    }
                });
            }
        });

        thread.start();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(UploadKycViewModel.class);
        // TODO: Use the ViewModel
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                if (requestCode == 1000) {
                    if (null != selectedImageUri) {

                        compressedAadhaarCard = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedAadhaarCard.getAbsolutePath())
                                .into(binding.aadhaarcard);
                        Toast.makeText(getContext(), compressedAadhaarCard.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 2000) {
                    if (null != selectedImageUri) {

                        compressedPanCard = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedPanCard.getAbsolutePath())
                                .into(binding.pancard);
                        Toast.makeText(getContext(), compressedPanCard.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 3000) {
                    if (null != selectedImageUri) {

                        compressedDrivingLicense = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedDrivingLicense.getAbsolutePath())
                                .into(binding.drivingLicense);
                        Toast.makeText(getContext(), compressedDrivingLicense.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 4000) {
                    if (null != selectedImageUri) {

                        compressedPic = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedPic.getAbsolutePath())
                                .into(binding.pic);
                        Toast.makeText(getContext(), compressedPic.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 1001) {
                    if (null != selectedImageUri) {
                        compressedAadhaarCarduri = data.getData();

                        File myFile = new File(String.valueOf(selectedImageUri));

                        compressedAadhaarCardPdf = new File(myFile.getAbsolutePath());
                        Glide.with(getActivity())
                                .load(R.drawable.pdf)
                                .into(binding.aadhaarcard);
                        Toast.makeText(getContext(), compressedAadhaarCardPdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 3001) {
                    if (null != selectedImageUri) {
                        compressedDrivingLicenseuri = data.getData();

                        File myFile = new File(String.valueOf(selectedImageUri));
                        String path = myFile.getAbsolutePath();
                        compressedDrivingLicensePdf = new File(myFile.getAbsolutePath());
                        Glide.with(getActivity())
                                .load(R.drawable.pdf)
                                .into(binding.drivingLicense);
                        Toast.makeText(getContext(), compressedDrivingLicensePdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 2001) {
                    if (null != selectedImageUri) {
                        compressedPanCarduri = data.getData();
                        File myFile = new File(String.valueOf(selectedImageUri));
                        String path = myFile.getAbsolutePath();
                        compressedPanCardPdf = new File(myFile.getAbsolutePath());
                        Glide.with(getActivity())
                                .load(R.drawable.pdf)
                                .into(binding.pancard);
                        Toast.makeText(getContext(), compressedPanCardPdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}