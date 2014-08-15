package com.gjkf.lib.util;

import java.io.File;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ModContainer;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatAllowedCharacters;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

public class CommonUtils{
    private static File minecraftDir;
    
    public static boolean isClient(){
        return FMLCommonHandler.instance().getSide().isClient();
    }
    
    public static File getWorldSaveLocation(World world, int dimension){
        File basesave = DimensionManager.getCurrentSaveRootDirectory();
        if(dimension != 0)
            return new File(basesave, world.provider.getSaveFolder());
        
        return basesave;
    }
    
    public static String getWorldName(World world){
        return world.getWorldInfo().getWorldName();
    }
    
    public static File getModsFolder(){
        return new File(getMinecraftDir(), "mods");
    }
    
    public static File getMinecraftDir(){
        if(minecraftDir == null)
            minecraftDir = ReflectionManager.getField(Loader.class, File.class, Loader.instance(), "minecraftDir");
        
        return minecraftDir;
    }
    
    public static int getDimension(World world){
        return world.provider.dimensionId;
    }     

    public static String getRelativePath(File parent, File child){
        if(parent.isFile() || !child.getPath().startsWith(parent.getPath()))
            return null;
        
        return child.getPath().substring(parent.getPath().length() + 1);
    }
    
    public static <T> T[] subArray(T[] args, int i){
        if(i > args.length)            
            return (T[]) Array.newInstance(args.getClass().getComponentType(), 0);
        
        T[] narray = (T[]) Array.newInstance(args.getClass().getComponentType(), args.length-i);
        System.arraycopy(args, i, narray, 0, narray.length);
        return narray;
    }
    
    private static byte[] charWidth = new byte[]{4, 2, 5, 6, 6, 6, 6, 3, 5, 5, 5, 6, 2, 6, 2, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 2, 2, 5, 6, 5, 6, 7, 6, 6, 6, 6, 6, 6, 6, 6, 4, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 6, 4, 6, 6, 3, 6, 6, 6, 6, 6, 5, 6, 6, 2, 6, 5, 3, 6, 6, 6, 6, 6, 6, 6, 4, 6, 6, 6, 6, 6, 6, 5, 2, 5, 7, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 6, 3, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 6, 4, 6, 6, 3, 6, 6, 6, 6, 6, 6, 6, 7, 6, 6, 6, 2, 6, 6};
    
    public static String colourPrefix(int colour){
        if(colour == -1)
            return "";
        return "\247"+"0123456789abcdef".charAt(colour);
    }
    

    public static ModContainer findModContainer(String modID){
        for(ModContainer mc : Loader.instance().getModList())
            if(modID.equals(mc.getModId()))
                return mc;
        
        return null;
    }

    public static ItemStack consumeItem(ItemStack stack){
        if(stack.getItem().hasContainerItem())
            return stack.getItem().getContainerItem(stack);
        
        if(stack.stackSize == 1)
            return null;
        
        stack.stackSize--;        
        return stack;
    }

    public static String filterText(String s){
        return ChatAllowedCharacters.filerAllowedCharacters(s.replaceAll("\247.", ""));
    }
}