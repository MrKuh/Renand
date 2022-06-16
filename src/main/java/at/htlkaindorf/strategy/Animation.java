package at.htlkaindorf.strategy;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public interface Animation {
    public List<BufferedImage> images = new ArrayList<BufferedImage>();
    public void use();
}
