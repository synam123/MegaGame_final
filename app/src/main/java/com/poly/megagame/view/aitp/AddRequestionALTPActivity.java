package com.poly.megagame.view.aitp;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.poly.megagame.R;
import com.poly.megagame.adapter.AddRequestionALTPAdapter;
import com.poly.megagame.model.altp.RequestionALTP;

import java.util.ArrayList;

public class AddRequestionALTPActivity extends AppCompatActivity implements TextWatcher {

    private RecyclerView    recRequestion;
    private RequestionALTP  requestion;
    private EditText        edtRequestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_requestion_altp);

        createToolbar();
        initView();
        createAddRequest();
        addEvents();
    }

    private void addEvents() {
        edtRequestion.addTextChangedListener(this);
    }

    private void createAddRequest() {
        ArrayList<String> arrayAnswer = new ArrayList<>();
        arrayAnswer.add("");
        arrayAnswer.add("");
        arrayAnswer.add("");
        arrayAnswer.add("");

        requestion = new RequestionALTP();
        requestion.setAnswer(4);
        requestion.setArrayAnswer(arrayAnswer);

        recRequestion.setHasFixedSize(true);
        recRequestion.setLayoutManager(new LinearLayoutManager(this));
        AddRequestionALTPAdapter requestionAdapter = new AddRequestionALTPAdapter(requestion);
        recRequestion.setAdapter(requestionAdapter);

    }

    private void initView() {
        edtRequestion = findViewById(R.id.edtRequestion);
        recRequestion = findViewById(R.id.recRequestion);
    }

    private void createToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_back_white_28dp);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        requestion.setRequestion(s.toString());
    }

    @Override
    public void afterTextChanged(Editable s) {}

    public void onClickSaveRequestion(View view) {
        if (TextUtils.isEmpty(requestion.getRequestion())){
            Toast.makeText(getApplicationContext(), "Bạn chưa điền câu hỏi", Toast.LENGTH_SHORT).show();
            return;
        }

        for (int i = 0; i < requestion.getArrayAnswer().size(); i++){
            if (TextUtils.isEmpty(requestion.getArrayAnswer().get(i))){
                Toast.makeText(getApplicationContext(), "Bạn chưa điền câu trả lời ở đáp án " + getPositionAnswer(i), Toast.LENGTH_SHORT).show();
                return;
            }
        }

        if (requestion.getAnswer() == 4){
            Toast.makeText(getApplicationContext(), "Bạn chưa chọn câu trả lời đúng", Toast.LENGTH_SHORT).show();
            return;
        }

        long currentTime = System.currentTimeMillis();

        FirebaseFirestore.getInstance()
                .collection("Requestion")
                .document(currentTime + "")
                .set(requestion)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(getApplicationContext(), "Thêm câu hỏi thành công !!!", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
    }

    private String getPositionAnswer(int position){
        switch (position){
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
        }
        return null;
    }
}
