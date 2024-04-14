package de.lcraft.lapi.configurationSystem.yaml;

import org.junit.jupiter.api.Test;

import java.io.File;

public class ConfigTest {

    @Test
    public void testConfig() throws Exception {
        File folder = new File("tests/configurationSystem/yaml/testConfig");
        folder.deleteOnExit();
        folder.mkdirs();

        // Big set, get, save, load, system
        File configFile = new File(folder, "testConfig_1");
        Config config = new Config();
        config.set("server", "Lcraft Networks");
        config.set("server.domain", "lcraft.de");
        config.set("server.msg", 4);
        config.set("server.msg.prefix", "Lcraft");
        config.set("server.msg.noHelp", "You cant get help!");
        config.set("server.msg.noPerm", "You have no permissions!");
        config.set("server.msg.notAuser", "Youre not user");
        config.set("server.maxPlayers", 5);
        config.save(configFile);

        config.load(configFile);
        if(!config.get("server").equals("Lcraft Networks")) throw new Exception("Configurationsystem Error 1");
        if(!config.get("server.domain").equals("lcraft.de")) throw new Exception("Configurationsystem Error 2");
        if(Integer.parseInt(config.get("server.msg").toString()) != 4) throw new Exception("Configurationsystem Error 3");
        if(!config.get("server.msg.prefix").equals("Lcraft")) throw new Exception("Configurationsystem Error 4");
        if(!config.get("server.msg.noHelp").equals("You cant get help!")) throw new Exception("Configurationsystem Error 5");
        if(!config.get("server.msg.noPerm").equals("You have no permissions!")) throw new Exception("Configurationsystem Error 6");
        if(!config.get("server.msg.notAuser").equals("Youre not user")) throw new Exception("Configurationsystem Error 7");
        if(Integer.parseInt(config.get("server.maxPlayers").toString()) != 5) throw new Exception("Configurationsystem Error 8");
    }

}
