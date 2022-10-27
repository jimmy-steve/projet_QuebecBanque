package com.example.projet_quebecbanque.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class Utilitaire {
    static private LocalDate today;

    public static long creationsID() {
        long first16 = (long) (Math.random() * 10000000000000000L);
        return first16;
    }

    public static  String setDateExpiration() {

        ZoneId zonedId = ZoneId.of("America/Montreal");
        today = LocalDate.now(zonedId);
        //trouverDateJour(today);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        try {
            //Définir la date
            cal.setTime(sdf.parse(String.valueOf(today)));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //Nombre de jours à ajouter
        cal.add(Calendar.DAY_OF_MONTH, 1826);
        //Date après avoir ajouté les jours à la date indiquée
        String d2 = sdf.format(cal.getTime());
        System.out.println("Date après l'addition: " + d2);
        today = LocalDate.parse(d2);
        System.out.println("Voici la date today " + today);

        int mois = Integer.parseInt(today.toString().substring(5, 7));
        int annnee = Integer.parseInt(today.toString().substring(2, 4));

        return mois + "/" + annnee;
    }
}
