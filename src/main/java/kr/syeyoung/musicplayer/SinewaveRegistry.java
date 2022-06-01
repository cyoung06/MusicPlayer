package kr.syeyoung.musicplayer;

import org.bukkit.Sound;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SinewaveRegistry {
    public static TreeMap<Double, String> FREQUENCY_SOUND = new TreeMap<>();
    public static Map<String, Double> SOUND_FREQUENCY = new HashMap<>();
    static {
//        register(40.0, "minecraft:sinewaves.40");
//        register(160.0, "minecraft:sinewaves.160");
//        register(640.0, "minecraft:sinewaves.640");
//        register(2560.0, "minecraft:sinewaves.2560");
//        register(10240.0, "minecraft:sinewaves.10240");
        register(92.50,"minecraft:block.note.base");
        register(369.99,"minecraft:block.note.pling");
        register(1479.98,"minecraft:block.note.xylophone");
    }

    private static void register(Double freq, String sound) {
        FREQUENCY_SOUND.put(Math.log(freq), sound);
        SOUND_FREQUENCY.put(sound, freq);
    }

    public static double getFrequency(String s) {
        return SOUND_FREQUENCY.get(s);
    }

    public static String getBestSound(double freq2) {
        double freq = Math.log(freq2);
        Double key2 = FREQUENCY_SOUND.floorKey(freq);
        Double key1 = FREQUENCY_SOUND.ceilingKey(freq);
        if (key2 == null) return FREQUENCY_SOUND.get(key1);
        if (key1 == null) return FREQUENCY_SOUND.get(key2);

        if (key1 + key2 > 2*freq) {
            return FREQUENCY_SOUND.get(key2);
        } else {
            return FREQUENCY_SOUND.get(key1);
        }
    }
}
