package components;

import java.util.ArrayList;

public class CircuitBreaker extends Component {
    private boolean onOff;
    private int draw;


    public CircuitBreaker(String name, Component source, int draw) {
        super(name, source);
        this.draw = draw;
        Reporter.report(this, Reporter.Msg.CREATING);
        source.setChildren(this);
        this.attach();
    }

    public void engage(){
        if (this.getSource().engaged() && onOff){
            Reporter.report(this, Reporter.Msg.ENGAGING);
            isEngaged = true;
            for(Component each : this.getChildren()){
                if(!each.isEngaged) {
                    each.engage();
                }
            }
        }
    }

    public void turnOn(){
        onOff = true;
        this.engage();
    }

    public void turnOff(){
        onOff = false;
    }

    public boolean isSwitchOn(){
        return onOff;
    }


    public int getLimit(){
        return this.draw;
    }


}
