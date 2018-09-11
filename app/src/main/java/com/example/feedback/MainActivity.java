package com.example.feedback;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.feedback.database.DatabaseHelper;
import com.example.feedback.database.Event;
import com.example.feedback.database.Responses;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.ForeignCollection;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.transform.Pivot;
import com.yarolegovich.discretescrollview.transform.ScaleTransformer;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_STORAGE = 20;

    private RelativeLayout relativeLayout;
    private Spinner spinner;
    private ProgressBar progressBar;
    private Button btnNext,btnPrevious,btnExport;
    private TextView tvQCount,tvText,tvRate,tvCheckbox,spinnerText,tvFestember;
    private LinearLayout layout_rate,layout_text,layout_checkbox;
    static int POS = 0;
    String[] events = {"Face Painting","Paper Dressing","T-Shirt Designing","Tattoo Making","Wall Painting",
                        "Choreo Nite(Themed)","Duet Freestyle","Eastern Solo","Western Freestyle Solo", "Choreo Nite(Non-Themed)",
                        "Theatrix","Improvathon",
                        "Fashionitas","Mr. & Ms. Festember",
                        "Acapella","Acoustics","GigaHertz","Tarangini",
                        "Instrument(Non Percussion)","Instrument(Percussion)","Vocals(Solo)"};
    //Responses[] temp_list;
    ArrayList<Responses> responses;
    int counter = 0;
    private Button btnSubmit;
    RecyclerAdapter recyclerAdapter;
    //RecyclerView recyclerView;
    DiscreteScrollView discreteScrollView;
    private LinearLayout btnLayout;

    DatabaseHelper databaseHelper;
    private Dao<Event, Long> eventDao;
    private Dao<Responses, Integer> responseDao;

    private Util u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_STORAGE);
        }

        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnSubmit = (Button) findViewById(R.id.submit);
        //btnNext = (Button) findViewById(R.id.btnNext);
        //btnPrevious = (Button) findViewById(R.id.btnPrevious);
        btnExport = (Button) findViewById(R.id.btnExport);
        tvQCount = (TextView) findViewById(R.id.quest_count);
        tvText = (TextView) findViewById(R.id.tv_text);
        tvRate = (TextView) findViewById(R.id.tv_rate);
        tvCheckbox = (TextView) findViewById(R.id.tv_cb);
        tvFestember = (TextView) findViewById(R.id.tvFestember);
        layout_rate = (LinearLayout) findViewById(R.id.layout_type_rating);
        layout_text = (LinearLayout) findViewById(R.id.layout_type_text);
        layout_checkbox = (LinearLayout) findViewById(R.id.layout_type_checkbox);
        //recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        discreteScrollView = (DiscreteScrollView) findViewById(R.id.discreteScrollView);
        spinnerText = (TextView) findViewById(R.id.spinner_text);
        btnLayout = (LinearLayout) findViewById(R.id.btnLayout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        u = new Util();

        responses = new ArrayList<>();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.spinner_item, events);
        spinner.setAdapter(adapter);

        setArrayList();
        //temp_list = Util.event_list.get(0);
        //responses.addAll(Arrays.asList(Util.event_list.get(0)));
        responses.addAll(Arrays.asList(u.event_list.get(0)));

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                discreteScrollView.scrollToPosition(0);
                POS = position;
                displayQuest(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        recyclerAdapter = new RecyclerAdapter(MainActivity.this, responses, new RecyclerAdapter.ClickListener() {
            @Override
            public void onRateListener(int position, String rating) {
                responses.get(position).ans = rating;
            }

            @Override
            public void onTextListener(int position, String ans) {
                responses.get(position).ans = ans;
            }

            @Override
            public void onCheckListener(int position, String checked) {
                responses.get(position).ans = checked;
            }

            @Override
            public void radioListener(int position, String checked) {
                responses.get(position).ans = checked;
            }
        });

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this,
                LinearLayoutManager.HORIZONTAL, false);
        //recyclerView.setLayoutManager(linearLayoutManager);
        //recyclerView.setAdapter(recyclerAdapter);

        discreteScrollView.setAdapter(recyclerAdapter);
        discreteScrollView.setItemTransformer(new ScaleTransformer.Builder()
                .setMaxScale(1.02f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER)
                .setPivotY(Pivot.Y.BOTTOM)
                .build());
        //TODO: put export function in button click listener and dont call it inside onCreate and put a progress to show the progress
        /*try {
            export();
        } catch (SQLException e) {
            e.printStackTrace();
        }*/

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==REQUEST_STORAGE) {
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED) {

            } else if(grantResults[0]== PackageManager.PERMISSION_DENIED) {
                if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("This permission is required").setTitle("Important permission required");
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_STORAGE);
                        }
                    });
                    ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},REQUEST_STORAGE);
                } else {

                }
            }
        }
    }

    public void setArrayList(){
        //Util.event_list.add(Util.ques_list_1);
        //Util.event_list.add(Util.ques_list_2);
        u.event_list.add(u.ques_list_1);
        u.event_list.add(u.ques_list_2);
        u.event_list.add(u.ques_list_3);
        u.event_list.add(u.ques_list_4);
        u.event_list.add(u.ques_list_5);

        u.event_list.add(u.ques_list_6);
        u.event_list.add(u.ques_list_7);
        u.event_list.add(u.ques_list_8);
        u.event_list.add(u.ques_list_9);
        u.event_list.add(u.ques_list_10);

        u.event_list.add(u.ques_list_11);
        u.event_list.add(u.ques_list_12);

        u.event_list.add(u.ques_list_13);
        u.event_list.add(u.ques_list_14);

        u.event_list.add(u.ques_list_15);
        u.event_list.add(u.ques_list_16);
        u.event_list.add(u.ques_list_17);
        u.event_list.add(u.ques_list_18);

        u.event_list.add(u.ques_list_19);
        u.event_list.add(u.ques_list_20);
        u.event_list.add(u.ques_list_21);
    }

    public void displayQuest(int pos){
        responses.clear();
        //responses.addAll(Arrays.asList(Util.event_list.get(pos)));
        responses.addAll(Arrays.asList(u.event_list.get(pos)));
        recyclerAdapter.notifyDataSetChanged();

    }

    public boolean check(){

        if(responses==null) return false;

        if(responses.size()==0) return false;

        for(int i=0;i<responses.size();i++) {

            if(responses.get(i).ans == null) return false;
            if(responses.get(i).ans.isEmpty()) {
                return false;
            }
            Log.d("Response",responses.get(i).ans);
        }
        return true;
    }


    public void submitListener(View view) throws SQLException {
        if(check()){

            openConnection();

            Event event = new Event();
            event.setEvent_name(events[POS]);
            event.setEvent_id(System.currentTimeMillis());

            eventDao.createOrUpdate(event);


            for(int i=0; i<responses.size(); i++){
                responses.get(i).setEvent(event);
                responseDao.createOrUpdate(responses.get(i));
            }

            closeConnection();
            Toast.makeText(getApplicationContext(),"Response Successfully Stored!",Toast.LENGTH_SHORT).show();
            Log.d("successfully stored","Data");

            Intent intent = new Intent(MainActivity.this,Main2Activity.class);
            startActivity(intent);
            finish();
        } else {
            Snackbar snackbar = Snackbar.make(relativeLayout,"Please submit all the responses!",Snackbar.LENGTH_SHORT);
            snackbar.show();
            //Toast.makeText(MainActivity.this,"Please submit all the responses!",Toast.LENGTH_SHORT).show();
        }
    }

    List<Event> eventArrayList = new ArrayList<>();
    ForeignCollection<Responses> responsesList;
    ArrayList<Responses> responsesArrayList = new ArrayList<>();

    private void export() throws SQLException {
        progressBar.setVisibility(View.VISIBLE);
        openConnection();
        eventArrayList = eventDao.queryForAll();

        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Festember/events/");
        if(f.exists())
            deleteRecursive(f);

        f.mkdirs();

        for(Event event: eventArrayList){
            responsesList = event.getResponses();
            responsesArrayList = new ArrayList<>(responsesList);
            writeToCSV(responsesArrayList, event.getEvent_name());
        }
        closeConnection();
        progressBar.setVisibility(View.GONE);
        Toast.makeText(MainActivity.this,"Exported!",Toast.LENGTH_SHORT).show();
        Log.d("events details", eventArrayList.size()+"");

    }

    void deleteRecursive(File fileOrDirectory) {
        if (fileOrDirectory.isDirectory())
            for (File child : fileOrDirectory.listFiles())
                deleteRecursive(child);

        fileOrDirectory.delete();
    }



    private static final String CSV_SEPARATOR = ",";
    private void writeToCSV(List<Responses> responsesList, String event_name)
    {
        Log.d("File Name", Environment.getExternalStorageDirectory().getAbsolutePath());

        StringBuffer oneLine = new StringBuffer();

        File f = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/Festember/events/"+event_name+".csv");
        /*
        //Read the existing file
        Scanner scanner = null;
        try {
            scanner = new Scanner(f);
            while (scanner.hasNextLine()){
                oneLine.append(scanner.nextLine().toString());
                oneLine.append("\n");
            }
            Log.d("file data", oneLine.toString()+":");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        */

        //Modify existing file
        try
        {
            //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(
            //        Environment.getExternalStorageDirectory().getAbsolutePath() + "/Festember/events/"+event_name+".csv"), "UTF-8"));

            BufferedWriter bw = new BufferedWriter(new FileWriter(f,true));

            for(Responses responses: responsesList){
                oneLine.append(responses.ans);
                oneLine.append(CSV_SEPARATOR);
            }

            bw.write(oneLine.toString());
            bw.newLine();
            bw.flush();
            bw.close();

        }
        catch (UnsupportedEncodingException e) {Log.d("Error", "Unsupported encoding");}
        catch (FileNotFoundException e){Log.d("Error", "File not found");}
        catch (IOException e){Log.d("Error", "IO exception");}
    }


    private DatabaseHelper getDatabaseHelper(){
        if(databaseHelper == null){
            databaseHelper = databaseHelper.getHelper(MainActivity.this);
        }
        return databaseHelper;
    }



    @Override
    public void onDestroy(){
        super.onDestroy();
        Runtime.getRuntime().gc();

        if(databaseHelper!=null){
            databaseHelper.close();
            databaseHelper = null;
        }

    }

    public void openConnection(){

        databaseHelper = getDatabaseHelper();
        try {
            responseDao = databaseHelper.getResponseDao();
            eventDao = databaseHelper.getEventDao();
        } catch (SQLException e) {
            Log.e("Error getting helper : ", e.getErrorCode() + " " + e.getMessage());
        }
    }

    public void closeConnection(){
        if(databaseHelper!=null){
            databaseHelper.close();
            databaseHelper = null;
        }
    }

    public void exportButton(View view) {
        try {
            export();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
