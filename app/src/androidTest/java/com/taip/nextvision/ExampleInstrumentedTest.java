package com.taip.nextvision;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.taip.nextvision.TelephonyEngine.CallEngine;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

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
}