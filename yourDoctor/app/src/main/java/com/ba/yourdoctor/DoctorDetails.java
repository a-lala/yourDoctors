package com.ba.yourdoctor;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.app.DialogFragment;
import android.os.Environment;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

import static android.support.constraint.Constraints.TAG;

/**
 * TODO: document your custom view class.
 */
public class DoctorDetails extends LinearLayout {
    private String mExampleString; // TODO: use a default from R.string...
    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
    private Drawable mExampleDrawable;

    private TextPaint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    private Button btn_submit;
    private Button btn_change;
    private TextView tv_date;
    private TextView tv_time;

    public DoctorDetails(Context context) {
        super(context);
    }// todo

    public DoctorDetails(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public DoctorDetails(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initControl(context, attrs);
    }

    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.doctor_details, this);

        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();
    }

    private void loadDateFormat(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.DoctorDetails);

        try {
            // try to load provided date format, and fallback to default otherwise
            mExampleString = ta.getString(R.styleable.DoctorsListItem_dr_fname);
            if (mExampleString == null)
                mExampleString = "محمد";

        } finally {
            ta.recycle();
        }
    }

    private void assignUiElements() {
        // layout is inflated, assign local variables to components
//        tv_dr_name = (TextView) findViewById(R.id.tv_dr_name);
//        tv_dr_name.setText(firstName + " "+lastName);
//        tv_dr_sp = (TextView) findViewById(R.id.tv_dr_sp);
        tv_date = (TextView) findViewById(R.id.tv_date);
        tv_time = (TextView) findViewById(R.id.tv_time);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_change = (Button) findViewById(R.id.btn_change);

        HashSet<Date> events = new HashSet<>();


        CalendarView cv = ((CalendarView) findViewById(R.id.calendar_view));
        cv.updateCalendar(events);

        // assign event handler
        cv.setEventHandler(new CalendarView.EventHandler() {
            @Override
            public void onDayLongPress(Date date) {
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(((Activity) getContext()).getFragmentManager(), "timePicker");
                // show returned day

                DateFormat df = SimpleDateFormat.getDateInstance();
                tv_date.setText(DateFormat.getDateInstance(DateFormat.SHORT).format(date));
                Toast.makeText(getContext(), df.format(date), Toast.LENGTH_SHORT).show();
            }
        });

        btn_submit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                writeToFile(getContext(),
                        "dates.txt",
                        tv_date.getText().toString() + "\n" + tv_time.getText().toString()
                );
            }
        });

        btn_change.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                tv_time.setText("");
                DialogFragment newFragment = new TimePickerFragment();
                newFragment.show(((Activity) getContext()).getFragmentManager(), "timePicker");
            }
        });
    }

    private void writeToFile(Context context, String fileName, String data) {


//        Log.e("Root path",Environment.getDataDirectory().toString());
//        File directory = new File(Environment.getRootDirectory()+"/myfiles");
//        directory.mkdirs();
//        File myfile = new File(Environment.getRootDirectory()+"/myfiles/"+fileName);
//        try {
//            myfile.createNewFile();
//        }catch (Exception e){
//
//        }
        String saveFolder ="/storage/emulated/0";
        File directory = new File(saveFolder + "/myfiles");
        if(directory.mkdirs()){
            Log.e(TAG, "writeToFile:create folder" );;
        }
        String path_file = saveFolder+ "/myfiles/" + fileName;
        File myfile = new File(path_file);

        try {
            myfile.createNewFile();
            Log.e("File", "created");
            Writer out = new FileWriter(path_file);
            BufferedWriter br = new BufferedWriter(out);
            br.write(data + "\n");
            br.close();

        } catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    private String readFromFile(Context context, String fileName) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    private void assignClickHandlers() {
        // add one month and refresh UI
//        btnNext.setOnClickListener(new OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                currentDate.add(Calendar.MONTH, 1);
//                updateCalendar();
//            }
//        });
//
//        // subtract one month and refresh UI
//        btnPrev.setOnClickListener(new OnClickListener()
//        {
//            @Override
//            public void onClick(View v)
//            {
//                currentDate.add(Calendar.MONTH, -1);
//                updateCalendar();
//            }
//        });
//
//        // long-pressing a day
//        grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
//        {
//
//            @Override
//            public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id)
//            {
//                // handle long-press
//                if (eventHandler == null)
//                    return false;
//
//                eventHandler.onDayLongPress((Date)view.getItemAtPosition(position));
//                return true;
//            }
//        });
    }

}
