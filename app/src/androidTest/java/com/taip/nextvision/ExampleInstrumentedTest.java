package com.taip.nextvision;

import android.content.Context;
import android.util.Log;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.taip.nextvision.AlarmEngine.AlarmEngine;
import com.taip.nextvision.BatteryEngine.BatteryEngine;
import com.taip.nextvision.TelephonyEngine.CallEngine;
import com.taip.nextvision.TelephonyEngine.SMSEngine;
import com.taip.nextvision.TimeEngine.DateEngine;
import com.taip.nextvision.TimeEngine.TimeEngine;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.taip.nextvision", appContext.getPackageName());
    }

    @Test
    public void call_number() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        CallEngine callEngine = new CallEngine(context);
        callEngine.execute("create");
        String number = callEngine.execute("call");
        assertEquals("07777777", number);
    }

    @Test
    public void call_number_fail() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        CallEngine callEngine = new CallEngine(context);
        String number = callEngine.execute("call");
        assertEquals("07777777", number);
    }

    @Test
    public void check_battery_percentage() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        BatteryEngine batteryEngine = new BatteryEngine(context);
        String percent = batteryEngine.execute("battery");
        assertEquals("100", percent);
    }

    @Test
    public void check_is_charging() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        BatteryEngine batteryEngine = new BatteryEngine(context);
        String charge = batteryEngine.execute("charge");
        Log.d("HELLO!!",charge);
        assertEquals("No", charge);
    }

    @Test
    public void check_if_battery_is_saving_mode() {
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        BatteryEngine batteryEngine = new BatteryEngine(context);
        String saveMode = batteryEngine.execute("CheckBatteryMode");
        Log.d("HELLO SAVE MODE!!", saveMode);
        assertEquals("No", saveMode);
    }

    @Test
    public void check_set_alarm() {
        Date currentTime = Calendar.getInstance().getTime();
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AlarmEngine alarmEngine = new AlarmEngine(context);
        String setAlarm = alarmEngine.execute("set alarm", currentTime);
        Log.d("HELLO SET ALARM!!", setAlarm);
        assertEquals("Created", setAlarm);
    }

    @Test
    public void check_delete_alarm() {
        Date currentTime = Calendar.getInstance().getTime();
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        AlarmEngine alarmEngine = new AlarmEngine(context);
        String setAlarm = alarmEngine.execute("delete alarm", currentTime);
        Log.d("HELLO DELETE ALARM!!", setAlarm);
        assertEquals("Deleted", setAlarm);
    }
    @Test
    public void testDate() {
        //andra
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DateEngine dateEngine = new DateEngine(context);
        String testCmd = "data";
        String date = dateEngine.execute(testCmd);
        System.out.print(date);
        assertEquals("22/12/2020", date);
    }

    @Test
    public void testDateFail() {
        //andra
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        DateEngine dateEngine = new DateEngine(context);
        String date = dateEngine.execute("");
        assertEquals("10/11/2021", date);
    }

    @Test
    //andra
    public void testTime(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        TimeEngine timeEngine = new TimeEngine(context);
        String date = timeEngine.execute("timp");
        assertEquals("15:43", date);
    }

    @Test
    public void SMSEngine_newSms_Test(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String lastSmsIndex;

        SMSEngine smsEngine = new SMSEngine(context);

        lastSmsIndex = smsEngine.execute("new sms");
        assertEquals(lastSmsIndex, "1");
        lastSmsIndex = smsEngine.execute("new sms");
        assertEquals(lastSmsIndex, "2");
    }

    @Test
    public void SMSEngine_newSms_Test_Fail(){
        Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
        String lastSmsIndex;

        SMSEngine smsEngine = new SMSEngine(context);

        lastSmsIndex = smsEngine.execute("new sms");
        assertEquals(lastSmsIndex, "5");
    }
}