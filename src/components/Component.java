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

    public void setChildren(ArrayList<Component> children) {
        this.children = children;
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
                System.out.println(Reporter.Msg.ENGAGING);
                each.engage();
            }
        }
    }


    public void disengage(){

    }

    public int getDraw(){
        return 0;
    }

    protected boolean engaged(){
        return isEngaged;
    }

    protected void changeDraw(int delta){

    }
    public void attach(){
        source.getChildren().add(this);
        Reporter.report(this, Reporter.Msg.ATTACHING, 2);
    }

}