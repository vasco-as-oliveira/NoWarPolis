package City;

public class Carro extends Veiculo {

    public Carro(int id, User owner) {
        super(id, owner);
    }

    @Override
    public String toString() {
        return "Carro{" + getId() + ", "+ getOwner().getUsername() + "}";
    }
}