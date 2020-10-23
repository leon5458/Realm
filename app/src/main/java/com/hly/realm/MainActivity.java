package com.hly.realm;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import io.realm.RealmResults;
public class MainActivity extends AppCompatActivity {

    private EditText phone, pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Realm realm = Realm.getDefaultInstance();
        phone = findViewById(R.id.phone);
        pw = findViewById(R.id.pw);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                realm.beginTransaction();
                User user = realm.createObject(User.class);
                user.setPhone(phone.getText().toString().trim());
                user.setPw(pw.getText().toString().trim());
                realm.copyToRealm(user);
                realm.commitTransaction();
                Toast.makeText(MainActivity.this, "插入数据库成功", Toast.LENGTH_SHORT).show();


            }
        });

        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                RealmResults<User> result = realm.where(User.class)
//                        .equalTo("phone", "1")
//                        .or()
//                        .equalTo("phone", "12")
//                        .findAllAsync();
//                Log.e("--------result",result.toString());

                RealmResults<User> results = realm.where(User.class).findAllAsync();
                for (User u : results) {
                    Log.e("-------results", u.getPhone() + "-------" + u.getPw());
                    Toast.makeText(MainActivity.this, "数据:"+u.getPhone()+","+u.getPw(),
                            Toast.LENGTH_SHORT).show();

                }
            }
        });

        findViewById(R.id.btn2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RealmResults<User> results = realm.where(User.class).findAll();
                realm.beginTransaction();
//                results.deleteFirstFromRealm(); //删除第一个
                results.deleteLastFromRealm();//最后一个数据
//                results.deleteAllFromRealm();//删除所有

//                User user =results.get(2); //指定下标删除
//                if (user != null) {
//                    user.deleteFromRealm();
//                }

                realm.commitTransaction();




            }
        });

        findViewById(R.id.btn3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = realm.where(User.class).equalTo("phone", "131").findFirst();
                realm.beginTransaction();
                if (user != null) {
                    user.setPhone("176");
                    Toast.makeText(MainActivity.this, "修改成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "这里只是测试填写的手机号错误修改不了", Toast.LENGTH_SHORT).show();
                }
                realm.commitTransaction();
            }
        });
    }


}
