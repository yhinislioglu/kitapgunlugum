package com.expoyazilim.kitapligim.myapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    RecyclerView recyclerView;
    String insertServices = "http://api.expoyazilim.com/insert.php";
    String selectServices = "http://api.expoyazilim.com/kitalikjson.php";
    private BookShelfAdapter bookShelfAdapter;
    public static JsonParser jsp = new JsonParser();
    Button bsAdd;
    View ChieldView;
    int GetItemPosition ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bsAdd = findViewById(R.id.btn_BookselfAdd);
        bsAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,BookShelfAddActivity.class);
                startActivity(intent);
            }
        });
        new AsyncFecth().execute();



    }

    public class AsyncFecth extends AsyncTask<String ,String ,String >{
        ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("\tYükleniyor...");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        @Override
        protected void onPostExecute(final String result) {
            progressDialog.dismiss();
            final List<BookShelf> data = new ArrayList<>();
            progressDialog.dismiss();

            try{
                JSONArray jsonArray = new JSONArray(result);
                for(int i = 0;i < jsonArray.length(); i++)
                {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    BookShelf bs = new BookShelf();
                    bs.setBookShelf(jsonObject.getString("kitaplikAdi"));
                    data.add(bs);
                }

                recyclerView = findViewById(R.id.recyclerview);
                bookShelfAdapter = new BookShelfAdapter(MainActivity.this,data);
                recyclerView.setAdapter(bookShelfAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {

                    GestureDetector gestureDetector = new GestureDetector(MainActivity.this,new GestureDetector.SimpleOnGestureListener(){
                        @Override
                        public boolean onSingleTapUp(MotionEvent e) {
                            return true;
                        }
                    });

                    @Override
                    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                        ChieldView = rv.findChildViewUnder(e.getX(),e.getY());
                        if(ChieldView != null && gestureDetector.onTouchEvent(e))
                        {
                            GetItemPosition = rv.getChildAdapterPosition(ChieldView);
                            Toast.makeText(MainActivity.this,data.get(GetItemPosition).getBookShelf(),Toast.LENGTH_LONG).show();
                        }
                        return false;
                    }

                    @Override
                    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

                    }

                    @Override
                    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

                    }
                });

            }catch (JSONException e){
                Toast.makeText(MainActivity.this,e.toString(),Toast.LENGTH_LONG).show();
            }

        }

        @Override
        protected String doInBackground(String... strings) {

            String json;
            try {
                json = JsonParser.getJSONFromUrl(selectServices);
                return json;

            } catch (Exception e) {
                e.printStackTrace();
                return e.toString();
            }
        }
    }


}
