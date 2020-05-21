package com.example.androidbaisic03_20200521;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.androidbaisic03_20200521.databinding.ActivityEditUserEmailBinding;

public class EditUserEmailActivity extends BaseActivity {

    ActivityEditUserEmailBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_edit_user_email);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputEmail = binding.emailEdt.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("email", inputEmail);

                setResult(RESULT_OK, resultIntent);

                finish();
            }
        });

    }

    @Override
    public void setValues() {

    }
}
