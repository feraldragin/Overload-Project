package components;

public class Appliance extends Component {
    private boolean onOff = false;
    private int rating;

    public Appliance(String name, Component source, int rating) {
        super(name, source);
        this.rating = rating;
        Reporter.report(this, Reporter.Msg.CREATING);
        source.setChildren(this);
        this.attach();
    }
    public void engage(){
        Reporter.report(this, Reporter.Msg.ENGAGING);
        isEngaged = true;
        if (onOff == true) {
            Component source = getSource();
            while (source != null) {
                source.changeDraw(this.getRating());
                if (source.blown == true) {
                    break;
                } else {
                    source = source.getSource();
                }
            }
        }
    }


    public void turnOn(){
        onOff = true;
        Reporter.report(this, Reporter.Msg.SWITCHING_ON);
        Component source = getSource();
        while (source != null) {
            source.changeDraw(this.getRating());
            if (source.blown == true){
                break;
            }
            else {
                source = source.getSource();
            }
        }
    }

    public void turnOff(){
        onOff = false;
        Reporter.report(this, Reporter.Msg.SWITCHING_OFF);
    }

    public boolean isSwitchOn(){
        return onOff;
    }

    public int getRating(){
        return this.rating;
    }

    @Override
    public void disengage(){
        if (this.isEngaged) {
            isEngaged = false;
            Reporter.report(this, Reporter.Msg.DISENGAGING);

            Component source = getSource();
            while (source != null) {
                source.changeDraw(-this.getRating());
                source = source.getSource();
            }
        }
    }
    
}
