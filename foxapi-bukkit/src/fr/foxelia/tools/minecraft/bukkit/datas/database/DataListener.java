package fr.foxelia.tools.minecraft.bukkit.datas.database;

public interface DataListener<T> {
    void onDataChanged(String key, T oldValue, T newValue);
}
