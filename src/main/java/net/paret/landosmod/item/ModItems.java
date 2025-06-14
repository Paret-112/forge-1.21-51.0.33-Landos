package net.paret.landosmod.item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.paret.landosmod.LandosMod;
import net.paret.landosmod.item.custom.FuelItem;
import net.paret.landosmod.item.custom.SawItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LandosMod.MOD_ID);

    public static final RegistryObject<Item>  STEELINGOT = ITEMS.register( "steel_ingot",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item>  STEELUNFIRED = ITEMS.register( "steel_unfired",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> COKE = ITEMS.register("coke",
            () -> new FuelItem(new Item.Properties(), 4800));

    public static final RegistryObject<Item> WOOD_SAW = ITEMS.register("wood_saw",
            () -> new SawItem(new Item.Properties().durability(128)));

    public static final RegistryObject<Item> LINGITE_BLUE = ITEMS.register("lingite_gem_blue",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> LINGITE_GREEN = ITEMS.register("lingite_gem_green",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> LINGITE_PURPLE = ITEMS.register("lingite_gem_purple",
            () -> new Item(new Item.Properties().rarity(Rarity.EPIC)));

    public static final RegistryObject<Item> BEAN = ITEMS.register("bean",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BEAN)));
    public static final RegistryObject<Item> BEAN_SOUP = ITEMS.register("bean_soup",
            () -> new Item(new Item.Properties().food(ModFoodProperties.BEAN_SOUP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
