package project.oop.engine;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SearchAct extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1;
    // The InputStream opens the resourceId and sends it to the buffer
    //=new File(Environment.getDataDirectory()+ "/niles/nalla.txt").toString(); //= Environment.getDataDirectory().toString();
    static String path;
    String query;
    Context mContext = SearchAct.this;
    ImageButton btn_search;
    TextView tv,ftext,ftitle;
    EditText sbar;
    ScrollView sv1;
    int x = 0;
    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //f = this.getResources().openRawResource(R.raw.your_file).toString();
        //f = Environment.getDataDirectory().toString() + "/Download/ex.txt";//"/storage/emulated/0/Download/ex.txt";
        // Example of a call to a native method
        tv = findViewById(R.id.result);
        btn_search = findViewById(R.id.searchbtn);
        sbar = findViewById(R.id.sbar);
        ftext = findViewById(R.id.filetext);
        sv1 = findViewById(R.id.sv1);
        ftitle = findViewById(R.id.filetitle);
        tv.setText("Click on the search icon (magnifying glass) to search");
        String bb = " ";
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                query = sbar.getText().toString();
                String check = stringFromJNI(query);
                sv1.setVisibility(View.GONE);
                ftitle.setVisibility(View.GONE);
                ftext.setVisibility(View.GONE);
                if(check.equals("pass")){
                    x = 0;
                    tv.setText("Searching...");
                    searchfunc();
                }else{
                    tv.setText("");
                    Toast.makeText(SearchAct.this,"Enter a valid query!",Toast.LENGTH_LONG).show();
                }

            }
        });
        //writeFileToPrivateStorage(R.raw.your_file,"your_output_file.txt");
        path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();//  + "/ello.txt";//getResources().toString();//mContext.getApplicationContext().getFilesDir().toString();


        //bb = setconfiguration(path);
        //bb = ndkopenfile(path);

      /**  if(!bb.equals("")&&!bb.equals(" ")){
            tv.setText(bb);
        }else{
            Toast.makeText(this,bb,Toast.LENGTH_LONG).show();
        }**/

    }
    @Override
    public void onBackPressed(){
       startActivity(new Intent(SearchAct.this,NavAct.class));
       finish();
    }
    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI(String qu);

    private List<File> getListFiles(File parentDir) {
        Toast.makeText(this,parentDir.toString(),Toast.LENGTH_LONG).show();
        ArrayList<File> inFiles = new ArrayList<File>();
        if (ContextCompat.checkSelfPermission(SearchAct.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            ActivityCompat.requestPermissions(SearchAct.this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);

        }else{
            File[] files = parentDir.listFiles();
            for (File file : files) {

             if(file.getName().endsWith(".txt")||file.getName().endsWith(".cpp")){
             inFiles.add(file);
             }

             }
        }

        return inFiles;
    }
    public void searchfunc(){

        //int fno = 1;
        String p2 = "/storage/emulated/0/Download";
        List<File> filesf = getListFiles(new File(p2));
        for(int fno = 0;fno<filesf.size();fno++) {
            if(x==0){
                acsearch(fno,filesf);
            }

        }
    }
    public void acsearch(int k,List<File> filesk){


        try {
            BufferedReader reader2 = new BufferedReader(new FileReader(filesk.get(k)));
            String line,totalt="";
            Toast.makeText(SearchAct.this,query,Toast.LENGTH_LONG).show();
            int stat = 0,mile=1;
            while ((line = reader2.readLine()) != null) {
                totalt = totalt + "\n" + mile + "\t\t\t" + line;
                if(stat==0){
                if (line.toLowerCase().equals(query.toLowerCase()) || line.toLowerCase().contains(query.toLowerCase())) {
                    tv.setText("Query: " + query + "\n\nFound in: "+filesk.get(k).toString()+"\n\nLine number: "+mile);
                    sbar.setText("");
                    x = 1;
                    stat = 1;

                }else{

                    tv.setText("Not found");

                }
                    }
                mile++;
            }

            if(stat==1){
                sv1.setVisibility(View.VISIBLE);
                ftitle.setVisibility(View.VISIBLE);
                ftext.setVisibility(View.VISIBLE);
                ftitle.setText(filesk.get(k).toString().substring(29));
                ftext.setText(totalt);
            }

            reader2.close();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        } catch (IOException l) {
            Toast.makeText(this, l.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
