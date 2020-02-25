package components;

import java.util.ArrayList;

public abstract class Component {
    private String name;
    private Component source;
    protected boolean isEngaged;
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
        return this.name;
    }

    public  void engage(){
        if (source.isEngaged){
            isEngaged = true;
            Reporter.report(this, Reporter.Msg.ENGAGING);
            for (Component each : children){
                if (!each.isEngaged) {
                    each.engage();
                }
            }
        }
    }


    public void disengage(){
        source.getChildren().clear();
    }

    public int getDraw(){
        int draw = 0;
        for (Component each: children){
            draw+=each.getDraw();
        }
        return draw;
    }

    protected boolean engaged(){
        return isEngaged;
    }

    protected void changeDraw(int delta){

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

}