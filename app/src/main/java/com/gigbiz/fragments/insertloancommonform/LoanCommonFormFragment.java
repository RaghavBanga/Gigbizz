package com.gigbiz.fragments.insertloancommonform;

import static android.app.Activity.RESULT_OK;
import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.gigbiz.R;
import com.gigbiz.RetrofitClient;
import com.gigbiz.activity.FormDashBoardActivity;
import com.gigbiz.activity.JobSuccessActivity;
import com.gigbiz.databinding.FragmentLoanCommonFormBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.AllListHandler;
import com.gigbiz.models.LoanCommonRequest;
import com.gigbiz.models.LoanCommonResponse;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoanCommonFormFragment extends Fragment {

    FragmentLoanCommonFormBinding binding;
    SharedPreferences sh;
    private FormDashBoardActivity formDashBoardActivity;
    private boolean check = false;
    File aadhaarfront_compressed;
    File aadhaarback_compressed;
    File compressedPanCard;
    File compressedPanCardPdf;
    File compressedtwomonthbankPdf;
    Uri compressedtwomonthbankuri;
    File compressedtwomonthbank;
    File compressedtwomonthsalaryPdf;
    Uri compressedtwomonthsalaryuri;
    File compressedtwomonthsalary;
    public String value,type_list;


    public LoanCommonFormFragment() {
        // Required empty public constructor
    }


    public static LoanCommonFormFragment newInstance(String param1, String param2) {
        LoanCommonFormFragment fragment = new LoanCommonFormFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoanCommonFormBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        formDashBoardActivity = (FormDashBoardActivity) getActivity();
        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);

        binding.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainHelper.isValidEmail(binding.email.getText().toString().trim()) == false) {
                    Toast.makeText(getContext(), "please enter correct email id", Toast.LENGTH_SHORT).show();
                }else if (MainHelper.isValidPhoneNumber(binding.mobNumber.getText().toString().trim()) == false){
                    Toast.makeText(getContext(), "please enter correct phone no", Toast.LENGTH_SHORT).show();
                }else if (binding.name.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "please enter name", Toast.LENGTH_SHORT).show();
                }else if (aadhaarfront_compressed == null){
                    Toast.makeText(getContext(), "please add aadhaar front image", Toast.LENGTH_SHORT).show();
                }else if (aadhaarback_compressed == null){
                    Toast.makeText(getContext(), "please add aadhaar back image", Toast.LENGTH_SHORT).show();
                }else if (compressedPanCard == null){
                    Toast.makeText(getContext(), "please add pan card image", Toast.LENGTH_SHORT).show();
                }else if (compressedtwomonthbankuri == null && compressedtwomonthbank == null){
                    Toast.makeText(getContext(), "please add bank image/pdf", Toast.LENGTH_SHORT).show();
                }else if (compressedtwomonthsalaryuri == null && compressedtwomonthsalary == null){
                    Toast.makeText(getContext(), "please add salary slip image/pdf", Toast.LENGTH_SHORT).show();
                }else {
                    binding.progressbar.setVisibility(View.VISIBLE);
                    submitFirstReport(root);
                }


            }
        });

        binding.add1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1000);

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
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2000);

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
//                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                        intent.setType("image/*");
//                        getlasttwo_monthbank.launch(intent);
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("application/pdf");
//                                    getlasttwo_monthbankPdf.launch(intent);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3001);

//                        final CharSequence[] options = {"Image From Gallery", "PDF", "Cancel"};
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                        builder.setTitle("Select Option");
//                        builder.setItems(options, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int item) {
//                                if (options[item].equals("Image From Gallery")) {
//                                    dialog.dismiss();
//                                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                                    intent.setType("image/*");
//                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3000);
////                                    getlasttwo_monthbank.launch(intent);
//                                } else if (options[item].equals("PDF")) {
//                                    dialog.dismiss();
//                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                                    intent.setType("application/pdf");
////                                    getlasttwo_monthbankPdf.launch(intent);
//                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 3001);
//
//                                } else if (options[item].equals("Cancel")) {
//                                    dialog.dismiss();
//                                }
//                            }
//                        });
//                        builder.show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.add4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        intent.setType("image/*");
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 4000);
//                        final CharSequence[] options = {"Image From Gallery", "PDF", "Cancel"};
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                        builder.setTitle("Select Option");
//                        builder.setItems(options, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int item) {
//                                if (options[item].equals("Image From Gallery")) {
//                                    dialog.dismiss();
//                                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                                    intent.setType("image/*");
////                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 2000);
//                                    getPanCardFromGallary.launch(intent);
//                                } else if (options[item].equals("PDF")) {
//                                    dialog.dismiss();
//                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                                    intent.setType("application/pdf");
//                                    getPanCardPdf.launch(intent);
//                                } else if (options[item].equals("Cancel")) {
//                                    dialog.dismiss();
//                                }
//                            }
//                        });
//                        builder.show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        binding.add5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (MainHelper.checkPermission(getActivity())) {
//                        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                        intent.setType("image/*");
//                        gettwomonthsalaryslipGallery.launch(intent);
                        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                        intent.setType("application/pdf");
