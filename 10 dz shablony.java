//class TV
class TV {
    public void turnOn() {
        System.out.println("TV is turned on.");
    }

    public void turnOff() {
        System.out.println("TV is turned off.");
    }

    public void setChannel(int channel) {
        System.out.println("TV channel set to " + channel);
    }
}

class AudioSystem {
    public void turnOn() {
        System.out.println("Audio system is turned on.");
    }

    public void turnOff() {
        System.out.println("Audio system is turned off.");
    }

    public void setVolume(int level) {
        System.out.println("Audio volume set to " + level);
    }
}

class DVDPlayer {
    public void play() {
        System.out.println("DVD is playing.");
    }

    public void pause() {
        System.out.println("DVD is paused.");
    }

    public void stop() {
        System.out.println("DVD is stopped.");
    }

    public void turnOn() {
        System.out.println("DVD player is turned on.");
    }

    public void turnOff() {
        System.out.println("DVD player is turned off.");
    }
}

class GameConsole {
    public void turnOn() {
        System.out.println("Game console is turned on.");
    }

    public void playGame(String game) {
        System.out.println("Game " + game + " is started.");
    }

    public void turnOff() {
    }
}
// class HomeTheaterFacade
public class HomeTheaterFacade {
    private TV tv;
    private AudioSystem audioSystem;
    private DVDPlayer dvdPlayer;
    private GameConsole gameConsole;

    public HomeTheaterFacade() {
        this.tv = new TV();
        this.audioSystem = new AudioSystem();
        this.dvdPlayer = new DVDPlayer();
        this.gameConsole = new GameConsole();
    }

    public void watchMovie(String movie) {
        System.out.println("Get ready to watch a movie...");
        tv.turnOn();
        audioSystem.turnOn();
        dvdPlayer.turnOn();
        dvdPlayer.play();
        System.out.println("Enjoy your movie: " + movie);
    }

    public void stopMovie() {
        System.out.println("Stopping movie...");
        dvdPlayer.stop();
        dvdPlayer.turnOff();
        audioSystem.turnOff();
        tv.turnOff();
    }

    public void playGame(String game) {
        System.out.println("Get ready to play a game...");
        tv.turnOn();
        gameConsole.turnOn();
        audioSystem.turnOn();
        gameConsole.playGame(game);
        System.out.println("Enjoy your game: " + game);
    }

    public void turnOffSystem() {
        System.out.println("Shutting down the home theater system...");
        tv.turnOff();
        audioSystem.turnOff();
        dvdPlayer.turnOff();
        gameConsole.turnOff();
    }

    public void listenToMusic() {
        System.out.println("Setting up for listening to music...");
        tv.turnOn();
        audioSystem.turnOn();
        audioSystem.setVolume(20);
        System.out.println("Music mode is on.");
    }
}
// class Main
public class Main {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();

        homeTheater.watchMovie("Inception");
        homeTheater.stopMovie();

        homeTheater.playGame("The Witcher 3");

        homeTheater.listenToMusic();

        homeTheater.turnOffSystem();
    }
}



//abstract class FileSystemComponent
abstract class FileSystemComponent {
    public abstract void display();
    public abstract int getSize();
}
// class File
class File extends FileSystemComponent {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    @Override
    public void display() {
        System.out.println("File: " + name + " (Size: " + size + " KB)");
    }

    @Override
    public int getSize() {
        return size;
    }
}

// class Directory
import java.util.ArrayList;
import java.util.List;

class Directory extends FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components;

    public Directory(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }

    public void addComponent(FileSystemComponent component) {
        if (!components.contains(component)) {
            components.add(component);
            System.out.println("Added component to directory: " + name);
        } else {
            System.out.println("Component already exists in directory: " + name);
        }
    }

    public void removeComponent(FileSystemComponent component) {
        if (components.contains(component)) {
            components.remove(component);
            System.out.println("Removed component from directory: " + name);
        } else {
            System.out.println("Component not found in directory: " + name);
        }
    }

    @Override
    public void display() {
        System.out.println("Directory: " + name);
        for (FileSystemComponent component : components) {
            component.display();
        }
    }

    @Override
    public int getSize() {
        int totalSize = 0;
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
}
//class MAIN
public class MAIN {
    public static void main(String[] args) {
        Directory root = new Directory("Root");
        File file1 = new File("file1.txt", 100);
        File file2 = new File("file2.txt", 200);

        Directory subDir = new Directory("SubDir");
        File file3 = new File("file3.txt", 300);

        root.addComponent(file1);
        root.addComponent(file2);
        root.addComponent(subDir);

        subDir.addComponent(file3);

        root.display();
        System.out.println("Total size of root directory: " + root.getSize() + " KB");
    }
}
