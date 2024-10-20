package net.foxirion.realitymod.datagen;

import net.foxirion.realitymod.RealityMod;
import net.foxirion.realitymod.block.ModBlocks;
import net.foxirion.realitymod.item.ModItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelBuilder;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.LinkedHashMap;

public class ModItemModelProvider extends ItemModelProvider {
    public static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();

    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, RealityMod.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // Basic Item
        basicItem(ModItems.COCONUT.get());
        basicItem(ModItems.COCONUT_MILK.get());
        basicItem(ModItems.DESERT_TURTLE_SCUTE.get());
        basicItem(ModItems.OASIS_CLAY_BALL.get());
        basicItem(ModItems.PALM_BOAT.get());
        basicItem(ModItems.PALM_CHEST_BOAT.get());
        basicItem(ModItems.PALM_HANGING_SIGN.get());
        basicItem(ModItems.PALM_SIGN.get());

        //EvenSimpler Items & Block Items

        evenSimplerBlockItem(ModBlocks.FOSSIL);
        evenSimplerBlockItem(ModBlocks.DEEPSLATE_FOSSIL);
        evenSimplerBlockItem(ModBlocks.FROZEN_FOSSIL);
        evenSimplerBlockItem(ModBlocks.NETHER_FOSSIL);
        evenSimplerBlockItem(ModBlocks.PALM_FENCE_GATE);
        evenSimplerBlockItem(ModBlocks.PALM_PRESSURE_PLATE);
        evenSimplerBlockItem(ModBlocks.PALM_SLAB);
        evenSimplerBlockItem(ModBlocks.PALM_STAIRS);

        //Existing Parent
        withExistingParent(ModItems.DESERT_TURTLE_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent("desert_turtle_egg", mcLoc("item/turtle_egg"))
                .texture("layer0", modLoc("item/desert_turtle_egg"));

        //Rest of the Items & BlockItems

        buttonItem(ModBlocks.PALM_BUTTON, ModBlocks.PALM_PLANKS);

        fenceItem(ModBlocks.PALM_FENCE, ModBlocks.PALM_PLANKS);

        trapdoorItem(ModBlocks.PALM_TRAPDOOR);

        trimmableArmorItem(ModItems.DESERT_TURTLE_HELMET.get());
    }

    //Methods
    public void evenSimplerBlockItem(DeferredBlock<Block> block) {
        this.withExistingParent(RealityMod.MODID + ":" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                modLoc("block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath()));
    }

    public void trapdoorItem(DeferredBlock<Block> block) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(),
                modLoc("block/" + BuiltInRegistries.BLOCK.getKey(block.get()).getPath() + "_bottom"));
    }

    public void fenceItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/fence_inventory"))
                .texture("texture", ResourceLocation.fromNamespaceAndPath(RealityMod.MODID, "block/" + BuiltInRegistries.BLOCK.getKey(baseBlock.get()).getPath()));
    }

    public void buttonItem(DeferredBlock<Block> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(BuiltInRegistries.BLOCK.getKey(block.get()).getPath(), mcLoc("block/button_inventory"))
                .texture("texture", RealityMod.rl("block/" + BuiltInRegistries.BLOCK.getKey(baseBlock.get()).getPath()));
    }

    public void trimmableArmorItem(Item item) {
        String name = getItemName(item);
        String itemName = "item/" + name;
        ModelFile mcItem = getExistingFile(mcLoc("item/generated"));

        if (item instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {
                // Variables
                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String trimType = entry.getKey().location().getPath();
                float trimValue = entry.getValue();
                ResourceLocation textureLocation;
                if (entry.getKey().location().getNamespace().equals("minecraft")) { // Vanilla trims
                    textureLocation = mcLoc("trims/items/" + armorType + "_trim_" + trimType);
                } else { // Modded trims (assuming they're in your mod's namespace)
                    textureLocation = modLoc("trims/items/" + armorType + "_trim_" + trimType);
                }

                ModelFile model = new ModelFile.UncheckedModelFile(modLoc(itemName + "_" + trimType + "_trim"));

                //existingFileHelper.trackGenerated(textureLocation, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed parts
                getBuilder(name + "_" + trimType + "_trim")
                        .parent(mcItem)
                        .texture("layer0", itemName)
                        .texture("layer1", textureLocation);

                // Armor with trimmed parts
                getBuilder(name)
                        .parent(mcItem)
                        .override()
                        .predicate(ResourceLocation.parse("trim_type"), trimValue)
                        .model(model).end()
                        .texture("layer0", modLoc(itemName));
            });
        }
    }

    private String getItemName(Item item) {
        return BuiltInRegistries.ITEM.getKey(item).toString().replace(RealityMod.MODID + ":", "");
    }
}