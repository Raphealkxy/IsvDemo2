package com.example.timmy.isvdemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FirstActivity extends Activity implements View.OnClickListener {

    private Toast mToast;

    private Button confirm;
    private EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);
        confirm= (Button) findViewById(R.id.make_queren);
        editText= (EditText) findViewById(R.id.userID);
        confirm.setOnClickListener(this);
        mToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.make_queren:
                // 过滤掉不合法的用户名
                String uname = ((EditText) findViewById(R.id.userID)).getText().toString();
                if (TextUtils.isEmpty(uname)) {
                    showTip("用户名不能为空");
                    return;
                } else {
                    Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
                    Matcher m = p.matcher(uname);
                    if (m.find()) {
                        showTip("不支持中文字符");
                        return;
                    } else if (uname.contains(" ")) {
                        showTip("不能包含空格");
                        return;
                    } else if (!uname.matches("^[a-zA-Z][a-zA-Z0-9_]{5,17}")) {
                        showTip("6-18个字母、数字或下划线的组合，以字母开头");
                        return;
                    }
                }

                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("uname", uname);
                startActivity(intent);
                break;

            default:
                break;
        }
    }
    private void showTip(final String str) {
        mToast.setText(str);
        mToast.show();
    }

}
