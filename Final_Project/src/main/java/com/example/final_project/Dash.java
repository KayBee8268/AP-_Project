package com.example.final_project;

import javafx.scene.image.ImageView;

class Dash{
    private int dash;
    private int pushorc;
    private ImageView orc;

    public Dash(int dash,int pushorc){
        this.dash=dash;
        this.pushorc=pushorc;
        this.orc=null;
    }

    public int getDash() {
        return dash;
    }
    public int getPushorc() {
        return pushorc;
    }
    public ImageView getOrc() {
        return orc;
    }

    public void setDash(int dash) {
        this.dash = dash;
    }
    public void setOrc(ImageView orc) {
        this.orc = orc;
    }
    public void setPushorc(int pushorc) {
        this.pushorc = pushorc;
    }
}