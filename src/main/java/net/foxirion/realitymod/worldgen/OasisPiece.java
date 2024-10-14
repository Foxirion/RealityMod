package net.foxirion.realitymod.worldgen;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.levelgen.structure.StructurePiece;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplate;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

public class OasisPiece extends StructurePiece {
    private final ResourceLocation templateLocation;
    private final Rotation rotation;
    private final StructurePlaceSettings placeSettings;

    public OasisPiece(StructurePieceType type, StructureTemplateManager templateManager, ResourceLocation templateLocation, BlockPos pos, Rotation rotation) {
        super(type, 0, templateManager.getOrCreate(templateLocation).getBoundingBox(new StructurePlaceSettings().setRotation(rotation), pos));
        this.templateLocation = templateLocation;
        this.rotation = rotation;
        this.placeSettings = new StructurePlaceSettings().setRotation(rotation);
    }

    public OasisPiece(StructurePieceType type, CompoundTag tag) {
        super(type, tag);
        this.templateLocation = new ResourceLocation(tag.getString("Template"));
        this.rotation = Rotation.valueOf(tag.getString("Rotation"));
        this.placeSettings = new StructurePlaceSettings().setRotation(this.rotation);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag tag) {
        super.addAdditionalSaveData(tag);
        tag.putString("Template", this.templateLocation.toString());
        tag.putString("Rotation", this.rotation.name());
    }

    @Override
    public void postProcess(ServerLevelAccessor level, StructureTemplateManager structureManager, ChunkGenerator chunkGenerator, RandomSource random, BoundingBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
        StructureTemplate template = structureManager.getOrCreate(this.templateLocation);
        StructurePlaceSettings placeSettings = this.placeSettings.copy()
                .setBoundingBox(boundingBox)
                .setRandom(random);
        BlockPos structurePos = this.getWorldPosition();
        template.placeInWorld(level, structurePos, structurePos, placeSettings, random, 2);
    }
}