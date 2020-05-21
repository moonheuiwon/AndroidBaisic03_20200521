package com.example.androidbaisic03_20200521;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.androidbaisic03_20200521.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;

    final int REQ_FOR_NICKNAME = 1001;
    final int REQ_FOR_PHONE_NUM = 1002;
    final int REQ_FOR_EMAIL = 1003;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setupEvents();
        setValues();
    }

    @Override
    public void setupEvents() {

        binding.webBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                웹 창 띄우기 (이동하기)
                String naverLinkUrl = "http://naver.com";

                Uri myUri = Uri.parse(naverLinkUrl);
                Intent myInten = new Intent(Intent.ACTION_VIEW, myUri);
                startActivity(myInten);
            }
        });

        binding.smsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                문자전송하기
                String dialPhoneNum = binding.userPhoneTxt.getText().toString();

                Uri myUri = Uri.parse(String.format("smsto:%s", dialPhoneNum));
                Intent myIntent = new Intent(Intent.ACTION_SENDTO, myUri);
                myIntent.putExtra("sms_body", "테스트 문자 내용");
                startActivity(myIntent);
            }
        });

        binding.calBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                전화걸어보기 (앱)죽음
                String dialPhoneNum = binding.userPhoneTxt.getText().toString();

                Uri myUri = Uri.parse(String.format("tel:%s", dialPhoneNum));
                Intent myIntent = new Intent(Intent.ACTION_CALL, myUri);
                startActivity(myIntent);
            }
        });

        binding.dialBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                전화걸기 등 안드로이드는 => uri 정보 요구.

                String dialPhoneNum = binding.userPhoneTxt.getText().toString();

                Uri myUri = Uri.parse(String.format("tel:%s", dialPhoneNum));
                Intent myIntent = new Intent(Intent.ACTION_DIAL, myUri);
                startActivity(myIntent);
            }
        });

        binding.editEmailBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, EditUserEmailActivity.class);
                startActivityForResult(myIntent, REQ_FOR_EMAIL);
            }
        });

        binding.editPhoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, EditUserPhoneActivity.class);
                startActivityForResult(myIntent, REQ_FOR_PHONE_NUM);
            }
        });

//        대부분 변수가 똑같을때 변수로 만들어서 사용한다.
        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(mContext, EditUserNickNameActivity.class);
                startActivityForResult(myIntent, REQ_FOR_NICKNAME);
            }
        };

        binding.editNickNameBtn.setOnClickListener(ocl);

    }

    @Override
    public void setValues() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_FOR_NICKNAME) {
            if (resultCode == RESULT_OK) {

                String nick = data.getStringExtra("editNickName");

                binding.userNickNameTxt.setText(nick);

            }

        } else if (requestCode == REQ_FOR_PHONE_NUM) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    String phoneNum = data.getStringExtra("phone");

                    binding.userPhoneTxt.setText(phoneNum);
                }
            } else {
                Toast.makeText(mContext, "전화번호 변경을 취소했습니다.", Toast.LENGTH_SHORT).show();
            }
        } else if (requestCode == REQ_FOR_EMAIL) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    String email = data.getStringExtra("email");

                    binding.userEmailTxt.setText(email);
                }
            } else {
                Toast.makeText(mContext, "이메일 변경을 취소했습니다.", Toast.LENGTH_SHORT).show();
            }
        }

    }

}
