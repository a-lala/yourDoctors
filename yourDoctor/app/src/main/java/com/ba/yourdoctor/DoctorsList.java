package com.ba.yourdoctor;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

/**
 * Created by a7med on 28/06/2015.
 */
public class DoctorsList extends LinearLayout
{
	// for logging
	private static final String LOGTAG = "Doctors List View";


	// internal components
	private LinearLayout list;



	public DoctorsList(Context context)
	{
		super(context);
	}

	public DoctorsList(Context context, AttributeSet attrs)
	{
		super(context, attrs);
		initControl(context, attrs);
	}

	public DoctorsList(Context context, AttributeSet attrs, int defStyleAttr)
	{
		super(context, attrs, defStyleAttr);
		initControl(context, attrs);
	}

	/**
	 * Load control xml layout
	 */
	private void initControl(Context context, AttributeSet attrs)
	{
		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		inflater.inflate(R.layout.doctor_list, this);

		loadDateFormat(attrs);
		assignUiElements();
		assignClickHandlers();
	}

	private void loadDateFormat(AttributeSet attrs)
	{
//		TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.DoctorList);

		try
		{
			// try to load provided date format, and fallback to default otherwise
//			dateFormat = ta.getString(R.styleable.DoctorList_exampleString);
//			if (dateFormat == null)
//				dateFormat = DATE_FORMAT;
		}
		finally
		{
//			ta.recycle();
		}
	}
	private void assignUiElements()
	{
		// layout is inflated, assign local variables to components
		list = (LinearLayout)findViewById(R.id.list);
	}

	private void assignClickHandlers()
	{
//		// add one month and refresh UI
//		btnNext.setOnClickListener(new OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//				currentDate.add(Calendar.MONTH, 1);
//				updateCalendar();
//			}
//		});
//
//		// subtract one month and refresh UI
//		btnPrev.setOnClickListener(new OnClickListener()
//		{
//			@Override
//			public void onClick(View v)
//			{
//				currentDate.add(Calendar.MONTH, -1);
//				updateCalendar();
//			}
//		});
//
//		// long-pressing a day
//		grid.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
//		{
//
//			@Override
//			public boolean onItemLongClick(AdapterView<?> view, View cell, int position, long id)
//			{
//				// handle long-press
//				if (eventHandler == null)
//					return false;
//
//				eventHandler.onDayLongPress((Date)view.getItemAtPosition(position));
//				return true;
//			}
//		});
	}

}
