package enpi23.modul4.prak.anrdro.victor_1202150100_modul4;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class ListMahasiswaActivity extends AppCompatActivity {

    private ListView mListView;
    private ProgressBar mProgressBar;
    private String [] mUsers= {
            "Zeus","Poseidon","Hades","Ares","Apollo","Artemis","Leto","Aphrodite","Kiklops",
            "Athena","Demeter", "Rhea","Hefaistos","Hera","Hermes","Lira","Dionisos","Hestia"};

    private AddItemToListView mAddItemToListView;
    private Button mStartAsyncTask;
    //Menyatakan Array dan Komponen lainnya yang digunakan pada Java.class ini

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_mahasiswa);

        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        mListView = (ListView) findViewById(R.id.listView);
        mStartAsyncTask = (Button) findViewById(R.id.button_startAsyncTask);
        mListView.setVisibility(View.GONE);
        mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, new ArrayList<String>()));
        //Menyatakan komponen-komponen yang akan digunakan dan inisialisasi setAdapter untuk ListView dengan menggunakan Array

        mStartAsyncTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAddItemToListView = new AddItemToListView();
                mAddItemToListView.execute();

                //Button melakukan eksekusi ketiak diclick
            }
        });
    }

    public class AddItemToListView extends AsyncTask<Void, String, Void> {

        private ArrayAdapter<String> mAdapter;
        private int counter=1;
        ProgressDialog mProgressDialog = new ProgressDialog(ListMahasiswaActivity.this);

        //Menyatakan semua komponen yang akan digunakan

        @Override
        protected void onPreExecute() {
            mAdapter = (ArrayAdapter<String>) mListView.getAdapter();

            mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            mProgressDialog.setTitle("Loading Data");
            mProgressDialog.setMessage("Please wait...");
            mProgressDialog.setCancelable(false);
            mProgressDialog.setProgress(0);

            mProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    mAddItemToListView.cancel(true);
                    mProgressBar.setVisibility(View.VISIBLE);
                    dialogInterface.dismiss();
                }
            });
            mProgressDialog.show();
            //Method ini menyatakan eksekusi progress dialog
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for (String item : mUsers){
                publishProgress(item);
                try {
                    Thread.sleep(100);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if(isCancelled()){
                    mAddItemToListView.cancel(true);
                }
            }
            return null;

            //Method ini menyatakan aktivitas dilakukan dibackground menggunakan AsyncTask
        }

        @Override
        protected void onProgressUpdate(String... values) {
            mAdapter.add(values[0]);
            Integer current_status = (int) ((counter/(float)mUsers.length)*100);
            mProgressBar.setProgress(current_status);
            mProgressDialog.setProgress(current_status);
            mProgressDialog.setMessage(String.valueOf(current_status+"%"));
            counter++;

            //Method ini menyatakan perhitungan presentase dari progress dialog
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            mProgressBar.setVisibility(View.GONE);
            mProgressDialog.dismiss();
            mListView.setVisibility(View.VISIBLE);

            //Method ini menyatakan eksekusi setImageBitmap setelah method doInBackground dijalankan
        }
    }
}
