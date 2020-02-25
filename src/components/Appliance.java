package components;

import java.util.ArrayList;

public class Appliance extends Component {
    private boolean onOff;
    private int draw;

    public Appliance(String name, Component source, int draw) {
        super(name, source);
        this.draw = draw;
        Reporter.report(this, Reporter.Msg.CREATING);
        source.setChildren(this);
        this.attach();
    }
    public void engage(){
        Reporter.report(this, Reporter.Msg.ENGAGING);
        isEngaged = true;
        for(Component each : this.getChildren()){
            if (!each.isEngaged) {
                each.engage();
            }

        }
    }


    public void turnOn(){
        onOff = true;
        Reporter.report(this, Reporter.Msg.SWITCHING_ON);
    }

    public void turnOff(){
        onOff = false;
        Reporter.report(this, Reporter.Msg.SWITCHING_OFF);
    }

    public boolean isSwitchOn(){
        return onOff;
    }

    public int getRating(){
        return this.draw;
    }
    
}
