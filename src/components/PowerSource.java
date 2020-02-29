package components;

public class PowerSource extends Component {
    public PowerSource(String name) {
        super(name, null);
        isEngaged = true;
        Reporter.report(this, Reporter.Msg.CREATING);
        this.attach();
    }

    public void engage(){
        Reporter.report(this, Reporter.Msg.POWERING_UP);
        Reporter.report(this, Reporter.Msg.ENGAGING);
        for (Component each : this.getChildren()){
            if (!each.isEngaged) {
                each.engage();
            }
        }
    }

    public boolean isSwitchOn(){
        return true;
    }


}
