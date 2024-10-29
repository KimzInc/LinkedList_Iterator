import java.util.LinkedList;
import java.util.Scanner;

record Place(String name, int distance){
    @Override
    public String toString() {
        return String.format("%s (%d)", name, distance);
    }
}
public class Main {
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        LinkedList<Place> placesToVisit = new LinkedList<>();
        Place nairobi = new Place("Nairobi", 1240);

        addPlace(placesToVisit, nairobi);
        addPlace(placesToVisit, new Place("Kampala", 1500));
        addPlace(placesToVisit, new Place("Mombasa", 1200));
        addPlace(placesToVisit, new Place("Kigali", 2000));
        addPlace(placesToVisit, new Place("London", 3000));
        addPlace(placesToVisit, new Place("Paris", 4000));
        addPlace(placesToVisit, new Place("Berlin", 5000));
        addPlace(placesToVisit, new Place("Melbourne", 6000));

        placesToVisit.addFirst(new Place("Kisumu", 0));
        System.out.println(placesToVisit);

        // Iterator
        //System.out.println(placesToVisit.listIterator(1).next());
        var iterator = placesToVisit.listIterator();
        boolean quit = false;
        boolean forward = true;
        printMenu();

        while (!quit) {
            if (!iterator.hasPrevious()) {
                System.out.println("Originating: " + iterator.next());
                forward = true;
            }
            if (!iterator.hasNext()) {
                System.out.println("Final : " + iterator.previous());
                forward = false;
            }
            System.out.println("Enter your choice: ");
            //convert it to a single character and uppercase
            String menuChoice = scanner.nextLine().toUpperCase().substring(0, 1);
            switch (menuChoice) {
                case "F":
                    System.out.println("User wants to go forward");
                    if (!forward) { // reversing direction
                        forward = true;
                        if (iterator.hasNext()) {
                            iterator.next(); //adjust position forward
                        }
                    }
                    if (iterator.hasNext()) {
                        System.out.println(iterator.next());
                    }
                    break;
                    case "B":
                        System.out.println("User wants to go backward");
                        if (forward) {          //reversing direction
                            forward = false;
                            if (iterator.hasPrevious()) {
                                iterator.previous(); //adjust position backwards
                            }
                        }
                        if (iterator.hasPrevious()) {
                            System.out.println(iterator.previous());
                        }
                        break;
                        case "M":
                            printMenu();
                            break;
                            case "L":
                                System.out.println(placesToVisit);
                                break;
                default:
                    quit = true;
                    break;
            }
        }

    }
    private static void addPlace(LinkedList<Place> places, Place place) {
        if (places.contains(place)) {
            System.out.println("Found duplicate place: " + place);
            return;
        }
        for (Place p : places) {
            if (p.name().equalsIgnoreCase(place.name())) {
                System.out.println("Found duplicate place: " + place);
                return;
            }
        }
        int matchedIndex = 0;
        for(var listPlace: places) {
            if(place.distance() < listPlace.distance()){
                places.add(matchedIndex, listPlace);
                return;
            }
            matchedIndex++;
        }
        places.add(place);
    }

    private static void printMenu(){
        System.out.println("""
                Available actions (select word or letter):
                (F)orward
                (B)ackwards
                (L)ist Places
                (M)enu
                (Q)uit
                """);
    }
}