package com.example.final_project;

public class PositionClass {
    private double x;
    private double y;
    private double xdimension;
    private double ydimension;

    public PositionClass(double xcor,double ycor,double xdim,double ydim){
        this.x=xcor;
        this.xdimension=xdim;

        this.y=ycor;
        this.ydimension=ydim;
    }

    public double getx(){
        return this.x;
    }

    public double gety(){
        return this.y;
    }

    public double getDimensionx(){
        return this.xdimension;
    }

    public double getDimensiony(){
        return this.ydimension;
    }

    public void updatex(double newx){
        this.x=newx;
    }
    public void updatey(double newy){
        this.y=newy;
    }

}
