package com.example.jsonplaceusers;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    AsyncHttpClient client;
    RequestParams params;

    ArrayList<String>idlist;
    ArrayList<String>namelist;
    ArrayList<String>usernamelist;
    ArrayList<String>emaillist;
    ArrayList<String>phonelist;
    ArrayList<String>websitelist;

    ArrayList<String>streetlist;
    ArrayList<String>suitelist;
    ArrayList<String>citylist;
    ArrayList<String>zipcodelist;
    ArrayList<String>latlist;
    ArrayList<String>lnglist;

    ArrayList<String>companynamelist;
    ArrayList<String>catchpraselist;
    ArrayList<String>bslist;

    ListView listviewusers;
    LayoutInflater inflate;

    String url="https://jsonplaceholder.typicode.com/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listviewusers=findViewById(R.id.users);

        client=new AsyncHttpClient();
        params=new RequestParams();

        idlist=new ArrayList<String>();
        namelist=new ArrayList<String>();
        usernamelist=new ArrayList<String>();
        emaillist=new ArrayList<String>();
        phonelist=new ArrayList<String>();
        websitelist=new ArrayList<String>();


        streetlist=new ArrayList<String>();
        suitelist=new ArrayList<String>();
        citylist=new ArrayList<String>();
        zipcodelist=new ArrayList<String>();

        latlist=new ArrayList<String>();
        lnglist=new ArrayList<String>();

        companynamelist=new ArrayList<String>();
        catchpraselist=new ArrayList<String>();
        bslist=new ArrayList<String>();

        client.get(url,params,new AsyncHttpResponseHandler(){
            @Override
            public void onSuccess(String content) {
                super.onSuccess(content);

                try{
                    JSONArray jarray=new JSONArray(content);
                    for (int i=0;i<jarray.length();i++){
                        JSONObject jobj=jarray.getJSONObject(i);
                        String idu=jobj.getString("id");
                        idlist.add("id :"+idu);
                        String nameu=jobj.getString("name");
                        namelist.add("Name:"+nameu);
                        String usernameu=jobj.getString("username");
                        usernamelist.add("Username :"+usernameu);
                        String emailu=jobj.getString("email");
                        emaillist.add("Email :"+emailu);
                        String phoneu=jobj.getString("phone");
                        phonelist.add("Phone :"+phoneu);
                        String websiteu=jobj.getString("website");
                        websitelist.add("Web :"+websiteu);

                        JSONObject addressobj=jobj.getJSONObject("address");
                        String streetu=addressobj.getString("street");
                        streetlist.add("Street :"+streetu);
                        String suiteu=addressobj.getString("suite");
                        suitelist.add("Suite :"+suiteu);
                        String cityu=addressobj.getString("city");
                        citylist.add("City :"+cityu);
                        String zipcodeu=addressobj.getString("zipcode");
                        zipcodelist.add("Zipcode :"+zipcodeu);

                        JSONObject geoobj=addressobj.getJSONObject("geo");
                        String latu=geoobj.getString("lat");
                        latlist.add("lat :"+latu);
                        String lngu=geoobj.getString("lng");
                        lnglist.add("lng :"+lngu);

                        JSONObject companyobj=jobj.getJSONObject("company");
                        String companynameu=companyobj.getString("name");
                        companynamelist.add("Company Name :"+companynameu);
                        String catchpraseu=companyobj.getString("catchPhrase");
                        catchpraselist.add("Catch Phrase :"+catchpraseu);
                        String bsu=companyobj.getString("bs");
                        bslist.add("bs :"+bsu);

                    }

                    Adapter adp=new Adapter();
                    listviewusers.setAdapter(adp);

                }catch (Exception e){
                    Toast.makeText(MainActivity.this, ""+e, Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    class Adapter extends BaseAdapter{

        @Override
        public int getCount() {
            return namelist.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            LayoutInflater inflate=(LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflate.inflate(R.layout.usersxml,null);

            TextView idtxt=convertView.findViewById(R.id.idxml);
            TextView nametxt=convertView.findViewById(R.id.namexml);
            TextView usernametxt=convertView.findViewById(R.id.usernamexml);
            TextView emailtxt=convertView.findViewById(R.id.emailxml);
            TextView phonetxt=convertView.findViewById(R.id.phonexml);
            TextView websitetxt=convertView.findViewById(R.id.websitexml);

            TextView streettxt=convertView.findViewById(R.id.streetxml);
            TextView suitetxt=convertView.findViewById(R.id.suitexml);
            TextView citytxt=convertView.findViewById(R.id.cityxml);
            TextView zipcodetxt=convertView.findViewById(R.id.zipcodexml);

            TextView lattxt=convertView.findViewById(R.id.latxml);
            TextView lngtxt=convertView.findViewById(R.id.lngxml);

            TextView companynametxt=convertView.findViewById(R.id.companynamexml);
            TextView catchprasetxt=convertView.findViewById(R.id.catchphrasexml);
            TextView bstxt=convertView.findViewById(R.id.bsxml);

            idtxt.setText(idlist.get(position));
            nametxt.setText(namelist.get(position));
            usernametxt.setText(usernamelist.get(position));
            emailtxt.setText(emaillist.get(position));
            phonetxt.setText(phonelist.get(position));
            websitetxt.setText(websitelist.get(position));

            streettxt.setText(streetlist.get(position));
            suitetxt.setText(suitelist.get(position));
            citytxt.setText(citylist.get(position));
            zipcodetxt.setText(zipcodelist.get(position));

            lattxt.setText(latlist.get(position));
            lngtxt.setText(lnglist.get(position));

            companynametxt.setText(companynamelist.get(position));
            catchprasetxt.setText(catchpraselist.get(position));
            bstxt.setText(bslist.get(position));

            return convertView;
        }
    }
}
