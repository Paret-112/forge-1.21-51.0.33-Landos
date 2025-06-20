package net.paret.landosmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.paret.landosmod.LandosMod;
import net.paret.landosmod.block.ModBlocks;
import net.paret.landosmod.item.ModItems;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider pRegistries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), pRegistries);
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.STEEL_BLOCK.get());
        dropSelf(ModBlocks.ALTAR_BLOCK.get());

        dropSelf(ModBlocks.JUNIPER_PLANKS.get());
        dropSelf(ModBlocks.JUNIPER_STAIRS.get());
        this.add(ModBlocks.JUNIPER_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.JUNIPER_SLAB.get()));

        dropSelf(ModBlocks.JUNIPER_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.JUNIPER_BUTTON.get());

        dropSelf(ModBlocks.JUNIPER_FENCE.get());
        dropSelf(ModBlocks.JUNIPER_FENCE_GATE.get());
        dropSelf(ModBlocks.JUNIPER_WALL.get());

        dropSelf(ModBlocks.JUNIPER_TRAPDOOR.get());
        this.add(ModBlocks.JUNIPER_DOOR.get(),
                block -> createDoorTable(ModBlocks.JUNIPER_DOOR.get()));

        this.add(ModBlocks.LINGITE_BLUE.get(),
                block -> createMultiOreDrops(ModBlocks.LINGITE_BLUE.get(),
                        ModItems.LINGITE_BLUE.get(), 6, 12));
        this.add(ModBlocks.LINGITE_GREEN.get(),
                block -> createMultiOreDrops(ModBlocks.LINGITE_GREEN.get(),
                        ModItems.LINGITE_GREEN.get(), 3, 6));
        this.add(ModBlocks.LINGITE_PURPLE.get(),
                block -> createMultiOreDrops(ModBlocks.LINGITE_PURPLE.get(),
                        ModItems.LINGITE_PURPLE.get(), 1, 3));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }

    protected LootTable.Builder createMultiOreDrops(Block pBlock, Item Items, float minDrop, float maxDrop) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createSilkTouchDispatchTable(
                pBlock,this.applyExplosionDecay(
                        pBlock, LootItem.lootTableItem(Items)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrop, maxDrop)))
                                .apply(ApplyBonusCount.addOreBonusCount(registrylookup.getOrThrow(Enchantments.FORTUNE)))
                )
        );
    }
}
