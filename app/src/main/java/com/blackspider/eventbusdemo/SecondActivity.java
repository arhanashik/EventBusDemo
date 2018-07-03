package com.blackspider.eventbusdemo;
/*
 *  ****************************************************************************
 *  * Created by : Arhan Ashik on 7/3/2018 at 11:46 AM.
 *  * Email : ashik.pstu.cse@gmail.com
 *  *
 *  * Last edited by : Arhan Ashik on 7/3/2018.
 *  *
 *  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
 *  ****************************************************************************
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    @Override
    protected void onStart() {
        super.onStart();
        GlobalBus.getBus().register(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void getMessage(Events.ActivityActivityMessage activityActivityMessage) {
        TextView messageView = (TextView) findViewById(R.id.messageReceived);
        String message = getString(R.string.message_received) + " " + activityActivityMessage.getMessage();
        messageView.setText(message);

        Toast.makeText(getApplicationContext(),
                getString(R.string.message_second_activity) + " " + activityActivityMessage.getMessage(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        GlobalBus.getBus().unregister(this);
    }

    private void getStickyEvent(){
        Events.ActivityActivityMessage stickyEvent = GlobalBus.getBus().getStickyEvent(Events.ActivityActivityMessage.class);
        //Check if stickyEvent is actually posted or not.
        if(stickyEvent != null) {
            //Perform some actions.
        }
    }

    private void removeStickyEvent(){
        Events.ActivityActivityMessage stickyEvent =  GlobalBus.getBus().removeStickyEvent(Events.ActivityActivityMessage.class);
        //Check if stickyEvent is actually posted or not.
        if(stickyEvent != null) {
            //Perform some actions.
        }
    }

    private void cancelEventDelivery(Object event){
        EventBus.getDefault().cancelEventDelivery(event);
    }
}

