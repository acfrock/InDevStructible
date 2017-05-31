package game;

public class Tile
{
    protected char    displayChar;
    protected boolean isObstruction;
    protected int     hitPoints;
    protected Point   position;
    protected boolean isDamagable;

    public char getDisplayChar()
    {
        return displayChar;
    }

    public boolean isObstruction()
    {
        return isObstruction;
    }

    public int getHitPoints()
    {
        return hitPoints;
    }

    public boolean isDamagable()
    {
        return isDamagable;
    }

    public Tile(char disp, boolean obst, int hit, Point pos, boolean dmg)
    {
        displayChar = disp;
        isObstruction = obst;
        hitPoints = hit;
        position = pos;
        isDamagable = dmg;
    }

    public Tile(int x, int y)
    {
        displayChar = ' ';
        isObstruction = false;
        hitPoints = 0;
        position = new Point(x, y);
        isDamagable = false;
    }

    public Point getPosition()
    {
        return position;
    }

    public void takeDmg()
    {
        if (hitPoints > 0)
        {
            hitPoints--;
        }
        if (isDamagable && (hitPoints == 1))
        {
            displayChar = Character.toLowerCase(displayChar);
        }

    }

}
