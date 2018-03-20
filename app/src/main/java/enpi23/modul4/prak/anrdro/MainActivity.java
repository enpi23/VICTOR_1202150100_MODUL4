package enpi23.modul4.prak.anrdro.victor_1202150100_modul4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnToList, btnToSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnToList = (Button)findViewById(R.id.btnListMahasiswa);
        btnToSearch = (Button)findViewById(R.id.btnImageSearch);

        btnToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ListMahasiswaActivity.class);
                startActivity(i);
            }
        });

        btnToSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainActivity.this, ImageSearchActivity.class);
                startActivity(j);
            }
        });
    }
}
