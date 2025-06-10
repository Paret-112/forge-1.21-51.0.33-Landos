package net.paret.landosmod.item.custom;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class SawItem extends Item {
    private static final Map<Block, Block> SAW_MAP_PLANKS =
            Map.of(
                    Blocks.OAK_PLANKS, Blocks.OAK_STAIRS,
                    Blocks.BIRCH_PLANKS, Blocks.BIRCH_STAIRS,
                    Blocks.SPRUCE_PLANKS, Blocks.SPRUCE_STAIRS,
                    Blocks.DARK_OAK_PLANKS, Blocks.DARK_OAK_STAIRS,
                    Blocks.JUNGLE_PLANKS, Blocks.JUNGLE_STAIRS,
                    Blocks.CHERRY_PLANKS, Blocks.CHERRY_STAIRS,
                    Blocks.WARPED_PLANKS, Blocks.WARPED_STAIRS
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
                level.setBlockAndUpdate(pContext.getClickedPos(), SAW_MAP_PLANKS.get(clickedBlock).defaultBlockState());

                pContext.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) pContext.getPlayer()),
                        item -> pContext.getPlayer().onEquippedItemBroken(item, EquipmentSlot.MAINHAND));

                level.playSound(null, pContext.getClickedPos(), SoundEvents.WOOD_BREAK, SoundSource.BLOCKS);
            }
        }

        return InteractionResult.SUCCESS;
    }
}
