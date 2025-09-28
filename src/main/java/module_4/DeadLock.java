package module_4;

public class DeadLock {

    static class Resource {
        private final String name;

        public Resource(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    static class Character {
        private final String name;
        private Resource sword;
        private Resource shield;

        public Character(String name) {
            this.name = name;
        }

        public Character pick(Resource sword, Resource shield) {
            synchronized (sword) {
                this.sword = sword;
                System.out.println(name + " picked up " + sword.getName());
                showResources();
                try {
                    Thread.sleep(300);
                } catch (InterruptedException ignored) {
                }

                System.out.println(name + " tries to pick up " + shield.getName());

                synchronized (shield) {
                    this.shield = shield;
                    System.out.println(name + " picked up " + shield.getName());
                                    showResources();
                }
            }

            return this;
        }

        public void showResources() {
            System.out.println(this.name + " is holding " + (sword != null ? sword.getName() : "nothing else") + " and " +
            (shield != null ? shield.name : "nothing else"));
        }
    }

    public static void main(String[] args) {
        Character Lightning = new Character("Lightning");
        Character Serah = new Character("Serah Farron");

        Resource sword = new Resource("Gladius");
        Resource shield = new Resource("Kore Soteira");

        new Thread(new Runnable() {
            public void run() {
                Lightning.pick(sword, shield);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Serah.pick(shield, sword);
            }
        }).start();
    }
}