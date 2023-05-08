package com.gigbiz.fragments.offerletter;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.gigbiz.databinding.FragmentOfferLetterBinding;

import org.imaginativeworld.oopsnointernet.callbacks.ConnectionCallback;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.DialogPropertiesPendulum;
import org.imaginativeworld.oopsnointernet.dialogs.pendulum.NoInternetDialogPendulum;


public class OfferLetterFragment extends Fragment {

    private FragmentOfferLetterBinding binding;
    public String offerletter;
    public int position;
    ProgressDialog dialog;



    public OfferLetterFragment() {

    }


    public OfferLetterFragment newInstance(String offerletter, int position) {
        this.offerletter=offerletter;
        this.position=position;
        return new OfferLetterFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentOfferLetterBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        dialog = ProgressDialog.show(getActivity(), "",
                "Loading Please wait...", true);

        checkInternetConnection();

        return root;
    }

//    private boolean isNetworkConnected() {
//        ConnectivityManager cm = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo ni = cm.getActiveNetworkInfo();
//        if (ni == null) {
//            // There are no active networks.
//            alertDialog.show();
//            return false;
//        } else{
//            WebSettings webSettings = binding.webView.getSettings();
//
//            binding.webView.setInitialScale(200);
//            binding.webView.getSettings().setSupportZoom(true);
//            binding.webView.getSettings().setLoadWithOverviewMode(true);
//            binding.webView.getSettings().setBuiltInZoomControls(true);
//
//            webSettings.setJavaScriptEnabled(true);
//            binding.webView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSdQhHKxFaiCKoUriZoUn7ajmF0UKTEn04qycFNIEQ3J6klt-g/viewform?fbzx=1151289496204921461");
//            binding.webView.setWebViewClient(new WebViewClient());
//
//        }
//        return true;
//    }

    private void checkInternetConnection() {
        NoInternetDialogPendulum.Builder builder = new NoInternetDialogPendulum.Builder(
                getActivity(),
                getLifecycle()
        );

        DialogPropertiesPendulum properties = builder.getDialogProperties();

        properties.setConnectionCallback(new ConnectionCallback() { // Optional
            @Override
            public void hasActiveConnection(boolean hasActiveConnection) {

                if (hasActiveConnection){
                    if (getActivity()!=null){
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
                                binding.webView.loadUrl(offerletter);
                                binding.webView.setWebViewClient(new WebViewClient(){

                                    public void onPageFinished(WebView view, String url){
                                        dialog.dismiss();
                                    }
                                });
                            }
                        });
                    }
                }else {
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

}