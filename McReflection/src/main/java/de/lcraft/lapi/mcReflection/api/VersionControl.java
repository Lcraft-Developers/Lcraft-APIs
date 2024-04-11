package de.lcraft.lapi.mcReflection.api;

import de.lcraft.lapi.mcReflection.bukkit.Versions;

public interface VersionControl {

    Versions getMcVersion(String versionName);
    Class getClassWithVersion(String classPrefix, String classSuffix, Versions version);

}
