package com.example;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.World;
import java.io.*;
import java.net.*;

public class DigitalEntity extends MobEntity {
    private static PrintWriter out;
    private static BufferedReader in;

    public DigitalEntity(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
        this.goalSelector.clear(); 
        this.targetSelector.clear();
    }

    @Override
    public void tick() {
        super.tick();
        if (this.getWorld().isClient) return;

        if (out == null) {
            try {
                Socket socket = new Socket("127.0.0.1", 8888);
                out = new PrintWriter(socket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (Exception e) {}
        }

        if (out != null) {
            out.println("STATE:" + this.getX() + "," + this.getY() + "," + this.getZ());
        }

        try {
            if (in != null && in.ready()) {
                String action = in.readLine();
                if ("FORWARD".equals(action)) {
                    this.setForwardSpeed(1.0f); 
                } else if ("STOP".equals(action)) {
                    this.setForwardSpeed(0.0f);
                }
            }
        } catch (Exception e) {}
    }
}
