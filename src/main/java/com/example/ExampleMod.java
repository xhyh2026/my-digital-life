package com.example;

import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ExampleMod implements ModInitializer {
    
    // 注册我们的数字生命实体
    public static final EntityType<DigitalEntity> DIGITAL_ENTITY = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("example", "digital_entity"),
            EntityType.Builder.create(DigitalEntity::new, SpawnGroup.CREATURE)
                    .dimensions(0.6f, 1.8f)
                    .build("digital_entity")
    );

    @Override
    public void onInitialize() {
        System.out.println("数字生命模组已加载！");
    }
}
