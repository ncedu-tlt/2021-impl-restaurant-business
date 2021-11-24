public class Name {
    String name;

    @Override
    public String toString() {
        return "Name{" +
                "name='" + name + '\''+
        '}';
    }

    Name(String name){
        this.name = name;
    }
}