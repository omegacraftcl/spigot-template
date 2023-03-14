package com.claudiobo.template.tasks;

import org.bukkit.scheduler.BukkitRunnable;
import com.claudiobo.template.Main;
import com.claudiobo.template.utils.RunAsync;
import lombok.Setter;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.exceptions.JedisConnectionException;

public class RedisTask {

    Main main;
    Jedis subscriber;
    @Setter
    boolean die;

    public RedisTask(Main main) {
        this.main = main;
        start();
    }

    public void start() {
        if (!main.getRedisConnection().isConnected()) {
            main.getRedisConnection().connectDatabase();
        }
        if (subscriber != null) {
            unsubscribe(false);
        }
        new RunAsync(main, () -> {
            subscribe();
        });
    }

    public void subscribe() {
        try {
            subscriber = main.getRedisConnection().getResource();
            subscriber.subscribe(new JedisPubSub() {
                @Override
                public void onMessage(String channel, String message) {
                    if (!message.contains(":")) {
                        main.getLogger().warning("Unexpected message from sync: " + message);
                        return;
                    }
                    String[] message_splitted = message.split(":");
                    String command = message_splitted[0];
                    switch (command) {
                        case "gotNumber": {
                            try {
                                int additional = Integer.valueOf(message_splitted[1]);
                                // do something
                            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                                main.getLogger().warning("Invalid id from sync: " + message);
                                return;
                            } catch (Exception e) {
                                main.getLogger().warning("Exception on method: " + message);
                                e.printStackTrace();
                                return;
                            }
                            break;
                        }
                        case "gotString": {
                            try {
                                int additional = Integer.valueOf(message_splitted[1]);
                                // do something
                            } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                                main.getLogger().warning("Invalid id from sync: " + message);
                                return;
                            } catch (Exception e) {
                                main.getLogger().warning("Exception on method: " + message);
                                e.printStackTrace();
                                return;
                            }
                            break;
                        }
                        default: {
                            main.getLogger().warning("Unexpected message from sync: " + message);
                            break;
                        }
                    }
                }
            }, "kreport.sync");
        } catch (JedisConnectionException e) {
            // TODO: handle exception
            if (die) {
                return;
            }
            main.getLogger().warning("Lost connection to Redis, retrying connection in 3 seconds...");
            new BukkitRunnable() {
                @Override
                public void run() {
                    start();
                }
            }.runTaskLaterAsynchronously(main, 20L * 3L);
        } catch (ClassCastException e) {
            // TODO: handle exception
        }
    }

    public void unsubscribe(boolean die) {
        try {
            setDie(die);
            subscriber.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