//                                    gettwomonthsalaryslipPdf.launch(intent);
                        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 5001);
//                        final CharSequence[] options = {"Image From Gallery", "PDF", "Cancel"};
//                        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
//                        builder.setTitle("Select Option");
//                        builder.setItems(options, new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog, int item) {
//                                if (options[item].equals("Image From Gallery")) {
//                                    dialog.dismiss();
//                                    Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
//                                    intent.setType("image/*");
//                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 5000);
////                                    gettwomonthsalaryslipGallery.launch(intent);
//                                } else if (options[item].equals("PDF")) {
//                                    dialog.dismiss();
//                                    Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
//                                    intent.setType("application/pdf");
////                                    gettwomonthsalaryslipPdf.launch(intent);
//                                    startActivityForResult(Intent.createChooser(intent, "Select Picture"), 5001);
//                                } else if (options[item].equals("Cancel")) {
//                                    dialog.dismiss();
//                                }
//                            }
//                        });
//                        builder.show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        return root;
    }

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
//                                .into(binding.panCardImg);
//                        Toast.makeText(getContext(), compressedPanCard.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });
//
//    private ActivityResultLauncher gettwomonthsalaryslipGallery = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                try {
//                    if (result.getData() != null) {
//                        Uri selectedImageUri = result.getData().getData();
//                        compressedtwomonthsalary = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
//                        Glide.with(getActivity())
//                                .load(compressedtwomonthsalary.getAbsolutePath())
//                                .into(binding.lastSthreemonthSalaryslipImg);
//                        Toast.makeText(getContext(), compressedtwomonthsalary.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });
//
//    private ActivityResultLauncher getlasttwo_monthbank = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                try {
//                    if (result.getData() != null) {
//                        Uri selectedImageUri = result.getData().getData();
//                        compressedtwomonthbank = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
//                        Glide.with(getActivity())
//                                .load(compressedtwomonthbank.getAbsolutePath())
//                                .into(binding.lastSixmonthBankImg);
//                        Toast.makeText(getContext(), compressedtwomonthbank.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });

//    private ActivityResultLauncher gettwomonthsalaryslipPdf = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                try {
//                    if (result.getData() != null) {
//                        Uri selectedPdfUri = result.getData().getData();
//                        File myFile = new File(String.valueOf(selectedPdfUri));
//                        String path = myFile.getAbsolutePath();
//                        compressedtwomonthsalaryPdf = new File(myFile.getAbsolutePath());
//                        Glide.with(getActivity())
//                                .load(R.drawable.pdf)
//                                .into(binding.lastSthreemonthSalaryslipImg);
//                        Toast.makeText(getContext(), compressedtwomonthsalaryPdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();
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
//                                .into(binding.panCardImg);
//                        Toast.makeText(getContext(), compressedPanCardPdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });

