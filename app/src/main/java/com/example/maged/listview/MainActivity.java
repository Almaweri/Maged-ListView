package com.example.maged.listview;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
   ListView listView = null;
   ProductAdapter productAdapter = null;
   int m_CurrentItemIndex = -1;
   String m_Currentoperation = "";
   Button btnAdd = null;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
      super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      listView = (ListView) findViewById(R.id.lvProduct);

      productAdapter = new ProductAdapter();

      productAdapter.addProduct("Galaxy Note 8", "$700", R.drawable.galaxy);
      productAdapter.addProduct("iPhone", "$900", R.drawable.iphone);
      productAdapter.addProduct("Nikon", "$1500.45", R.drawable.nikon);

      listView.setAdapter(productAdapter);

      btnAdd = (Button) findViewById(R.id.btnAdd);
      btnAdd.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view)  {
            Intent intent = new Intent(view.getContext(), ProductDetailsActivity.class);
            intent.putExtra("OperationType", "ADD");
            startActivityForResult(intent, 555);
         }
      });
   }

   @Override
   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
      super.onActivityResult(requestCode, resultCode, data);
      try {
         // When an Image is picked
         if (requestCode == 555 && resultCode == RESULT_OK && null != data) {
            if (data.getStringExtra("OperationType").equals("ADD"))
            {
               productAdapter.addProduct(
                    data.getStringExtra("ProductName")
                    , data.getStringExtra("ProductPrice")
                    , R.drawable.galaxy);
               listView.setAdapter(productAdapter);
            }

         }
      } catch (Exception e) {
         //
      }
   }

}