package net.paret.landosmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.paret.landosmod.LandosMod;
import net.paret.landosmod.block.ModBlocks;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LandosMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> LANDOSMOD_TAB = CREATIVE_MODE_TABS.register("landos_mod_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.LINGITE_BLUE.get()))
                    .title(Component.translatable("creativetab.landosmod.landosmodtab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.LINGITE_BLUE.get());
                        output.accept(ModItems.LINGITE_GREEN.get());
                        output.accept(ModItems.LINGITE_PURPLE.get());

                        output.accept(ModBlocks.LINGITE_BLUE.get());
                        output.accept(ModBlocks.LINGITE_GREEN.get());
                        output.accept(ModBlocks.LINGITE_PURPLE.get());

                        output.accept(ModBlocks.ALTAR_BLOCK.get());
                    }).build()
    );


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
