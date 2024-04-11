package de.lcraft.lapi.mcReflection.bukkit;

public class VersionControl implements de.lcraft.lapi.mcReflection.api.VersionControl {

    @Override
    public Versions getMcVersion(String versionName) {
        String nmsVersion = versionName.split("\\.")[3];
        for(Versions version : Versions.values()) {
            if(version.getNMSVersion().equalsIgnoreCase(nmsVersion)) return version;
        }
        return Versions.values()[Versions.values().length - 1];
    }
    @Override
    public Class getClassWithVersion(String classPrefix, String classSuffix, Versions version) {
        String classPath = classPrefix + "." + version.getNMSVersion() + "." + classSuffix;
        try {
            return Class.forName(classPath);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
