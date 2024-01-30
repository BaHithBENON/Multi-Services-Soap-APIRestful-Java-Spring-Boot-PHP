package com.app.ui;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color borderColor;
    private int cornerRadius;

    public RoundedBorder(Color borderColor, int cornerRadius) {
        this.borderColor = borderColor;
        this.cornerRadius = cornerRadius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(borderColor);
        g2d.draw(new RoundRectangle2D.Double(x, y, width - 1, height - 1, cornerRadius, cornerRadius));
        g2d.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(cornerRadius + 1, cornerRadius + 1, cornerRadius + 2, cornerRadius); // Adjust the insets as needed
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
    	insets.left = insets.top = insets.right = insets.bottom = cornerRadius + 1;
        return insets;
    }
}
