package com.poly.megagame.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.poly.megagame.R;
import com.poly.megagame.model.Account;
import com.poly.megagame.utils.AccountUtils;

public class LoginActivity extends AppCompatActivity {

    private TextView txtRules;
    private EditText edtUserName, edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
        loadUI();
    }

    private void loadUI() {
        SpannableString formatRules = new SpannableString("Bằng cách đăng ký, bạn đồng ý với Điều Khoản và Chính Sách Riêng Tư của chúng tôi");
        formatRules.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 34,44, 0);
        formatRules.setSpan(new ForegroundColorSpan(Color.RED), 34,44, 0);
        formatRules.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 48,67, 0);
        formatRules.setSpan(new ForegroundColorSpan(Color.RED), 48,67, 0);
        txtRules.setText(formatRules);
    }

    private void initView() {
        txtRules        = findViewById(R.id.txtRules);
        edtUserName     = findViewById(R.id.edtUserName);
        edtPassword     = findViewById(R.id.edtPassword);
    }

    public void loginOnClick(View view) {
        String userName = edtUserName.getText().toString();
        String passWord = edtPassword.getText().toString();

        if (TextUtils.isEmpty(userName)){
            Toast.makeText(getApplicationContext(), "Bạn chưa điền tên đăng nhập", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(passWord)){
            Toast.makeText(getApplicationContext(), "Bạn chưa điền mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        FirebaseFirestore.getInstance()
                .collection("Account")
                .document(userName)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()){
                            Account account = task.getResult().toObject(Account.class);

                            AccountUtils.setAccount(account);
                            startActivity(new Intent(getApplicationContext(),HouseActivity.class));
                            finish();
                        }else {
                            Toast.makeText(getApplicationContext(), "Tài khoản không tồn tại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    public void onClickSignup(View view) {
        startActivity(new Intent(getApplicationContext(),SignupActivity.class));
    }
}
