package game;

public class Player extends Unit
{
    enum Direction
    {
        UP, DOWN, LEFT, RIGHT;
    }

    Direction direction;
    int       actionPoints;

    boolean   isOverWatch;

    public Player(Point pos)
    {
        super('^', true, 5, pos, true);
        direction = Direction.UP;
        actionPoints = 3;
        isOverWatch = false;
    }

    public boolean getOverwatch()
    {
        return isOverWatch;
    }

    public void reduceAP()
    {
        actionPoints--;
    }

    public void resetAP()
    {
        actionPoints = 2;
    }

    public void setDirection(Direction d)
    {
        direction = d;
        if (d.ordinal() == 0)
        {
            displayChar = '^';
        }
        else if (d.ordinal() == 1)
        {
            displayChar = 'v';
        }
        else if (d.ordinal() == 2)
        {
            displayChar = '<';
        }
        else if (d.ordinal() == 3)
        {
            displayChar = '>';
        }
    }

    public void setOverwatch(boolean o)
    {
        isOverWatch = o;
    }

    @Override
    public void takeDmg()
    {
        if (hitPoints > 0)
        {
            hitPoints--;
        }
    }
}
