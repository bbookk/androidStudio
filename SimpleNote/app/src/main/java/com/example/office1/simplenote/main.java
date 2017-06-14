package com.example.office1.simplenote;

import static android.provider.BaseColumns._ID;
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import static com.example.office1.simplenote.Constants.*;
import android.text.format.DateFormat;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import java.util.Date;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class main extends Activity {

    private NotesHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        helper = new NotesHelper(this);

        //read all data form DB to show
        try{
            Cursor cursor = getAllNotes();
            showNotes(cursor);
        }
        finally { //close connection with DB
            helper.close();
        }

        final EditText newTxt = (EditText) findViewById(R.id.new_txt);
        Button btnSave = (Button) findViewById(R.id.saveBtn);


        btnSave.setOnClickListener(new OnClickListener() {
            public void onClick(View view) {
                try{
                    addNote(newTxt.getText().toString());
                    Cursor cursor =  getAllNotes();
                    showNotes(cursor);
                    newTxt.setText(null);
                }
                finally {
                    helper.close();
                }
            }
        });
    }

    private void addNote(String str){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TIME, System.currentTimeMillis());
        values.put(CONTENT, str);
        db.insertOrThrow(TABLE_NAME, null, values);
    }

    private static String[] COLUMNS = { _ID, TIME, CONTENT};
    private static String ORDER_BY = TIME + " DESC";

    private Cursor getAllNotes(){
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, COLUMNS, null, null, null, null, ORDER_BY);
        startManagingCursor(cursor);
        return cursor;
    }

    private void showNotes(Cursor cursor){
        StringBuilder builder = new StringBuilder("ข้อความที่บันทึกไว้:\n\n");

        while(cursor.moveToNext()){
            long id = cursor.getLong(0); //read column 0 _ID
            long time = cursor.getLong(1); // read Column 1 TIME
            String content = cursor.getString(2); // Read Colum 2 CONTENT

            builder.append("ลำดับ ").append(id).append(": ");
            String date = (String) DateFormat.format("yyyy-MM-dd hh:mm:ss", new Date(time));

            builder.append(date).append("\n");
            builder.append("\t").append(content).append("\n");
        }

        TextView tv = (TextView) findViewById(R.id.all_txt);
        tv.setText(builder);
    }
}
