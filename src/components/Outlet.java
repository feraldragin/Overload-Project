package components;

import java.util.ArrayList;

public class Outlet extends Component {

    public Outlet(String name, Component source) {
        super(name, source);
        Reporter.report(this, Reporter.Msg.CREATING);
        //source.setChildren(this);
        this.attach();
        switchable = false;
    }

    public boolean isSwitchOn(){
        return true;
    }

    public void turnOff(){}
    public void turnOn(){}


}
