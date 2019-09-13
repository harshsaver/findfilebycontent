package project.oop.engine;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SearchAct extends AppCompatActivity {
    // The InputStream opens the resourceId and sends it to the buffer
    static String f;//=new File(Environment.getDataDirectory()+ "/niles/nalla.txt").toString(); //= Environment.getDataDirectory().toString();
    static String path;
    Context mContext = SearchAct.this;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //f = this.getResources().openRawResource(R.raw.your_file).toString();
        f = Environment.getDataDirectory().toString() + "/Download/ex.txt";//"/storage/emulated/0/Download/ex.txt";
        // Example of a call to a native method
        TextView tv = findViewById(R.id.sample_text);
        //tv.setText(stringFromJNI());
        String bb = " ";

        writeFileToPrivateStorage(R.raw.your_file,"your_output_file.txt");
        path = getResources().toString();//mContext.getApplicationContext().getFilesDir().toString();
        tv.setText("Hello Harsh");
        //bb = setconfiguration(path);
        bb = ndkopenfile(path);

        if(!bb.equals("")&&!bb.equals(" ")){
            tv.setText(bb);
        }else{
            Toast.makeText(this,bb,Toast.LENGTH_LONG).show();
        }

    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    //public native String stringFromJNI();

    public native String ndkopenfile(String j);

    public static native String setconfiguration(String mpath);

    public void writeFileToPrivateStorage(int fromFile, String toFile)
    {

        InputStream is =   mContext.getResources().openRawResource(fromFile);
        int bytes_read;
        byte[] buffer = new byte[4096];
        try
        {
            FileOutputStream fos = mContext.openFileOutput(toFile, Context.MODE_PRIVATE);

            while ((bytes_read = is.read(buffer)) != -1)
                fos.write(buffer, 0, bytes_read); // write

            fos.close();
            is.close();

        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

}
