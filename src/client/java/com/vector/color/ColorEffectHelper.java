package com.vector.color;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import net.minecraft.client.MinecraftClient;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TextColor;

public class ColorEffectHelper {
    private static final float DEFAULT_SPEED = 1.0F;
    private static final int DEFAULT_DIRECTION = 1;
    private static final float DEFAULT_SATURATION = 1.0F;
    private static final float DEFAULT_BRIGHTNESS = 1.0F;
    private static final float DEFAULT_CHAR_SPACING = 0.05F;
    private static final List<Integer> DEFAULT_RAINBOW = Arrays.asList(16711680, 16744192, 16776960, 65280, 255, 4915330, 9699539);
    public static final List<Integer> FIRE_THEME = Arrays.asList(16711680, 16729344, 16747520, 16766720);
    public static final List<Integer> ICE_THEME = Arrays.asList(65535, 49151, 2003199, 8900346, 11393254);
    public static final List<Integer> NATURE_THEME = Arrays.asList(3329330, 2263842, 8190976, 11403055);

    public ColorEffectHelper() {
    }

    public static MutableText createCustomGradientText(String text, List<Integer> colors, float speed, int direction, float charSpacing, float gradientSpan) {
        long time = getTimeBase();
        if (((List)colors).size() < 2) {
            colors = new ArrayList((Collection)colors);
            ((List)colors).add((Integer)((List)colors).get(0));
        }

        MutableText gradientText = Text.empty();
        int length = text.length();

        for(int i = 0; i < length; ++i) {
            int index = direction > 0 ? i : length - 1 - i;
            float position = ((float)time * speed / 100.0F + (float)i * charSpacing) % 1.0F;
            position = position * gradientSpan % 1.0F;
            int color = getGradientColor((List)colors, position);
            gradientText.append(Text.literal(String.valueOf(text.charAt(index))).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(color))));
        }

        return gradientText;
    }

    private static int getGradientColor(List<Integer> colors, float position) {
        int colorCount = colors.size();
        float segment = 1.0F / (float)(colorCount - 1);
        int segmentIndex = (int)(position / segment);
        if (segmentIndex >= colorCount - 1) {
            return (Integer)colors.get(colorCount - 1);
        } else {
            float segmentPos = position % segment / segment;
            int startColor = (Integer)colors.get(segmentIndex);
            int endColor = (Integer)colors.get(segmentIndex + 1);
            return interpolateColor(startColor, endColor, segmentPos);
        }
    }

    private static int interpolateColor(int start, int end, float progress) {
        int startR = start >> 16 & 255;
        int startG = start >> 8 & 255;
        int startB = start & 255;
        int endR = end >> 16 & 255;
        int endG = end >> 8 & 255;
        int endB = end & 255;
        int r = (int)((float)startR + (float)(endR - startR) * progress);
        int g = (int)((float)startG + (float)(endG - startG) * progress);
        int b = (int)((float)startB + (float)(endB - startB) * progress);
        return r << 16 | g << 8 | b;
    }

    public static MutableText createRainbowText(String text, float speed, int direction, float saturation, float brightness, float charSpacing) {
        long time = getTimeBase();
        MutableText rainbowText = Text.empty();
        int length = text.length();

        for(int i = 0; i < length; ++i) {
            int index = direction > 0 ? i : length - 1 - i;
            float hue = ((float)time * speed / 100.0F + (float)i * charSpacing) % 1.0F;
            int rgb = Color.HSBtoRGB(hue, saturation, brightness);
            rainbowText.append(Text.literal(String.valueOf(text.charAt(index))).setStyle(Style.EMPTY.withColor(TextColor.fromRgb(rgb & 16777215))));
        }

        return rainbowText;
    }

    public static MutableText createRainbowText(String text, float speed, int direction, float charSpacing) {
        return createCustomGradientText(text, DEFAULT_RAINBOW, speed, direction, charSpacing, 1.0F);
    }

    public static MutableText createRainbowText(String text) {
        return createRainbowText(text, 1.0F, 1, 1.0F, 1.0F, 0.05F);
    }

    private static long getTimeBase() {
        return MinecraftClient.getInstance().world != null
                ? MinecraftClient.getInstance().world.getTime()
                : System.currentTimeMillis() / 50;
    }
}
