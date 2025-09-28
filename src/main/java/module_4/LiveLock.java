package module_4;

public class LiveLock {
    public enum Direction {
        LEFT, FORWARD, RIGHT, BACK
    }

    static class Character {
        private final String name;
        private Direction direction;
        private boolean isMoving = false;

        public Character(String name) {
            this.name = name;
        }

        public Character walk(Direction initial) {
            this.direction = initial;
            this.isMoving = true;
            return this;
        }

        public void getDestination() {
            log(this.name + ": I have reached my destination!");
            isMoving = false;
        }

        public void meet(Character other) {
            while (isMoving) {
                log(this.name + " was moving " + this.direction);

                if (this.direction == other.direction) {
                    log(this.name + ": Oops! Looks like we both went " + direction + "! I'll step " + stepAside());
                } else {
                    log(this.name + ": I see you step " + other.direction + ", when I stepped " + stepAside());
                }

                try {
                    Thread.sleep(400);
                } catch (InterruptedException ignored) {
                }
            }
        }

        private Direction stepAside() {
            switch (this.direction) {
                case FORWARD -> this.direction = Direction.LEFT;
                case LEFT -> this.direction = Direction.BACK;
                case BACK -> this.direction = Direction.RIGHT;
                case RIGHT -> this.direction = Direction.FORWARD;
            }
            ;

            return this.direction;
        }

        private void log(String message) {
            synchronized (System.out) {
                System.out.println(message);
            }
        }
    }

    public static void main(String[] args) {
        Character Lightning = new Character("Lightning");
        Character Serah = new Character("Serah Farron").walk(Direction.FORWARD);

        new Thread(new Runnable() {
            public void run() {
                Lightning.walk(Direction.FORWARD).meet(Serah);
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                Serah.meet(Lightning);
            }
        }).start();
    }
}