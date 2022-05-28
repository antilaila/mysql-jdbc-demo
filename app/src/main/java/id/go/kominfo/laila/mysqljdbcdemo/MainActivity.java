package id.go.kominfo.laila.mysqljdbcdemo;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import id.go.kominfo.laila.mysqljdbcdemo.adapter.FriendAdapter;
import id.go.kominfo.laila.mysqljdbcdemo.dbhelper.FriendJDBCMySQL;
import id.go.kominfo.laila.mysqljdbcdemo.model.Friend;

public class MainActivity extends AppCompatActivity {
    /**
     * Database, Table and User in MySQL :
     *
     * create database if not exists antilaila8;
     *
     * use antilaila8;
     *
     * create table friend(
     *     id int not null primary key auto_increment,
     *     name varchar(20) not null,
     *     address varchar(30) null,
     *     phone varchar(14) null
     * );
     *
     * grant all privileges on antilaila8.friend to 'kodejava'@'%' identified by 'kodejava';
     *
     *  insert into friend values
     *     (null, "Ullah", "Mabeo", "098889889"),
     *     (null, "Hing", "Mabeo", "098889887"),
     *     (null, "Eson", "Mabeo", "098889886"),
     *     (null, "Ko Acun", "Mabeo", "098889883");
     *
     */
    private static final String URL = "jdbc:mysql://192.168.100.151:3306/antilaila8";
    private static final String USER = "kodejava";
    private static final String PASSWORD = "kodejava";

    private FriendJDBCMySQL db;

    private List<Friend> ls;
    private FriendAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ls = new ArrayList<>();
        adapter = new FriendAdapter(this, ls);
        ListView listView = findViewById(R.id.listview);
        listView.setAdapter(adapter);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //load data from table to listview
        ls.clear();
        //make connection to mysql database
        FriendJDBCMySQL db = new FriendJDBCMySQL(URL, USER, PASSWORD);
        ls.addAll(db.getAllFriends());
        //notify perubahan ke listview
        adapter.notifyDataSetChanged();
    }
}