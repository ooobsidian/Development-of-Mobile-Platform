package com.example.obsidian.a6;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @program: 6
 * @description:
 * @author: lrx
 * @create: 2019-01-15 21:34
 */
public class RegisterMember extends AppCompatActivity {
    private ToggleButton toggle;
    private EditText name, phone, date, email;
    private Button btn1;
    private RadioGroup radioGroup;
    private RadioButton man;
    private RadioButton woman;
    private Spinner adr;
    private AutoCompleteTextView adr1;
    private CheckBox hobby1, hobby2;
    private String sex;
    private String chooseAdr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_member);
        toggle = (ToggleButton) findViewById(R.id.toggleButton);
        final View hobby = (View) findViewById(R.id.hobby);
        name = (EditText) findViewById(R.id.name);
        phone = (EditText) findViewById(R.id.phone);
        date = (EditText) findViewById(R.id.date);
        email = (EditText) findViewById(R.id.email);
        radioGroup = (RadioGroup) findViewById(R.id.sex);
        man = (RadioButton) findViewById(R.id.man);
        woman = (RadioButton) findViewById(R.id.woman);
        adr = (Spinner) findViewById(R.id.adr);
        adr1 = (AutoCompleteTextView) findViewById(R.id.adr1);
        hobby1 = (CheckBox) findViewById(R.id.hobby1);
        hobby2 = (CheckBox) findViewById(R.id.hobby2);
        btn1 = (Button) findViewById(R.id.go);
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
                // TODO Auto-generated method stub
                if (isChecked) {
                    //设置LinearLayout垂直布局
                    hobby.setVisibility(View.VISIBLE);
                } else {
                    hobby.setVisibility(View.GONE);
                }
            }

        };
        toggle.setOnCheckedChangeListener(listener);

        //为radioGroup添加监听器
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                if (man.getId() == checkedId) {
                    sex = "男";
                } else if (woman.getId() == checkedId) {
                    sex = "女";
                }
            }
        });

        //设置spinner的监听事件
        adr.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                //获取选择的
                chooseAdr = (String) adr.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub

            }
        });

    }

    @Override
    protected void onResume() {//Activity的生命周期中的可交互阶段，所以可以将Button按钮的点击监听事件放入其中
        super.onResume();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str1 = "姓名:  " + name.getText().toString();//获取EditText1中的内容
                String str2 = "电话:  " + phone.getText().toString();//获取EditText2中的内容
                String str3 = "出生年月:    " + date.getText().toString();
                String str4 = "电子邮箱:    " + email.getText().toString();
                String str5 = "所在区域:    " + adr1.getText().toString();
                List<String> resultList = new ArrayList<String>();
                if (hobby1.isChecked()) {
                    resultList.add(hobby1.getText().toString());
                }
                if (hobby2.isChecked()) {
                    resultList.add(hobby2.getText().toString());
                }

                StringBuilder sb = new StringBuilder("爱好:   ");
                String result;
                if (resultList.size() != 0) {
                    for (Iterator<String> it = resultList.iterator(); it.hasNext(); ) {
                        sb.append(it.next());
                        sb.append("，");
                    }
                    result = sb.substring(0, sb.length() - 1);
                } else {
                    sb.append("您没选择爱好");
                    result = sb.substring(0, sb.length());
                }


                String content =
                        str1 + "\n" +
                                str2 + "\n" +
                                str3 + "\n" +
                                str4 + "\n" +
                                "性别:    " + sex + "\n" +
                                "地址:    " + chooseAdr + "\n" +
                                str5 + "\n" +
                                result + "\n";

                Toast.makeText(RegisterMember.this, content, Toast.LENGTH_SHORT).show();//Toast提示内容
            }
        });
    }
}

