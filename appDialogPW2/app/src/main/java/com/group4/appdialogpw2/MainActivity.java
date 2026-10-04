package com.group4.appdialogpw2;

import android.os.Bundle;
import android.content.Intent;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    String[] members = {"Normunds Lazdins", "Andis Polakovs"};
    boolean[] checked = {false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Go to second activity
        findViewById(R.id.btnGoToSecond).setOnClickListener(v -> {
            startActivity(new Intent(this, SecondActivity.class));
        });

        // Open dialog
        findViewById(R.id.btnDialog).setOnClickListener(v -> showDialog());
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.dialog_title);

        builder.setMultiChoiceItems(members, checked, (dialog, which, isChecked) -> {
            checked[which] = isChecked;
            String msg = members[which] + (isChecked ? " checked" : " unchecked");
            Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        });

        // OK button – does not close dialog
        builder.setPositiveButton(R.string.ok, null);

        // Close button
        builder.setNegativeButton(R.string.close, (dialog, which) -> {
            Toast.makeText(this, "You closed dialog", Toast.LENGTH_SHORT).show();
            dialog.dismiss();
        });

        AlertDialog dialog = builder.create();

        dialog.setOnShowListener(d -> {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v -> {
                Toast.makeText(this, "You clicked OK", Toast.LENGTH_SHORT).show();
            });
        });

        dialog.show();
    }
}