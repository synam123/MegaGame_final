package com.poly.megagame.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.poly.megagame.R;
import com.poly.megagame.model.Account;
import com.poly.megagame.utils.AccountUtils;

import java.util.Random;

public class SignupActivity extends AppCompatActivity {

    private EditText edtUserName,edtPassword,edtRePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        initView();
    }

    private void initView() {
        edtUserName     = findViewById(R.id.edtUserName);
        edtPassword     = findViewById(R.id.edtPassword);
        edtRePassword   = findViewById(R.id.edtRePassword);
    }

    public void onClickSignup(View view) {
        final String username     = edtUserName.getText().toString();
        String password     = edtPassword.getText().toString();
        String rePassword   = edtRePassword.getText().toString();

        if (TextUtils.isEmpty(username)){
            Toast.makeText(getApplicationContext(), "Bạn chưa điền tên tài khoản", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)){
            Toast.makeText(getApplicationContext(), "Bạn chưa điền mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(rePassword)){
            Toast.makeText(getApplicationContext(), "Bạn chưa điền nhập lại mật khẩu", Toast.LENGTH_SHORT).show();
            return;
        }

        final Account account = new Account();
        account.setUserName(username);
        account.setPassword(password);
        account.setName("account" + new Random().nextInt(10000));

        FirebaseFirestore.getInstance()
                .collection("Account")
                .document(username)
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.getResult().exists()){
                            Toast.makeText(getApplicationContext(), "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
                        }else {
                            FirebaseFirestore.getInstance()
                                    .collection("Account")
                                    .document(username)
                                    .set(account)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(getApplicationContext(), "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();

                                            AccountUtils.setAccount(account);

                                            Intent intent = new Intent(getApplicationContext(),HouseActivity.class);
                                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(getApplicationContext(), "Đăng ký thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        }
                    }
                });
    }
}
