package com.example.fengxinlin.nanodegreep10;

import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by fengxinlin on 10/8/16.
 */
public class AddNewItem extends AppCompatActivity{
    public String price;
    public String countitem;
    public String name;
    public long nextID;

    public Inventory inventoryObject = new Inventory();
    DBHelper db = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_new_item);
        nextID = (db.rowCount() + 1);
        Intent intent = getIntent();
        String message = intent.getStringExtra("HEADER");
        setTitle(message);

        Button add_button = (Button) findViewById(R.id.addButton);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSubmit(view);
            }
        });
    }

    public void onClickSubmit(View view) {
        EditText nameText = (EditText) findViewById(R.id.productName);
        EditText priceText = (EditText) findViewById(R.id.productPrice);
        EditText quantityText = (EditText) findViewById(R.id.productQuantity);

        ImageView img = (ImageView) findViewById(R.id.imageSelected);
        ImageView imgError = (ImageView) findViewById(R.id.imageViewError);
        name = nameText.getText().toString();
        price = priceText.getText().toString();
        countitem = quantityText.getText().toString();

        if (nameText.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Name cannot be Blank", Toast.LENGTH_LONG).show();
            nameText.setError("Name cannot be Blank");
            return;
        } else if (priceText.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "Price for the product is must", Toast.LENGTH_LONG).show();
            priceText.setError("Invalid Price");
            return;
        } else if (quantityText.getText().toString().length() == 0) {
            Toast.makeText(getApplicationContext(), "You have to enter a quantity", Toast.LENGTH_LONG).show();
            quantityText.setError("Invalid Input");
            return;
        } else if (img.getDrawable() == null) {
            Toast.makeText(getApplicationContext(), "you have to upload an image", Toast.LENGTH_LONG).show();

            imgError.setVisibility(View.VISIBLE);
            imgError.setImageResource(R.drawable.ic_error_outline_black_150dp);
            return;
        }
        else {
            db.insert(new Inventory(name, Integer.parseInt(countitem), Double.parseDouble(price)));
            Toast.makeText(this, "Item Added Successfully", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }
    public void btnImageOnClick(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 &&
                resultCode == RESULT_OK && null != data) {
            Toast.makeText(this, "Uploading...", Toast.LENGTH_LONG).show();
            Uri selectedImageUri = data.getData();
            try {
                Log.v("Path:", selectedImageUri.toString());
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                ImageView imageView = (ImageView) findViewById(R.id.imageSelected);
                imageView.setImageBitmap(bitmap);
                Log.v("TAG NOTE", "Product before created, ID: " + nextID);
                String filename = Long.toString(nextID);
                Log.v("Image path", filename);
                saveToInternalStorage(bitmap, filename);
                Toast.makeText(this, "good to get image", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Failed to get image", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void saveToInternalStorage(Bitmap bmp, String filename) {
        ContextWrapper contextWrapper = new ContextWrapper(getApplicationContext());
        File appDirectory = contextWrapper.getFilesDir();

        File currentPath = new File(appDirectory, filename);

        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(currentPath);
            bmp.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
