package net.paret.landosmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.paret.landosmod.LandosMod;
import net.paret.landosmod.block.custom.AltarBlock;
import net.paret.landosmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, LandosMod.MOD_ID);

    public static final RegistryObject<Block> STEEL_BLOCK = registerBlock("steel_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .sound(SoundType.STONE).strength(2f, 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LINGITE_BLUE = registerBlock("lingite_ore_blue",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .sound(SoundType.STONE).strength(2f, 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LINGITE_GREEN = registerBlock("lingite_ore_green",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .sound(SoundType.STONE).strength(2f, 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> LINGITE_PURPLE = registerBlock("lingite_ore_purple",
            () -> new DropExperienceBlock(UniformInt.of(2, 4), BlockBehaviour.Properties.of()
                    .sound(SoundType.STONE).strength(2f, 4).requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ALTAR_BLOCK = registerBlock("altar_block",
            () -> new AltarBlock(BlockBehaviour.Properties.of().strength(1f)));

    public static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    public static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
