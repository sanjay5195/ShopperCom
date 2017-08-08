package com.sanju.developer.shoppercom;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, Animation.AnimationListener {
    public static final String FB_DATABASE_PATH="passid";
//    private DatabaseReference mDatabaseRef;
    private String data;
    private TextView textView3,textView4;
    private LinearLayout llwel;
    private ImageView imageview2;
    Animation slide;

    private DatabaseReference mDatabaseRef;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);






        mDatabaseRef= FirebaseDatabase.getInstance().getReference(FB_DATABASE_PATH);
        textView3 = (TextView) findViewById(R.id.textView3);
        textView4 = (TextView) findViewById(R.id.textView4);
        llwel= (LinearLayout) findViewById(R.id.llwel);
        imageview2= (ImageView) findViewById(R.id.imageView2);
        slide = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.slidedown);
        slide.setAnimationListener(this);
        llwel.setVisibility(View.VISIBLE);

        // start the animation
        llwel.startAnimation(slide);
        imageview2.setVisibility(View.VISIBLE);

        // start the animation
        imageview2.startAnimation(slide);

      //  Query queryRef = mDatabaseRef.orderByChild(FB_DATABASE_PATH);
       // data=queryRef.toString().trim();


        textView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "9039441417";
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);

            }
        });
        textView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = "9039441417";
                Uri call = Uri.parse("tel:" + number);
                Intent surf = new Intent(Intent.ACTION_DIAL, call);
                startActivity(surf);

            }
        });



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "App is created by Mr.Sanjay Choudhary", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                Intent intent=new Intent(MainActivity.this,About.class);
                startActivity(intent);


            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    protected void onStart() {
        super.onStart();
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                    data = (String) dataSnapshot.getValue();


                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Fragment fragment = null;
        int id = item.getItemId();

        if (id == R.id.kurta) {
            Intent intent=new Intent(MainActivity.this,KurtaRcv.class);
            startActivity(intent);
            // Handle the camera action
        }  else if (id == R.id.heavysuits) {
            Intent intent=new Intent(MainActivity.this,HeavySuitRcv.class);
            startActivity(intent);


        } else if (id == R.id.top) {
            Intent intent=new Intent(MainActivity.this,TopRcv.class);
            startActivity(intent);


        }
        else if (id == R.id.purse) {
            Intent intent=new Intent(MainActivity.this,PurseRcv.class);
            startActivity(intent);


        }
        else if (id == R.id.suitprint) {
            Intent intent=new Intent(MainActivity.this,SuitPrintRcv.class);
            startActivity(intent);


        }
        else if (id == R.id.Woolens) {
            Intent intent=new Intent(MainActivity.this,WoolenRcv.class);
            startActivity(intent);


        }
        else if (id == R.id.leggings) {
            Intent intent=new Intent(MainActivity.this,LeggingRcv.class);
            startActivity(intent);


        }
        else if (id == R.id.more) {
            Intent intent=new Intent(MainActivity.this,MoreRcv.class);
            startActivity(intent);


        }
        else if (id == R.id.owneruse)
        {




            AlertDialog.Builder alertDialog2 = new AlertDialog.Builder(
                    MainActivity.this);
            final EditText edittext = new EditText(MainActivity.this);
           // edittext.getInputType(InputType.TYPE_CLASS_NUMBER);
            edittext.setHorizontallyScrolling(true);
            edittext.setInputType(InputType.TYPE_CLASS_NUMBER);
// Setting Dialog Title
            alertDialog2.setTitle("This Activity is Only For Owner.");


// Setting Dialog Message
            alertDialog2.setMessage("Type Your Id.");
            alertDialog2.setView(edittext);

// Setting Icon to Dialog
            alertDialog2.setIcon(R.drawable.common_google_signin_btn_icon_dark);

// Setting Positive "Yes" Btn
            alertDialog2.setPositiveButton("YES",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            String YouEditTextValue = edittext.getText().toString();
                            if(YouEditTextValue.equals(data))
                            {
                                Toast.makeText(getApplicationContext(),"Welcome Mrs.Choudhary",Toast.LENGTH_LONG)
                                        .show();
                                Intent intent=new Intent(MainActivity.this,OwnerChoice.class);
                                   startActivity(intent);

                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"Sorry...!!!you are Not the owner ",Toast.LENGTH_SHORT)
                                        .show();
                            }



                        }
                    });

// Setting Negative "NO" Btn
            alertDialog2.setNegativeButton("NO",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // Write your code here to execute after dialog
                            Toast.makeText(getApplicationContext(),
                                    "You clicked on NO", Toast.LENGTH_SHORT)
                                    .show();
                            dialog.cancel();
                        }
                    });

// Showing Alert Dialog
            alertDialog2.show();


        }
        else if (id == R.id.about) {
            Intent intent=new Intent(MainActivity.this,About.class);
            startActivity(intent);


        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }


}
