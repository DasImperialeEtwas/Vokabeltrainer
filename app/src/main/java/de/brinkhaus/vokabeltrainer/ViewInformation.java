package de.brinkhaus.vokabeltrainer;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toolbar;

import java.util.Random;

/**
 * Main activity of the app.
 */

public class ViewInformation extends AppCompatActivity {

    /**
     * This method is called when the activity is first created.
     *
     * After API 23 the permission request for accessing external storage is changed
     * Before API 23 permission request is asked by the user during installation of app
     * After API 23 permission request is asked at runtime
     * */
    private int EXTERNAL_STORAGE_PERMISSION_CODE = 23;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_information);

        Button eins = (Button) findViewById(R.id.answer_1);
        Button zwei = (Button) findViewById(R.id.answer_2);
        Button drei = (Button) findViewById(R.id.answer_3);
        Button vier = (Button) findViewById(R.id.answer_4);

        TextView vocabel = (TextView) findViewById(R.id.vocabel);
        TextView anweisung = (TextView) findViewById(R.id.anweisung);

        Random random = new Random();

        zwei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewInformation.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
/*
    public void savePublicly(View view) {
        Requesting Permission to access External Storage
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                EXTERNAL_STORAGE_PERMISSION_CODE);
        String editTextData = editText.getText().toString();
        Toast.makeText(this, "Test 1", Toast.LENGTH_SHORT).show();

        getExternalStoragePublicDirectory() represents root of external storage, we are using DOWNLOADS
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        Storing the data in file with name as geeksData.txt
        File file = new File(folder, "geeksData.txt");
        writeTextData(file, editTextData);
        editText.setText("");
        Toast.makeText(this, "Test 2", Toast.LENGTH_SHORT).show();
    }

    /*try {
            File csvfile = new File(Environment.getExternalStorageDirectory() + "/teest.csv");
            CSVReader reader = new CSVReader(new FileReader(csvfile.getAbsolutePath()));
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                // nextLine[] is an array of values from the line
                System.out.println(nextLine[0] + nextLine[1] + "etc...");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "The specified file was not found", Toast.LENGTH_SHORT).show();
        }

        public void showPublicData(View view) {
        // Accessing the saved data from the downloads folder
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

        // geeksData represent the file data that is saved publicly
        File file = new File(folder, "teest.csv");
        String data = getdata(file);
        if (data != null) {
            textView.setText(data);
        } else {
            textView.setText("No Data Found");
        }
    }

 */
 /*
    public void showPrivateData(View view) {

        // GeeksForGeeks represent the folder name to access privately saved data
        File folder = getExternalFilesDir("GeeksForGeeks");

        // gft.txt is the file that is saved privately
        File file = new File(folder, "gfg.txt");
        String data = getdata(file);
        if (data != null) {
            textView.setText(data);
        } else {
            textView.setText("No Data Found");
        }
    }

    // getdata() is the method which reads the data
    // the data that is saved in byte format in the file
    private String getdata(File myfile) {
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(myfile);
            int i = -1;
            StringBuffer buffer = new StringBuffer();
            while ((i = fileInputStream.read()) != -1) {
                buffer.append((char) i);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

        */

    //This Mthods was located in another MEthod so i put them outside
    //Variable lile writeTextData has to be global vars to access it in all Variable, think about to pass them as an parameter instat of making it public. :)
    //Do i even need this method? I only copied it to try out if it works then.
    // You didn't need all of them. Try to read the code and understand what it does. Than you can work with it -> Copy pasting to evry ScriptKiddi you have to understand what your codes
    //okay :)

}