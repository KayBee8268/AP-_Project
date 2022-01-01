package com.example.final_project;

import java.io.Serializable;

public class PositionClass implements Serializable {
    private double xCoordinate;
    private double yCoordinate;
    private double xDimension;
    private double yDimension;


    public PositionClass(double xcor,double ycor,double xdim,double ydim){
        this.xCoordinate=xcor;
        this.xDimension=xdim;

        this.yCoordinate=ycor;
        this.yDimension=ydim;
    }

    public double getXCoordinate() {
        return xCoordinate;
    }

    public double getXDimension() {
        return xDimension;
    }

    public double getYCoordinate() {
        return yCoordinate;
    }

    public double getYDimension() {
        return yDimension;
    }

    public void setXCoordinate(double xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setXDimension(double xDimension) {
        this.xDimension = xDimension;
    }

    public void setYCoordinate(double yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public void setYDimension(double yDimension) {
        this.yDimension = yDimension;
    }
}
