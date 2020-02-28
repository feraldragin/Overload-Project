package components;

import java.util.ArrayList;

public abstract class Component {
    private String name;
    private Component source;
    protected boolean isEngaged;
    protected int draw;
    protected boolean blown = false;
    protected boolean onOff = false;
    private ArrayList<Component> children = new ArrayList<Component>();


    public Component getSource(){
        return source;
    }

    public ArrayList<Component> getChildren() {
        return children;
    }

    public void setChildren(Component c) {
        children.add(c);
    }


    public Component(String name, Component source) {
        this.name = name;
        this.source = source;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return Reporter.identify( this );
    }

    public  void engage(){
        if (source.isEngaged){
            isEngaged = true;
            Reporter.report(this, Reporter.Msg.ENGAGING);
            for (Component each : this.getChildren()){
                if (!each.isEngaged) {
                    each.engage();
                }
            }
        }
    }


    public void disengage(){
        if (this.isEngaged){
            isEngaged = false;
            Reporter.report(this, Reporter.Msg.DISENGAGING);
            for (Component each : children){
                if (each.isEngaged) {
                    each.disengage();
                }
            }
        }
    }


    public int getDraw(){
        return draw;
    }

    protected boolean engaged(){
        return isEngaged;
    }

    protected void changeDraw(int delta){
        draw += delta;
        Reporter.report(this, Reporter.Msg.DRAW_CHANGE, delta);

    }

    public void attach(){
        if (source == null){
            for (Component each :children){
                each.attach();
            }
        }
        else {
            source.getChildren().add(this);
            Reporter.report(source, this, Reporter.Msg.ATTACHING);
        }
    }

    public void display(){
        if(this instanceof PowerSource) {
            System.out.println();
        }
        String result = "";
        Component source = this.source;
        while(source != null) {
            result += "    ";
            source = source.getSource();
        }
        result += "+ "+ Reporter.identify(this);
        System.out.print(result);
        System.out.println();
        for(Component each: this.getChildren()) {
            each.display();
        }
        if(this instanceof PowerSource) {
            System.out.println();
        }
    }


    public abstract boolean isSwitchOn();


}