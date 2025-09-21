package module_3;

interface DataSource {
    void writeData(String data);
    String readData();
}

class FileStorage implements DataSource {
    private StringBuilder storage = new StringBuilder();

    @Override
    public void writeData(String data) {
        storage.append(data);
    }

    @Override
    public String readData() {
        return storage.toString();
    }
}

abstract class Decorator implements DataSource {
    protected DataSource inner;

    public Decorator(DataSource source) {
        this.inner = source;
    }

    @Override
    public void writeData(String data) {
        inner.writeData(data);
    }

    @Override
    public String readData() {
        return inner.readData();
    }
}

class Compression extends Decorator {
    public Compression(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        String compressed = "[COMPRESSED]" + data;
        super.writeData(compressed);
    }

    @Override
    public String readData() {
        String data = super.readData();
        return data.replace("[COMPRESSED]", "");
    }
}

class Encryption extends Decorator {
    public Encryption(DataSource source) {
        super(source);
    }

    @Override
    public void writeData(String data) {
        String encrypted = new StringBuilder(data).reverse().toString();
        super.writeData(encrypted);
    }

    @Override
    public String readData() {
        String data = super.readData();
        return new StringBuilder(data).reverse().toString();
    }
}

public class DecoratorPattern {
    public static void main(String[] args) {
        String data = "Hello, Aston!";

        DataSource file = new FileStorage();
        DataSource decorated = new Encryption(new Compression(file));

        decorated.writeData(data);

        System.out.println("Read raw from file storage: " + file.readData());
        System.out.println("Read after decorators: " + decorated.readData());
    }
}