package net.paret.landosmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Items;

public class ModFoodProperties {
    public static final FoodProperties BEAN = new FoodProperties.Builder()
            .nutrition(2)
            .saturationModifier(.2f)
            .build();
    public static final FoodProperties BEAN_SOUP = new FoodProperties.Builder()
            .nutrition(6)
            .saturationModifier(.6f)
            .effect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 400), .5f)
            .usingConvertsTo(Items.BOWL)
            .build();
}
