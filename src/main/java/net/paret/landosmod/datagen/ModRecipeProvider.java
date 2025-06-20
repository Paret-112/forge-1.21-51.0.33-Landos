package net.paret.landosmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.paret.landosmod.LandosMod;
import net.paret.landosmod.block.ModBlocks;
import net.paret.landosmod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> STEEL_SMELTABLE = List.of(ModItems.STEELUNFIRED.get());

    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.STEEL_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.STEELINGOT.get())
                .unlockedBy(getHasName(ModItems.STEELINGOT.get()), has(ModItems.STEELINGOT.get())).save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEELINGOT.get(), 9)
                .requires(ModBlocks.STEEL_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.STEEL_BLOCK.get()), has(ModBlocks.STEEL_BLOCK.get())).save(pRecipeOutput);

        oreSmelting(pRecipeOutput, STEEL_SMELTABLE, RecipeCategory.MISC, ModItems.STEELINGOT.get(), 0.5f, 200, "steel");
        oreBlasting(pRecipeOutput, STEEL_SMELTABLE, RecipeCategory.MISC, ModItems.STEELINGOT.get(), 0.5f, 100, "steel");

        stairBuilder(ModBlocks.JUNIPER_STAIRS.get(), Ingredient.of(ModBlocks.JUNIPER_PLANKS.get())).group("juniper")
                .unlockedBy(getHasName(ModBlocks.JUNIPER_PLANKS.get()), has(ModBlocks.JUNIPER_PLANKS.get())).save(pRecipeOutput);
        slab(pRecipeOutput, RecipeCategory.MISC, ModBlocks.JUNIPER_SLAB.get(), ModBlocks.JUNIPER_PLANKS.get());
        buttonBuilder(ModBlocks.JUNIPER_BUTTON.get(), Ingredient.of(ModBlocks.JUNIPER_PLANKS.get())).group("juniper")
                .unlockedBy(getHasName(ModBlocks.JUNIPER_PLANKS.get()), has(ModBlocks.JUNIPER_PLANKS.get())).save(pRecipeOutput);
        pressurePlate(pRecipeOutput, ModBlocks.JUNIPER_PRESSURE_PLATE.get(),ModBlocks.JUNIPER_PLANKS.get());
        fenceBuilder(ModBlocks.JUNIPER_FENCE.get(), Ingredient.of(ModBlocks.JUNIPER_PLANKS.get())).group("juniper")
                .unlockedBy(getHasName(ModBlocks.JUNIPER_PLANKS.get()), has(ModBlocks.JUNIPER_PLANKS.get())).save(pRecipeOutput);
        fenceGateBuilder(ModBlocks.JUNIPER_FENCE_GATE.get(), Ingredient.of(ModBlocks.JUNIPER_PLANKS.get())).group("juniper")
                .unlockedBy(getHasName(ModBlocks.JUNIPER_PLANKS.get()), has(ModBlocks.JUNIPER_PLANKS.get())).save(pRecipeOutput);
        wall(pRecipeOutput, RecipeCategory.MISC, ModBlocks.JUNIPER_WALL.get(), ModBlocks.JUNIPER_PLANKS.get());
        doorBuilder(ModBlocks.JUNIPER_DOOR.get(), Ingredient.of(ModBlocks.JUNIPER_PLANKS.get())).group("juniper")
                .unlockedBy(getHasName(ModBlocks.JUNIPER_PLANKS.get()), has(ModBlocks.JUNIPER_PLANKS.get())).save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.JUNIPER_TRAPDOOR.get()).pattern("***").pattern("**").define('*', ModBlocks.JUNIPER_PLANKS.get()).save(pRecipeOutput);

        //trapdoorBuilder(ModBlocks.JUNIPER_TRAPDOOR.get(), Ingredient.of(ModBlocks.JUNIPER_PLANKS.get())).group("juniper")
        //      .unlockedBy(getHasName(ModBlocks.JUNIPER_PLANKS.get()), has(ModBlocks.JUNIPER_PLANKS.get())).save(pRecipeOutput);
    }
    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, LandosMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}