//    private ActivityResultLauncher getlasttwo_monthbankPdf = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
//        @Override
//        public void onActivityResult(ActivityResult result) {
//            if (result.getResultCode() == Activity.RESULT_OK) {
//                try {
//                    if (result.getData() != null) {
//                        Uri selectedPdfUri = result.getData().getData();
//                        File myFile = new File(String.valueOf(selectedPdfUri));
//                        String path = myFile.getAbsolutePath();
//                        compressedtwomonthbankPdf = new File(myFile.getAbsolutePath());
//                        Glide.with(getActivity())
//                                .load(R.drawable.pdf)
//                                .into(binding.lastSixmonthBankImg);
//                        Toast.makeText(getContext(), compressedtwomonthbankPdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();
//
//                    }
//                } catch (Exception exception) {
//                    Log.d("TAG", "" + exception.getLocalizedMessage());
//                }
//            }
//        }
//    });


    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onAllListHandler(AllListHandler event) {
        value = event.message;
        type_list = event.list_type;
//        Toast.makeText(getActivity(), event.message+"Earning", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    private void submitFirstReport(View root) {

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
//                if (gst_status_radioButton == null) {
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            Toast.makeText(getContext(), "please fill all fields", Toast.LENGTH_SHORT).show();
//                            binding.progressbar.setVisibility(View.GONE);
//
//                        }
//                    });
//                }
//                else {
                Call<LoanCommonResponse> call = RetrofitClient.getInstance().getApi().getLoanCommonResponse(
                        new LoanCommonRequest(MainHelper.getUserDataList(sh).get(0).getUserId(),
                                MainHelper.getUserDataList(sh).get(0).getToken()
                                , MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                                , value,
                                binding.name.getText().toString().trim(),
                                binding.email.getText().toString().trim(),
                                binding.mobNumber.getText().toString().trim(),
                                aadhaarfront_compressed == null ? "null" : MainHelper.getFileToByte(aadhaarfront_compressed.getAbsolutePath()),
                                aadhaarback_compressed == null ? "null" : MainHelper.getFileToByte(aadhaarback_compressed.getAbsolutePath()),
                                compressedPanCard == null ? "null" : MainHelper.getFileToByte(compressedPanCard.getAbsolutePath()),
                                MainHelper.getStringPdf(compressedtwomonthbankuri,getContext()),
                                MainHelper.getStringPdf(compressedtwomonthsalaryuri,getContext())
                        ));

                call.enqueue(new Callback<LoanCommonResponse>() {
                    @Override
                    public void onResponse(Call<LoanCommonResponse> call, Response<LoanCommonResponse> response) {
                        LoanCommonResponse submitFlipKartFirstResponse = response.body();
                        if (response.isSuccessful()) {
                            try {
                                formDashBoardActivity.report_id = submitFlipKartFirstResponse.getReportId();
                                check = true;
                                Toast.makeText(getContext(), submitFlipKartFirstResponse.getMsg(), Toast.LENGTH_SHORT).show();
                                binding.progressbar.setVisibility(View.GONE);

                            } catch (Exception e) {
                                e.printStackTrace();
                                check = false;
                                binding.progressbar.setVisibility(View.GONE);
                                Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();

                            }

                        }

                        if (check == true) {

//                            formDashBoardActivity.binding.viewPager.setOnTouchListener(null);
//                            formDashBoardActivity.binding.viewPager.setCurrentItem(2, true);
//                            EventBus.getDefault().post(new MessageEvent(submitFlipKartFirstResponse.getReportId()));
                            try {
                                Intent intent = new Intent(getActivity(), JobSuccessActivity.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("key1", MainHelper.getUserDataList(sh).get(0).getToken());
                                intent.putExtras(bundle);
                                getActivity().startActivity(intent);
                                getActivity().finish();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }

                        }


                    }

                    @Override
                    public void onFailure(Call<LoanCommonResponse> call, Throwable t) {
                        try {
                            Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                            binding.progressbar.setVisibility(View.GONE);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                });
//                }

            }
        });

        thread.start();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            if (resultCode == RESULT_OK) {
                Uri selectedImageUri = data.getData();
                if (requestCode == 1000) {
                    if (null != selectedImageUri) {

                        aadhaarfront_compressed = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(aadhaarfront_compressed.getAbsolutePath())
                                .into(binding.aadhaarFrontImg);
                        Toast.makeText(getContext(), aadhaarfront_compressed.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 2000) {
                    if (null != selectedImageUri) {

                        aadhaarback_compressed = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(aadhaarback_compressed.getAbsolutePath())
                                .into(binding.aadhaarBackImg);
                        Toast.makeText(getContext(), aadhaarback_compressed.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 3001) {
                    if (null != selectedImageUri) {

                        compressedtwomonthbankuri = data.getData();
                        File myFile = new File(String.valueOf(selectedImageUri));
                        String path = myFile.getAbsolutePath();
                        compressedtwomonthbankPdf = new File(myFile.getAbsolutePath());
                        Glide.with(getActivity())
                                .load(R.drawable.pdf)
                                .into(binding.lastSixmonthBankImg);
                        Toast.makeText(getContext(), compressedtwomonthbankPdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 3000) {
                    if (null != selectedImageUri) {
//                        Uri selectedImageUri = result.getData().getData();
                        compressedtwomonthbank = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedtwomonthbank.getAbsolutePath())
                                .into(binding.lastSixmonthBankImg);
                        Toast.makeText(getContext(), compressedtwomonthbank.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 5000) {
                    if (null != selectedImageUri) {
//                        Uri selectedImageUri = result.getData().getData();
                        compressedtwomonthsalary = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedtwomonthsalary.getAbsolutePath())
                                .into(binding.lastSthreemonthSalaryslipImg);
                        Toast.makeText(getContext(), compressedtwomonthsalary.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 5001) {
                    if (null != selectedImageUri) {
                        compressedtwomonthsalaryuri = data.getData();
                        File myFile = new File(String.valueOf(selectedImageUri));
                        String path = myFile.getAbsolutePath();
                        compressedtwomonthsalaryPdf = new File(myFile.getAbsolutePath());
                        Glide.with(getActivity())
                                .load(R.drawable.pdf)
                                .into(binding.lastSthreemonthSalaryslipImg);
                        Toast.makeText(getContext(), compressedtwomonthsalaryPdf.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                } else if (requestCode == 4000) {
                    if (null != selectedImageUri) {
//                        Uri selectedImageUri = result.getData().getData();
                        compressedPanCard = new File(MainHelper.getRealPathFromURI(getActivity(), selectedImageUri));
                        Glide.with(getActivity())
                                .load(compressedPanCard.getAbsolutePath())
                                .into(binding.panCardImg);
                        Toast.makeText(getContext(), compressedPanCard.getAbsolutePath(), Toast.LENGTH_SHORT).show();

                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}