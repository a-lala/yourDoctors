package com.ba.yourdoctor;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * TODO: document your custom view class.
 */
public class DoctorsListItem extends LinearLayout {

//    private int mExampleColor = Color.RED; // TODO: use a default from R.color...
//    private float mExampleDimension = 0; // TODO: use a default from R.dimen...
//    private Drawable mExampleDrawable;

    //    private TextPaint mTextPaint;
//    private float mTextWidth;
//    private float mTextHeight;
    private String firstName; //
    private String lastName;
    private String dr_sp;

    public FragmentManager manager;


    private TextView tv_dr_name;
    private TextView tv_dr_sp;

    TextView get_Dr_sp(){
        return tv_dr_sp;
    }

    TextView get_Dr_name(){
        return tv_dr_name;
    }

    public DoctorsListItem(Context context) {
        super(context);
    }

    public DoctorsListItem(Context context, AttributeSet attrs) {
        super(context, attrs);
        initControl(context, attrs);
    }

    public DoctorsListItem(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initControl(context, attrs);
    }

    private void initControl(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.doctors_list_item, this);
        manager = ((Activity)context).getFragmentManager();
        loadDateFormat(attrs);
        assignUiElements();
        assignClickHandlers();
    }

    private void loadDateFormat(AttributeSet attrs) {
        TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.DoctorsListItem);

        try {
            // try to load provided date format, and fallback to default otherwise
            firstName = ta.getString(R.styleable.DoctorsListItem_dr_fname);
            if (firstName == null)
                firstName = "محمد";

            lastName = ta.getString(R.styleable.DoctorsListItem_dr_lname);
            if (lastName == null)
                lastName = "محمودي";

            dr_sp = ta.getString(R.styleable.DoctorsListItem_dr_sp);
            if (dr_sp == null)
                dr_sp = "عام";

        } finally {
            ta.recycle();
        }
    }

    private void assignUiElements() {
        // layout is inflated, assign local variables to components
        tv_dr_name = (TextView) findViewById(R.id.tv_dr_name);
        tv_dr_name.setText(firstName + " "+lastName);
        tv_dr_sp = (TextView) findViewById(R.id.tv_dr_sp);
        tv_dr_sp.setText(dr_sp);
    }

    private void assignClickHandlers() {
        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                DoctorDetailsFragment frag = new DoctorDetailsFragment();

                FragmentTransaction transaction = manager.beginTransaction();
//                transaction.remove(manager.findFragmentById(R.id.fragment_container));
                transaction.replace(R.id.fragment_container,  frag);
//                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
//        advanced.setOnClickListener
//            var frag: Fragment = AdvanceSettingsFragment()
//            fragmentManager?.beginTransaction()
//                    ?.replace(R.id.fragment_container, frag)
//                    ?.addToBackStack(null)
//                    ?.commit()
//        }

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
