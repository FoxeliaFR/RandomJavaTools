package fr.foxelia.tools.minecraft.bukkit.datas.database;

public interface DataUpdateListener<T> {
    void onUpdate(T instance, String fieldName, Object oldVal, Object newVal);
}
