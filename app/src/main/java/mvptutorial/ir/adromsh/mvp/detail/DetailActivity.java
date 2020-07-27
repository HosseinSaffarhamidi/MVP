package mvptutorial.ir.adromsh.mvp.detail;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import mvptutorial.ir.adromsh.mvp.R;

public class DetailActivity extends AppCompatActivity {

    ImageView imgNews;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView txtTitle,txtDescription,txtDate;
    String newsTitle,newsImage,newsDate,newsDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Bundle bundle = getIntent().getExtras();
         newsTitle = bundle.getString("news_title");
         newsImage = bundle.getString("news_image");
         newsDate = bundle.getString("news_date");
         newsDesc = bundle.getString("news_desc");

        setupViews();

    }

    private void setupViews() {
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar_detail);
        collapsingToolbarLayout=(CollapsingToolbarLayout) findViewById(R.id.collaps_detail);
        collapsingToolbarLayout.setExpandedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.transparent));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColorStateList(this,android.R.color.white));
        collapsingToolbarLayout.setTitle(newsTitle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });




        imgNews=(ImageView)findViewById(R.id.img_detail_imgNews);
        txtTitle=(TextView)findViewById(R.id.txt_detailContent_title);
        txtDescription=(TextView)findViewById(R.id.txt_ditailContent_desc);
        txtDate=(TextView)findViewById(R.id.txt_detailContent_date);

        Picasso.get().load(newsImage).into(imgNews);
        txtTitle.setText(newsTitle);
        txtDescription.setText(newsDesc);
        txtDate.setText(newsDate);
    }
}
