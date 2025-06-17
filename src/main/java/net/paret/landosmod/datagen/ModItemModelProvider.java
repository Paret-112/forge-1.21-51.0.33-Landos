package net.paret.landosmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.common.Mod;
import net.paret.landosmod.LandosMod;
import net.paret.landosmod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, LandosMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.STEELINGOT.get());
        basicItem(ModItems.STEELUNFIRED.get());
        basicItem(ModItems.COKE.get());

        basicItem(ModItems.BEAN.get());
        basicItem(ModItems.BEAN_SOUP.get());

        basicItem(ModItems.WOOD_SAW.get());

        basicItem(ModItems.LINGITE_BLUE.get());
        basicItem(ModItems.LINGITE_GREEN.get());
        basicItem(ModItems.LINGITE_PURPLE.get());
    }
}
