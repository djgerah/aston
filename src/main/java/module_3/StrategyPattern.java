package module_3;

interface Attack {
    void action();
}

class Swordsman implements Attack {
    public void action() {
        System.out.println("Attacks with sword ...");
    }
}

class Archer implements Attack {
    public void action() {
        System.out.println("Shoots an arrow ...");
    }
}

class Character {
    private final Attack type;

    public Character(Attack type) {
        this.type = type;
    }

    public void attack() {
        type.action();
    }
}

public class StrategyPattern {
    public static void main(String[] args) {
        Character tarlyjac = new Character(new Swordsman());
        Character djgerah = new Character(new Archer());

        tarlyjac.attack();
        djgerah.attack();
    }
}