package components;

public class Outlet extends Component {
    private Component source;
    public Outlet(String name, Component source) {
        super(name);
        this.source = source;
    }
}
