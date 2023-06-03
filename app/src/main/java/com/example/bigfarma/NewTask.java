package com.example.bigfarma;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;
import android.Manifest;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.text.DateFormat;

import android.widget.ImageView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class NewTask extends AppCompatActivity {
        EditText Title;
        EditText Desc;
        EditText StartDate;
        EditText EndDate;
        EditText Time;

        ImageButton camera;

        ImageView image;



    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private String currentPhotoPath;


    private SimpleDateFormat dateFormat;

    private static final String TAG = "NewTask";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);
        innit();


    }

    public void innit(){
        Title = findViewById(R.id.titeInput);
        Desc= findViewById(R.id.DecriptionInput);
        StartDate= findViewById(R.id.StartDateEditTxt);
        EndDate= findViewById(R.id.endDateEditTxt);
        Time= findViewById(R.id.TimeInput);
        image = findViewById(R.id.capturedImage);
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.US);
        camera = findViewById(R.id.cameraPrompt);
        Title.getText().toString();


        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File FilePhoto = null;
                try{

                    FilePhoto = createImageFile();
                    Log.d(TAG, "onClick: "+FilePhoto);
                }
                catch(IOException ei){

                }

                startActivityForResult(intent, 0);
            }
        });

    }



    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);
            Bitmap bm = (Bitmap) data.getExtras().get("data");
            image.setImageBitmap(bm);
        } catch (Exception ex) {
            Toast.makeText(this, "Pic not saved", Toast.LENGTH_SHORT).show();
        }
    }




    public void showTimePickerDialog(View view) {
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                        calendar.set(Calendar.MINUTE, selectedMinute);

                        // Format the selected time as desired
                        DateFormat timeFormat = DateFormat.getTimeInstance(DateFormat.SHORT);
                        String selectedTime = timeFormat.format(calendar.getTime());

                        Time.setText(selectedTime);
                    }
                }, hour, minute, true);

        timePickerDialog.show();
    }

    public void showStartDatePickerDialog(View view) {

        // Get current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(NewTask.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Set selected date to the EditText field
                        calendar.set(year, monthOfYear, dayOfMonth);
                        StartDate.setText(dateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);

        // Show DatePickerDialog
        datePickerDialog.show();
    }
    public void showEndDatePickerDialog(View view) {

        // Get current date
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(NewTask.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // Set selected date to the EditText field
                        calendar.set(year, monthOfYear, dayOfMonth);
                        EndDate.setText(dateFormat.format(calendar.getTime()));
                    }
                }, year, month, day);

        // Show DatePickerDialog
        datePickerDialog.show();
    }

    public void AddNewTask(View view){


        Utility util = new Utility();
        int id =  util.getTaskId();
//        ArrayList<Task> This = new ArrayList<>();
//        This.add(new Task(id, Title.getText().toString(), Desc.getText().toString(),Desc.getText().toString(),EndDate.getText().toString(),StartDate.getText().toString(),Time.getText().toString()));
        util.setNewTask(id, Title.getText().toString(), currentPhotoPath,Desc.getText().toString(),EndDate.getText().toString(),StartDate.getText().toString(),Time.getText().toString());

        for (Task task : util.getNewTask()) {
            System.out.println("Task ID: " + task.getId());
            System.out.println("Title: " + task.getTitle());
            System.out.println("Title: " + task.getImageUrl());
            System.out.println("Description: " + task.getDesc());
            System.out.println("End Date: " + task.getEndDate());
            System.out.println("Start Date: " + task.getStartDate());
            System.out.println("Time: " + task.getTime());
            System.out.println("-------------------------");
        }

             Intent ToMain = new Intent(NewTask.this, MainView.class);
             startActivity(ToMain);

    }
}