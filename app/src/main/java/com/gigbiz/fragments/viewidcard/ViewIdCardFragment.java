package com.gigbiz.fragments.viewidcard;

import static android.app.Activity.RESULT_OK;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintJob;
import android.print.PrintManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.gigbiz.R;
import com.gigbiz.databinding.FragmentViewIdCardBinding;
import com.gigbiz.models.IcardUrl;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;

import java.util.List;


public class ViewIdCardFragment extends Fragment {

    private FragmentViewIdCardBinding binding;
    List<IcardUrl> icardUrls;
    public int position;
    ProgressDialog dialog;
    PrintJob mPrintJob;
    private static final int PRINT_JOB_REQUEST_CODE = 1000;


    public ViewIdCardFragment() {
        // Required empty public constructor
    }


    public ViewIdCardFragment newInstance(List<IcardUrl> param1, int param2) {
        ViewIdCardFragment fragment = new ViewIdCardFragment();
        this.icardUrls = param1;
        this.position = param2;
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
        binding = FragmentViewIdCardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dialog = ProgressDialog.show(getActivity(), "",
                "Loading Please wait...", true);

        checkInternetConnection();

        return root;

    }

    private void checkInternetConnection() {
        NoInternetDialogPendulum.Builder builder = new NoInternetDialogPendulum.Builder(
                getActivity(),
                getLifecycle()
        );

        DialogPropertiesPendulum properties = builder.getDialogProperties();

        properties.setConnectionCallback(new ConnectionCallback() { // Optional
            @Override
            public void hasActiveConnection(boolean hasActiveConnection) {

                if (hasActiveConnection) {
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                WebSettings webSettings = binding.webView.getSettings();

                                binding.webView.setInitialScale(200);
                                binding.webView.getSettings().setSupportZoom(true);
                                binding.webView.getSettings().setLoadWithOverviewMode(true);
                                binding.webView.getSettings().setBuiltInZoomControls(true);
                                binding.webView.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
//                                binding.webView.getSettings().setAppCacheEnabled(true);
                                webSettings.setJavaScriptEnabled(true);
                                binding.webView.loadUrl(icardUrls.get(position).getIcardUrl());
//                                binding.webView.addJavascriptInterface(new WebViewJavaScriptInterface(getContext()), "GigBiz App"); //

                                binding.webView.setWebViewClient(new WebViewClient() {

                                    public void onPageFinished(WebView view, String url) {
                                        dialog.dismiss();
                                        binding.printBtn.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view1) {
                                                createWebPrintJob(view);
                                            }
                                        });
                                    }
                                });
                            }
                        });
                    }
                } else {
                    binding.webView.reload();
                }

            }
        });

        properties.setCancelable(false); // Optional
        properties.setNoInternetConnectionTitle("No Internet"); // Optional
        properties.setNoInternetConnectionMessage("Check your Internet connection and try again"); // Optional
        properties.setShowInternetOnButtons(true); // Optional
        properties.setPleaseTurnOnText("Please turn on"); // Optional
        properties.setWifiOnButtonText("Wifi"); // Optional
        properties.setMobileDataOnButtonText("Mobile data"); // Optional

        properties.setOnAirplaneModeTitle("No Internet"); // Optional
        properties.setOnAirplaneModeMessage("You have turned on the airplane mode."); // Optional
        properties.setPleaseTurnOffText("Please turn off"); // Optional
        properties.setAirplaneModeOffButtonText("Airplane mode"); // Optional
        properties.setShowAirplaneModeOffButtons(true); // Optional

        builder.build();
    }

    private void createWebPrintJob(WebView webView) {
        PrintManager printManager = (PrintManager) getContext().getSystemService(Context.PRINT_SERVICE);

        // Get a print adapter instance
        PrintDocumentAdapter printAdapter = webView.createPrintDocumentAdapter();

        // Create a print job with name and adapter instance
        String jobName = getString(R.string.app_name) + " Print Test";
        PrintJob printJob = printManager.print(jobName, printAdapter,
                new PrintAttributes.Builder().build());

        mPrintJob = printJob;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == PRINT_JOB_REQUEST_CODE) {
                Toast.makeText(getContext(), "Print successful", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(getContext(), "Print failed", Toast.LENGTH_LONG).show();
            }
            mPrintJob = null;
        }
    }

}