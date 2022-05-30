package com.example.esercitazionebonus3;
import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    private Calendar date;

    public Calendar getDate() {
        return date;
    }
    public void setDate(Calendar date) {
        this.date = date;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        DatePicker datePicker = new DatePicker(getActivity());
        date = Calendar.getInstance();

        return new AlertDialog.Builder(requireContext())
                .setMessage("Inserisci una data")
                .setView(datePicker)
                .setPositiveButton("Ok", (dialog, which) -> {
                    //cosa succede quando premo ok?
                    date.set(Calendar.YEAR, datePicker.getYear());
                    date.set(Calendar.MONDAY, datePicker.getMonth());
                    date.set(Calendar.DAY_OF_MONTH, datePicker.getDayOfMonth());

                    ((RegistrazioneActivity)getActivity()).doPositiveClick(date);
                })
                .setNegativeButton("Annulla", (dialog, which) -> {
                    dialog.dismiss();
                })
                .create();
    }

    public static String TAG = "DatePickerDialogue";
}
