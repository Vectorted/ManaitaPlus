package com.vector.mixin.client;

import com.vector.ManaitaMinecraftClient;
import com.vector.utils.Player;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ArrowEntity.class)
public class ArrowEntityMixin {
    @Unique
    public boolean checkArrowOnGround(ArrowEntity arrow) {
        Vec3d arrowPos = arrow.getPos();
        Vec3d[] directions = {
                new Vec3d(0, -1, 0),
                new Vec3d(0, 1, 0),
                new Vec3d(1, 0, 0),
                new Vec3d(-1, 0, 0),
                new Vec3d(0, 0, 1),
                new Vec3d(0, 0, -1)
        };

        for (Vec3d direction : directions) {
            Vec3d end = arrowPos.add(direction.multiply(0.1d));
            BlockHitResult hitResult = arrow.getWorld().raycast(new RaycastContext(arrowPos, end, RaycastContext.ShapeType.OUTLINE, RaycastContext.FluidHandling.NONE, arrow));

            if (hitResult.getType() == HitResult.Type.BLOCK) {
                return true;
            }
        }
        return false;
    }

    @Inject(at = @At("HEAD"), method = "tick", cancellable = true)
    private void onTick(CallbackInfo callbackInfo) {
        ArrowEntity arrow = (ArrowEntity) (Object) this;
        arrow.getWorld().getEntitiesByClass(EndermanEntity.class, arrow.getBoundingBox().expand(2.0f), entity -> {
            if(ManaitaMinecraftClient.player != null) {
                if (Player.getMainHandManaitaBow(ManaitaMinecraftClient.player) || Player.getOffHandManaitaBow(ManaitaMinecraftClient.player)) {
                    entity.setHealth(0.0f);
                }
            }
            return true;
        });
        if(checkArrowOnGround(arrow)) {
            arrow.remove(Entity.RemovalReason.KILLED);
        }
    }
}
