package com.claudiobo.template.tasks;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class AutoUpdaterTask extends BukkitRunnable {

    JavaPlugin main;

    boolean gotUpdate;
    String pluginName;
    String currentVersion;
    String currentJarName;
    String updaterPassword;

    public AutoUpdaterTask(JavaPlugin main) {
        this.main = main;
        gotUpdate = false;
        pluginName = main.getDescription().getName().toLowerCase();
        currentVersion = main.getDescription().getVersion();
        currentJarName = new File(main.getClass().getProtectionDomain().getCodeSource().getLocation().getFile()).getName();
        updaterPassword = main.getConfig().getString("autoupdater-password");
        if (updaterPassword == null || updaterPassword.length() <= 4) {
            gotUpdate = true;
        }
    }

    @Override
    public void run() {
        if (gotUpdate) {
            cancel();
            return;
        }
        doCheck(true);
    }

    public void doCheck(boolean silent) {
        String latestVersion = checkIfUpToDate();
        if (latestVersion == null) {
            main.getLogger().info("No se pudo comprobar si hay alguna actualizacion");
            return;
        } else if (!currentVersion.equalsIgnoreCase(latestVersion)) {
            cancel();
            gotUpdate = true;
            if (downloadLatestVersion()) {
                main.getLogger().info("Se ha actualizado el plugin de la version '" + currentVersion + "' a '" + latestVersion + "'");
                main.getLogger().info("Hara efecto cuando se reinicie el servidor");
            } else if (!silent) {
                main.getLogger().info("No se pudo comprobar/descargar la ultima version");
            }
        } else if(!silent){
            main.getLogger().info("Ya esta en la ultima version");
        }
    }

    public String checkIfUpToDate() {
        URL url = null;
        try {
            url = new URL("https://api.claudiobo.com/updater/" + pluginName);
        } catch (MalformedURLException e) {
            // e.printStackTrace();
            return null;
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                try {
                    String receivedString = "";
                    try (InputStream is = connection.getInputStream()) {
                        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
                            String text;
                            while ((text = reader.readLine()) != null) {
                                receivedString += text;
                            }
                        } catch (Exception e) {
                            return null;
                        }
                    } catch (Exception e) {
                        return null;
                    }
                    JSONObject obj = (JSONObject) new JSONParser().parse(receivedString);
                    String latestVersion = (String) obj.get("latest_version");
                    return latestVersion;
                } catch (Exception e) {
                    return null;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public boolean downloadLatestVersion() {

        try {
            main.getServer().getUpdateFolderFile().mkdir();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        URL url = null;
        try {
            url = new URL("https://api.claudiobo.com/updater/" + pluginName + "/" + updaterPassword);
        } catch (MalformedURLException e) {
            // e.printStackTrace();
            return false;
        }

        try {
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setDoInput(true);
            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                File saveJarTo = new File(main.getServer().getUpdateFolderFile() + "/" + currentJarName);
                try (InputStream inputStream = connection.getInputStream()) {
                    try (FileOutputStream outputStream = new FileOutputStream(saveJarTo)) {
                        int bytesRead = -1;
                        byte[] buffer = new byte[4096];
                        while ((bytesRead = inputStream.read(buffer)) != -1) {
                            outputStream.write(buffer, 0, bytesRead);
                        }
                        return true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }



}
