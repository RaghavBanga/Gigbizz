package com.gigbiz.fragments.teamreport;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.gigbiz.RetrofitClient;
import com.gigbiz.adapters.ProjectPayoutAdapter;
import com.gigbiz.databinding.FragmentTeamReportBinding;
import com.gigbiz.helper.MainHelper;
import com.gigbiz.models.PaymentModel;
import com.gigbiz.models.PayoutReportRequest;
import com.gigbiz.models.PayoutReportResponse;
import com.gigbiz.models.Project_Report;
import com.gigbiz.models.UserData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Teamreportfragment extends Fragment {


    private FragmentTeamReportBinding binding;
    List<PaymentModel> paymentlist=new ArrayList<>();
    ProjectPayoutAdapter adapter;
    SharedPreferences sh;List<UserData> userData;
    List<Project_Report> reports;
    public String userid;
    public String usertoken;
    public Teamreportfragment() {
        // Required empty public constructor
    }


    public  Teamreportfragment newInstance(String param1, String param2) {
        Teamreportfragment fragment = new Teamreportfragment();
        this.userid=param1;
        this.usertoken=param2;
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTeamReportBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        sh = getContext().getSharedPreferences("gigbiz", MODE_PRIVATE);
        userData = new ArrayList<>();
        reports = new ArrayList<>();
        userData = MainHelper.getUserDataList(sh);

        binding.pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    getFragmentManager().beginTransaction()
                            .remove(Teamreportfragment.this).commit();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        getPayoutReport();
        setRecyclerView();
        return root;
    }

    private void getPayoutReport() {
        Call<PayoutReportResponse> call = RetrofitClient.getInstance().getApi().getPayoutReportResponse(
                new PayoutReportRequest(userid,usertoken,MainHelper.getUserDataList(sh).get(0).getType().toLowerCase()
                ));
//        Call<PayoutReportResponse> call = RetrofitClient.getInstance().getApi().getPayoutReportResponse(
//                new PayoutReportRequest("MTk4OQ==","4f1143fd9fd520eb6b09a53adde00a39","employee"));

        call.enqueue(new Callback<PayoutReportResponse>() {
            @Override
            public void onResponse(Call<PayoutReportResponse> call, Response<PayoutReportResponse> response) {
                PayoutReportResponse payoutReportResponse = response.body();
                if (response.isSuccessful()) {
                    try {

                        reports=payoutReportResponse.getProject_report();
                        adapter.setNewList(reports);
                        if (reports.get(0).getMsg().equals("No Record Found")) {
                            binding.nodata.setVisibility(View.VISIBLE);
                            binding.recyclerview.setVisibility(View.GONE);
                        }
                        else{
                            binding.nodata.setVisibility(View.GONE);
                            binding.recyclerview.setVisibility(View.VISIBLE);
                        }
                        Toast.makeText(getContext(), payoutReportResponse.getProject_report().get(0).getMsg(), Toast.LENGTH_SHORT).show();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("test1", e.getMessage());
                        Toast.makeText(getContext(), "System Fail", Toast.LENGTH_SHORT).show();
                    }

                }


            }

            @Override
            public void onFailure(Call<PayoutReportResponse> call, Throwable t) {
                try {
                    Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });



    }

    private void setRecyclerView() {
        binding.recyclerview.setHasFixedSize(true);
        binding.recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL);
        adapter =new ProjectPayoutAdapter(getContext(),reports);
        binding.recyclerview.addItemDecoration(dividerItemDecoration);
        binding.recyclerview.setAdapter(adapter);


    }
//    private List<PaymentModel> getList(){
//        List<PaymentModel> payment_list=new ArrayList<>();
//
//        payment_list.add(new PaymentModel("1","Raghav","10000","kgchg "," sffg ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("2","Avinash","9000 ","dfef "," efefe ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("3","Saarthak","20000","fwef "," wefgw ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("5","Rishu","70000","wefw"," wfwf ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("6","Vaibhav","80000","ewefw"," efwf ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("7","Namita","60000","ewfwfr "," wefw ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("8","Nikita","50000","efwef"," wfewr ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("1","Raghav","10000","kgchg "," sffg ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("2","Avinash","9000 ","dfef "," efefe ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("3","Saarthak","20000","fwef "," wefgw ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("5","Rishu","70000","wefw"," wfwf ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("6","Vaibhav","80000","ewefw"," efwf ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("7","Namita","60000","ewfwfr "," wefw ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("8","Nikita","50000","efwef"," wfewr ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("1","Raghav","10000","kgchg "," sffg ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("2","Avinash","9000 ","dfef "," efefe ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("3","Saarthak","20000","fwef "," wefgw ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("5","Rishu","70000","wefw"," wfwf ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("6","Vaibhav","80000","ewefw"," efwf ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("7","Namita","60000","ewfwfr "," wefw ","kgchg "," sffg "));
//        payment_list.add(new PaymentModel("8","Nikita","50000","efwef"," wfewr ","kgchg "," sffg "));
//
//        return payment_list;
//    }
}
