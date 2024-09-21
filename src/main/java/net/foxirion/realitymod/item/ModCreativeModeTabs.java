package net.foxirion.realitymod.item;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RealityMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> RM_NATURAL_BLOCKS = CREATIVE_MODE_TABS.register("rm_natural_blocks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.OASIS_CLAY.get()))
                    .title(Component.translatable("creativetab.rm_natural_blocks"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModBlocks.OASIS_CLAY.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> RM_INGREDIENTS = CREATIVE_MODE_TABS.register("rm_ingredients",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.DESERT_TURTLE_SCUTE.get()))
                    .title(Component.translatable("creativetab.rm_ingredients"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.DESERT_TURTLE_SCUTE.get());
                        pOutput.accept(ModItems.OASIS_CLAY_BALL.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> RM_FOOD_AND_DRINKS = CREATIVE_MODE_TABS.register("rm_food_and_drinks",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.COCONUT.get()))
                    .title(Component.translatable("creativetab.rm_food_and_drinks"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.COCONUT.get());
                        pOutput.accept(ModItems.COCONUT_MILK.get());
                    })
                    .build());


    public static void register(IEventBus eventBus){
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
