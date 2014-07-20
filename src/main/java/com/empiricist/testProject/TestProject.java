package com.empiricist.testProject;


import com.empiricist.testProject.reference.Reference;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import com.empiricist.testProject.proxy.IProxy;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION)
public class TestProject {
    @Mod.Instance(Reference.MOD_ID)
    public static TestProject instance;//instance of mod

    @SidedProxy(clientSide = Reference.CLIENT_PROXY, serverSide = Reference.SERVER_PROXY)
    public static IProxy proxy;//holds proxy (ClientProxy on client, ServerProxy on server)

    //Preinit - config, network, items, blocks
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event){

    }
    //init - gui handler, tileentity, renderers, event handlers, recipes
    @Mod.EventHandler
    public void init(FMLInitializationEvent event){

    }
    //postinit - wrap up after mods initialize (ie compatibility)
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event){

    }
}
