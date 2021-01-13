package com.taip.nextvision;

import android.content.Context;
import android.widget.Toast;

import com.taip.nextvision.AlarmEngine.AlarmEngine;
import com.taip.nextvision.BatteryEngine.BatteryEngine;
import com.taip.nextvision.HelpEngine.HelpEngine;
import com.taip.nextvision.NotesEngine.NotesEngine;
import com.taip.nextvision.TelephonyEngine.CallEngine;
import com.taip.nextvision.TelephonyEngine.SMSEngine;
import com.taip.nextvision.TimeEngine.DateEngine;
import com.taip.nextvision.TimeEngine.TimeEngine;
import com.taip.nextvision.directions.DirectionsEngine;

import java.text.Normalizer;

public class CommandDispatcher {
    Context context;

    public CommandDispatcher(Context main) {
        context = main;
    }

    public void dispatch(String command) {
        try {
            Buttons.deactivateAll();

            String cmd = Normalizer.normalize(command, Normalizer.Form.NFD)
                    .replaceAll("\\p{InCombiningDiacriticalMarks}+", "").toLowerCase();
//            Toast.makeText(context, cmd, Toast.LENGTH_LONG).show();

            CommandEngine commandEngine = new CommandEngine() {
                @Override
                public String execute(String cmd) {
                    return "Nu am înțeles comanda";
                }
            };

            if (cmd.startsWith("suna ") || cmd.startsWith("apeleaza ") || cmd.startsWith("creeaza ") || cmd.startsWith("adauga ") || cmd.startsWith("salveaza ")) {
                commandEngine = new CallEngine(context);
            } else if (cmd.startsWith("ajutor")) {
                commandEngine = new HelpEngine(context);
            } else if (cmd.contains("gaseste sms")) {
                commandEngine = new SMSEngine(context);
            } else if (cmd.contains("citeste ultimul sms")) {
                commandEngine = new SMSEngine(context);
            } else if (cmd.contains("sms nou")) {
                commandEngine = new SMSEngine(context);
            } else if (cmd.equals("directii")) {
                commandEngine = new DirectionsEngine(context);
            } else if (cmd.equals("de la")) {
                commandEngine = new DirectionsEngine(context);
            } else if (cmd.equals("pana la")) {
                commandEngine = new DirectionsEngine(context);
            } else if (cmd.equals("data")) {
                commandEngine = new DateEngine(context);
            } else if (cmd.equals("timp") || cmd.equals(("ora"))) {
                commandEngine = new TimeEngine(context);
            } else if (cmd.equals("baterie")) {
                commandEngine = new BatteryEngine(context);
            } else if (cmd.equals("incarca")) {
                commandEngine = new BatteryEngine(context);
            } else if (cmd.equals("mod economisire")) {
                commandEngine = new BatteryEngine(context);
            } else if (cmd.contains("seteaza alarma")) {
                commandEngine = new AlarmEngine(context);
            } else if (cmd.contains("sterge alarma")) {
                commandEngine = new AlarmEngine(context);
            } else if (cmd.contains("exista alarma")) {
                commandEngine = new AlarmEngine(context);
            } else if (cmd.contains("nota noua")) {
                commandEngine = new NotesEngine(context);
            } else if (cmd.contains("citeste note")) {
                commandEngine = new NotesEngine(context);
            } else if (cmd.contains("citeste ultima nota")) {
                commandEngine = new NotesEngine(context);
            } else if (cmd.contains("sterge ultima nota")) {
                commandEngine = new NotesEngine(context);
            }
            String result = commandEngine.execute(cmd);
            Toast.makeText(context, result, Toast.LENGTH_SHORT).show();
            SpeechEngine.textToSpeech(result);

        } catch (Error err) {
            Toast.makeText(context, err.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
