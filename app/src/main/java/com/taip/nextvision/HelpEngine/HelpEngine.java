package com.taip.nextvision.HelpEngine;

import android.content.Context;

import com.taip.nextvision.CommandEngine;

public class HelpEngine implements CommandEngine {
    private Context context;

    public HelpEngine (Context context) {
        this.context = context;
    }

    @Override
    public String execute(String cmd) {
        String[] splitStr;
        splitStr = cmd.split(" ", 2);

        if(splitStr.length == 1) {
            return this.mainCommands();
        }
        else if(splitStr.length == 2) {
            return this.functionCommands(splitStr[1]);
        }
        else {
            return this.specificCommands(splitStr[1]);
        }
    }

    private String mainCommands() {
        String actionResult;

        actionResult = "Pentru a afla comenzile disponibile pentru o anumita functie, folositi comanda " +
                "ajutor, urmata de una din urmatoarele: apel, sms, directii, note, alarma sau utile";

        return actionResult;
    }

    private String functionCommands(String function) {
        String actionResult;

        if(function.equals("apel")) {
            actionResult = "Functiile disponibile pentru apel sunt suna si creeaza.";
        } else if(function.equals("sms")) {
            actionResult = "Functiile disponibile pentru sms sunt gaseste sms, citeste ultimul sms si sms nou.";
        } else if(function.equals("directii")) {
            actionResult = "Functiile disponibile pentru directii sunt directii.";
        } else if(function.equals("note")) {
            actionResult = "Functiile disponibile pentru note sunt nota noua, citeste ultima nota, sterga ultima nota si citeste note.";
        } else if(function.equals("alarma")) {
            actionResult = "Functiile disponibile pentru alarma sunt seteaza alarma, sterge alarma si exista alarma.";
        } else if(function.equals("utile")) {
            actionResult = "Functiile disponibile pentru utile sunt data, timp, baterie, incarca si mod economisire.";
        } else {
            actionResult = specificCommands(function);
        }

        return actionResult;
    }

    private String specificCommands(String command) {
        String actionResult;

        if(command.equals("sms nou")) {
            actionResult = "Comanda sms nou creeaza si trimite un sms. Pentru aceasta, folositi " +
                    "comanda sms nou urmata de numele destinatarului si apoi mesajul.";
        } else if(command.equals("citeste ultimul sms")) {
            actionResult = "Comanda citeste ultimul sms va citi expeditorul si textul ultimului sms primit.";
        } else if(command.equals("gaseste sms")) {
            actionResult = "Comanda gaseste sms va citi ultimul sms primit de la un anumit contact. " +
                    "Pentru aceasta, folositi comanda gaseste sms urmata de numele contactului.";
        } else if(command.equals("data")) {
            actionResult = "Comanda data va citi data actuala completa.";
        } else if(command.equals("timp")) {
            actionResult = "Comanda timp va citi ora actuala completa.";
        } else if(command.equals("baterie")) {
            actionResult = "Comanda baterie va citi procentul actual al bateriei.";
        } else if(command.equals("incarca")) {
            actionResult = "Comanda incarca va spune daca dispozitivul este la incarcat.";
        } else if(command.equals("mod economisire")) {
            actionResult = "Comanda mod economisire va spune daca dispozitivul se afla in modul economisire.";
        } else {
            actionResult = "Comanda nu exista.";
        }

        return actionResult;
    }
}
