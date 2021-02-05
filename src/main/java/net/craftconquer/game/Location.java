package net.craftconquer.game;

import lombok.Getter;
import lombok.Setter;

public class Location
{
    @Getter @Setter
    private double x;

    @Getter @Setter
    private double y;

    @Getter @Setter
    private double z;

    public Location(double x, double y, double z)
    {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
