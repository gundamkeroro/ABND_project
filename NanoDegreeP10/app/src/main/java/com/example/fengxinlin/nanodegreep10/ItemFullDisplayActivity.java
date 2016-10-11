package com.example.fengxinlin.nanodegreep10;

import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by fengxinlin on 10/8/16.
 */
public class ItemFullDisplayActivity extends AppCompatActivity {
    public int quantity;
    public int rowID;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_item);

        TextView name = (TextView) findViewById(R.id.productName);
        TextView price = (TextView) findViewById(R.id.productPrice);
        final TextView quantityTextView = (TextView) findViewById(R.id.productQuantity);

        quantityTextView.setText("0");
        final Intent details = getIntent();
        final Inventory inventory = new Inventory(details.getStringExtra("productName"), details.getIntExtra("productQuantity", 0), details.getDoubleExtra("price", 0.00));

        setTitle(details.getStringExtra("productName"));
        name.setText(details.getStringExtra("productName"));
        quantityTextView.setText("" + details.getIntExtra("productQuantity", 0));
        price.setText("$" + String.valueOf(details.getDoubleExtra("price", 0.00)));

        ContextWrapper cw = new ContextWrapper(this);
        File dir = cw.getFilesDir();
        String imageLocationDir = dir.toString();
        rowID = details.getIntExtra("id", 0);
        String imagePath = imageLocationDir + "/" + rowID;
        Log.v("Image path: ","After click Item"+imagePath);

        ImageView imageView = (ImageView) findViewById(R.id.imgIcon);

        Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
        // Set the image view
        imageView.setImageBitmap(bitmap);

        Button button_offer = (Button) findViewById(R.id.orderNow);
        button_offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSubmitMore(view);
            }
        });

        Button button_delete = (Button) findViewById(R.id.delete);
        button_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickDelete(view);
            }
        });

        Button reload_button = (Button) findViewById(R.id.reload);
        reload_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper db = new DBHelper(view.getContext());
                int among = quantity + inventory.getQuantity();
                inventory.setQuantity(among);
                int id  = details.getIntExtra("id",0);
                inventory.setId(id);
                Long i = db.update(inventory.getId(), inventory);
                Intent intent = new Intent(view.getContext(), MainActivity.class);
                view.getContext().startActivity(intent);
            }
        });

        Button track_button = (Button) findViewById(R.id.track);
        track_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (quantity > inventory.getQuantity()) {
                    Toast.makeText(ItemFullDisplayActivity.this, "We don't have enough item(s)", Toast.LENGTH_SHORT).show();
                } else {
                    DBHelper db = new DBHelper(view.getContext());
                    int among = inventory.getQuantity() - quantity;
                    inventory.setQuantity(among);
                    int id  = details.getIntExtra("id",0);
                    inventory.setId(id);
                    Long i = db.update(inventory.getId(), inventory);
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    view.getContext().startActivity(intent);
                }
            }
        });
    }

    public void quantityIncrement(View view) {
        if (quantity == 100) {
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void quantityDecrement(View view) {
        if (quantity == 0) {
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    public void onClickDelete(final View view) {
        final Intent intent = getIntent();
        new AlertDialog.Builder(this)
                .setTitle("Warning")
                .setMessage("Are you sure you want to delete this record permanently?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        deleteItemPermanently(intent.getStringExtra("productName"));
                        Toast.makeText(view.getContext(), "Deleted Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(view.getContext(), MainActivity.class);
                        view.getContext().startActivity(intent);
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    public void deleteItemPermanently(String name) {
        DBHelper db = new DBHelper(this);
        db.delete(name);
    }

    public void onSubmitMore(View view) {
        Intent details = getIntent();
        String subject = "URGENT: ORDER MORE ITEMS";
        String message = "Product Name: " + details.getStringExtra("productName") +
                "\nQuantity To be ordered: " + quantity +
                "\n\nI need this item" +
                "\n\nThanks";
        Log.v("Message:", message);
        String[] emails = {"workOrderMore@gmail.com"};

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, emails);
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    private void displayQuantity(int count) {
        TextView quantityTextView_display = (TextView) findViewById(R.id.quantity);
        quantityTextView_display.setText("" + count);
    }
}
