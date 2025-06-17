package net.paret.landosmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.paret.landosmod.LandosMod;
import net.paret.landosmod.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, LandosMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.STEEL_BLOCK.get())
                .add(ModBlocks.LINGITE_BLUE.get())
                .add(ModBlocks.LINGITE_GREEN.get())
                .add(ModBlocks.LINGITE_PURPLE.get());

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.LINGITE_BLUE.get())
                .add(ModBlocks.LINGITE_GREEN.get());
        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.LINGITE_PURPLE.get());
    }
}
