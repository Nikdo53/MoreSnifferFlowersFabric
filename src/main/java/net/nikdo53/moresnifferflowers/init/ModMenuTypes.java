package net.nikdo53.moresnifferflowers.init;

import io.github.fabricators_of_create.porting_lib.util.LazyRegistrar;
import io.github.fabricators_of_create.porting_lib.util.RegistryObject;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.container.ContainerFactory;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.nikdo53.moresnifferflowers.MoreSnifferFlowers;
import net.nikdo53.moresnifferflowers.client.gui.menu.RebrewingStandMenu;
import net.nikdo53.moresnifferflowers.client.gui.screen.RebrewingStandScreen;

public class ModMenuTypes {
    public static LazyRegistrar<MenuType<?>> MENU_TYPES = LazyRegistrar.create(BuiltInRegistries.MENU, MoreSnifferFlowers.MOD_ID);

    public static final RegistryObject<MenuType<RebrewingStandMenu>> REBREWING_STAND = MENU_TYPES.register("rebrewing_stand", () -> new ExtendedScreenHandlerType<>(RebrewingStandMenu::new));


    @Environment(EnvType.CLIENT)
    public static void renderScreens() {
        MenuScreens.register(REBREWING_STAND.get(), RebrewingStandScreen::new);
    }
}