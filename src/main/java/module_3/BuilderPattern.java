package module_3;

class Computer {
    private final String cpu;
    private final String gpu;
    private final int ram;
    private final int rom;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.gpu = builder.gpu;
        this.ram = builder.ram;
        this.rom = builder.rom;
    }

    public String getCPU() {
        return cpu;
    }

    public String getGPU() {
        return gpu;
    }

    public int getRAM() {
        return ram;
    }

    public int getROM() {
        return rom;
    }

    public static class Builder {
        private String cpu;
        private String gpu;
        private int ram;
        private int rom;

        public Builder setCpu(String cpu) {
            this.cpu = cpu;
            return this;
        }

        public Builder setGpu(String gpu) {
            this.gpu = gpu;
            return this;
        }

        public Builder setRam(int ram) {
            this.ram = ram;
            return this;
        }

        public Builder setStorage(int rom) {
            this.rom = rom;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}

public class BuilderPattern {
    public static void main(String[] args) {
        Computer laptop = new Computer.Builder()
                .setCpu("i5-12450H")
                .setGpu("Intel UHD Graphics")
                .setRam(8)
                .setStorage(512)
                .build();

        System.out.println("Huawei MateBook D14: CPU=" + laptop.getCPU() +
                ", GPU=" + laptop.getGPU() +
                ", RAM=" + laptop.getRAM() + "GB" +
                ", ROM=" + laptop.getROM() + "GB");
    }
}