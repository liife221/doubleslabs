package ru.jetdev.mmc;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.SlabType;
import net.minecraft.world.level.block.state.BlockBehaviour; // Добавлен корректный импорт
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Mmc.MODID)
public class DoubleSlabHandler {

    @SubscribeEvent
    public static void onBlockPlace(PlayerInteractEvent.RightClickBlock event) {
        Level level = event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = level.getBlockState(pos);

        // Проверяем, является ли блок полублоком
        if (state.getBlock() instanceof SlabBlock slabBlock) {
            SlabType slabType = state.getValue(SlabBlock.TYPE);

            // Проверяем, является ли полублок нижним
            if (slabType == SlabType.BOTTOM) {
                BlockState heldItemState = Block.byItem(event.getItemStack().getItem()).defaultBlockState();

                if (heldItemState.getBlock() instanceof SlabBlock heldSlabBlock) {
                    // Проверяем, что второй полублок отличается от первого
                    if (heldSlabBlock != slabBlock) {
                        // Создаем новый блок DoubleSlabBlock
                        DoubleSlabBlock doubleSlabBlock = new DoubleSlabBlock(
                                slabBlock,
                                heldSlabBlock,
                                BlockBehaviour.Properties.copy(slabBlock) // Используем правильные свойства
                        );
                        level.setBlock(pos, doubleSlabBlock.defaultBlockState(), 3);

                        // Уменьшаем количество предметов в руке игрока
                        event.getEntity().getItemInHand(event.getHand()).shrink(1);
                        event.setCanceled(true);
                    }
                }
            }
        }
    }
}