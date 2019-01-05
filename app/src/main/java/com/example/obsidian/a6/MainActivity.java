package com.example.obsidian.a6;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button button;

    private void initView() {
        button = (Button) findViewById(R.id.getCity);
        button.setOnClickListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    public void onClick(View v) {
        String city = getCityName();

        //创建一个填充物,用于填充Toast
        LayoutInflater inflater = getLayoutInflater();

        //填充物来自的xml文件,在这个改成一个LinearLayout
        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.toast, null);

        //找到xml里面的组件，设置组件里面的具体内容
        TextView text = linearLayout.findViewById(R.id.toastText);
        text.setText(city);

        //利用匿名类实现Toast
        Toast toast = Toast.makeText(MainActivity.this, null, Toast.LENGTH_SHORT);
        toast.setView(linearLayout);
        toast.setGravity(Gravity.TOP, 0, 200);
        toast.show();
    }

    //随机获取资源数组中的一个城市
    public String getCityName() {
        String[] cities;
        cities = this.getResources().getStringArray(R.array.city);
        int index = (int) (Math.random() * (cities.length - 1));
        String city = cities[index];
        System.out.println(city);
        return city;
    }
}
