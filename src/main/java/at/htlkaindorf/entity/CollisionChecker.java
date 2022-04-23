package at.htlkaindorf.entity;

import at.htlkaindorf.display.GamePanel;

public class CollisionChecker {

    private GamePanel gp;

    public CollisionChecker(GamePanel gp) {
        this.gp = gp;
    }

    public void checkCollision(Entity entity){
        int entityLeftX = entity.getX() + entity.getHitBox().x;
        int entityRightX = entity.getX() + entity.getHitBox().x + entity.getHitBox().width;
        int entityTopY = entity.getY() + entity.getHitBox().y;
        int entityBottomY = entity.getY() + entity.getHitBox().y + entity.getHitBox().height;

        
    }
}
