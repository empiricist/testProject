package com.empiricist.testproject.item;

import com.empiricist.testproject.creativetab.CreativeTabTestProject;

public class ItemStaff extends ItemBase{
    public ItemStaff(){
        super();
        this.setUnlocalizedName("staff");
        this.setCreativeTab(CreativeTabTestProject.TEST_PROJECT_TAB);
    }
}
