package net.foxirion.realitymod.worldgen;

import com.mojang.serialization.Codec;
import net.foxirion.realitymod.RealityMod;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePiecesBuilder;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureTemplateManager;

import java.util.Optional;

public class OasisStructure extends Structure {
    public static final Codec<OasisStructure> CODEC = simpleCodec(OasisStructure::new);

    protected OasisStructure(StructureSettings pSettings) {
        super(pSettings);
    }

    @Override
    protected Optional<GenerationStub> findGenerationPoint(GenerationContext pContext) {
        ChunkPos chunkPos = pContext.chunkPos();
        BlockPos centerOfChunk = chunkPos.getMiddleBlockPosition(0);
        int landHeight = pContext.chunkGenerator().getFirstOccupiedHeight(
                centerOfChunk.getX(),
                centerOfChunk.getZ(),
                Heightmap.Types.WORLD_SURFACE_WG,
                pContext.heightAccessor(),
                pContext.randomState()
        );
        BlockPos positionForStructure = new BlockPos(centerOfChunk.getX(), landHeight, centerOfChunk.getZ());

        return Optional.of(new GenerationStub(positionForStructure, (builder) -> generatePieces(builder, positionForStructure)));
    }

    private void generatePieces(StructurePiecesBuilder builder, BlockPos pos) {
        StructureTemplateManager templateManager = RealityMod.getTemplateManager();
        ResourceLocation templateLocation = new ResourceLocation(RealityMod.MOD_ID, "oasis");
        StructurePlaceSettings placeSettings = new StructurePlaceSettings().setRotation(Rotation.NONE);

        OasisPiece piece = new OasisPiece(
                ModStructureTypes.OASIS_PIECE.get(),
                templateManager,
                templateLocation,
                pos,
                placeSettings
        );
        builder.addPiece(piece);
    }

    @Override
    public StructureType<?> type() {
        return ModStructureTypes.OASIS.get();
    }
}