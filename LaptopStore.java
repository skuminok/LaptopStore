import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

public class LaptopStore {

    // Вложенный класс Laptop
    public static class Laptop {
        private String brand;
        private int ram; // ОЗУ в гигабайтах
        private int hdd; // Объем ЖД в гигабайтах
        private String os; // Операционная система
        private String color;

        public Laptop(String brand, int ram, int hdd, String os, String color) {
            this.brand = brand;
            this.ram = ram;
            this.hdd = hdd;
            this.os = os;
            this.color = color;
        }

        // Геттеры и сеттеры для полей
        public String getBrand() {
            return brand;
        }

        public void setBrand(String brand) {
            this.brand = brand;
        }

        public int getRam() {
            return ram;
        }

        public void setRam(int ram) {
            this.ram = ram;
        }

        public int getHdd() {
            return hdd;
        }

        public void setHdd(int hdd) {
            this.hdd = hdd;
        }

        public String getOs() {
            return os;
        }

        public void setOs(String os) {
            this.os = os;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        @Override
        public String toString() {
            return "Laptop{" +
                    "brand='" + brand + '\'' +
                    ", ram=" + ram +
                    ", hdd=" + hdd +
                    ", os='" + os + '\'' +
                    ", color='" + color + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        Set<Laptop> laptops = new HashSet<>();
        laptops.add(new Laptop("Dell", 16, 512, "Windows", "Black"));
        laptops.add(new Laptop("Apple", 8, 256, "macOS", "Silver"));
        laptops.add(new Laptop("Asus", 16, 1024, "Windows", "White"));
        laptops.add(new Laptop("HP", 32, 512, "Windows", "Black"));
        laptops.add(new Laptop("Lenovo", 8, 512, "Linux", "Red"));

        Map<Integer, String> criteriaMap = new HashMap<>();
        criteriaMap.put(1, "ram");
        criteriaMap.put(2, "hdd");
        criteriaMap.put(3, "os");
        criteriaMap.put(4, "color");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int criterion = scanner.nextInt();
        scanner.nextLine();  // Consume newline

        Map<String, String> filterCriteria = new HashMap<>();

        if (criterion == 1 || criterion == 2) {
            System.out.println("Введите минимальное значение для критерия:");
            filterCriteria.put(criteriaMap.get(criterion), String.valueOf(scanner.nextInt()));
        } else if (criterion == 3 || criterion == 4) {
            System.out.println("Введите значение для критерия:");
            filterCriteria.put(criteriaMap.get(criterion), scanner.nextLine());
        } else {
            System.out.println("Некорректный ввод.");
            return;
        }

        Set<Laptop> filteredLaptops = filterLaptops(laptops, filterCriteria);

        System.out.println("Ноутбуки, подходящие под критерии фильтрации:");
        filteredLaptops.forEach(System.out::println);
    }

    public static Set<Laptop> filterLaptops(Set<Laptop> laptops, Map<String, String> filterCriteria) {
        return laptops.stream()
                .filter(laptop -> {
                    for (Map.Entry<String, String> entry : filterCriteria.entrySet()) {
                        switch (entry.getKey()) {
                            case "ram":
                                if (laptop.getRam() < Integer.parseInt(entry.getValue())) return false;
                                break;
                            case "hdd":
                                if (laptop.getHdd() < Integer.parseInt(entry.getValue())) return false;
                                break;
                            case "os":
                                if (!laptop.getOs().equalsIgnoreCase(entry.getValue())) return false;
                                break;
                            case "color":
                                if (!laptop.getColor().equalsIgnoreCase(entry.getValue())) return false;
                                break;
                        }
                    }
                    return true;
                })
                .collect(Collectors.toSet());
    }
}
