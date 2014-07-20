package com.empiricist.testProject;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = "testProject", name = "Test Project", version="1.7.10-0.1")
public class TestProject {
    @Mod.Instance("testProject")
    public static TestProject instance;//instance of mod

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
