package de.lcraft.lapi.configurationSystem.yaml;

import de.lcraft.lapi.javaUtils.ListArrayUtils;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BiConsumer;

public class ConfigTest {

    @Test
    public void testBasicConfigFunctions() throws Exception {
        File folder = new File("tests/yaml/testConfig0");
        folder.deleteOnExit();
        folder.mkdirs();

        File configFile = new File(folder, "testConfig.yml");
        File reConfigFile = new File(folder, "reConfig.yml");
        Config config = new Config();
        config.set("server", "Lcraft Networks");
        config.set("server.domain", "lcraft.de");
        config.set("server.msg", 4);
        config.set("server.msg.prefix", "Lcraft");
        config.set("server.msg.noHelp", "You cant get help!");
        config.set("server.msg.noPerm", "You have no permissions!");
        config.set("server.msg.notAuser", "Youre not user");
        config.set("server.maxPlayers", 5);

        config.addComments("server", "This is the first comment.", "Yuhu!");
        config.addComments("server.msg.prefix", "Here you can edit the prefix");
        config.addComments("server.maxPlayers", "Maybe try infinite? (Spoiler: It will not work)");
        config.addComments("server.msg", "How many messages?");
        config.save(configFile);

        config.load(configFile);
        if(!isRight(config, "server", "Lcraft Networks")) throw new Exception("ConfigurationSystem Error 1");
        if(!isRight(config, "server.domain", "lcraft.de")) throw new Exception("ConfigurationSystem Error 2");
        if(!isRight(config, "server.msg", 4)) throw new Exception("ConfigurationSystem Error 3");
        if(!isRight(config, "server.msg.prefix", "Lcraft")) throw new Exception("ConfigurationSystem Error 4");
        if(!isRight(config, "server.msg.noHelp", "You cant get help!")) throw new Exception("ConfigurationSystem Error 5");
        if(!isRight(config, "server.msg.noPerm", "You have no permissions!")) throw new Exception("ConfigurationSystem Error 6");
        if(!isRight(config, "server.msg.notAuser", "Youre not user")) throw new Exception("ConfigurationSystem Error 7");
        if(!isRight(config, "server.maxPlayers", 5)) throw new Exception("ConfigurationSystem Error 8");
        config.save(reConfigFile);
    }

    @Test
    public void testAdvancedConfigFunctions() throws Exception {
        File folder = new File("tests/yaml/testConfig1");
        folder.deleteOnExit();
        folder.mkdirs();

        // Save and global set methods
        File configFile = new File(folder, "testConfig.yml");
        File reConfigFile = new File(folder, "reConfig.yml");
        Config config = new Config();
        config.set("server", "Lcraft Networks");
        config.set("server.domain", "lcraft.de");
        config.set("server.msg", 4);
        config.set("server.msg.prefix", "Lcraft");
        config.set("server.msg.noHelp", "You cant get help!");
        config.set("server.msg.noPerm", "You have no permissions!");
        config.set("server.msg.notAuser", "Youre not user");
        config.set("server.maxPlayers", 5);
        config.setArray("server.players", new Object[] {"eindeutigLPD", "Cooler_LK", "YouTuber"});
        config.setList("server.socialMedia", ListArrayUtils.makeStringArrayToList("YouTube", "Twitch", "Twitter", "Amazon"));
        config.setMap("server.points", new HashMap<>() {{put("eindeutigLPD", 100); put("Cooler_LK", 250); put("Youtuber", 560); }});
        config.setSet("server.updates", new HashSet<>() {{ add("1.12"); add("1.13"); add("1.14"); add("1.15"); add("1.16"); }});

        config.addComments("server", "This is the first comment.", "Yuhu!");
        config.addComments("server.msg.prefix", "Here you can edit the prefix");
        config.addComments("server.maxPlayers", "Maybe try infinite? (Spoiler: It will not work)");
        config.addComments("server.msg", "How many messages?");
        config.addComments("server.updates", "Here you can see all updates of Minecraft");
        config.save(configFile);

        // Load and global load methods
        config.load(configFile);
        if(!isRight(config, "server", "Lcraft Networks")) throw new Exception("ConfigurationSystem Error 9");
        if(!isRight(config, "server.domain", "lcraft.de")) throw new Exception("ConfigurationSystem Error 10");
        if(!isRight(config, "server.msg", 4)) throw new Exception("ConfigurationSystem Error 11");
        if(!isRight(config, "server.msg.prefix", "Lcraft")) throw new Exception("ConfigurationSystem Error 12");
        if(!isRight(config, "server.msg.noHelp", "You cant get help!")) throw new Exception("ConfigurationSystem Error 13");
        if(!isRight(config, "server.msg.noPerm", "You have no permissions!")) throw new Exception("ConfigurationSystem Error 14");
        if(!isRight(config, "server.msg.notAuser", "Youre not user")) throw new Exception("ConfigurationSystem Error 15");
        if(!isRight(config, "server.maxPlayers", 5)) throw new Exception("ConfigurationSystem Error 16");
        if(!config.getList("server.socialMedia").equals(ListArrayUtils.makeStringArrayToList("YouTube", "Twitch", "Twitter", "Amazon"))) throw new Exception("ConfigurationSystem Error 17");
        if(!areEqualKeyValues(new HashMap<>() {{put("eindeutigLPD", 100); put("Cooler_LK", 250); put("Youtuber", 560); }}, config.getMap("server.points"))) throw new Exception("ConfigurationSystem Error 18");
        if(!config.getSet("server.updates").equals(new HashSet<>() {{ add("1.12"); add("1.13"); add("1.14"); add("1.15"); add("1.16"); }})) throw new Exception("ConfigurationSystem Error 19");
        config.save(reConfigFile);
    }

    @Test
    public void testAllConfigFunctions() throws Exception {
        Config config = new Config();
        File folder = new File("tests/yaml/testConfig2");
        File configFile = new File(folder, "testConfig.yml");
        File reConfigFile = new File(folder, "reConfig.yml");

        folder.deleteOnExit();
        folder.mkdirs();
    }

    private Boolean isRight(Config cfg, String root, Object obj) {
        if(obj instanceof String) return cfg.get(root).equals(obj);
        if(obj instanceof Integer) return cfg.getInt(root).equals(obj);
        return false;
    }
    private Boolean areEqualKeyValues(Map<?, ?> first, Map<?, ?> second) {
        AtomicReference<Boolean> equal = new AtomicReference<>(true);
        first.forEach((BiConsumer<Object, Object>) (o, o2) -> {
            if(!second.get(o).toString().equals(o2.toString())) equal.set(false);
        });
        return equal.get();
    }

}
