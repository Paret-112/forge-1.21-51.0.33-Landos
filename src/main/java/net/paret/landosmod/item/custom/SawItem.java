package net.paret.landosmod.item.custom;

import com.mojang.serialization.Codec;
import net.minecraft.client.multiplayer.chat.LoggedChatMessage;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Rotation;

import java.util.Map;

public class SawItem extends Item {
    private static final Map<Block, Block> SAW_MAP_PLANKS =
            Map.ofEntries(
                    Map.entry(Blocks.OAK_PLANKS, Blocks.OAK_STAIRS),
                    Map.entry(Blocks.BIRCH_PLANKS, Blocks.BIRCH_STAIRS),
                    Map.entry(Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_STAIRS),
                    Map.entry(Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_STAIRS),
                    Map.entry(Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_STAIRS),
                    Map.entry(Blocks.CHERRY_PLANKS, Blocks.CHERRY_STAIRS),
                    Map.entry(Blocks.WARPED_PLANKS, Blocks.WARPED_STAIRS),
                    Map.entry(Blocks.ACACIA_PLANKS, Blocks.ACACIA_STAIRS),
                    Map.entry(Blocks.MANGROVE_PLANKS, Blocks.MANGROVE_STAIRS),
                    Map.entry(Blocks.CRIMSON_PLANKS, Blocks.CRIMSON_STAIRS),
                    Map.entry(Blocks.BAMBOO_PLANKS, Blocks.BAMBOO_STAIRS)
            );
    private static final Map<Block, Block> SAW_MAP_SLABS =
            Map.ofEntries(
                    Map.entry(Blocks.OAK_STAIRS, Blocks.OAK_SLAB),
                    Map.entry(Blocks.BIRCH_STAIRS, Blocks.BIRCH_SLAB),
                    Map.entry(Blocks.SPRUCE_STAIRS, Blocks.SPRUCE_SLAB),
                    Map.entry(Blocks.DARK_OAK_STAIRS, Blocks.DARK_OAK_SLAB),
                    Map.entry(Blocks.JUNGLE_STAIRS, Blocks.JUNGLE_SLAB),
                    Map.entry(Blocks.CHERRY_STAIRS, Blocks.CHERRY_SLAB),
                    Map.entry(Blocks.WARPED_STAIRS, Blocks.WARPED_SLAB),
                    Map.entry(Blocks.ACACIA_STAIRS, Blocks.ACACIA_SLAB),
                    Map.entry(Blocks.MANGROVE_STAIRS, Blocks.MANGROVE_SLAB),
                    Map.entry(Blocks.CRIMSON_STAIRS, Blocks.CRIMSON_SLAB),
                    Map.entry(Blocks.BAMBOO_STAIRS, Blocks.BAMBOO_SLAB)
            );


    public SawItem(Properties pProperties) {
        super(pProperties);
    }


    @Override
    public boolean isEnchantable(ItemStack pStack) {
        return super.isEnchantable(pStack);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        Level level = pContext.getLevel();
        Block clickedBlock = level.getBlockState(pContext.getClickedPos()).getBlock();

        if(SAW_MAP_PLANKS.containsKey(clickedBlock)) {
            if(!level.isClientSide) {

                level.setBlockAndUpdate(pContext.getClickedPos(), SAW_MAP_PLANKS.get(clickedBlock).defaultBlockState().rotate(level, pContext.getClickedPos(), Rotation.getRandom(RandomSource.create())));

                pContext.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, pContext.getClickedPos(), SoundEvents.WOOD_BREAK, SoundSource.BLOCKS);
            }
        }
        if(SAW_MAP_SLABS.containsKey(clickedBlock)) {
            if(!level.isClientSide) {
                level.setBlockAndUpdate(pContext.getClickedPos(), SAW_MAP_SLABS.get(clickedBlock).defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, pContext.getClickedPos(), SoundEvents.WOOD_BREAK, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
