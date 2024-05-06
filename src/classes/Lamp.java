public class Lamp {
    int[] _values;
    int[] _coord = new int[2];
    int[] _switches = new int[4];

    public Lamp(int[] values){
        _values = values;
        for (int i = 0; i <= 1; i++){
            _coord[i] = values[i];
        }
        for (int i = 0; i < 4; i++){
            _switches[i] = values[i + 2];
        }
    }

    public int[] getValues(){
        return _values;
    }

    public int[] getCoordinates(){
        return _coord;
    }

    public int[] getSwitches(){
        return _switches;
    }

    public boolean getSwitchBool(int value, int state) {
        if (_switches[value] == state) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString(){
        return "[" + _values[0] + ", " + _values[1] + ", " + _values[2] + ", " + _values[3] + ", " + _values[4] + ", " + _values[5] + "]";
    }
}
