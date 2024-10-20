package net.foxirion.realitymod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.List;

@EventBusSubscriber(modid = RealityMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {

    //Custom Trades Villagers & Wandering Trader
    //Villagers
    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        //Farmer
        if (event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            //Apprentice
            trades.get(2).add((trader, random) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    new ItemStack(ModItems.COCONUT.get(), 4),
                    12, 5, 0.05f));
        }
    }

    //Wandering Traders
    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add((trader, random) -> new MerchantOffer(
                new ItemCost(Items.EMERALD, 5),
                new ItemStack(ModBlocks.PALM_SAPLING.get().asItem(), 1),
                8, 12, 0.2f));

    }

    //Desert Turtle Helmet effect
/*    private static final String COOLDOWN_TAG = "DesertTurtleHelmetCooldown";

    @SubscribeEvent
    public static void onPlayerDamaged(LivingDamageEvent event) {
        if (!(event.getEntity() instanceof Player player)) return;

        // Check if player is wearing the helmet
        ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);
        if (!(helmet.getItem() instanceof DesertTurtleHelmetItem)) return;

        // Get the current game time
        long currentTime = player.level().getGameTime();
        long lastActivated = helmet.getOrCreateTag().getLong(COOLDOWN_TAG);

        // Check the cooldown (20 seconds in ticks = 400 ticks)
        if (currentTime >= lastActivated + 400) {
            player.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 80, 0));

            // Set the new cooldown time
            helmet.getOrCreateTag().putLong(COOLDOWN_TAG, currentTime);
        }
    }*/

}
