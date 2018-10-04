package com.example.haiba.androidlabs;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends Activity {

    protected static final String ACTIVITY_NAME = "ChatWindow";
    ListView list;
    EditText edit;
    Button send;
    ArrayList<String> chatMessage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);

        list = (ListView)findViewById(R.id.list);
        edit = (EditText)findViewById(R.id.editText);
        send = (Button)findViewById(R.id.send);
        chatMessage = new ArrayList<String>();
        final ChatAdapter messageAdapter = new ChatAdapter(this);
        list.setAdapter(messageAdapter);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatMessage.add(edit.getText().toString());
                messageAdapter.notifyDataSetChanged();
                edit.setText("");
            }
        });
    }

    private class ChatAdapter extends ArrayAdapter<String>{
        public ChatAdapter(Context ctx){
            super(ctx,0);
        }

        public int getCount(){
            return chatMessage.size();
        }

        public String getItem(int position){
            return chatMessage.get(position);
        }

        public View getView(int position,View convertView,ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null;
            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming,null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing,null);

            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(getItem(position));
            return result;
        }

        public long getItemId(int position){
            return position;
        }
    }
}
