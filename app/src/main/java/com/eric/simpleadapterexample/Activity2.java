package com.eric.simpleadapterexample;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class Activity2 extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);

        Button button = (Button)findViewById(R.id.withoutImagesButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("title", "Poker Face");
        map.put("artist", "Lady Gaga");
        map.put("pic", ((Integer)R.drawable.pic).toString());
        data.add(map);
        map = new HashMap<String, String>();
        map.put("title", "Yeah!");
        map.put("artist", "Usher");
        map.put("pic", ((Integer)R.drawable.pic2).toString());
        data.add(map);

        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                data,
                R.layout.listview_with_images_view,
                new String[]{ "title", "artist", "pic" },
                new int[]{ R.id.title, R.id.artist, R.id.imageView });
        simpleAdapter.setViewBinder(new SimpleAdapter.ViewBinder(){

            @Override
            public boolean setViewValue(View view, Object o, String s) {
                if(view.getClass() == ImageView.class) {
                    ImageView image = (ImageView)view;
                    image.setImageResource(Integer.parseInt(s));
                    return true;
                }
                return false;
            }
        });
        ListView withImages = (ListView)findViewById(R.id.listViewImages);
        withImages.setAdapter(simpleAdapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity2, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
