package com.mattdahepic.superhardmode.asm;

import net.minecraft.launchwrapper.IClassTransformer;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.*;

public class WeatherEventTransformer implements IClassTransformer {
    @Override
    public byte[] transform (String name, String name2, byte[] bytes) {
        if (bytes == null) return null;

        try {
            if (name.equals("net.minecraft.world.WorldServer")) {
                System.out.println("!!!! Temporary Patch: Patching weather events into WorldServer.java! Waiting for pull request at https://github.com/MinecraftForge/MinecraftForge/pull/2343 !!!!");
                return patchWorldServer(bytes);
            }
            if (name.equals("net.minecraft.world.storage.WorldInfo")) {
                System.out.println("!!!! Temporary Patch: Patching weather events into WorldInfo.java! Waiting for pull request at https://github.com/MinecraftForge/MinecraftForge/pull/2343 !!!!");
                return patchWorldInfo(bytes);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
    private byte[] patchWorldServer (byte[] bytes) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode,0);

        for (MethodNode method : classNode.methods) {
            if (method.name.equals("updateBlocks") && method.desc.equals("()V")) {
                InsnList list = new InsnList();
                for (AbstractInsnNode node : method.instructions.toArray()) {
                    if (node instanceof MethodInsnNode) {
                        MethodInsnNode methodInsnNode = (MethodInsnNode)node;
                        if (methodInsnNode.name.equals("addWeatherEffect") && methodInsnNode.desc.equals("(Lnet/minecraft/entity/Entity;)B")) {
                            //list.add(); //FIXME: dis
                        }
                    }
                }
            }
        }
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
    private byte[] patchWorldInfo (byte[] bytes) {
        ClassNode classNode = new ClassNode();
        ClassReader classReader = new ClassReader(bytes);
        classReader.accept(classNode,0);

        for (MethodNode method : classNode.methods) {
            if (method.name.equals("setCleanWeatherTime") && method.desc.equals("(I)V")) {
                //TODO
                continue;
            }
            if (method.name.equals("setThundering") && method.desc.equals("(B)V")) {
                //TODO
                continue;
            }
            if (method.name.equals("setRaining") && method.desc.equals("(B)V")) {
                //TODO
                continue;
            }
        }
        ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
        classNode.accept(writer);
        return writer.toByteArray();
    }
}
