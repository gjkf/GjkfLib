package com.gjkf.lib.gui;

import java.awt.Point;
import java.util.ArrayList;

import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;

public class GuiScreenWidget extends GuiScreen implements IGuiActionListener{    

	public ArrayList<GuiWidget> widgets = new ArrayList<GuiWidget>();

	public int xSize;
	public int ySize;
	public static int minX;
	public static int minY;
	public int midX;
	public int midY;

	public GuiScreenWidget(){
		this(176, 166);
	}

	public GuiScreenWidget(int xSize, int ySize){
		super();
		this.xSize = xSize;
		this.ySize = ySize;
		this.minX = (width - this.xSize) / 2;
		this.minY = (height - this.ySize) / 2;
		this.midX = width/2;
		this.midY = height/2;
	}

	/*
	 * Returns half of the width
	 */
	
	public int getMidX(){
		return midX;
	}
	
	/*
	 * Returns half of the height
	 */
	
	public int getMidY(){
		return midY;
	}
	
	public static int getMinX(){
		return minX;
	}

	public static int getMinY(){
		return minY;
	}

	public void reset(){
		initGui();
		widgets.clear();
		addWidgets();
	}

	@Override
	public void setWorldAndResolution(Minecraft mc, int i, int j){
		boolean init = this.mc == null;
		super.setWorldAndResolution(mc, i, j);
		if(init)
			addWidgets();
	}

	public void add(GuiWidget widget){
		widgets.add(widget);
		widget.onAdded(this);
	}

	@Override
	public void drawScreen(int mousex, int mousey, float f){
		GL11.glTranslated(getMinX(), getMinY(), 0);
		drawBackground();
		for(GuiWidget widget : widgets)
			widget.draw(mousex-getMinX(), mousey-getMinY(), f);
		drawForeground();
		GL11.glTranslated(-getMinX(), -getMinY(), 0);
	}

	public void drawBackground(){
	}

	public void drawForeground(){
	}

	@Override
	protected void mouseClicked(int x, int y, int button){
		super.mouseClicked(x, y, button);
		for(GuiWidget widget : widgets)
			widget.mouseClicked(x-getMinX(), y-getMinY(), button);
	}

	@Override
	protected void mouseMovedOrUp(int x, int y, int button){
		super.mouseMovedOrUp(x, y, button);
		for(GuiWidget widget : widgets)
			widget.mouseMovedOrUp(x-getMinX(), y-getMinY(), button);
	}

	@Override
	protected void mouseClickMove(int x, int y, int button, long time){
		super.mouseClickMove(x, y, button, time);
		for(GuiWidget widget : widgets)
			widget.mouseDragged(x-getMinX(), y-getMinY(), button, time);
	}

	@Override
	public void updateScreen(){
		super.updateScreen();
		if(mc.currentScreen == this)
			for(GuiWidget widget : widgets)
				widget.update();
	}

	@Override
	public void keyTyped(char c, int keycode){
		super.keyTyped(c, keycode);
		for(GuiWidget widget : widgets)
			widget.keyTyped(c, keycode);        
	}

	@Override
	public void handleMouseInput(){
		super.handleMouseInput();
		int i = Mouse.getEventDWheel();
		if(i != 0){
			Point p = GuiDraw.getMousePosition();
			int scroll = i > 0 ? 1 : -1;
			for(GuiWidget widget : widgets)
				widget.mouseScrolled(p.x, p.y, scroll);
		}
	}

	@Override
	public void actionPerformed(String ident, Object... params){
	}

	public void addWidgets(){
	}
}