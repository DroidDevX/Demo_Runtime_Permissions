package com.example.demo_runtime_permissions;

import android.os.Build;
import android.widget.CompoundButton;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.*;



@RunWith(RobolectricTestRunner.class)
@Config(manifest= Config.NONE,sdk= Build.VERSION_CODES.O_MR1)

public class MainActivityTest {
    CompoundButton switchBtn;
    MainActivity activity;
    @Before
    public void setup(){
        switchBtn = Mockito.mock(CompoundButton.class);
        activity = Robolectric.buildActivity(MainActivity.class).create().get();
    }

    @Test
    public void testSwitchClicked(){
        switchBtn.setId(R.id.switchLocation);
        activity.onCheckedChanged(switchBtn,true);
        activity.onCheckedChanged(switchBtn,false);

        switchBtn.setId(R.id.switchSensor);
        activity.onCheckedChanged(switchBtn,true);
        activity.onCheckedChanged(switchBtn,false);

        switchBtn.setId(R.id.switchContacts);
        activity.onCheckedChanged(switchBtn,true);
        activity.onCheckedChanged(switchBtn,false);

        switchBtn.setId(R.id.switchMircophone);
        activity.onCheckedChanged(switchBtn,true);
        activity.onCheckedChanged(switchBtn,false);

        switchBtn.setId(R.id.switchSMS);
        activity.onCheckedChanged(switchBtn,true);
        activity.onCheckedChanged(switchBtn,false);

        switchBtn.setId(R.id.switchStorage);
        activity.onCheckedChanged(switchBtn,true);
        activity.onCheckedChanged(switchBtn,false);

        switchBtn.setId(R.id.switchTelephone);
        activity.onCheckedChanged(switchBtn,true);
        activity.onCheckedChanged(switchBtn,false);

        switchBtn.setId(R.id.switchCalendar);
        activity.onCheckedChanged(switchBtn,true);
        activity.onCheckedChanged(switchBtn,false);

    }

}