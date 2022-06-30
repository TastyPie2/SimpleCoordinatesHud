package com.tasty.simplecordinateshud;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

 // Class to render the coordinates on the screen
 @Mod.EventBusSubscriber(modid = SimpleCordinatesHud.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
 public class CoordinatesRenderer
 {
     // The MinecraftClient instance
     private static final Minecraft client = Minecraft.getInstance();
     private static boolean isDebugScreenOpen = false;

     // OnOverlayRender is called every frame
     @SubscribeEvent
     public static void onOverlayRender(RenderGameOverlayEvent.Pre event)
     {
         // Check if the event is for the hud
         if (event.getType() != RenderGameOverlayEvent.ElementType.TEXT)
         {
             return;
         }

         if(isDebugScreenOpen)
         {
             return;
         }
         
         renderCordinates();
     }

     // OnClientTick is called every frame
     @SubscribeEvent
     public static void onClientTick(RenderGameOverlayEvent.Pre event)
     {
         // Check if the event is for the hud
         if (event.getType() != RenderGameOverlayEvent.ElementType.DEBUG)
         {
             isDebugScreenOpen = false;
         }
         else
         {
             isDebugScreenOpen = true;
         }
     }

     // Render the coordinates on the screen
     public static void renderCordinates()
     {
         Vec3 playerPos = client.player.position();
         int x = (int) Math.floor(playerPos.x);
         int y = (int) Math.floor(playerPos.y);
         int z = (int) Math.floor(playerPos.z);


         PoseStack poseStack = new PoseStack();
         poseStack.pushPose();
         poseStack.translate(0, 0, 0);

         Gui.drawString(poseStack, client.font, "X:" + x  + " Y:" + y + " Z:" + z, 2, 2, 0xFFFFFF);
     }
 }