package com.gigbiz.helper;

import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.core.app.ActivityCompat;

import com.gigbiz.Utilities;
import com.gigbiz.models.Approved;
import com.gigbiz.models.Project;
import com.gigbiz.models.Rejected;
import com.gigbiz.models.SignUpResponse;
import com.gigbiz.models.Submitted;
import com.gigbiz.models.UserData;
import com.gigbiz.models.UserDatum;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class MainHelper {

    public static List<UserData> getUserDataList(SharedPreferences sh) {
        List<UserData> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.LOGIN_DATA_OF_USER, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<UserData>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }

    public static List<UserDatum> getUserDataSignupList(SharedPreferences sh) {
        List<UserDatum> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.LOGIN_DATA_OF_USER, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<UserDatum>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }

    public static SignUpResponse getSignUpDataList(SharedPreferences sh) {

        String serializedObject = sh.getString(Utilities.SIGNUP_DATA_OF_USER, null);
        SignUpResponse signUpResponse = new SignUpResponse();
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<SignUpResponse>() {
            }.getType();
            signUpResponse = gson.fromJson(serializedObject, type);
        }

        return signUpResponse;
    }

    public static List<Project> getUserTaskDetails(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }
    public static List<Project> getUserTaskDetailsCREDIT_CARD(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL_CREDIT_CARD, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }
    public static List<Project> getUserTaskDetailsPERSONAL_LOANS(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL_PERSONAL_LOANS, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }
    public static List<Project> getUserTaskDetailsHOME_LOANS(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL_HOME_LOANS, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }
    public static List<Project> getUserTaskDetailsBUSINESS_LOANS(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL_BUSINESS_LOANS, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }
    public static List<Project> getUserTaskDetailsCAR_LOANS(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL_CAR_LOANS, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }
    public static List<Project> getUserTaskDetailsHEALTH(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL_HEALTH, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }
    public static List<Project> getUserTaskDetailsCAR(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL_CAR, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }
    public static List<Project> getUserTaskDetailsLIFE(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL_LIFE, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }
    public static List<Project> getUserTaskDetailsSAVING(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL_SAVING, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }
    public static List<Project> getUserTaskDetailsDEMAT(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL_DEMAT, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }
    public static List<Project> getUserTaskDetailsMORE(SharedPreferences sh) {
        List<Project> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.USER_TASK_DETAIL_MORE, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Project>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }

    public static List<Submitted> getReportListSubmittedDetails(SharedPreferences sh) {
        List<Submitted> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.SUBMITTED_LIST_DETAIL, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Submitted>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }

    public static List<Rejected> getReportListRejectedDetails(SharedPreferences sh) {
        List<Rejected> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.REJECTED_LIST_DETAIL, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Rejected>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }

    public static List<Approved> getReportListApprovedDetails(SharedPreferences sh) {
        List<Approved> arrayItems = new ArrayList<>();
        String serializedObject = sh.getString(Utilities.APPROVED_LIST_DETAIL, null);
        if (serializedObject != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<List<Approved>>() {
            }.getType();
            arrayItems = gson.fromJson(serializedObject, type);
        }

        return arrayItems;
    }

    public static boolean checkPermission(Activity context) {
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        }
        else {
            ActivityCompat.requestPermissions(context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 111);
            return false;
        }
