package components;

public class PowerSource extends Component {
    public PowerSource(String name) {
        super(name, null);
        isEngaged = true;
        Reporter.report(this, Reporter.Msg.CREATING);
    }

    public String display(){
        return "null";
    }

    public void engage(){
        Reporter.report(this, Reporter.Msg.ENGAGING);
        for (Component each : this.getChildren()){
            each.engage();
        }
    }


}
