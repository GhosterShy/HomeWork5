import java.util.Scanner;

interface IVehicle {
    public void Drive();
    public void Refuel();
}

class Car implements IVehicle {
    private String model;
    private String marka;
    private String typeTopliva;

    public Car(String model, String marka, String typeTopliva) {
        this.model = model;
        this.marka = marka;
        this.typeTopliva = typeTopliva;
    }

    @Override
    public void Drive() {
        System.out.println("Car " + marka + " " + model + " быстол едет!");
    }

    @Override
    public void Refuel() {
        System.out.println("Car is refueled with " + typeTopliva);
    }
}

class Motocycle implements IVehicle {
    private String type_of_motocylce;
    private String size_motor;

    public Motocycle(String type_of_motocylce, String size_motor) {
        this.type_of_motocylce = type_of_motocylce;
        this.size_motor = size_motor;
    }

    @Override
    public void Drive() {
        System.out.println("Motocycle " + type_of_motocylce + " with " + size_motor);
    }

    @Override
    public void Refuel() {
        System.out.println("Motocycle is refueled.");
    }
}

class Truck implements IVehicle {
    private int gruz;
    private int count_os;

    public Truck(int gruz, int count_os) {
        this.gruz = gruz;
        this.count_os = count_os;
    }

    @Override
    public void Drive() {
        System.out.println("Truck с" + gruz + " тонны и  " + count_os);
    }

    @Override
    public void Refuel() {
        System.out.println("Truck is refueled.");
    }
}

class BUS implements IVehicle {
    private int count_mesto;

    public BUS(int count_mesto) {
        this.count_mesto = count_mesto;
    }

    @Override
    public void Drive() {
        System.out.println("Bus:" + count_mesto + " место ");
    }

    @Override
    public void Refuel() {
        System.out.println("Bus is refueled.");
    }
}

/// Factory
interface VehicleFactory {
    public IVehicle vehiclefactory();
}

class CarFactory implements VehicleFactory {
    @Override
    public IVehicle vehiclefactory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите модель машины: ");
        String model = scanner.nextLine();
        System.out.print("Введите марку машины: ");
        String marka = scanner.nextLine();
        System.out.print("Введите тип: ");
        String typeTopliva = scanner.nextLine();
        return new Car(model, marka, typeTopliva);
    }
}

class MotocycleFactory implements VehicleFactory {
    @Override
    public IVehicle vehiclefactory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите тип мотоцикла ");
        String type_of_motocylce = scanner.nextLine();
        System.out.print("Обем двигателя: ");
        String size_motor = scanner.nextLine();
        return new Motocycle(type_of_motocylce, size_motor);
    }
}

class TruckFactory implements VehicleFactory {
    @Override
    public IVehicle vehiclefactory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Грузоподьемность ");
        int gruz = scanner.nextInt();
        System.out.print("Количетсво осей ");
        int count_os = scanner.nextInt();
        return new Truck(gruz, count_os);
    }
}

class BUSFactory implements VehicleFactory {
    @Override
    public IVehicle vehiclefactory() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество сиденьи ");
        int count_mesto = scanner.nextInt();
        return new BUS(count_mesto);
    }
}

enum Transporttype {
    Car, Motocycle, Truck, Bus
}

class Programm {
    public IVehicle GetTransport(Transporttype type) {
        VehicleFactory creator = null;
        IVehicle transport = null;

        creator = switch (type) {
            case Car -> new CarFactory();
            case Motocycle -> new MotocycleFactory();
            case Truck -> new TruckFactory();
            case Bus -> new BUSFactory();
        };
        transport = creator.vehiclefactory();
        return transport;
    }
}

public class Main {
    public static void main(String[] args) {
        Programm programm = new Programm();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите тип транспорта (Car, Motocycle, Truck, Bus): ");
        String userInput = scanner.nextLine();
        Transporttype type = Transporttype.valueOf(userInput);

        IVehicle vehicle = programm.GetTransport(type);
        vehicle.Drive();
        vehicle.Refuel();
    }
}
