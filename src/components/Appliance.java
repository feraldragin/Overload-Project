package components;

public class Appliance extends Component {
    private boolean onOff;
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
        for(Component each : this.getChildren()){
            if (!each.isEngaged) {
                each.engage();
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
    
}
