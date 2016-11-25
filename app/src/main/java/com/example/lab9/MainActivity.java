package com.example.lab9;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG_MAIN_ACTIVITY = "In-MainActivity";
    private static final String MY_SHARED_PREFERENCES = "MySharedPrefsKey";
    private static final String outputFilename = "internalStorageFile.txt";
    //private static final String outputFilename = "internalStorageFile2.txt";

    private TextView myTextView;
    private EditText myEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.myTextView = (TextView) this.findViewById(R.id.text_view);
        this.myEditText = (EditText) this.findViewById(R.id.text_edit);
        final Button ok_Btn = (Button) this.findViewById(R.id.ok_but);
        ok_Btn.setOnClickListener(this);
        final Button internalStorage_Btn = (Button) this.findViewById(R.id.save_int_but);
        internalStorage_Btn.setOnClickListener(this);
        final Button externalStorage_Btn = (Button) this.findViewById(R.id.save_ext_but);
        externalStorage_Btn.setOnClickListener(this);
        final Button getFiles_Btn = (Button) this.findViewById(R.id.curr_but);
        getFiles_Btn.setOnClickListener(this);
        final Button createDB_Btn = (Button) this.findViewById(R.id.create_db);
        createDB_Btn.setOnClickListener(this);

        SharedPreferences mSettings = this.getSharedPreferences(MainActivity.MY_SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        String mString = mSettings.getString("mtStringKey","");
    }

    @Override
    protected void onStop(){
//        Si la aplicación se cierra completamente (las
//        Shared Preferences se guardan en el método onStop() del ciclo de vida de la Activity) y se vuelve a iniciar, el
//        TextView mostrará el último mensaje con que fue actualizado.
        SharedPreferences mSettings =this.getSharedPreferences(MainActivity.MY_SHARED_PREFERENCES,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor mEditor = mSettings.edit();
        mEditor.putString("myStringKey",this.myTextView.getText().toString());
        mEditor.apply();
    }


    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.ok_but:
                //TODO: Chekear que el texto esté OK
                this.myTextView.setText(this.myEditText.getText().toString());
            break;
            case R.id.save_int_but:
                try
                {
                    FileOutputStream mFileOutputStream =
                            this.openFileOutput("internalStorageFile.txt", Context.MODE_APPEND);
                    String outputString = this.myEditText.getText().toString() + "\n";
                    mFileOutputStream.write(outputString.getBytes());
                    mFileOutputStream.close();
                } catch (IOException e) { e.printStackTrace(); }
            break;
            case R.id.save_ext_but:
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED) ||
                    Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
                    File downloadFolder =
                            new
                                    File(Environment.getExternalStoragePublicDirectory(
                                    Environment.DIRECTORY_DOWNLOADS),
                                    "newDirectory/newSubDirectory");
                    if (!downloadFolder.mkdirs())
                        Toast.makeText(this, "Directory not created", Toast.LENGTH_SHORT).show();
                }

            break;
            case R.id.create_db:
                MyDatabase database = new MyDatabase(this);
                SQLiteDatabase sqLiteDatabase = database.getReadableDatabase();
            break;
            case R.id.curr_but:

            break;
            default:
                break;
        }

    }
}
