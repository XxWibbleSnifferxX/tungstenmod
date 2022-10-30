package net.xxwibblesnifferxx.tungstenmod.sound;

import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.xxwibblesnifferxx.tungstenmod.TungstenMod;

public class ModSounds
{
    public static SoundEvent ROSS_BANGER_1 = registerSoundEvent("ross_banger_1");
    public static SoundEvent ROSS_BANGER_2 = registerSoundEvent("ross_banger_2");
    public static SoundEvent ROSS_BANGER_3 = registerSoundEvent("ross_banger_3");
    private static SoundEvent registerSoundEvent(String name)
    {
        Identifier id = new Identifier(TungstenMod.MOD_ID, name);
        return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
    }
}
