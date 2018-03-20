package enpi23.modul4.prak.anrdro.victor_1202150100_modul4;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.InputStream;

public class ImageSearchActivity extends AppCompatActivity {

    private EditText etURL;
    private Button btnSearch;
    private ImageView ivHasilSearch;
    private ProgressDialog progressDialog;
    private String textURL;

    //Mnyetakan semua komponen yang akan digunakan pada Java.class ini


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_search);

        etURL = (EditText) findViewById(R.id.etURL);
        btnSearch = (Button) findViewById(R.id.btnSearch);
        ivHasilSearch = (ImageView) findViewById(R.id.ivHasilSearch);

        //Inisialisasi semua komponen yang akan digunakan

    }

    public void cariGambar(View view) {
        textURL = etURL.getText().toString();
        //Mengubah EditText menjadi String
        if (textURL.isEmpty()) {
            Toast.makeText(this, "Isi URL Gambar Dahulu", Toast.LENGTH_LONG).show();
            //Ketika EditText tidak terisi akan memunculkan Toast
        } else {
            new DownloadGambar().execute(textURL);
            //Ketika EditText terisi String maka akan di eksekusi
        }
    }

    private class DownloadGambar extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(ImageSearchActivity.this);
            progressDialog.setTitle("Cari Gambar");
            progressDialog.setMessage("Getting Image");
            progressDialog.setIndeterminate(false);
            progressDialog.show();

            //Method ini menyatakan eksekusi progress dialog sebelum method onPostExecute dijalankan
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap bimage = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                bimage = BitmapFactory.decodeStream(in);

            } catch (Exception e) {
                Log.e("Error Message", e.getMessage());
                e.printStackTrace();
            }
            return bimage;

            //Method ini menyatakan untuk aktivitas dilakukan dibackground menggunakan AsyncTask
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            ivHasilSearch.setImageBitmap(bitmap);
            progressDialog.dismiss();

            //Method ini menyatakan eksekusi setImageBitmap setelah method doInBackground dijalankan
        }
    }
}