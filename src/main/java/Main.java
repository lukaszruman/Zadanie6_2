/*
Program sprawdza poprawność wpisywanego imienia. W przypadku wystąpienia spacji w imieniu, funkcja wyrzuca zdefiniowany wyjątek WrongStudentName, który jest wyłapywany w pętli głównej Commit6_0.
Poniższe zadania będą się sprowadzały do modyfikacji bazowego kodu. Proces modyfikacji ogólnie może wyglądać następująco:
• Ustalenie jaki błąd chcę się sprawdzić i wyłapać.
• Decyzja, czy użyje się własnej klasy wyjątku, czy wykorzysta już istniejące (np. Exception, IOException).
• Napisanie kodu sprawdzającego daną funkcjonalność. W przypadku warunku błędu wyrzucany będzie wyjątek: throw new WrongStudentName().
• W definicji funkcji, która zawiera kod wyrzucania wyjątku dopisuje się daną nazwę wyjątku, np. public static String ReadName() throws WrongStudentName.
• We wszystkich funkcjach, które wywołują powyższą funkcję także należy dopisać, że one wyrzucają ten wyjątek – inaczej program się nie skompiluje.
• W pętli głównej, w main’ie, w zdefiniowanym już try-catch dopisuje się Nazwę wyjątku i go obsługuje, np. wypisuje w konsoli co się stało.
*/
//Commit6_1. Na podstawie analogii do wyjątku WrongStudentName utwórz i obsłuż wyjątki WrongAge oraz WrongDateOfBirth. 
//Niepoprawny wiek – gdy jest mniejszy od 0 lub większy niż 100. Niepoprawna data urodzenia – gdy nie jest zapisana w formacie DD-MM-YYYY, np. 28-02-2023.
import java.io.IOException;
import java.util.Scanner;
import java.io.IOException;
class WrongStudentName extends Exception { }
class WrongAge extends Exception { }
class WrongDateOfBirth extends Exception { }
class WrongDayBirth extends Exception { }
class WrongMonthBirth extends Exception { }
class WrongYearBirth extends Exception { }
class WrongMenuChoice extends Exception { }
class Main {
    public static Scanner scan = new Scanner(System.in);
    public static void main(String[] args) {
        while(true) {
            try {
                int ex = menu();
                switch(ex) {
                    case 1: exercise1(); break;
                    case 2: exercise2(); break;
                    case 3: exercise3(); break;
                    default: return;
                }
            } catch(IOException e) {
            } catch(WrongStudentName e) {
                System.out.println("Błędne imie studenta!");
            } catch(WrongAge e) {
                System.out.println("Błędny wiek studenta!");
            } catch(WrongDateOfBirth e) {
                System.out.println("Błędna data urodzenia studenta!");
            } catch(WrongDayBirth e) {
                System.out.println("Błędny dzień urodzenia studenta!");
            } catch(WrongMonthBirth e) {
                System.out.println("Błędny miesiąc urodzenia studenta!");
            } catch(WrongYearBirth e) {
                System.out.println("Błędny rokk urodzenia studenta!");
            } catch(WrongMenuChoice e) {
                System.out.println("Niepoprawny wybór! Spróbuj ponownie.");
            }
        }
    }
    public static int menu() throws WrongMenuChoice {
        System.out.println("Wciśnij:");
        System.out.println("1 - aby dodać studenta");
        System.out.println("2 - aby wypisać wszystkich stuudentów");
        System.out.println("3 - aby wyszukać studenta po imieniu");
        System.out.println("0 - aby wyjść z programu");
        if(scan.hasNextInt()) {
            int choice = scan.nextInt();
            if(choice < 0 || choice > 3)
                throw new WrongMenuChoice();
            return choice;
        } else {
            scan.next(); // Consume the non-integer input
            throw new WrongMenuChoice();
        }
    }
    public static String ReadName() throws WrongStudentName {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        String name = scan.nextLine();
        if(name.contains(" "))
            throw new WrongStudentName();
        return name;
    }
    public static int ReadAge() throws WrongAge {
        System.out.println("Podaj wiek: ");
        var age = scan.nextInt();
        if(age < 0 || age > 100)
            throw new WrongAge();
        scan.nextLine();
        return age;
    }
    public static String ReadDateOfBirth() throws WrongDateOfBirth, WrongDayBirth, WrongMonthBirth, WrongYearBirth {
        System.out.println("Podaj datę urodzenia DD-MM-YYYY");
        var date = scan.nextLine();
        if(!date.matches("\\d{2}-\\d{2}-\\d{4}"))
            throw new WrongDateOfBirth();
        String[] parts = date.split("-");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        if(day < 1 || day > 31)
            throw new WrongDayBirth();
        if(month < 1 || month > 12)
            throw new WrongMonthBirth();
        if(year < 1900 || year > 2024)
            throw new WrongYearBirth();
        return date;
    }
    public static void exercise1() throws IOException, WrongStudentName, WrongAge, WrongDateOfBirth, WrongDayBirth, WrongMonthBirth, WrongYearBirth {
        var name = ReadName();
        var age = ReadAge();
        var date = ReadDateOfBirth();
        (new Service()).addStudent(new Student(name, age, date));
    }
    public static void exercise2() throws IOException {
        var students = (new Service()).getStudents();
        for(Student current : students) {
            System.out.println(current.ToString());
        }
    }
    public static void exercise3() throws IOException {
        scan.nextLine();
        System.out.println("Podaj imie: ");
        var name = scan.nextLine();
        var wanted = (new Service()).findStudentByName(name);
        if(wanted == null)
            System.out.println("Nie znalezionoo...");
        else {
            System.out.println("Znaleziono: ");
            System.out.println(wanted.ToString());
        }
    }
}