package com.claudiobo.template.utils;

import com.claudiobo.template.Main;

public class Lang {

    public static String prefijo;

    public static void loadLang() {
        Main main = Main.getInstance();
        prefijo = Utils.color(main.getConfig().getString("mensajes.prefijo"));
    }
}
