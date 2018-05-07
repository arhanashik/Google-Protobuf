package com.blackspider.googleprotobuf.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.blackspider.googleprotobuf.AddressBookProtos;
import com.blackspider.googleprotobuf.R;
import com.google.protobuf.InvalidProtocolBufferException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        textView = findViewById(R.id.text_view);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // You can deserialize Person bytes
                try {
                    AddressBookProtos.AddressBook myAddressBook = AddressBookProtos.AddressBook
                            .parseFrom(checkProtobuf());
                    AddressBookProtos.Person person = myAddressBook.getPeople(0);
                    List<AddressBookProtos.Person.PhoneNumber> phoneNumbers = person.getPhonesList();
                    String txt = "Total: " + myAddressBook.getPeopleCount()
                            + "\nName: " + person.getName()
                            + "\nEmail: " + person.getEmail()
                            + "\nPhone home: " + phoneNumbers.get(0).getNumber()
                            + "\nPhone mobile: " + phoneNumbers.get(1).getNumber();
                    textView.setText(txt);
                } catch (InvalidProtocolBufferException e) {
                    e.printStackTrace();
                }
            }
        });

        //Intent i = new Intent(IPersonService.class.getName());
        //bindService(i, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    private byte[] checkProtobuf(){
        // building PhoneNumber objects
        AddressBookProtos.Person.PhoneNumber phoneHome = AddressBookProtos.Person.PhoneNumber.newBuilder()
                .setNumber("+49123456")
                .setType(AddressBookProtos.Person.PhoneType.HOME)
                .build();
        AddressBookProtos.Person.PhoneNumber phoneMobile = AddressBookProtos.Person.PhoneNumber.newBuilder()
                .setNumber("+49654321")
                .setType(AddressBookProtos.Person.PhoneType.MOBILE)
                .build();

        List<AddressBookProtos.Person.PhoneNumber> allPhone = new ArrayList<>();
        allPhone.add(phoneHome);
        allPhone.add(phoneMobile);

        // building a Person object using phone data
        AddressBookProtos.Person person = AddressBookProtos.Person.newBuilder()
                .setId(1)
                .setName("Mohsen")
                .setEmail("info@mohsenoid.com")
                .addAllPhones(allPhone)
                .build();

        List<AddressBookProtos.Person> allPerson = new ArrayList<>();
        allPerson.add(person);

        // building an Person object using person data
        AddressBookProtos.AddressBook addressBook = AddressBookProtos.AddressBook.newBuilder()
                .addAllPeople(allPerson)
                .build();

        // finally this is how you get serialized ByteArray
        return addressBook.toByteArray();
    }
}
