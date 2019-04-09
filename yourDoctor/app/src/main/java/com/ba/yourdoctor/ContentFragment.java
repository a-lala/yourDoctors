package com.ba.yourdoctor;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.*;

public class ContentFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    LinearLayout list;
    Spinner spinner2, spinner1;
    EditText et_name;
    List<DoctorsListItem> doctorsListItems;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list = (LinearLayout) view.findViewById(R.id.list_drs);
        doctorsListItems = new ArrayList<DoctorsListItem>();
        spinner2 = (Spinner) view.findViewById(R.id.sp_title);

        et_name = (EditText) view.findViewById(R.id.et_name);
        et_name.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
                if(s.length() != 0){
                    View vi = LayoutInflater.from(getContext()).inflate(R.layout.fragment_content, null);
                    LinearLayout list2 = (LinearLayout) vi.findViewById(R.id.list_drs);
                    int cnt = list2.getChildCount();

                    DoctorsListItem v;

                    list.removeAllViews();
                    for (int jj = cnt - 1; jj > -1; jj--) {
                        //doctorsListItems.get(j);
                        v = (DoctorsListItem) list2.getChildAt(jj);
                        if (v.get_Dr_name().getText().toString().contains(s)) {
                            ((LinearLayout) v.getParent()).removeView(v);
                            list.addView(v);
                        }
                        //list.addView((doctorsListItems.get(j)));
                    }
                }

            }
        });
        spinner1 = (Spinner) view.findViewById(R.id.seach_tag);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.search_tag, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner


        spinner1.setAdapter(adapter);
        spinner1.setOnItemSelectedListener(this);

        int count = list.getChildCount();
        DoctorsListItem v = null;
        ArrayList<String> ll = new ArrayList<String>();
        for (int i = 0; i < count; i++) {
            v = (DoctorsListItem) list.getChildAt(i);
            String t = v.get_Dr_sp().getText().toString();
            ll.add(t);
            doctorsListItems.add(v);
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_spinner_item,
                new ListHelper<>(new String[0]).unique(ll)
        );

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

// (4) set the adapter on the spinner
        spinner2.setAdapter(adapter2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String key = ((TextView) view).getText().toString();

                View vi = LayoutInflater.from(getContext()).inflate(R.layout.fragment_content, null);
                LinearLayout list2 = (LinearLayout) vi.findViewById(R.id.list_drs);
                int count = list2.getChildCount();
//                DoctorsListItem vv = null;
//                ArrayList<String> lll = new ArrayList<String>();
//                for (int ii = 0; ii < count; ii++) {
//                    vv = (DoctorsListItem) list.getChildAt(i);
//                    String t = vv.get_Dr_sp().getText().toString();
//                    lll.add(t);
//                    doctorsListItems.add(vv);
//                }
//
//
//

                //String key2 = spinner2.getSelectedItem().toString();
                DoctorsListItem v;
                //ArrayList ll = (ArrayList) doctorsListItems;
                list.removeAllViews();
                for (int j = count - 1; j > -1; j--) {
                    //doctorsListItems.get(j);
                    v = (DoctorsListItem) list2.getChildAt(j);
                    if (v.get_Dr_sp().getText().equals(key)) {
                        ((LinearLayout) v.getParent()).removeView(v);
                        list.addView(v);
                    }
                    //list.addView((doctorsListItems.get(j)));
                }

//                for (int j = 0; j < count; j++) {
//                    v = (DoctorsListItem) list.getChildAt(i);
//                    if (!v.get_Dr_sp().getText().equals(key))
//                        ((LinearLayout)v.getParent()).removeView(v);
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    public class ListHelper<T> {
        private final T[] t;

        public ListHelper(T[] t) {
            this.t = t;
        }

        public List<T> unique(List<T> list) {
            Set<T> set = new HashSet<>(list);
            return Arrays.asList(set.toArray(t));
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int j, long l) {
        Toast.makeText(getContext(), "gggggg", Toast.LENGTH_SHORT).show();
        switch (j) {
            case 0:
                spinner2.setVisibility(View.VISIBLE);
                et_name.setVisibility(View.INVISIBLE);
                /// ToDo
                //// visible only doctors that has the same major

                break;
            case 1:
                /// ToDo
                //// invisible All doctors with
                spinner2.setVisibility(View.INVISIBLE);
                et_name.setVisibility(View.VISIBLE);
                View vi = LayoutInflater.from(getContext()).inflate(R.layout.fragment_content, null);
                LinearLayout list2 = (LinearLayout) vi.findViewById(R.id.list_drs);
                int count = list2.getChildCount();

                DoctorsListItem v;

                list.removeAllViews();
                for (int jj = count - 1; jj > -1; jj--) {
                    //doctorsListItems.get(j);
                    v = (DoctorsListItem) list2.getChildAt(jj);
                    ((LinearLayout) v.getParent()).removeView(v);
                    list.addView(v);

                    //list.addView((doctorsListItems.get(j)));
                }
                break;
            default:
                break;
        }
//        spinner1.setOnItemSelectedListener(this);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


/*******************



 // set the spinner data programmatically, from a string array or list

 // (1) get a reference to the spinner
 Spinner spinner1 = (Spinner)qbTableLayout.findViewById(R.id.spinner1);

 // (2) create a simple static list of strings
 List<Integer> spinnerArray = new ArrayList<>();
 spinnerArray.add(10);
 spinnerArray.add(11);

 // (3) create an adapter from the list
 ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(
 getActivity(),
 android.R.layout.simple_spinner_item,
 spinnerArray
 );

 //adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

 // (4) set the adapter on the spinner
 spinner1.setAdapter(adapter);

 *///////////////////////

