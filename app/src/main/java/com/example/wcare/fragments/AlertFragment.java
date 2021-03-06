package com.example.wcare.fragments;


import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.wcare.R;
import com.google.android.material.datepicker.MaterialDatePicker;
import com.google.android.material.datepicker.MaterialPickerOnPositiveButtonClickListener;
import com.google.android.material.radiobutton.MaterialRadioButton;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;


public class AlertFragment extends Fragment {


    Button datePickerButton;
    TextView pickedDate,cycleperiod,ovulation,cycle;
    Spinner datecycle;
    String cycleCounter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate( R.layout.fragment_alert, container, false );

        cycleperiod = v.findViewById( R.id.cycleTextView );
        cycle = v.findViewById( R.id.cycleTextViewId );
        ovulation = v.findViewById( R.id.ovulationTextView);
        datecycle = v.findViewById( R.id.datespinner );
        ArrayAdapter<CharSequence> spinarrayadapter = ArrayAdapter.createFromResource( getContext(),R.array.dateofcycle,android.R.layout.simple_spinner_item );
        spinarrayadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item );
        datecycle.setAdapter( spinarrayadapter );
        datecycle.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                cycleCounter = datecycle.getSelectedItem().toString();
                cycleperiod.setText( cycleCounter );

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        } );


        datePickerButton = v.findViewById( R.id.datePickerbutton );
        MaterialDatePicker.Builder builder = MaterialDatePicker.Builder.datePicker();
        builder.setTitleText( "Select a Date" );
        final MaterialDatePicker materialDatePicker = builder.build();

        datePickerButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                materialDatePicker.show( getFragmentManager(),"DATE_PICKER" );

            }
        } );



        pickedDate = v.findViewById( R.id.pickedDateTextView );

        materialDatePicker.addOnPositiveButtonClickListener( new MaterialPickerOnPositiveButtonClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onPositiveButtonClick(Object selection) {

                pickedDate.setText("Selected Date : " + materialDatePicker.getHeaderText());
                String newdate = pickedDate.getText().toString();
                Date date = new Date();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
                newdate = formatter.format(date);
                Calendar c = Calendar.getInstance();

                try {
                    c.setTime(formatter.parse(newdate));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                c.add(Calendar.DATE, 14);
                ovulation.setText("Ovulation Day : "+c.getTime());
                c.add(Calendar.DATE, 28);
                cycle.setText("Next Cycle Day : "+c.getTime());




            }
        } );

        //Getting current date


        return v;
    }

}
