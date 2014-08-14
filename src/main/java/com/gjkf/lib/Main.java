package com.gjkf.lib;

import com.gjkf.lib.helper.LogHelper;
import com.gjkf.lib.proxy.CommonProxy;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = References.MODID, name = References.MOD_NAME,version = References.VERSION)
public class Main {
	
	@Instance
	public static Main instance;

	@SidedProxy(clientSide = References.CLIENT_PROXY, serverSide = References.SERVER_PROXY)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preinit(FMLPreInitializationEvent event){
		
	}

	@EventHandler
	public void init(FMLInitializationEvent event){

	}
	@EventHandler
	public void postinit(FMLPostInitializationEvent event){

	}
}