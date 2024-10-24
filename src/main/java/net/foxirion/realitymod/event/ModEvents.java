package net.foxirion.realitymod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.entity.ModEntities;
import net.foxirion.realitymod.entity.custom.DesertTurtleEntity;
import net.foxirion.realitymod.item.ModItems;
import net.foxirion.realitymod.item.custom.DesertTurtleHelmet;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = RealityMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {

    //Mob Spawning
    @SubscribeEvent
    public static void onSpawnPlacementRegister(SpawnPlacementRegisterEvent event) {
        event.register(
                ModEntities.DESERT_TURTLE.get(),
                SpawnPlacements.Type.ON_GROUND,
                Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                DesertTurtleEntity::checkDesertTurtleSpawnRules,
                SpawnPlacementRegisterEvent.Operation.AND);
    }


    //Custom Trades Villagers & Wandering Trader
    //Villagers
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        //Farmer
        if (event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            //Apprentice
            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 1),
                    new ItemStack(ModItems.COCONUT.get(), 4),
                    12, 5, 0.05f));
        }
    }

    //Wandering Traders
    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 5),
                new ItemStack(ModBlocks.PALM_SAPLING.get().asItem(), 1),
                8, 12, 0.2f));

    }

    //Desert Turtle Helmet effect
    public static final int COOLDOWN_TICKS = 1400; // 30 seconds

    @SubscribeEvent
    public static void onPlayerDamaged(LivingDamageEvent event) {
        if (!event.getEntity().level().isClientSide() && event.getEntity() instanceof Player player) {
            ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
            if (helmet.getItem() instanceof DesertTurtleHelmet) {
                DesertTurtleHelmet.setCooldown(helmet, COOLDOWN_TICKS);
            }
        }
    }
}
