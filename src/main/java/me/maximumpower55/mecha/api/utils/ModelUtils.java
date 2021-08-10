package me.maximumpower55.mecha.api.utils;

import java.util.Random;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.fabricmc.fabric.api.renderer.v1.model.ModelHelper;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.Direction;

public final class ModelUtils {
    private static final Random rand = new Random();

    public static void renderModel(VertexConsumer vertexConsumer, PoseStack matrices, BakedModel model, int light, int overlay, Direction lightDirection) {
        float quadLight;

        for(int i = 0; i <= 6; i++) {
            for(BakedQuad quad : model.getQuads(null, ModelHelper.faceFromIndex(i), rand)) {
                quadLight = getDirectionalLight(quad.getDirection(), lightDirection);

                vertexConsumer.putBulkData(matrices.last(), quad, quadLight, quadLight, quadLight, light, overlay);
            }
        }
    }

    public static float getDirectionalLight(Direction face, Direction facing) {
        switch(rotate(face, facing)) {
            case NORTH:
            case SOUTH:
                return 0.75f;
            case EAST:
            case WEST:
                return 0.6f;
            case DOWN:
                return 0.5f;
            default:
                return 1.0f;
        }
    }

    private static Direction rotate(Direction face, Direction facing) {
        if(face == Direction.UP || face == Direction.DOWN || facing == Direction.UP || facing == Direction.DOWN) return face;
        int rotations = 0;
        switch(facing) {
            case EAST:
                rotations = 1;
                break;
            case SOUTH:
                rotations = 2;
                break;
            case WEST:
                rotations = 3;
                break;
            default:
                rotations = 1;
                break;
        }

        for (int i = 0; i < rotations; i++) {
            face = face.getCounterClockWise();
        }

        return face;
    }
}
