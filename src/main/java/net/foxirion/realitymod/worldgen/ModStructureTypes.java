package net.foxirion.realitymod.worldgen;

import net.foxirion.realitymod.RealityMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.structure.StructureType;
import net.minecraft.world.level.levelgen.structure.pieces.StructurePieceType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModStructureTypes {
    public static final DeferredRegister<StructureType<?>> STRUCTURE_TYPES = DeferredRegister.create(Registries.STRUCTURE_TYPE, RealityMod.MOD_ID);
    public static final DeferredRegister<StructurePieceType> STRUCTURE_PIECE_TYPES = DeferredRegister.create(Registries.STRUCTURE_PIECE, RealityMod.MOD_ID);

    public static final RegistryObject<StructureType<OasisStructure>> OASIS = STRUCTURE_TYPES.register("oasis",
            () -> () -> OasisStructure.CODEC);

    public static final RegistryObject<StructurePieceType> OASIS_PIECE = STRUCTURE_PIECE_TYPES.register("oasis_piece",
            () -> (context, tag) -> new OasisPiece(context, tag));
}