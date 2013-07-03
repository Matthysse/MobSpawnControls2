package com.mcf.davidee.gui;

import com.mcf.davidee.gui.Scrollbar.Shiftable;

public abstract class Button extends Widget implements Shiftable {

	public interface ButtonHandler {
		void buttonClicked(Button button);
	}

	protected ButtonHandler handler;

	public Button(int width, int height, ButtonHandler handler) {
		super(width, height);

		this.handler = handler;
	}

	@Override
	public boolean click(int mx, int my) {
		return enabled && inBounds(mx, my);
	}

	@Override
	public void shiftY(int dy) {
		this.y += dy;
	}
	

	@Override
	public void handleClick(int mx, int my) {
		if (handler != null)
			handler.buttonClicked(this);
	}

	public void setEnabled(boolean flag) {
		this.enabled = flag;
	}
	
	public String getText() {
		return "";
	}

}