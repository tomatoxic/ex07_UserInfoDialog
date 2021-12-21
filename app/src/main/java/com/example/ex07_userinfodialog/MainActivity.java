package com.example.ex07_userinfodialog;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView edtName, edtEmail;
    Button button1;
    EditText dlgEdtName, dlgEdtEmail;
    TextView toastText;
    View dialogView, toastView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("사용자 정보 입력");

        edtName = (TextView) findViewById(R.id.edtName);
        edtEmail = (TextView) findViewById(R.id.edtEmail);
        button1 = (Button) findViewById(R.id.button1);

        // 버튼 눌렀을 때 다이얼로그 생성
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 1. 다이얼로그 생성
                AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);

                // 불러올 다이얼로그 xml 지정
                dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog1, null);

                dlg.setTitle("사용자 정보 입력");
                dlg.setIcon(R.drawable.ic_menu_allfriends);
                dlg.setView(dialogView);

                // 다이얼로그의 editText를 선언 후
                dlgEdtName = (EditText) dialogView.findViewById(R.id.dlgEdt1);
                dlgEdtEmail = (EditText) dialogView.findViewById(R.id.dlgEdt2);

                // 메인화면의 editText 값을 받아온다
                dlgEdtName.setText(edtName.getText().toString());
                dlgEdtEmail.setText(edtEmail.getText().toString());

                dlg.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // 다이얼로그의 editText 값을 받아서 메인화면의 editText로 지정
                        edtName.setText(dlgEdtName.getText().toString());
                        edtEmail.setText(dlgEdtEmail.getText().toString());
                    }
                });

                dlg.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast toast = new Toast(MainActivity.this);
                        toastView = (View) View.inflate(MainActivity.this, R.layout.toast1, null);
                        toastText = (TextView) toastView.findViewById(R.id.toastText1);
                        toastText.setText("취소했습니다");
                        toast.setView(toastView);

                        // 디스플레이의 너비를 구해서 토스트의 x,y 좌표를 random 함수로 임의의 위치로 계산
                        Display display = ((WindowManager) getSystemService(WINDOW_SERVICE)).getDefaultDisplay();
                        int x = (int) (Math.random() * display.getWidth());
                        int y = (int) (Math.random() * display.getHeight());
                        toast.setGravity(Gravity.TOP | Gravity.LEFT, x,y);

                        toast.show();
                    }
                });

                dlg.show();

            }
        });

    }
}