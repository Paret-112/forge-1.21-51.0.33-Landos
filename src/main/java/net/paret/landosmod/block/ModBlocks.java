package net.paret.landosmod.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.WoodType;
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
    public static final RegistryObject<Block> JUNIPER_PLANKS = registerBlock("juniper_planks",
            () -> new Block(BlockBehaviour.Properties.of().strength(1f)){
                @Override
                public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return true;
                }

                @Override
                public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 5;
                }

                @Override
                public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
                    return 20;
                }
            });
    public static final RegistryObject<StairBlock> JUNIPER_STAIRS = registerBlock("juniper_stairs",
            () -> new StairBlock(ModBlocks.JUNIPER_PLANKS.get().defaultBlockState(),
                    BlockBehaviour.Properties.ofFullCopy(ModBlocks.JUNIPER_PLANKS.get())));
    public static final RegistryObject<SlabBlock> JUNIPER_SLAB = registerBlock("juniper_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.JUNIPER_PLANKS.get())));

    public static final RegistryObject<PressurePlateBlock> JUNIPER_PRESSURE_PLATE = registerBlock("juniper_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(ModBlocks.JUNIPER_PLANKS.get())));
    public static final RegistryObject<ButtonBlock> JUNIPER_BUTTON = registerBlock("juniper_button",
            () -> new ButtonBlock(BlockSetType.OAK, 10, BlockBehaviour.Properties.ofFullCopy(ModBlocks.JUNIPER_PLANKS.get()).noCollission()));

    public static final RegistryObject<FenceBlock> JUNIPER_FENCE = registerBlock("juniper_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.JUNIPER_PLANKS.get())));
    public static final RegistryObject<FenceGateBlock> JUNIPER_FENCE_GATE = registerBlock("juniper_fence_gate",
            () -> new FenceGateBlock(WoodType.OAK, BlockBehaviour.Properties.ofFullCopy(ModBlocks.JUNIPER_PLANKS.get())));
    public static final RegistryObject<WallBlock> JUNIPER_WALL = registerBlock("juniper_wall",
            () -> new WallBlock(BlockBehaviour.Properties.ofFullCopy(ModBlocks.JUNIPER_PLANKS.get())));

    public static final RegistryObject<DoorBlock> JUNIPER_DOOR = registerBlock("juniper_door",
            () -> new DoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(ModBlocks.JUNIPER_PLANKS.get()).noOcclusion()));
    public static final RegistryObject<TrapDoorBlock> JUNIPER_TRAPDOOR = registerBlock("juniper_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.OAK, BlockBehaviour.Properties.ofFullCopy(ModBlocks.JUNIPER_PLANKS.get()).noOcclusion()));


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
