package ru.jetdev.mmc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nonnull;

public class DoubleSlabBlock extends Block {
    private final Block firstSlab; // Объявляем поле для первого полублока
    private final Block secondSlab; // Объявляем поле для второго полублока

    // Форма для нижнего и верхнего полублоков
    private static final VoxelShape LOWER_SHAPE = Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D);
    private static final VoxelShape UPPER_SHAPE = Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    public DoubleSlabBlock(Block firstSlab, Block secondSlab, BlockBehaviour.Properties properties) {
        super(properties);
        this.firstSlab = firstSlab;
        this.secondSlab = secondSlab;
    }

    @Override
    @Nonnull
    public VoxelShape getShape(@Nonnull BlockState state, @Nonnull BlockGetter world, @Nonnull BlockPos pos, @Nonnull CollisionContext context) {
        // Возвращаем форму полного блока (нижний + верхний)
        return Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);
    }

    @Nonnull
    public Block getFirstSlab() {
        return firstSlab;
    }

    @Nonnull
    public Block getSecondSlab() {
        return secondSlab;
    }
}