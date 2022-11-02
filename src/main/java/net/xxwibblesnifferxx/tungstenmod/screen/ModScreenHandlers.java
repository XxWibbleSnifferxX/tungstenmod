package net.xxwibblesnifferxx.tungstenmod.screen;

import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.xxwibblesnifferxx.tungstenmod.TungstenMod;
import net.xxwibblesnifferxx.tungstenmod.screen.slot.CrucibleScreenHandler;

public class ModScreenHandlers
{
    public static ScreenHandlerType<CrucibleScreenHandler> CRUCIBLE_SCREEN_HANDLER =
            Registry.register(
                    Registry.SCREEN_HANDLER,
                    new Identifier(TungstenMod.MOD_ID, "crucible"),
                    new ScreenHandlerType<>(CrucibleScreenHandler::new)
            );
}
