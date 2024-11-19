package net.foxirion.realitymod.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.init.ModDataComponents;
import net.foxirion.realitymod.item.custom.DesertTurtleHelmet;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.living.LivingDamageEvent;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;
import net.neoforged.neoforge.event.village.WandererTradesEvent;

import java.util.List;

@EventBusSubscriber(modid = RealityMod.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {
    /// Custom Trades Villagers & Wandering Trader
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
    public static final int COOLDOWN_TICKS = 1400; // 30 seconds

    @SubscribeEvent
    public static void onPlayerDamaged(LivingDamageEvent.Pre event) {
        if (!event.getEntity().level().isClientSide() && event.getEntity() instanceof Player player) {
            ItemStack helmet = player.getItemBySlot(EquipmentSlot.HEAD);

            if (helmet.getItem() instanceof DesertTurtleHelmet) {
                helmet.set(ModDataComponents.TURTLE_HELMET_COOLDOWN, COOLDOWN_TICKS);
            }
        }
    }

    /// Get water into aa bowl
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickEmpty event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        Player player = event.getEntity();
        BlockState blockState = level.getBlockState(pos);
        ItemStack heldItem = event.getItemStack();
        InteractionHand hand = event.getHand();

        // Check if player is holding a bowl and clicking on water
        if (blockState.getFluidState().getType() == Fluids.WATER && heldItem.is(Items.BOWL)) {

            // Create new bowl of water item stack
            ItemStack bowlOfWater = new ItemStack(ModItems.BOWL_OF_WATER.get());

            // Shrink the bowl stack by 1
            heldItem.shrink(1);

            // Give the player the bowl of water
            if (heldItem.isEmpty()) {
                player.setItemInHand(event.getHand(), bowlOfWater);
            } else if (!player.getInventory().add(bowlOfWater)) {
                player.drop(bowlOfWater, false);
            }

            // Play bowl fill sound
            level.playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.GENERIC_SPLASH, SoundSource.NEUTRAL, 1.0F, 1.0F);

            // Trigger the animation and events
            player.startUsingItem(hand);
            player.swing(hand);
            player.awardStat(Stats.ITEM_USED.get(Items.BOWL));
            level.gameEvent(player, GameEvent.FLUID_PICKUP, pos);

            // Add some particles for effect
            if (level instanceof ServerLevel serverLevel) {
                serverLevel.sendParticles(ParticleTypes.FALLING_WATER,
                        pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5,
                        10, // particle count
                        0.5, 0.5, 0.5, // spread
                        0.1 // speed
                );
            }
        }
    }
}
