package components;

import java.util.ArrayList;

public class Outlet extends Component {

    public Outlet(String name, Component source) {
        super(name, source);
        Reporter.report(this, Reporter.Msg.CREATING);
        //source.setChildren(this);
        this.attach();
    }

    public boolean isSwitchOn(){
        return true;
    }


}