//        if (SDK_INT >= Build.VERSION_CODES.R) {
//            return Environment.isExternalStorageManager();
//        } else {
//            int result = ContextCompat.checkSelfPermission(context, READ_EXTERNAL_STORAGE);
//            int result1 = ContextCompat.checkSelfPermission(context, WRITE_EXTERNAL_STORAGE);
//            return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED;
//        }
    }

    public static void requestPermission(Activity context) {

        //todo just changed some code here
        try {
//            ActivityCompat.requestPermissions(context, new String[]{MANAGE_EXTERNAL_STORAGE}, 111);
//            Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
//            Uri uri = Uri.fromParts("package", context.getPackageName(), null);
//            intent.setData(uri);
//            context.startActivity(intent);
            ActivityCompat.requestPermissions(context, new String[]{WRITE_EXTERNAL_STORAGE}, 111);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        if (SDK_INT >= Build.VERSION_CODES.R) {
//
//        } else {
//            //below android 11
//            ActivityCompat.requestPermissions(context, new String[]{WRITE_EXTERNAL_STORAGE}, 111);
//        }
    }

    public static String getRealPathFromURI (Context context , Uri contentUri) {
        String path = null;
        String[] proj = { MediaStore.MediaColumns.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if (cursor.moveToFirst()) {
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
            path = cursor.getString(column_index);
        }
        cursor.close();
        return path;
    }

    public static String getFileToByte(String filePath){
        Bitmap bmp = null;
        ByteArrayOutputStream bos = null;
        byte[] bt = null;
        String encodeString = null;
        try{
            bmp = BitmapFactory.decodeFile(filePath);
            bos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, bos);
            bt = bos.toByteArray();
            encodeString = Base64.encodeToString(bt, Base64.DEFAULT);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return encodeString;
    }

//    public static boolean isValidMail(String email) {
//
//        String EMAIL_STRING = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
//                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
//
//        return Pattern.compile(EMAIL_STRING).matcher(email).matches();
//
//    }

//    public static boolean isValidMail(String email) {
//        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
//    }

//    public static boolean isValidMobile(String phone) {
//        if(!Pattern.matches("[a-zA-Z]+", phone)) {
//            return phone.length() > 6 && phone.length() <= 13;
//        }
//        return false;
//    }

//    public static boolean isValidMobile(String phone) {
//        return android.util.Patterns.PHONE.matcher(phone).matches();
//    }

    public static boolean isValidEmail(CharSequence email) {
        if (!TextUtils.isEmpty(email)) {
            return Pattern.compile("^(.+)@(.+)$").matcher(email).matches();
        }
        return false;

    }

    public static boolean isValidPhoneNumber(CharSequence phoneNumber) {
        if (!TextUtils.isEmpty(phoneNumber)) {
            return Pattern.compile("^\\d{10}$").matcher(phoneNumber).matches();
        }
        return false;
    }

    public static int getWidth(Context context) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        WindowManager windowmanager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowmanager.getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static String encodeFileToBase64Binary(File yourFile) {
//        String encodeFileToBase64Binary = encodeFileToBase64Binary(yourFile);

        int size = (int) yourFile.length();
        byte[] bytes = new byte[size];
        try {
            BufferedInputStream buf = new BufferedInputStream(new FileInputStream(yourFile));
            buf.read(bytes, 0, bytes.length);
            buf.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String encoded = Base64.encodeToString(bytes,Base64.NO_WRAP);
        return encoded;
    }

    public static  String getStringPdf (Uri filepath,Context context){
        InputStream inputStream = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            inputStream =  context.getContentResolver().openInputStream(filepath);

            byte[] buffer = new byte[1024];
            byteArrayOutputStream = new ByteArrayOutputStream();

            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        byte[] pdfByteArray = byteArrayOutputStream.toByteArray();

        return Base64.encodeToString(pdfByteArray, Base64.DEFAULT);
    }

//    public static String convertPdfToBase64(String filePath) {
//        String encodedString = null;
//        try (FileInputStream fileInputStream = new FileInputStream(filePath);
//             ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
//            byte[] buffer = new byte[1024];
//            int bytesRead;
//            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
//                byteArrayOutputStream.write(buffer, 0, bytesRead);
//            }
//            encodedString = Base64.encodeBase64String(byteArrayOutputStream.toByteArray());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return encodedString;
//    }
//
    public static long getmsTime(String starttime){
//        String time = "12:30";
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date date = null;
        try {
            date = sdf.parse(starttime);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return date.getTime();
    }

}
