package kr.hs.emirim.dbfl0406.simplediary;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    DatePicker datepic;
    EditText editDiary;
    Button butSave;
    String fileName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        datepic=(DatePicker)findViewById(R.id.date_picker);
        editDiary=(EditText)findViewById(R.id.edit_text);
        butSave=(Button)findViewById(R.id.but_save);

        //현재 날짜 구하기
        Calendar calendar=Calendar.getInstance();
        int nowYear=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH)+1;
        int date=calendar.get(Calendar.DATE);

        //DatePicker에 현재 날짜 설정
        datepic.init(nowYear, month, date, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                fileName=year+"_"+(monthOfYear+1)+"_"+dayOfMonth+".txt";
                String content=readDiary(fileName);
                editDiary.setText(content);
                butSave.setEnabled(true);
            }
        });
    }

    String readDiary(String fileName){
        String diaryContents=null;
        try {
            FileInputStream in=openFileInput(fileName);
            byte[] txt=new byte[500];
            in.read(txt);
            in.close();
            diaryContents=new String(txt);
            butSave.setText("수정하기");
        } catch (IOException e) {
            editDiary.setHint("읽어올 일기가 없음");
            butSave.setText("새로 저장");
        }

        return diaryContents;
    }
}
