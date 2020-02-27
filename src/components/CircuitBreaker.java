package components;

import java.util.ArrayList;

public class CircuitBreaker extends Component {
    private boolean onOff;
    private int limit;


    public CircuitBreaker(String name, Component source, int limit) {
        super(name, source);
        this.limit = limit;
        Reporter.report(this, Reporter.Msg.CREATING);
        source.setChildren(this);
        this.attach();
    }

    public void engage(){
        if (this.getSource().engaged()){
            Reporter.report(this, Reporter.Msg.ENGAGING);
            isEngaged = true;

        }
    }

    public void turnOn(){
        onOff = true;
        Reporter.report(this, Reporter.Msg.SWITCHING_ON);
        for(Component each : this.getChildren()){
            if(!each.isEngaged) {
                each.engage();
            }
        }
    }

    public void turnOff(){
        onOff = false;
    }

    public boolean isSwitchOn(){
        return onOff;
    }


    public int getLimit(){
        return this.limit;
    }

    @Override
    public void changeDraw(int delta){
        draw = delta + this.getDraw();
        if (draw > this.getLimit()){
            Reporter.report(this, Reporter.Msg.BLOWN, draw);
            blown = true;
        }
        else{
            Reporter.report(this, Reporter.Msg.DRAW_CHANGE, delta);
        }
    }


}
