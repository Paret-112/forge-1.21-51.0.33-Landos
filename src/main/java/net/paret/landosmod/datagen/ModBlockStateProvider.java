package net.paret.landosmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.paret.landosmod.LandosMod;
import net.paret.landosmod.block.ModBlocks;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LandosMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.STEEL_BLOCK);
        blockWithItem(ModBlocks.ALTAR_BLOCK);

        blockWithItem(ModBlocks.JUNIPER_PLANKS);

        blockWithItem(ModBlocks.LINGITE_BLUE);
        blockWithItem(ModBlocks.LINGITE_GREEN);
        blockWithItem(ModBlocks.LINGITE_PURPLE);

        stairsBlock(ModBlocks.JUNIPER_STAIRS.get(), blockTexture(ModBlocks.JUNIPER_PLANKS.get()));
        slabBlock(ModBlocks.JUNIPER_SLAB.get(), blockTexture(ModBlocks.JUNIPER_PLANKS.get()), blockTexture(ModBlocks.JUNIPER_PLANKS.get()));

        buttonBlock(ModBlocks.JUNIPER_BUTTON.get(), blockTexture(ModBlocks.JUNIPER_PLANKS.get()));
        pressurePlateBlock(ModBlocks.JUNIPER_PRESSURE_PLATE.get(), blockTexture(ModBlocks.JUNIPER_PLANKS.get()));

        fenceBlock(ModBlocks.JUNIPER_FENCE.get(), blockTexture(ModBlocks.JUNIPER_PLANKS.get()));
        fenceGateBlock(ModBlocks.JUNIPER_FENCE_GATE.get(), blockTexture(ModBlocks.JUNIPER_PLANKS.get()));
        wallBlock(ModBlocks.JUNIPER_WALL.get(), blockTexture(ModBlocks.JUNIPER_PLANKS.get()));

        doorBlockWithRenderType(ModBlocks.JUNIPER_DOOR.get(), modLoc("block/juniper_door_bottom"), modLoc("block/juniper_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.JUNIPER_TRAPDOOR.get(), modLoc("block/juniper_trapdoor"),true, "cutout");


        blockItem(ModBlocks.JUNIPER_STAIRS);
        blockItem(ModBlocks.JUNIPER_SLAB);

        blockItem(ModBlocks.JUNIPER_PRESSURE_PLATE);

        blockItem(ModBlocks.JUNIPER_FENCE_GATE);

        blockItem(ModBlocks.JUNIPER_TRAPDOOR, "_bottom");

    }
    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
    private void blockItem(RegistryObject<? extends Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("landosmod:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void blockItem(RegistryObject<? extends Block> blockRegistryObject, String appendix) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile("landosmod:block/" +
                ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath() + appendix));
    }
}
