package components;

public class Appliance extends Component {
    private boolean onOff;
    private Component source;
    private int draw;
    public Appliance(String name, Component source, int draw) {
        super(name);
        this.source = source;
        this.draw = draw;
    }

    public void turnOn(){
        onOff = true;
    }

    public void turnOff(){
        onOff = false;
    }

    public boolean isSwitchOn(){
        return onOff;
    }

    public int getRating(){
        return this.draw;
    }
    
}
