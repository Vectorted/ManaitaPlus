package com.vector.render;

import java.awt.Color;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LightningEntityRenderer;
import net.minecraft.client.render.entity.state.LightningEntityRenderState;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LightningEntity;
import net.minecraft.util.math.random.Random;
import org.joml.Matrix4f;

public class Lightning extends LightningEntityRenderer {
    public Lightning(EntityRendererFactory.Context context) {
        super(context);
    }

    static boolean isOpen = true;

    public void render(LightningEntityRenderState lightningEntityRenderState, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        float[] fs = new float[8];
        float[] gs = new float[8];
        float f = 0.0F;
        float g = 0.0F;
        Random random = Random.create(lightningEntityRenderState.seed);

        for(int j = 7; j >= 0; --j) {
            fs[j] = f;
            gs[j] = g;
            f += (float)(random.nextInt(11) - 5);
            g += (float)(random.nextInt(11) - 5);
        }

        VertexConsumer vertexConsumer = vertexConsumerProvider.getBuffer(RenderLayer.getLightning());
        Matrix4f matrix4f = matrixStack.peek().getPositionMatrix();

        for(int k = 0; k < 4; ++k) {
            Random random2 = Random.create(lightningEntityRenderState.seed);

            for(int l = 0; l < 3; ++l) {
                int m = 7;
                int n = 0;
                if (l > 0) {
                    m = 7 - l;
                }

                if (l > 0) {
                    n = m - 2;
                }

                float h = fs[m] - f;
                float o = gs[m] - g;

                for(int p = m; p >= n; --p) {
                    float q = h;
                    float r = o;
                    if (l == 0) {
                        h += (float)(random2.nextInt(11) - 5);
                        o += (float)(random2.nextInt(11) - 5);
                    } else {
                        h += (float)(random2.nextInt(31) - 15);
                        o += (float)(random2.nextInt(31) - 15);
                    }

                    float s = 0.5F;
                    float t = 0.45F;
                    float u = 0.45F;
                    float v = 0.5F;
                    float w = 0.1F + (float)k * 0.2F;
                    if (l == 0) {
                        w *= (float)p * 0.1F + 1.0F;
                    }

                    float x = 0.1F + (float)k * 0.2F;
                    if (l == 0) {
                        x *= ((float)p - 1.0F) * 0.1F + 1.0F;
                    }

                    int color = Color.HSBtoRGB(random.nextFloat(), 1.0f, 1.0f);
                    float f9 = ((float) ((color >> 16) & 255)) / 255.0f;
                    float f10 = ((float) ((color >> 8) & 255)) / 255.0f;
                    float f11 = ((float) (color & 255)) / 255.0f;

                    if(isOpen) {
                        drawBranch(matrix4f, vertexConsumer, h, o, p, q, r, f9, f10, f11, w, x, false, false, true, false);
                        drawBranch(matrix4f, vertexConsumer, h, o, p, q, r, f9, f10, f11, w, x, true, false, true, true);
                        drawBranch(matrix4f, vertexConsumer, h, o, p, q, r, f9, f10, f11, w, x, true, true, false, true);
                        drawBranch(matrix4f, vertexConsumer, h, o, p, q, r, f9, f10, f11, w, x, false, true, false, false);
                    } else {
                        drawBranch(matrix4f, vertexConsumer, h, o, p, q, r, 0.45F, 0.45F, 0.5F, w, x, false, false, true, false);
                        drawBranch(matrix4f, vertexConsumer, h, o, p, q, r, 0.45F, 0.45F, 0.5F, w, x, true, false, true, true);
                        drawBranch(matrix4f, vertexConsumer, h, o, p, q, r, 0.45F, 0.45F, 0.5F, w, x, true, true, false, true);
                        drawBranch(matrix4f, vertexConsumer, h, o, p, q, r, 0.45F, 0.45F, 0.5F, w, x, false, true, false, false);
                    }
                }
            }
        }
    }

    public static void open(boolean open) {
        isOpen = open;
    }
    private static void drawBranch(Matrix4f matrix4f, VertexConsumer buffer, float f, float f2, int i, float f3, float f4, float f5, float f6, float f7, float f8, float f9, boolean z, boolean z2, boolean z3, boolean z4) {
        buffer.vertex(matrix4f, f + (z ? f9 : -f9), (float) (i * 16), (z2 ? f9 : -f9) + f2).color(f5, f6, f7, 0.3f);
        buffer.vertex(matrix4f, f3 + (z ? f8 : -f8), (float) ((i + 1) * 16), (z2 ? f8 : -f8) + f4).color(f5, f6, f7, 0.3f);
        float f10 = (z3 ? f8 : -f8) + f3;
        float f11 = (float) ((i + 1) * 16);
        if (!z4) {
            f8 = -f8;
        }
        buffer.vertex(matrix4f, f10, f11, f4 + f8).color(f5, f6, f7, 0.3f);
        f10 = (z3 ? f9 : -f9) + f;
        f11 = (float) (i * 16);
        if (!z4) {
            f9 = -f9;
        }
        buffer.vertex(matrix4f, f10, f11, f2 + f9).color(f5, f6, f7, 0.3f);
    }

    protected boolean canBeCulled(LightningEntity lightningEntity) {
        return false;
    }
}
